object Dependencies {

    const val ANDROID_GRADLE_PLUGIN = "com.android.tools.build:gradle:${Versions.GRADLE_PLUGIN}"
    const val KOTLIN_GRADLE_PLUGIN = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.KOTLIN}"
    const val KOTLIN = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.KOTLIN}"
    const val RX_JAVA2 = "io.reactivex.rxjava2:rxjava:${Versions.RX_JAVA2}"
    const val RX_KOTLIN = "io.reactivex.rxjava2:rxkotlin:${Versions.RX_KOTLIN}"
    const val ARCH_COMPONENTS = "androidx.lifecycle:lifecycle-extensions:${Versions.ARCHI_LIFE_CYCLE}"

    @JvmField
    val SUPPORT_LIBS = arrayOf(
        "androidx.appcompat:appcompat:${Versions.ANDROID_X}",
        "com.google.android.material:material:${Versions.MATERIAL}",
        "androidx.constraintlayout:constraintlayout:${Versions.CONSTRAINT_LAYOUT}",
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.ARCHI_LIFE_CYCLE}"
    )

    @JvmField
    val KTX = arrayOf(
        "androidx.core:core-ktx:${Versions.KTX}",
        "androidx.fragment:fragment-ktx:${Versions.KTX_FRAGMENTS}"
    )

    const val JUNIT = "junit:junit:${Versions.JUNIT}"


    @JvmField
    val KOIN = arrayOf(
        "org.koin:koin-core:${Versions.KOIN}",
        "org.koin:koin-android:${Versions.KOIN}",
        "org.koin:koin-androidx-scope:${Versions.KOIN}",
        "org.koin:koin-androidx-viewmodel:${Versions.KOIN}"
    )

    const val GLIDE = "com.github.bumptech.glide:glide:${Versions.GLIDE}"

    const val GSON = "com.google.code.gson:gson:${Versions.GSON}"

    @JvmField
    val NETWORK = arrayOf("com.squareup.retrofit2:retrofit:${Versions.RETROFIT}",
        "com.squareup.retrofit2:adapter-rxjava2:${Versions.RETROFIT}",
        "com.squareup.retrofit2:converter-gson:${Versions.RETROFIT}",
        "com.squareup.okhttp3:logging-interceptor:${Versions.OKHTTP}"
    )

    @JvmField
    val NAVIGATION = arrayOf(
        "androidx.navigation:navigation-fragment-ktx:${Versions.NAVIGATION}",
        "androidx.navigation:navigation-ui-ktx:${Versions.NAVIGATION}"
    )

    const val SAFE_ARGS_PLUGIN = "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.NAVIGATION}"

    const val LOTTIE = "com.airbnb.android:lottie:${Versions.LOTTIE}"

    @JvmField
    val FIREBASE = arrayOf(
        "com.google.firebase:firebase-firestore:${Versions.FIREBASE_FIRESTORE}",
        "com.google.firebase:firebase-auth:${Versions.FIREBASE_AUTH}",
        "com.google.firebase:firebase-core:${Versions.FIREBASE_CORE}",
        "com.firebaseui:firebase-ui-storage:${Versions.FIREBASE_UI}",
        "com.google.firebase:firebase-messaging:${Versions.FIREBASE_MESSAGING}"
    )
}