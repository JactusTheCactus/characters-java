#!/usr/bin/env bash
set -euo pipefail
flag() {
	for f in "$@"; do
		[[ -e ".flags/$f" ]] || return 1
	done
}
DIRS=(
	dist
	bin
	log
)
GRAALVM_HOME="/opt/graalvm-community-openjdk-25.0.1+8.1"
MF="dist/manifest.mf"
JAR="dist/App.jar"
JARvar="${JAR//[.\/]/_}"
JARlen="${JARvar}_len"
H="dist/data.h"
C="dist/launcher.c"
APP="bin/app"
LOG="log/java.log"
if flag log; then
	exec > $LOG 2>&1
fi
error() {
	printf "[X] %s\n" "$1" >&2
	return 1
}
warning() {
	printf "[!] %s\n" "$1"
}
log() {
	printf "[O] %s\n" "$1"
}
compile() {
	{
		javac $(find src -name "*.java") -d dist
	} || error "Java Compilation Failed"
}
main() {
	if compile; then
		java -cp dist app.App
	fi
}
main