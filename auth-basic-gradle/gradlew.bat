@rem Copyright 2015 the original authors.

@echo off

setlocal

set APP_BASE_NAME=%~n0
set APP_HOME=%~dp0

set WRAPPER_JAR=%APP_HOME%\gradle\wrapper\gradle-wrapper.jar

if not exist "%WRAPPER_JAR%" (
    set WRAPPER_URL=https://repo.gradle.org/gradle/libs-releases-local/org/gradle/gradle-wrapper/9.2.1/gradle-wrapper-9.2.1.jar
    if exist "%SystemRoot%\System32\curl.exe" (
        curl -fsSL -o "%WRAPPER_JAR%" "%WRAPPER_URL%"
    ) else if exist "%SystemRoot%\System32\WindowsPowerShell\v1.0\powershell.exe" (
        powershell -Command "Invoke-WebRequest -UseBasicParsing -Uri '%WRAPPER_URL%' -OutFile '%WRAPPER_JAR%'"
    ) else (
        echo ERROR: Could not find curl or powershell to download gradle-wrapper.jar
        exit /b 1
    )
)

set CLASSPATH=%WRAPPER_JAR%

set DEFAULT_JVM_OPTS=-Xmx64m -Xms64m

"%JAVA_HOME%\bin\java.exe" -classpath "%CLASSPATH%" org.gradle.wrapper.GradleWrapperMain %*
