@REM ----------------------------------------------------------------------------
@REM Licensed to the Apache Software Foundation (ASF) under one
@REM or more contributor license agreements.
@REM
@REM Maven Wrapper script for Windows
@REM ----------------------------------------------------------------------------

@echo off
setlocal

set MAVEN_VERSION=3.9.6
set WRAPPER_JAR=".mvn\wrapper\maven-wrapper.jar"
set WRAPPER_URL="https://repo.maven.apache.org/maven2/org/apache/maven/wrapper/maven-wrapper/3.2.0/maven-wrapper-3.2.0.jar"
set MAVEN_URL="https://repo.maven.apache.org/maven2/org/apache/maven/apache-maven/%MAVEN_VERSION%/apache-maven-%MAVEN_VERSION%-bin.zip"

set MAVEN_HOME=%USERPROFILE%\.m2\wrapper\dists\apache-maven-%MAVEN_VERSION%
set MVN_CMD=%MAVEN_HOME%\bin\mvn.cmd

if exist "%MVN_CMD%" goto runMaven

echo Maven not found. Downloading Apache Maven %MAVEN_VERSION%...

if not exist "%USERPROFILE%\.m2\wrapper\dists" mkdir "%USERPROFILE%\.m2\wrapper\dists"

set DOWNLOAD_DIR=%USERPROFILE%\.m2\wrapper\dists
set ZIP_FILE=%DOWNLOAD_DIR%\apache-maven-%MAVEN_VERSION%-bin.zip

powershell -Command "[Net.ServicePointManager]::SecurityProtocol = [Net.SecurityProtocolType]::Tls12; Invoke-WebRequest -Uri '%MAVEN_URL%' -OutFile '%ZIP_FILE%'"

if errorlevel 1 (
    echo ERROR: Failed to download Maven.
    exit /b 1
)

echo Extracting Maven...
powershell -Command "Expand-Archive -Path '%ZIP_FILE%' -DestinationPath '%DOWNLOAD_DIR%' -Force"

if errorlevel 1 (
    echo ERROR: Failed to extract Maven.
    exit /b 1
)

del "%ZIP_FILE%" 2>nul

:runMaven
set JAVA_EXE=java
if defined JAVA_HOME set JAVA_EXE=%JAVA_HOME%\bin\java

"%MVN_CMD%" %*
