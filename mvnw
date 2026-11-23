#!/bin/sh
BASE_DIR=$(cd "$(dirname "$0")" && pwd)
if [ -n "$JAVA_HOME" ]; then
  JAVA_EXE="$JAVA_HOME/bin/java"
else
  JAVA_EXE=java
fi
exec "$JAVA_EXE" $MAVEN_OPTS --class-path "$BASE_DIR/.mvn/wrapper/maven-wrapper.jar" "-Dmaven.multiModuleProjectDirectory=$BASE_DIR" org.apache.maven.wrapper.MavenWrapperMain "$@"
