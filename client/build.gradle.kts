buildscript {
    val agp_version by extra("7.4.2")
}
// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "7.4.2" apply false
    id("org.jetbrains.kotlin.android") version "1.8.10" apply false
    id ("com.android.library") version "8.1.1" apply false
    id ("org.jetbrains.kotlin.multiplatform") version "1.8.10" apply false
    id ("org.jetbrains.kotlin.plugin.serialization") version "1.8.10" apply false
    id ("com.google.dagger.hilt.android") version "2.44" apply false
}