#!/usr/bin/env bash
set -euo pipefail
flag() {
	for f in "$@"; do
		[[ -e ".flags/$f" ]] || return 1
	done
}
if flag local; then
	:
else
	:
fi
build() {
	javac src/$1.java -d bin
	java -cp bin $1
}
build App