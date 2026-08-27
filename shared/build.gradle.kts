plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.sigeschool"
    compileSdk = 36

    defaultConfig {
        minSdk = 24

        buildConfigField("String", "WHATSAPP_API_URL", "\"https://graph.facebook.com/v21.0/\"")
        buildConfigField("String", "WHATSAPP_PHONE_ID", "\"542387612282386\"")
        buildConfigField("String", "WHATSAPP_ACCESS_TOKEN", "\"EAANvvZAy5qZBsBOZBDtM9aZAeYy39uW4ZAyWpZBaE1qZA2eZAwZAw\"")
        buildConfigField("String", "EMAIL_SMTP_HOST", "\"smtp.gmail.com\"")
        buildConfigField("String", "EMAIL_SMTP_PORT", "\"587\"")
        buildConfigField("String", "EMAIL_USER", "\"soporte@sigeschool.pro\"")
        buildConfigField("String", "EMAIL_PASSWORD", "\"sigeschool2024*\"")
    }

    buildFeatures {
        buildConfig = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

kotlin {
    androidTarget {
        compilerOptions {
            jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11)
        }
    }
    
    jvm("desktop") {
        compilerOptions {
            jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11)
        }
    }
    
    @OptIn(org.jetbrains.kotlin.gradle.ExperimentalWasmDsl::class)
    wasmJs {
        browser {
            commonWebpackConfig {
                outputFileName = "shared.js"
            }
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            isStatic = true
        }
    }

    sourceSets {
        all {
            languageSettings.optIn("kotlin.time.ExperimentalTime")
        }
        val commonMain by getting {
            dependencies {
                // implementation(project(":shared-local")) // Movido a plataformas para evitar círculos y errores Wasm
                // Forzar alineación de stdlib para WasmJs
                implementation(kotlin("stdlib"))
                
                implementation(libs.supabase.kt)
                implementation(libs.supabase.postgrest)
                implementation(libs.supabase.auth)
                implementation(libs.supabase.storage)
                implementation(libs.supabase.functions)
                api(libs.supabase.auth)
                api(libs.supabase.storage)
                implementation(libs.koin.core)
                implementation(libs.kotlinx.serialization.json)
                api(libs.kotlinx.coroutines.core)
                api(libs.kotlinx.datetime)
                implementation(libs.koalaplot.core)
                implementation(libs.kotlinx.io.core)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(libs.kotlinx.coroutines.test)
                implementation(libs.koin.test)
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(libs.androidx.core.ktx)
                implementation(libs.androidx.lifecycle.runtime.ktx)
                implementation(libs.ktor.client.okhttp)
                implementation(libs.zxing.core)
                implementation(libs.mlkit.text.recognition)
                implementation(libs.androidx.biometric)
                implementation(libs.androidx.security.crypto)
                implementation(libs.litert)
                implementation(libs.litert.support)
                implementation(libs.timber)
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-play-services:1.11.0")
                implementation(libs.onnxruntime.android)
                implementation(libs.pdfbox.android)
                implementation(libs.poi.ooxml)
                
                // iText for Android
                implementation(libs.itext.kernel)
                implementation(libs.itext.io)
                implementation(libs.itext.layout)
                implementation(libs.itext.pdfa)
                implementation(libs.xmlsec)
            }
        }
        val desktopMain by getting {
            dependencies {
                implementation(libs.ktor.client.cio)
                implementation(libs.onnxruntime.desktop)
                api(libs.javacpp)
                api(libs.tesseract)
                api("org.bytedeco:tesseract:5.3.4-1.5.10:linux-x86_64")
                api(libs.leptonica)
                api("org.bytedeco:leptonica:1.84.1-1.5.10:linux-x86_64")
                implementation(libs.pdfbox)
                implementation(libs.itext.kernel)
                implementation(libs.itext.io)
                implementation(libs.itext.layout)
                implementation(libs.itext.pdfa)
                implementation(libs.poi.ooxml)
                implementation(libs.xmlsec)
            }
        }
        val wasmJsMain by getting {
            dependencies {
            }
        }
        val iosMain by creating {
            dependsOn(commonMain)
            dependencies {
                implementation(libs.ktor.client.darwin)
            }
        }
        val desktopTest by getting {
            dependencies {
                // implementation(project(":shared-local"))
            }
        }
    }
}
