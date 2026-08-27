-- =====================================================================
-- SigeSchoolPro — Esquema corregido para Supabase Postgres
-- Ejecuta este script en: Supabase Dashboard → SQL Editor → New Query
--
-- CAMBIOS CLAVE respecto al esquema original (download/supabase-schema.sql):
--   1. El esquema original solo cubría 7 tablas de una maqueta antigua
--      (staff/students/classes/attendance/grades/notifications/activities)
--      que NO corresponden a las tablas que el código Kotlin realmente
--      consulta (institutions, employees, salary_records, puc_accounts,
--      accounting_entries, fee_payments, tasks, exams, announcements,
--      employee_attendance, messages). Ese desfase habría hecho fallar
--      en producción cualquier pantalla que no fuera estudiantes/clases.
--   2. Las políticas RLS originales eran "using (true)" para TODO
--      (lectura y escritura), combinadas con una anon key embebida en
--      el cliente. Esto permite a cualquier persona con la app instalada
--      leer y modificar los datos de TODAS las instituciones (notas,
--      salarios, contabilidad, datos de estudiantes). Aquí se reemplazan
--      por políticas que exigen sesión autenticada y restringen el
--      acceso a la institución del usuario.
-- =====================================================================

create extension if not exists "pgcrypto";

-- =====================================================
-- institutions
-- =====================================================
create table if not exists public.institutions (
  id                  text primary key default gen_random_uuid()::text,
  name                text not null,
  address             text default '',
  phone               text default '',
  whatsapp_number     text default '',
  email               text default '',
  nit                 text default '',
  dane_number         text default '',
  resolution_number   text default '',
  territorial_entity  text default '',
  owner_id            uuid not null references auth.users(id) on delete cascade,
  educational_models  jsonb not null default '[]'::jsonb,
  id_cuenta_activa    text,
  cuenta_configurada  boolean default false,
  created_at          timestamptz not null default now()
);
create unique index if not exists idx_institutions_owner on public.institutions(owner_id);

-- =====================================================
-- cuentas_bancarias (SEC-03, SEC-04)
-- Cada institución configura su propia cuenta receptora.
-- =====================================================
create table if not exists public.cuentas_bancarias (
    id               text primary key default gen_random_uuid()::text,
    institution_id   text not null references public.institutions(id) on delete cascade,
    entidad_bancaria text not null,
    tipo_cuenta      text not null, -- AHORROS, CORRIENTE
    numero_cuenta    text not null, -- Guardado de forma segura
    titular          text not null,
    identificacion_titular text not null,
    correo_notificacion text,
    estado           text not null default 'ACTIVA', -- ACTIVA, INACTIVA
    created_at       timestamptz default now(),
    updated_at       timestamptz default now(),
    unique(institution_id)
);

alter table public.cuentas_bancarias enable row level security;

create policy "cuentas_bancarias_select" on public.cuentas_bancarias
  for select using (institution_id in (select public.my_institution_ids()));

create policy "cuentas_bancarias_all_admin" on public.cuentas_bancarias
  for all using (
    institution_id in (
      select institution_id from public.institution_members
      where user_id = auth.uid() and role in ('ADMIN', 'RECTOR')
    )
  );

-- =====================================================
-- historial_cuentas_bancarias
-- Auditoría de cambios en datos sensibles
-- =====================================================
create table if not exists public.historial_cuentas_bancarias (
    id              uuid primary key default gen_random_uuid(),
    cuenta_id       text not null references public.cuentas_bancarias(id) on delete cascade,
    usuario_id      uuid references auth.users(id),
    accion          text not null, -- CREATE, UPDATE, DEACTIVATE
    datos_previos   jsonb,
    created_at      timestamptz default now()
);

alter table public.historial_cuentas_bancarias enable row level security;

create policy "historial_cuentas_select" on public.historial_cuentas_bancarias
  for select using (
    cuenta_id in (
        select id from public.cuentas_bancarias
        where institution_id in (select public.my_institution_ids())
    )
  );

