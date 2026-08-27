-- =====================================================================
-- SigeSchoolPro — Schema para Supabase Postgres
-- Ejecuta este script en: Supabase Dashboard → SQL Editor → New Query
-- =====================================================================

-- Habilitar extensión para IDs (opcional, recomendado)
create extension if not exists "pgcrypto";

-- =====================================================
-- Tabla: staff (personal del colegio)
-- =====================================================
create table if not exists public.staff (
  id          text primary key default gen_random_uuid()::text,
  name        text not null,
  email       text unique not null,
  role        text not null default 'teacher',
  phone       text,
  avatar_color text default '#10b981',
  bio         text,
  department  text,
  subject     text,
  hire_date   timestamptz default now(),
  status      text default 'active',
  created_at  timestamptz default now(),
  updated_at  timestamptz default now()
);

-- =====================================================
-- Tabla: students (estudiantes)
-- =====================================================
create table if not exists public.students (
  id              text primary key default gen_random_uuid()::text,
  name            text not null,
  grade           text,
  section         text,
  guardian_name   text,
  guardian_phone  text,
  email           text,
  enrollment_date timestamptz default now(),
  status          text default 'active',
  gpa             numeric default 0,
  created_at      timestamptz default now(),
  updated_at      timestamptz default now()
);

-- =====================================================
-- Tabla: classes (clases / cursos)
-- =====================================================
create table if not exists public.classes (
  id             text primary key default gen_random_uuid()::text,
  name           text not null,
  subject        text,
  grade          text,
  staff_id       text references public.staff(id) on delete set null,
  room           text,
  schedule       text,
  capacity       int default 30,
  enrolled_count int default 0,
  created_at     timestamptz default now(),
  updated_at     timestamptz default now()
);

-- =====================================================
-- Tabla: attendance (asistencia)
-- =====================================================
create table if not exists public.attendance (
  id         text primary key default gen_random_uuid()::text,
  student_id text not null references public.students(id) on delete cascade,
  class_id   text not null references public.classes(id) on delete cascade,
  date       timestamptz default now(),
  status     text not null,
  notes      text,
  created_at timestamptz default now(),
  updated_at timestamptz default now()
);
create index if not exists idx_attendance_class_date on public.attendance(class_id, date);
create index if not exists idx_attendance_student on public.attendance(student_id);

-- =====================================================
-- Tabla: grades (calificaciones)
-- =====================================================
create table if not exists public.grades (
  id          text primary key default gen_random_uuid()::text,
  student_id  text not null references public.students(id) on delete cascade,
  class_id    text not null references public.classes(id) on delete cascade,
  term        text,
  value       numeric,
  observation text,
  created_at  timestamptz default now(),
  updated_at  timestamptz default now()
);

-- =====================================================
-- Tabla: notifications (notificaciones)
-- =====================================================
create table if not exists public.notifications (
  id         text primary key default gen_random_uuid()::text,
  title      text not null,
  message    text,
  type       text default 'info',
  audience   text default 'all',
  staff_id   text references public.staff(id) on delete set null,
  read       boolean default false,
  created_at timestamptz default now()
);

-- =====================================================
-- Tabla: activities (registro de actividad)
-- =====================================================
create table if not exists public.activities (
  id          text primary key default gen_random_uuid()::text,
  type        text,
  description text,
  actor_id    text references public.staff(id) on delete set null,
  target      text,
  created_at  timestamptz default now()
);
create index if not exists idx_activities_created on public.activities(created_at desc);

-- =====================================================
-- Row Level Security (RLS)
-- =====================================================
-- Habilitamos RLS pero permitimos acceso total al anon/authenticated
-- porque el control de acceso se hace en la app (login simple por email).
-- En producción deberías ajustar estas políticas según tus roles.

alter table public.staff         enable row level security;
alter table public.students      enable row level security;
alter table public.classes       enable row level security;
alter table public.attendance    enable row level security;
alter table public.grades        enable row level security;
alter table public.notifications enable row level security;
alter table public.activities    enable row level security;

-- Políticas permissivas (anon + authenticated pueden leer y escribir todo)
-- El SDK usa la clave secreta para bypass de RLS en operaciones administrativas.

create policy "public_read_staff"  on public.staff         for select using (true);
create policy "public_write_staff" on public.staff         for all using (true) with check (true);

create policy "public_read_students"  on public.students      for select using (true);
create policy "public_write_students" on public.students      for all using (true) with check (true);

create policy "public_read_classes"  on public.classes       for select using (true);
create policy "public_write_classes" on public.classes       for all using (true) with check (true);

create policy "public_read_attendance"  on public.attendance    for select using (true);
create policy "public_write_attendance" on public.attendance    for all using (true) with check (true);

create policy "public_read_grades"  on public.grades        for select using (true);
create policy "public_write_grades" on public.grades        for all using (true) with check (true);

