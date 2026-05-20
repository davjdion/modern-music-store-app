#!/bin/bash

# Exit immediately if a command exits with a non-zero status
set -e

ROOT_DIR="$(cd "$(dirname "$0")" && pwd)"

# ─── Colors ───
RED='\033[0;31m'
GREEN='\033[0;32m'
NC='\033[0m' # No Color

ok()   { echo -e "${GREEN}✔ $1${NC}"; }
fail() { echo -e "${RED}✘ $1${NC}"; exit 1; }

# ─── Prerequisites ───
echo "Checking prerequisites..."

# npm
command -v npm >/dev/null 2>&1 || fail "npm is not installed. Please install Node.js / npm."
ok "npm $(npm --version)"

# Java 17 – try to locate it and set JAVA_HOME
JAVA17_HOME=$(/usr/libexec/java_home -v 17 2>/dev/null || true)
if [ -z "$JAVA17_HOME" ]; then
    # fallback: check sdkman candidates
    for d in "$HOME/.sdkman/candidates/java"/17.*; do
        [ -d "$d" ] && JAVA17_HOME="$d" && break
    done
fi
if [ -n "$JAVA17_HOME" ]; then
    export JAVA_HOME="$JAVA17_HOME"
    export PATH="$JAVA_HOME/bin:$PATH"
    ok "JAVA_HOME set to $JAVA_HOME"
else
    fail "Java 17 not found. Please install Java 17."
fi
JAVA_VERSION=$(java -version 2>&1 | head -1 | sed 's/.*"\([0-9]*\)\..*/\1/')
[ "$JAVA_VERSION" -ge 17 ] 2>/dev/null || fail "Java 17+ is required (found version $JAVA_VERSION)"
ok "java $(java -version 2>&1 | head -1)"

# Maven (prefer system mvn over the wrapper)
if command -v mvn >/dev/null 2>&1; then
    MVN="mvn"
    ok "mvn $(mvn --version | head -1)"
elif [ -x "$ROOT_DIR/backend/mvnw" ]; then
    MVN="$ROOT_DIR/backend/mvnw"
    ok "Using Maven wrapper (mvnw)"
else
    fail "Neither mvn nor mvnw found. Please install Maven."
fi

echo ""

# ─── 1. Build Frontend ───
echo "═══════════════════════════════════════"
echo " Building Frontend"
echo "═══════════════════════════════════════"
cd "$ROOT_DIR/backend"
sh build_frontend.sh

echo ""

# ─── 2. Build Backend ───
echo "═══════════════════════════════════════"
echo " Building Backend"
echo "═══════════════════════════════════════"
cd "$ROOT_DIR/backend"
$MVN clean install

echo ""

# ─── 3. Run Server ───
echo "═══════════════════════════════════════"
echo " Starting Server"
echo "═══════════════════════════════════════"
cd "$ROOT_DIR/backend"
$MVN spring-boot:run