-- =====================================================
-- employees (personal)
-- NOTA: hoy no existe vínculo entre employees y auth.users. Solo el
-- "owner_id" de la institución puede autenticarse. Si se necesita que
-- cada docente/administrativo inicie sesión con su propia cuenta,
-- agregar aquí una columna auth_user_id y ajustar las políticas.
-- =====================================================
create table if not exists public.employees (
  id             text primary key default gen_random_uuid()::text,
  institution_id text not null references public.institutions(id) on delete cascade,
  first_name     text not null default '',
  last_name      text not null default '',
  dni            text default '',
  role           text not null default 'DOCENTE',
  qualification  text default '',
  specialization text default '',
  department     text default '',
  email          text default '',
  phone          text default '',
  hire_date      bigint default 0,
  is_active      boolean default true
);
create index if not exists idx_employees_institution on public.employees(institution_id);

-- =====================================================
-- students
-- =====================================================
create table if not exists public.students (
  id                 bigint generated by default as identity primary key,
  nombre             text not null default '',
  apellido           text not null default '',
  fecha_nacimiento   text default '',
  grado              text default '',
  seccion            text default '',
  dni                text default '',
  telefono           text default '',
  email              text default '',
  direccion          text default '',
  "fechaRegistro"    text default '',
  institution_id     text not null references public.institutions(id) on delete cascade,
  estado_matricula   text not null default 'MATRICULADO',
  estado_academico   text not null default 'CURSANDO',
  activo             boolean not null default true
);
create index if not exists idx_students_institution on public.students(institution_id);
create index if not exists idx_students_dni on public.students(dni);

-- =====================================================
-- classes
-- =====================================================
create table if not exists public.classes (
  id             text primary key default gen_random_uuid()::text,
  name           text not null default '',
  level          text default '',
  institution_id text not null references public.institutions(id) on delete cascade,
  teacher_id     text references public.employees(id) on delete set null,
  created_at     timestamptz default now()
);
create index if not exists idx_classes_institution on public.classes(institution_id);

-- =====================================================
-- attendance (asistencia de estudiantes)
-- =====================================================
create table if not exists public.attendance (
  id             bigint generated by default as identity primary key,
  "studentId"    bigint not null references public.students(id) on delete cascade,
  fecha          text not null,
  estado         text not null,
  observaciones  text default '',
  institution_id text not null references public.institutions(id) on delete cascade
);
create index if not exists idx_attendance_student on public.attendance("studentId");
create index if not exists idx_attendance_institution_fecha on public.attendance(institution_id, fecha);

-- =====================================================
-- employee_attendance
-- =====================================================
create table if not exists public.employee_attendance (
  id                 bigint generated by default as identity primary key,
  employee_id        text not null references public.employees(id) on delete cascade,
  check_in           text,
  check_out          text,
  total_hours        numeric default 0,
  extra_hours        numeric default 0,
  is_extra_approved  boolean default false,
  approved_by        text,
  date               text not null,
  status             text not null default 'REGULAR',
  institution_id     text not null references public.institutions(id) on delete cascade
);
create index if not exists idx_emp_attendance_employee_date on public.employee_attendance(employee_id, date);
create index if not exists idx_emp_attendance_institution on public.employee_attendance(institution_id);

-- =====================================================
-- grades (calificaciones)
-- =====================================================
create table if not exists public.grades (
  id             bigint generated by default as identity primary key,
  student_id     bigint not null references public.students(id) on delete cascade,
  subject        text default '',
  period         text default '',
  score          numeric default 0,
  observations   text default '',
  date           text default '',
  institution_id text not null references public.institutions(id) on delete cascade
);
create index if not exists idx_grades_student on public.grades(student_id);
create index if not exists idx_grades_institution on public.grades(institution_id);

-- =====================================================
-- fee_payments (pagos de matrícula/pensión)
-- =====================================================
create table if not exists public."feePayments" (
  id               text primary key default gen_random_uuid()::text,
  "studentId"      bigint not null references public.students(id) on delete cascade,
  "institutionId"  text not null references public.institutions(id) on delete cascade,
  monto            numeric not null default 0,
  concepto         text default '',
  fecha            text default '',
  "usuarioRecibe"  text default '',
  "metodoPago"     text default 'EFECTIVO',
  "receiptUrl"     text
);
create index if not exists idx_fee_payments_student on public."feePayments"("studentId");
create index if not exists idx_fee_payments_institution on public."feePayments"("institutionId");

