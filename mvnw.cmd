@echo off
setlocal
set MAVEN_PROJECTBASEDIR=%~dp0
if defined JAVA_HOME (
  set JAVA_EXE=%JAVA_HOME%\bin\java.exe
) else (
  set JAVA_EXE=java
)
"%JAVA_EXE%" %MAVEN_OPTS% --class-path "%MAVEN_PROJECTBASEDIR%.mvn\wrapper\maven-wrapper.jar" "-Dmaven.multiModuleProjectDirectory=%MAVEN_PROJECTBASEDIR%." org.apache.maven.wrapper.MavenWrapperMain %*
endlocal
