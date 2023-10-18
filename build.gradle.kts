// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    val hiltVersion = "2.48.1"
    id("com.android.application") version "8.1.2" apply false
    id("org.jetbrains.kotlin.android") version "1.9.10" apply false
    id("com.google.dagger.hilt.android") version hiltVersion apply false
}