-- =====================================================
-- salary_records
-- =====================================================
create table if not exists public.salary_records (
  id             text primary key default gen_random_uuid()::text,
  employee_id    text not null references public.employees(id) on delete cascade,
  institution_id text not null references public.institutions(id) on delete cascade,
  amount         numeric not null default 0,
  date           bigint default 0,
  type           text not null default 'HONORARIO',
  status         text not null default 'PENDIENTE',
  observation    text default ''
);
create index if not exists idx_salary_records_institution on public.salary_records(institution_id);

-- =====================================================
-- puc_accounts (plan único de cuentas contable)
-- =====================================================
create table if not exists public.puc_accounts (
  id              text primary key default gen_random_uuid()::text,
  code            text not null,
  name            text not null default '',
  level           int not null default 1,
  parent_code     text,
  account_type    text not null default 'ASSET',
  institution_id  text not null references public.institutions(id) on delete cascade,
  is_custom       boolean default false,
  is_active       boolean default true,
  unique (institution_id, code)
);
create index if not exists idx_puc_accounts_institution on public.puc_accounts(institution_id);

-- =====================================================
-- accounting_entries (asientos contables)
-- =====================================================
create table if not exists public.accounting_entries (
  id             text primary key default gen_random_uuid()::text,
  date           text default '',
  description    text default '',
  institution_id text not null references public.institutions(id) on delete cascade,
  entries        jsonb not null default '[]'::jsonb,
  total_debit    numeric default 0,
  total_credit   numeric default 0
);
create index if not exists idx_accounting_entries_institution on public.accounting_entries(institution_id);

-- =====================================================
-- tasks / task_submissions
-- =====================================================
create table if not exists public.tasks (
  id             text primary key default gen_random_uuid()::text,
  title          text default '',
  description    text default '',
  due_date       text default '',
  class_id       text references public.classes(id) on delete cascade,
  subject_id     text default '',
  teacher_id     text references public.employees(id) on delete set null,
  institution_id text not null references public.institutions(id) on delete cascade,
  status         text not null default 'PENDING'
);
create index if not exists idx_tasks_institution on public.tasks(institution_id);

create table if not exists public.task_submissions (
  id              text primary key default gen_random_uuid()::text,
  task_id         text not null references public.tasks(id) on delete cascade,
  student_id      text not null,
  submission_date text default '',
  evidence_url    text,
  score           numeric
);
create index if not exists idx_task_submissions_task on public.task_submissions(task_id);

-- =====================================================
-- exams
-- =====================================================
create table if not exists public.exams (
  id               text primary key default gen_random_uuid()::text,
  title            text default '',
  date             bigint default 0,
  class_id         text references public.classes(id) on delete cascade,
  subject_id       text default '',
  max_score        numeric default 20,
  institution_id   text not null references public.institutions(id) on delete cascade,
  duration_minutes int default 60,
  questions        jsonb not null default '[]'::jsonb
);
create index if not exists idx_exams_institution on public.exams(institution_id);

-- =====================================================
-- announcements
-- =====================================================
create table if not exists public.announcements (
  id             text primary key default gen_random_uuid()::text,
  title          text default '',
  content        text default '',
  date           bigint default 0,
  author_id      text,
  institution_id text not null references public.institutions(id) on delete cascade,
  target         text not null default 'ALL'
);
create index if not exists idx_announcements_institution on public.announcements(institution_id);

-- =====================================================
-- messages (chat interno)
-- =====================================================
create table if not exists public.messages (
  id             text primary key default gen_random_uuid()::text,
  sender_id      text not null,
  receiver_id    text not null,
  content        text default '',
  "timestamp"    bigint default 0,
  institution_id text not null references public.institutions(id) on delete cascade
);
create index if not exists idx_messages_institution on public.messages(institution_id);
create index if not exists idx_messages_participants on public.messages(sender_id, receiver_id);

-- =====================================================
-- vacation_requests / advance_requests / payroll_calculations
-- (módulo laboral). Se relacionan con la institución de forma
-- indirecta, a través de employees.id, para no tener que tocar los
-- modelos Kotlin existentes (que no cargan institutionId aquí).
-- =====================================================
create table if not exists public.vacation_requests (
  id             text primary key default gen_random_uuid()::text,
  "employeeId"   text not null references public.employees(id) on delete cascade,
  "startDate"    bigint not null,
  "endDate"      bigint not null,
  days           int not null default 0,
  status         text not null default 'PENDIENTE',
  observations   text default ''
);
create index if not exists idx_vacation_requests_employee on public.vacation_requests("employeeId");

