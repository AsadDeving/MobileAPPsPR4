# KMP Advanced Project Structure: Practice 4

Kotlin Multiplatform practice project for **Lectures 10–11** (advanced project structure · choosing a project configuration). It continues [Practice 3](https://github.com/AsadDeving/MobileAPPsPR3), which covered Questions 1–13.

📄 **Sections 3–4 (Questions 14–25) are answered in [`ANSWERS.md`](ANSWERS.md).** Hands-on answers link to the code in this repository and show the real Gradle output.

## Modules

```
.
├── advanced-lab/       JVM + JS + Linux + iOS, custom jvmAndJsMain via manual dependsOn (Q16, Q17, Q21)
├── template-lab/       same targets, default hierarchy template + custom group (Q17)
├── dependencies-lab/   propagation, compatibility, version alignment (Q18, Q19, Q20)
├── build-logic/        convention plugin: printSourceSets / printCompilations report tasks (Lecture 7)
└── gradle/libs.versions.toml   single version catalog
```

### Source-set tree in `advanced-lab` (Q16)

```
                 commonMain
        ┌───────────┼──────────────┬───────────────────┐
  jvmAndJsMain  linuxX64Main  iosArm64Main  iosSimulatorArm64Main
    ┌────┴────┐
 jvmMain    jsMain
```

`jvmAndJsMain` is created with `by creating` and wired in with three `dependsOn` calls. Because of the manual wiring, the default hierarchy template is not applied, so there is no `iosMain`/`appleMain`/`nativeMain` (Q17). `template-lab` gets the same `jvmAndJsMain` by extending the template with `group("jvmAndJs")`, and keeps all of the template's intermediate source sets.

## Build and test

Requirements: JDK 17+. Building the iOS targets also needs macOS + Xcode.

```bash
./gradlew :advanced-lab:printSourceSets :template-lab:printSourceSets      # Q16 / Q17
./gradlew :advanced-lab:printCompilations :advanced-lab:printJvmMainCompilation   # Q21
./gradlew :dependencies-lab:printCoroutinesVersions                        # Q18 / Q20
./gradlew :dependencies-lab:compileCommonMainKotlinMetadata -Pq19.showError=true  # Q19 (fails on purpose)

./gradlew :advanced-lab:jvmTest :advanced-lab:compileKotlinJs :template-lab:jvmTest :dependencies-lab:jvmTest
./gradlew :advanced-lab:linuxX64Test :template-lab:linuxX64Test             # Linux
./gradlew :dependencies-lab:iosSimulatorArm64Test                          # macOS
```

GitHub Actions ([`.github/workflows/build.yml`](.github/workflows/build.yml)) runs all of these on Ubuntu (JVM, JS, Linux) and macOS (iOS). It also checks that the Q19 build fails with exactly `Unresolved reference 'asExecutor'`.