create policy "public_read_notifications"  on public.notifications for select using (true);
create policy "public_write_notifications" on public.notifications for all using (true) with check (true);

create policy "public_read_activities"  on public.activities    for select using (true);
create policy "public_write_activities" on public.activities    for all using (true) with check (true);

-- =====================================================
-- Datos de ejemplo (seed inicial)
-- =====================================================

-- Personal (8 miembros)
insert into public.staff (name, email, role, phone, avatar_color, bio, department, subject, hire_date, status) values
  ('Prof. Elena Vargas',     'elena.vargas@sigeschool.edu',     'principal',       '+57 300 111 2233', '#8b5cf6', 'Directora con 18 años de experiencia liderando comunidades educativas.', 'Dirección',     'Liderazgo',     now() - interval '5 years', 'active'),
  ('Prof. Roberto Méndez',   'roberto.mendez@sigeschool.edu',   'vice_principal',  '+57 300 222 3344', '#06b6d4', 'Vicerrector académico. Especialista en currículo y evaluación.',          'Dirección',     'Coordinación',  now() - interval '4 years', 'active'),
  ('Prof. Ana Lucía Castro', 'ana.castro@sigeschool.edu',       'teacher',         '+57 301 555 1212', '#10b981', 'Docente de matemáticas con énfasis en pensamiento lógico.',              'Ciencias',      'Matemáticas',   now() - interval '3 years', 'active'),
  ('Prof. Diego Fernández',  'diego.fernandez@sigeschool.edu',  'teacher',         '+57 301 666 3434', '#f59e0b', 'Físico apasionado por la experimentación y proyectos.',                  'Ciencias',      'Física',        now() - interval '2 years', 'active'),
  ('Prof. Mariana López',    'mariana.lopez@sigeschool.edu',    'teacher',         '+57 302 777 5656', '#ec4899', 'Licenciada en letras. Lectura crítica y escritura creativa.',           'Humanidades',   'Lengua y Literatura', now() - interval '6 years', 'active'),
  ('Prof. Sebastián Ruiz',   'sebastian.ruiz@sigeschool.edu',   'teacher',         '+57 302 888 7878', '#ef4444', 'ESL certified. Enseña inglés comunicativo con metodología CLIL.',       'Idiomas',       'Inglés',        now() - interval '4 years', 'active'),
  ('Prof. Catalina Ortega',  'catalina.ortega@sigeschool.edu',  'counselor',       '+57 303 999 9090', '#14b8a6', 'Psicóloga educativa. Acompaña convivencia y bienestar.',                 'Bienestar',     'Orientación',   now() - interval '3 years', 'active'),
  ('Prof. Andrés Torres',    'andres.torres@sigeschool.edu',    'admin',           '+57 304 123 4567', '#f97316', 'Administrador escolar. Coordina matrículas y procesos.',                'Administración','Gestión',       now() - interval '5 years', 'active')
on conflict (email) do nothing;

-- Clases (asignadas a docentes)
insert into public.classes (name, subject, grade, staff_id, room, schedule, capacity, enrolled_count)
select 'Matemáticas 9° A', 'Matemáticas', '9°', s.id, 'A-201', 'Lun/Mié/Vie 08:00-09:30', 30, 27 from public.staff s where s.email = 'ana.castro@sigeschool.edu'
on conflict do nothing;

insert into public.classes (name, subject, grade, staff_id, room, schedule, capacity, enrolled_count)
select 'Matemáticas 10° B', 'Matemáticas', '10°', s.id, 'A-205', 'Mar/Jue 10:00-11:30', 28, 22 from public.staff s where s.email = 'ana.castro@sigeschool.edu'
on conflict do nothing;

insert into public.classes (name, subject, grade, staff_id, room, schedule, capacity, enrolled_count)
select 'Física 11° A', 'Física', '11°', s.id, 'Lab-1', 'Lun/Mié 09:45-11:15', 24, 19 from public.staff s where s.email = 'diego.fernandez@sigeschool.edu'
on conflict do nothing;

insert into public.classes (name, subject, grade, staff_id, room, schedule, capacity, enrolled_count)
select 'Lengua 8° C', 'Lengua y Literatura', '8°', s.id, 'B-102', 'Lun a Vie 07:00-08:00', 32, 28 from public.staff s where s.email = 'mariana.lopez@sigeschool.edu'
on conflict do nothing;

insert into public.classes (name, subject, grade, staff_id, room, schedule, capacity, enrolled_count)
select 'Inglés 9° A', 'Inglés', '9°', s.id, 'C-301', 'Mar/Jue 13:00-14:30', 25, 21 from public.staff s where s.email = 'sebastian.ruiz@sigeschool.edu'
on conflict do nothing;