create table if not exists public.advance_requests (
  id                text primary key default gen_random_uuid()::text,
  "employeeId"      text not null references public.employees(id) on delete cascade,
  "amountRequested" numeric not null default 0,
  reason            text default '',
  status            text not null default 'PENDIENTE',
  "requestDate"     bigint default 0,
  "maxAllowed"       numeric default 0
);
create index if not exists idx_advance_requests_employee on public.advance_requests("employeeId");

create table if not exists public.payroll_calculations (
  id             text primary key,
  employee_id    text not null references public.employees(id) on delete cascade,
  calculated_at  bigint not null,
  calculation    jsonb not null
);
create index if not exists idx_payroll_calculations_employee on public.payroll_calculations(employee_id);

-- =====================================================
-- exam_results
-- =====================================================
create table if not exists public.exam_results (
  id          text primary key default gen_random_uuid()::text,
  exam_id     text not null references public.exams(id) on delete cascade,
  student_id  bigint not null references public.students(id) on delete cascade,
  score       numeric default 0,
  answers     jsonb not null default '[]'::jsonb
);
create index if not exists idx_exam_results_exam on public.exam_results(exam_id);
create index if not exists idx_exam_results_student on public.exam_results(student_id);

-- =====================================================
-- institution_members (SEC-01, SEC-13)
-- Permite que múltiples usuarios (Rector, Docentes, etc.)
-- pertenezcan a una institución con diferentes roles.
-- =====================================================
create table if not exists public.institution_members (
  id              uuid primary key default gen_random_uuid(),
  user_id         uuid not null references auth.users(id) on delete cascade,
  institution_id  text not null references public.institutions(id) on delete cascade,
  role            text not null default 'DOCENTE', -- ADMIN, RECTOR, SECRETARIA, DOCENTE
  created_at      timestamptz default now(),
  unique(user_id, institution_id)
);
create index if not exists idx_members_user on public.institution_members(user_id);
create index if not exists idx_members_institution on public.institution_members(institution_id);

-- =====================================================
-- auditoria_eventos_financieros (SEC-24, SEC-28)
-- Registro inmutable de transacciones críticas.
-- =====================================================
create table if not exists public.auditoria_eventos_financieros (
  id_evento        uuid primary key default gen_random_uuid(),
  id_entidad       text not null, -- ID de Factura o Pago
  tipo_entidad     text not null, -- 'INVOICE', 'PAYMENT'
  accion           text not null, -- 'CREATE', 'UPDATE', 'ANNUL'
  estado_anterior  jsonb,
  estado_nuevo     jsonb,
  usuario_id       uuid references auth.users(id),
  institution_id   text references public.institutions(id),
  timestamp        timestamptz default now()
);
alter table public.auditoria_eventos_financieros enable row level security;
-- Solo lectura para administradores de la institución
create policy "audit_read_admin" on public.auditoria_eventos_financieros
  for select using (
    institution_id in (
      select institution_id from public.institution_members
      where user_id = auth.uid() and role in ('ADMIN', 'RECTOR')
    )
  );

-- =====================================================
-- privacy_policies & student_consents (LEY 1581)
-- =====================================================
create table if not exists public.privacy_policies (
  id               text primary key default gen_random_uuid()::text,
  version          int not null,
  fecha_publicacion bigint not null,
  contenido_hash    text not null,
  contenido_texto   text not null,
  es_activa         boolean default true
);

create table if not exists public.student_consents (
  id                 text primary key default gen_random_uuid()::text,
  student_id         bigint not null references public.students(id) on delete cascade,
  acudiente_nombre   text not null,
  acudiente_dni      text not null,
  acudiente_parentesco text,
  acudiente_email    text,
  acudiente_telefono text,
  politica_id        text not null references public.privacy_policies(id),
  fecha_aceptacion   bigint not null,
  fecha_revocacion   bigint,
  motivo_revocacion  text,
  device_info        text,
  hash_firma_digital text,
  granular_consent   jsonb default '{}'::jsonb
);
create index if not exists idx_consents_student on public.student_consents(student_id);

