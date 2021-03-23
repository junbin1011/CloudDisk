package com.cloud.disk.plugin


public class LibPluginExtension {
    String roomVersion = '2.2.6'
    String lifeCycleVersion = '2.3.0'
    String hiltVersion = '2.31.2-alpha'
    String coroutinesVersion = '1.4.2'
    String mockitoVersion = '3.3.3'
    String junitVersion = '4.13.2'
    String robolectricVersion = '4.5.1'
    String espressoVersion = '3.3.0'
    String aRouterVersion = '1.5.1'
    String appCompactVersion = '1.2.0'
    String materialVersion = '1.3.0'
    String coreKtxVersion = '1.3.2'
    String constraintlayoutVersion = '2.0.4'
    String kotlinVersion = '1.4.31'
    String supportV4Version = '1.0.0'

    void test() {
        BasePlugin.mProject.dependencies {
            testImplementation "junit:junit:$junitVersion"
            testImplementation "org.mockito:mockito-inline:$mockitoVersion"
            testImplementation "android.arch.core:core-testing:1.1.1"
            testImplementation "com.google.truth:truth:1.0.1"
            testImplementation 'com.tngtech.archunit:archunit-junit4:0.15.0'
            testImplementation "org.robolectric:robolectric:$robolectricVersion"
            testImplementation 'androidx.test:core:1.3.0'
            testImplementation 'androidx.test.ext:junit:1.1.2'
            testImplementation "androidx.test.espresso:espresso-core:$espressoVersion"
            testImplementation "androidx.test.espresso:espresso-intents:$espressoVersion"
            debugImplementation "androidx.fragment:fragment-testing:1.3.1"
        }
    }

    void room() {
        BasePlugin.mProject.dependencies {
            implementation "androidx.room:room-runtime:$roomVersion"
            implementation "androidx.room:room-ktx:$roomVersion"
            kapt "androidx.room:room-compiler:$roomVersion"
            testImplementation "androidx.room:room-testing:$roomVersion"
        }
    }

    void coroutines() {
        BasePlugin.mProject.dependencies {
            implementation "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion"
            implementation "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion"
            testImplementation "org.jetbrains.kotlinx:kotlinx-coroutines-test:1.3.3"
        }
    }

    void hilt() {
        BasePlugin.mProject.dependencies {
            implementation "com.google.dagger:hilt-android:$hiltVersion"
            kapt "com.google.dagger:hilt-android-compiler:$hiltVersion"
            testImplementation "com.google.dagger:hilt-android-testing:$hiltVersion"
            kaptTest "com.google.dagger:hilt-android-compiler:$hiltVersion"
        }
    }

    void mvvm() {
        BasePlugin.mProject.dependencies {
            implementation "androidx.lifecycle:lifecycle-livedata-ktx:${lifeCycleVersion}"
            implementation "androidx.lifecycle:lifecycle-viewmodel-ktx:${lifeCycleVersion}"
            implementation "androidx.fragment:fragment-ktx:1.3.1"
        }
    }

    void ktx() {
        BasePlugin.mProject.dependencies {
            implementation "androidx.core:core-ktx:${coreKtxVersion}"
        }
    }

    void router() {
        BasePlugin.mProject.dependencies {
            implementation "com.alibaba:arouter-api:${aRouterVersion}"
            kapt "com.alibaba:arouter-compiler:${aRouterVersion}"
        }
    }


    void appCompact() {
        BasePlugin.mProject.dependencies {
            implementation "androidx.appcompat:appcompat:${appCompactVersion}"
        }
    }

    void material() {
        BasePlugin.mProject.dependencies {
            implementation "com.google.android.material:material:${materialVersion}"
        }
    }

    void constraintlayout() {
        BasePlugin.mProject.dependencies {
            implementation "androidx.constraintlayout:constraintlayout:${constraintlayoutVersion}"
        }
    }

    void kotlin() {
        BasePlugin.mProject.dependencies {
            implementation "org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion"
        }
    }

    void supportV4() {
        BasePlugin.mProject.dependencies {
            implementation "androidx.legacy:legacy-support-v4:$supportV4Version"
        }
    }

}
