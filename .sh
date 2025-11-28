#!/usr/bin/env bash
set -euo pipefail
shopt -s expand_aliases
flag() {
	for f in "$@"; do
		[[ -e ".flags/$f" ]] || return 1
	done
}
GRAALVM_HOME="/opt/graalvm-community-openjdk-25.0.1+8.1"
alias jlink="$GRAALVM_HOME/bin/jlink"
alias jpackage="$GRAALVM_HOME/bin/jpackage"
rm -rf bin dist
mkdir -p bin dist
javac $(find src -name "*.java") -d bin
echo "Main-Class: app.App" > manifest.mf
jar cfm dist/App.jar manifest.mf -C bin .
rm manifest.mf
xxd -i dist/App.jar > src/jar.h
gcc src/launcher.c -o dist/launcher
cp dist/launcher app
./app