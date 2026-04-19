plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
    id("org.jetbrains.kotlin.plugin.serialization")
}

android {
    namespace = "cat.arnaugilco.basketball"
    compileSdk {
        version = release(36) {
            minorApiLevel = 1
        }
    }

    defaultConfig {
        applicationId = "cat.arnaugilco.basketball"
        minSdk = 26
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)

    //Serialització ((CAl activar el plugin a l'altre gradle)
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.11.0")
    //Navegació
    implementation("androidx.navigation:navigation-compose:2.9.7")
    //Biblioteca extesa d'icones
    implementation ("androidx.compose.material:material-icons-extended:1.7.8")
    //DataStore
    implementation ("androidx.datastore:datastore-preferences:1.2.1")
    //Lifecycle
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.10.0")

    //Retrofit
    implementation ("com.squareup.retrofit2:retrofit:3.0.0")

    //Scalar converter de Retrofit
    implementation ("com.squareup.retrofit2:converter-scalars:3.0.0")

    //Gson converter de Retrofit
    implementation ("com.squareup.retrofit2:converter-gson:3.0.0")


    //Convertidors moshi de retrofit
    implementation ("com.squareup.moshi:moshi:1.15.2")
    implementation ("com.squareup.retrofit2:converter-moshi:3.0.0")

    implementation ("com.squareup.okhttp3:logging-interceptor:5.3.2")


    //Coil  (Per a carregar imatges d'internet
    implementation ("io.coil-kt:coil-compose:2.7.0")
    implementation("io.coil-kt:coil-svg:2.5.0")

    //Constraint layout per a Compose
    implementation ("androidx.constraintlayout:constraintlayout-compose:1.1.1")
    implementation(libs.androidx.lifecycle.runtime.compose)



    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}