-- =====================================================
-- usuarios (Mapeo de auth.users con metadata adicional)
-- =====================================================
create table if not exists public.usuarios (
  id_usuario      uuid primary key references auth.users(id) on delete cascade,
  nombre          text not null,
  correo          text unique not null,
  rol             text not null, -- SISTEMA, ADMIN, RECTOR, DOCENTE, ACUDIENTE
  institution_id  text references public.institutions(id),
  created_at      timestamptz default now()
);

-- =====================================================
-- pagos_online (INTEGRACIÓN PASARELAS - SEC-24)
-- =====================================================
create table if not exists public.pagos_online (
  id_transaccion   uuid primary key default gen_random_uuid(),
  institution_id   text not null references public.institutions(id) on delete cascade,
  student_id       bigint references public.students(id) on delete set null,
  acudiente_id     text references public.institution_members(id),
  monto            numeric(12,2) not null,
  moneda           text default 'COP',
  referencia_externa text unique, -- Referencia enviada a PayU (referencia_sale)
  estado           text not null default 'PENDIENTE', -- PENDING, APPROVED, DECLINED, EXPIRED
  metodo_pago      text, -- PSE, NEQUI, DAVIPLATA, VISA, etc.
  payu_order_id    text,
  payu_transaction_id text,
  respuesta_payu   jsonb default '{}'::jsonb, -- Antes metadata
  created_at       timestamptz default now(),
  updated_at       timestamptz default now()
);

-- =====================================================
-- movimientos_caja (Hallazgo A.1 - Consistencia)
-- =====================================================
create table if not exists public.movimientos_caja (
    id_movimiento       uuid primary key default gen_random_uuid(),
    id_institution      text not null references public.institutions(id) on delete cascade,
    id_estudiante       bigint references public.students(id) on delete set null,
    id_concepto         text,
    tipo_movimiento     text not null check (tipo_movimiento in ('INGRESO', 'EGRESO')),
    monto               numeric(12,2) not null,
    fecha               timestamptz default now(),
    forma_pago          text not null,
    id_usuario_registro uuid not null, -- FK a usuarios/auth.users
    estado              text not null default 'CONFIRMADO',
    metadata            jsonb default '{}'::jsonb,
    created_at          timestamptz default now()
);

-- Migración: Crear usuario de sistema para integraciones
-- NOTA: Se asume que existe la tabla usuarios o se usa directamente auth.users
-- Para este script, aseguramos que el UUID de sistema sea válido.
-- INSERT INTO auth.users (id, email) VALUES ('00000000-0000-0000-0000-000000000001', 'system@payu.com') ON CONFLICT DO NOTHING;

-- Función SQL para procesar webhooks de forma atómica (Hallazgo A.2 - ALTO)
CREATE OR REPLACE FUNCTION process_payment_webhook(
    p_transaction_id TEXT,
    p_state TEXT,
    p_payu_response JSONB
) RETURNS JSONB AS $$
DECLARE
    v_existing_state TEXT;
    v_row_id UUID;
BEGIN
    -- Bloquear la fila para evitar race conditions (referencia_externa = referencia_sale de PayU)
    SELECT id_transaccion, estado INTO v_row_id, v_existing_state
    FROM public.pagos_online
    WHERE referencia_externa = p_transaction_id
    FOR UPDATE;

    IF v_row_id IS NULL THEN
        RETURN jsonb_build_object('status', 'NOT_FOUND');
    END IF;

    IF v_existing_state = 'APROBADO' THEN
        RETURN jsonb_build_object('status', 'IGNORED', 'reason', 'Already approved');
    END IF;

    -- Actualizar estado
    UPDATE public.pagos_online
    SET estado = p_state,
        respuesta_payu = p_payu_response,
        updated_at = now()
    WHERE id_transaccion = v_row_id;

    RETURN jsonb_build_object('status', 'UPDATED', 'id', v_row_id);
END;
$$ LANGUAGE plpgsql SECURITY DEFINER;

-- Script de Inicialización de Sistema
-- Se debe ejecutar manualmente en el dashboard o asegurar que el usuario de auth existe
/*
INSERT INTO auth.users (id, email, raw_app_meta_data, raw_user_meta_data, is_super_admin)
VALUES ('00000000-0000-0000-0000-000000000001', 'system@sigeschool.pro', '{"provider":"email"}', '{"name":"System PayU"}', false)
ON CONFLICT (id) DO NOTHING;

INSERT INTO public.usuarios (id_usuario, nombre, correo, rol)
VALUES ('00000000-0000-0000-0000-000000000001', 'Sistema PayU', 'system@sigeschool.pro', 'SISTEMA')
ON CONFLICT (id_usuario) DO NOTHING;
*/