insert into public.classes (name, subject, grade, staff_id, room, schedule, capacity, enrolled_count)
select 'Inglés 10° B', 'Inglés', '10°', s.id, 'C-302', 'Lun/Mié/Vie 11:30-12:30', 25, 23 from public.staff s where s.email = 'sebastian.ruiz@sigeschool.edu'
on conflict do nothing;

-- Estudiantes (12 de ejemplo)
insert into public.students (name, grade, section, guardian_name, guardian_phone, email, status, gpa) values
  ('Ana García',         '9°',  'A', 'María Pérez',     '+57 311 111 1111', 'ana.garcia@student.edu',         'active', 4.2),
  ('Carlos Gutiérrez',   '10°', 'B', 'Pedro Gutiérrez', '+57 311 222 2222', 'carlos.gutierrez@student.edu',   'active', 3.8),
  ('María Fernández',    '11°', 'A', 'Lucía Díaz',      '+57 311 333 3333', 'maria.fernandez@student.edu',    'active', 4.5),
  ('José Martínez',      '8°',  'C', 'Ana Torres',      '+57 311 444 4444', 'jose.martinez@student.edu',      'active', 3.5),
  ('Lucía Ramírez',      '9°',  'A', 'Carlos Ramírez',  '+57 311 555 5555', 'lucia.ramirez@student.edu',      'active', 4.0),
  ('Pedro Sánchez',      '10°', 'B', 'Elena Vargas',    '+57 311 666 6666', 'pedro.sanchez@student.edu',      'active', 3.9),
  ('Sofía Torres',       '11°', 'A', 'Roberto Méndez',  '+57 311 777 7777', 'sofia.torres@student.edu',       'active', 4.7),
  ('Diego Flores',       '8°',  'C', 'Marta Flores',    '+57 311 888 8888', 'diego.flores@student.edu',       'active', 3.6),
  ('Valentina Cruz',     '9°',  'A', 'Jorge Cruz',      '+57 311 999 9999', 'valentina.cruz@student.edu',     'active', 4.3),
  ('Mateo Reyes',        '10°', 'B', 'Patricia Reyes',  '+57 312 111 2222', 'mateo.reyes@student.edu',        'active', 3.7),
  ('Camila Herrera',     '11°', 'A', 'Andrés Herrera',  '+57 312 222 3333', 'camila.herrera@student.edu',     'active', 4.4),
  ('Sebastián Ortega',   '8°',  'C', 'Sofía Ortega',    '+57 312 333 4444', 'sebastian.ortega@student.edu',   'active', 3.8)
on conflict do nothing;

-- Notificaciones iniciales
insert into public.notifications (title, message, type, audience, created_at) values
  ('Reunión de departamento',     'El departamento de Ciencias se reunirá mañana a las 16:00 en la sala A-101.', 'info',    'teachers', now() - interval '2 hours'),
  ('Entrega de notas T2',         'Recuerden que el plazo para cargar calificaciones del T2 vence este viernes.', 'warning', 'teachers', now() - interval '5 hours'),
  ('Bienvenida al nuevo ciclo',   'Damos la bienvenida a toda la comunidad educativa al nuevo período escolar.', 'success', 'all',      now() - interval '1 day'),
  ('Mantenimiento de plataforma', 'El sistema estará en mantenimiento el sábado de 02:00 a 04:00.',               'alert',   'all',      now() - interval '1 day'),
  ('Capacitación obligatoria',    'Taller de metodologías activas - jueves 15:00.',                                'info',    'teachers', now() - interval '3 hours')
on conflict do nothing;

-- Actividades iniciales
insert into public.activities (type, description, target, created_at) values
  ('login',        'Prof. Ana Lucía Castro inició sesión',                          null,     now() - interval '30 minutes'),
  ('attendance',   'Asistencia registrada para Matemáticas 9° A',                    '9° A',   now() - interval '1 hour'),
  ('grade',        'Calificaciones T2 cargadas para Física 11° A',                   '11° A',  now() - interval '2 hours'),
  ('profile',      'Prof. Mariana López actualizó su perfil',                        null,     now() - interval '3 hours'),
  ('notification', 'Nueva notificación publicada: Reunión de departamento',          null,     now() - interval '2 hours'),
  ('student',      'Nuevo estudiante matriculado en 8° C',                           '8° C',   now() - interval '4 hours')
on conflict do nothing;

-- Mensaje final
do $$
begin
  raise notice '✅ SigeSchoolPro: Schema creado y seed insertado correctamente.';
  raise notice '📊 Personal: %', (select count(*) from public.staff);
  raise notice '📚 Clases: %', (select count(*) from public.classes);
  raise notice '🎓 Estudiantes: %', (select count(*) from public.students);
  raise notice '🔔 Notificaciones: %', (select count(*) from public.notifications);
  raise notice '⏱  Actividades: %', (select count(*) from public.activities);
end;
$$;
