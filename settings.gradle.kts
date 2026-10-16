rootProject.name = "KmpAdvancedProjectStructure"

pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }
}

include(":advanced-lab")      // Q16, Q17, Q21: custom jvmAndJsMain source set + compilations
include(":template-lab")      // Q17: same targets, no manual dependsOn (default hierarchy template)
include(":dependencies-lab")  // Q18, Q19, Q20: propagation, compatibility, version alignment
