Java 21 Upgrade Plan

Goal: Upgrade the project `laboratoio07DSOO` to Java 21 (LTS).

Overview:
- Project appears to be a simple Java application under `javaapplication46/` with plain .java source files and no build tool (no pom.xml or build.gradle found).

Assumptions:
- This is a small single-module project compiled and run using `javac`/`java` or an IDE.
- No external dependencies or build tool were detected in the repository root.

Steps to upgrade locally (Windows PowerShell):

1) Install JDK 21
- Download from Adoptium/Eclipse Temurin or use package managers.
- PowerShell command (winget):
  winget install -e --id Eclipse.Adoptium.Temurin.21.JRE
  # or for JDK
  winget install -e --id Eclipse.Adoptium.Temurin.21.JDK

2) Verify installation and set JAVA_HOME (PowerShell):
  $env:JAVA_HOME = "C:\\Program Files\\Eclipse Adoptium\\jdk-21"
  [Environment]::SetEnvironmentVariable('JAVA_HOME', $env:JAVA_HOME, 'User')
  java -version
  javac -version

3) Build the project with javac
- From the repository root run (PowerShell):
  cd .\javaapplication46
  javac *.java

4) Run the application
  java Main

5) If compilation errors occur due to language-level features or APIs removed/changed, fix them. Common issues when upgrading:
- Removed internal JDK APIs (sun.*) usage.
- Strict encapsulation with modules (unlikely for this small app).

6) If you want to add a build tool (recommended):
- Initialize Maven or Gradle. For Maven:
  mvn archetype:generate -DgroupId=com.example -DartifactId=javaapplication46 -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
- Move source files into `src/main/java` and update `pom.xml` to set `maven-compiler-plugin` source/target to 21.

7) Update README and CI to require JDK 21.

Notes:
- Because the project currently has no build descriptor, the upgrade is mainly about installing JDK 21 and compiling with it. If you want, I can add a Maven or Gradle build (pom.xml or build.gradle) and set the compiler target to 21; tell me which you prefer.

Next steps I can take now:
- Add a `pom.xml` with Java 21 settings (Maven) and update project layout.
- Or add a `build.gradle` file (Gradle) and configure Java 21.
- Or simply verify compilation with JDK 21 if you have it installed; I can run javac in a terminal.
