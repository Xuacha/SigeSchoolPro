# Script de Diagnóstico para Tesseract en SigeSchool Pro (Windows/Desktop)

Write-Host "--- Iniciando Diagnóstico de Tesseract ---" -ForegroundColor Cyan

# 1. Verificar variables de entorno
Write-Host "`n1. Verificando variables de entorno..."
$tessDataPrefix = [System.Environment]::GetEnvironmentVariable("TESSDATA_PREFIX")
if ($null -eq $tessDataPrefix) {
    Write-Host "AVISO: TESSDATA_PREFIX no está configurado. El sistema usará la carpeta local 'tessdata/'." -ForegroundColor Yellow
} else {
    Write-Host "TESSDATA_PREFIX: $tessDataPrefix" -ForegroundColor Green
}

# 2. Verificar existencia de archivos de entrenamiento
Write-Host "`n2. Verificando archivos de entrenamiento (.traineddata)..."
$localTessData = Join-Path (Get-Location) "tessdata"
if (Test-Path $localTessData) {
    $files = Get-ChildItem -Path $localTessData -Filter "*.traineddata"
    if ($files.Count -gt 0) {
        Write-Host "Encontrados $($files.Count) archivos en $localTessData" -ForegroundColor Green
        $files | ForEach-Object { Write-Host " - $($_.Name)" }
    } else {
        Write-Host "ERROR: No se encontraron archivos .traineddata en $localTessData" -ForegroundColor Red
    }
} else {
    Write-Host "ERROR: La carpeta $localTessData no existe." -ForegroundColor Red
}

# 3. Verificar dependencias de JavaCPP en caché de Gradle
Write-Host "`n3. Verificando caché de Gradle para dependencias nativas..."
$gradleCache = Join-Path $env:USERPROFILE ".gradle\caches\modules-2\files-2.1\org.bytedeco"
if (Test-Path $gradleCache) {
    $leptonica = Get-ChildItem -Path $gradleCache -Filter "leptonica-platform" -Recurse
    $tesseract = Get-ChildItem -Path $gradleCache -Filter "tesseract-platform" -Recurse

    if ($leptonica) { Write-Host "Leptonica Platform: Presente" -ForegroundColor Green }
    else { Write-Host "Leptonica Platform: Ausente en caché" -ForegroundColor Red }

    if ($tesseract) { Write-Host "Tesseract Platform: Presente" -ForegroundColor Green }
    else { Write-Host "Tesseract Platform: Ausente en caché" -ForegroundColor Red }
}

Write-Host "`n--- Diagnóstico Finalizado ---" -ForegroundColor Cyan