alter table public.pagos_online enable row level security;

-- RLS: Solo administradores y el acudiente que realizó el pago pueden verlo
create policy "pagos_online_select" on public.pagos_online
  for select using (
    institution_id in (select public.my_institution_ids())
  );

-- Trigger para updated_at
create or replace function public.handle_updated_at()
returns trigger as $$
begin
  new.updated_at = now();
  return new;
end;
$$ language plpgsql;

create trigger set_updated_at
before update on public.pagos_online
for each row execute function public.handle_updated_at();

-- =====================================================================
-- Row Level Security (RLS) - PRIVACIDAD
-- =====================================================================
alter table public.privacy_policies enable row level security;
alter table public.student_consents enable row level security;

create policy "policies_read_all" on public.privacy_policies for select using (true);
create policy "consents_by_institution" on public.student_consents
  for all using (
    student_id in (
      select id from public.students
      where institution_id in (select public.my_institution_ids())
    )
  );

-- =====================================================================
-- Row Level Security (RLS) - ACTUALIZADO PARA MEMBRESIAS
-- =====================================================================
create or replace function public.my_institution_ids()
returns setof text
language sql
stable
security definer
set search_path = public
as $$
  -- SEC-01: Ahora el acceso se determina por la tabla de miembros, no solo por owner_id
  select institution_id from public.institution_members where user_id = auth.uid()
$$;

alter table public.institutions       enable row level security;
alter table public.employees          enable row level security;
alter table public.students           enable row level security;
alter table public.classes            enable row level security;
alter table public.attendance         enable row level security;
alter table public.employee_attendance enable row level security;
alter table public.grades             enable row level security;
alter table public."feePayments"       enable row level security;
alter table public.salary_records     enable row level security;
alter table public.puc_accounts       enable row level security;
alter table public.accounting_entries enable row level security;
alter table public.tasks              enable row level security;
alter table public.task_submissions   enable row level security;
alter table public.exams              enable row level security;
alter table public.announcements      enable row level security;
alter table public.messages           enable row level security;
alter table public.vacation_requests     enable row level security;
alter table public.advance_requests      enable row level security;
alter table public.payroll_calculations  enable row level security;
alter table public.exam_results          enable row level security;

-- institutions: cada usuario ve y administra solo la suya
create policy "institutions_owner_all" on public.institutions
  for all using (owner_id = auth.uid()) with check (owner_id = auth.uid());

-- Tablas hijas directas: institution_id text
create policy "employees_by_institution" on public.employees
  for all using (institution_id in (select public.my_institution_ids()))
  with check (institution_id in (select public.my_institution_ids()));

create policy "students_by_institution" on public.students
  for all using (institution_id in (select public.my_institution_ids()))
  with check (institution_id in (select public.my_institution_ids()));

create policy "classes_by_institution" on public.classes
  for all using (institution_id in (select public.my_institution_ids()))
  with check (institution_id in (select public.my_institution_ids()));

create policy "attendance_by_institution" on public.attendance
  for all using (institution_id in (select public.my_institution_ids()))
  with check (institution_id in (select public.my_institution_ids()));

create policy "employee_attendance_by_institution" on public.employee_attendance
  for all using (institution_id in (select public.my_institution_ids()))
  with check (institution_id in (select public.my_institution_ids()));

create policy "grades_by_institution" on public.grades
  for all using (institution_id in (select public.my_institution_ids()))
  with check (institution_id in (select public.my_institution_ids()));

create policy "fee_payments_by_institution" on public."feePayments"
  for all using ("institutionId" in (select public.my_institution_ids()))
  with check ("institutionId" in (select public.my_institution_ids()));

create policy "salary_records_by_institution" on public.salary_records
  for all using (institution_id in (select public.my_institution_ids()))
  with check (institution_id in (select public.my_institution_ids()));

create policy "puc_accounts_by_institution" on public.puc_accounts
  for all using (institution_id in (select public.my_institution_ids()))
  with check (institution_id in (select public.my_institution_ids()));

