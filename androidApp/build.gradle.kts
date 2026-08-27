import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.gestionescolar.sigeschoolpro"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.gestionescolar.sigeschoolpro"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0.0"

        // Leer propiedades
        val props = Properties().apply {
            file("../local.properties").takeIf { it.exists() }?.inputStream()?.use { load(it) }
        }
        
        buildConfigField("String", "WHATSAPP_API_URL", "\"${props.getProperty("WHATSAPP_API_URL", "")}\"")
        buildConfigField("String", "WHATSAPP_PHONE_ID", "\"${props.getProperty("WHATSAPP_PHONE_NUMBER_ID", "")}\"")
        buildConfigField("String", "WHATSAPP_ACCESS_TOKEN", "\"${props.getProperty("WHATSAPP_ACCESS_TOKEN", "")}\"")
        buildConfigField("String", "EMAIL_SMTP_HOST", "\"${props.getProperty("EMAIL_SMTP_HOST", "")}\"")
        buildConfigField("String", "EMAIL_SMTP_PORT", "\"${props.getProperty("EMAIL_SMTP_PORT", "587")}\"")
        buildConfigField("String", "EMAIL_USER", "\"${props.getProperty("EMAIL_USER", "")}\"")
        buildConfigField("String", "EMAIL_PASSWORD", "\"${props.getProperty("EMAIL_PASSWORD", "")}\"")
    }

    signingConfigs {
        create("release") {
            val props = Properties().apply {
                file("../local.properties").takeIf { it.exists() }?.inputStream()?.use { load(it) }
            }
            // Prioridad: Propiedades locales o Variables de Entorno
            val path = props.getProperty("KEYSTORE_PATH") ?: System.getenv("KEYSTORE_PATH")
            if (path != null) {
                storeFile = file(path)
                storePassword = props.getProperty("KEYSTORE_PASSWORD") ?: System.getenv("KEYSTORE_PASSWORD")
                keyAlias = props.getProperty("KEY_ALIAS") ?: System.getenv("KEY_ALIAS")
                keyPassword = props.getProperty("KEY_PASSWORD") ?: System.getenv("KEY_PASSWORD")
            }
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            signingConfig = signingConfigs.getByName("release")
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
        debug {
            signingConfig = signingConfigs.getByName("debug")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    
    buildFeatures {
        compose = true
        buildConfig = true
    }
    
    kotlinOptions {
        jvmTarget = "11"
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            excludes += "META-INF/DEPENDENCIES"
            // Resolver conflictos de Bouncy Castle
            pickFirsts += "META-INF/versions/9/OSGI-INF/MANIFEST.MF"
        }
    }
}

configurations.all {
    resolutionStrategy {
        force("org.bouncycastle:bcprov-jdk15to18:1.72")
        force("org.bouncycastle:bcpkix-jdk15to18:1.72")
        force("org.bouncycastle:bcutil-jdk15to18:1.72")
    }
    exclude(group = "org.bouncycastle", module = "bcprov-jdk15on")
    exclude(group = "org.bouncycastle", module = "bcpkix-jdk15on")
    exclude(group = "org.bouncycastle", module = "bcutil-jdk15on")
}

// Forzar el target de JVM 11 para Kotlin de forma global en este módulo
tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinJvmCompile>().configureEach {
    compilerOptions {
        jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11)
    }
}

dependencies {
    implementation(project(":shared"))
    implementation(project(":shared-ui"))
    implementation(project(":shared-local"))
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.material3)
    implementation(libs.koin.android)
    implementation(libs.androidx.work.runtime.ktx)
    implementation(libs.androidx.core.ktx)
    implementation(libs.timber)
}
