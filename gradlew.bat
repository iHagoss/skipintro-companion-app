@echo off
REM Gradle wrapper script (Windows)
setlocal
set DIR=%~dp0
set JAVA_HOME=%JAVA_HOME%
"%DIR%gradle\wrapper\gradle-wrapper.jar" %*
endlocal