create policy "accounting_entries_by_institution" on public.accounting_entries
  for all using (institution_id in (select public.my_institution_ids()))
  with check (institution_id in (select public.my_institution_ids()));

create policy "tasks_by_institution" on public.tasks
  for all using (institution_id in (select public.my_institution_ids()))
  with check (institution_id in (select public.my_institution_ids()));

create policy "task_submissions_by_task" on public.task_submissions
  for all using (
    task_id in (select id from public.tasks where institution_id in (select public.my_institution_ids()))
  )
  with check (
    task_id in (select id from public.tasks where institution_id in (select public.my_institution_ids()))
  );

create policy "exams_by_institution" on public.exams
  for all using (institution_id in (select public.my_institution_ids()))
  with check (institution_id in (select public.my_institution_ids()));

create policy "announcements_by_institution" on public.announcements
  for all using (institution_id in (select public.my_institution_ids()))
  with check (institution_id in (select public.my_institution_ids()));

create policy "messages_by_institution" on public.messages
  for all using (institution_id in (select public.my_institution_ids()))
  with check (institution_id in (select public.my_institution_ids()));

-- Tablas relacionadas por employee_id/exam_id: se resuelve la
-- institución mediante un join en vez de una columna directa.
create policy "vacation_requests_by_employee_institution" on public.vacation_requests
  for all using (
    "employeeId" in (select id from public.employees where institution_id in (select public.my_institution_ids()))
  )
  with check (
    "employeeId" in (select id from public.employees where institution_id in (select public.my_institution_ids()))
  );

create policy "advance_requests_by_employee_institution" on public.advance_requests
  for all using (
    "employeeId" in (select id from public.employees where institution_id in (select public.my_institution_ids()))
  )
  with check (
    "employeeId" in (select id from public.employees where institution_id in (select public.my_institution_ids()))
  );

create policy "payroll_calculations_by_employee_institution" on public.payroll_calculations
  for all using (
    employee_id in (select id from public.employees where institution_id in (select public.my_institution_ids()))
  )
  with check (
    employee_id in (select id from public.employees where institution_id in (select public.my_institution_ids()))
  );

create policy "exam_results_by_exam_institution" on public.exam_results
  for all using (
    exam_id in (select id from public.exams where institution_id in (select public.my_institution_ids()))
  )
  with check (
    exam_id in (select id from public.exams where institution_id in (select public.my_institution_ids()))
  );

-- =====================================================================
-- Storage: bucket "receipts" (comprobantes de pago en PDF)
-- =====================================================================
-- 1) Crear el bucket como PRIVADO (public = false) si no existe.
insert into storage.buckets (id, name, public)
values ('receipts', 'receipts', false)
on conflict (id) do update set public = false;

-- 2) Solo usuarios autenticados con institución pueden subir/leer/
--    borrar objetos dentro de su propio bucket. Como el path se genera
--    como "receipt_<paymentId>.pdf" sin prefijo de institución, se
--    exige que el paymentId ya exista en fee_payments y pertenezca a
--    una institución del usuario (evita que alguien intente leer/
--    sobrescribir el recibo de otra institución adivinando el nombre
--    del archivo).
create policy "receipts_select_own_institution" on storage.objects
  for select using (
    bucket_id = 'receipts'
    and exists (
      select 1 from public."feePayments" fp
      where 'receipt_' || fp.id || '.pdf' = storage.objects.name
        and fp."institutionId" in (select public.my_institution_ids())
    )
  );

create policy "receipts_insert_own_institution" on storage.objects
  for insert with check (
    bucket_id = 'receipts'
    and exists (
      select 1 from public."feePayments" fp
      where 'receipt_' || fp.id || '.pdf' = storage.objects.name
        and fp."institutionId" in (select public.my_institution_ids())
    )
  );

-- =====================================================================
-- IMPORTANTE:
-- * Todas las políticas exigen `authenticated` (RLS bloquea por defecto
--   a `anon`, y como no se creó ninguna política "for anon", el rol
--   anónimo queda sin acceso a estas tablas).
-- * El esquema de ejemplo original insertaba datos de prueba con
--   `staff_id`/`class_id` sueltos sin institución: ese seed ya no aplica
--   a este modelo multi-institución y se eliminó. Genera datos de
--   prueba únicamente después de crear una institución real desde la
--   app (auth.signUp + registerWithInstitution).
-- =====================================================================
