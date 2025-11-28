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
mkdir -p bin
MF=$(mktemp)
JAR=$(mktemp)
JARvar=${JAR//[.\/]/_}
H=$(mktemp)
C=src/launcher.c
javac $(find src -name "*.java") -d bin
echo "Main-Class: app.App" > $MF
jar cfm $JAR $MF -C bin .
xxd -i $JAR > $H
cat << EOF > $C
#include <stdio.h>
#include <stdlib.h>
#include "$H"
int main()
{
	FILE *f = fopen("$JAR", "wb");
	if (!f)
	{
		perror("fopen");
		return 1;
	}
	fwrite(${JARvar}, 1, ${JARvar}_len, f);
	fclose(f);
	int ret = system("\$GRAALVM_HOME/bin/java -jar $JAR");
	remove("$JAR");
	return ret;
}
EOF
gcc "$C" -o app
rm -r bin $MF $JAR $H $C
./app