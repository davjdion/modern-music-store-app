#!/bin/bash

set -e

ROOT_DIR="$(cd "$(dirname "$0")" && pwd)"

echo "Cleaning backend build output..."
cd "$ROOT_DIR/backend"
mvn clean
cd "$ROOT_DIR"

echo ""
echo "Cleaning frontend build..."
rm -rf "$ROOT_DIR/frontend/build"

echo ""
echo "Cleaning frontend node_modules..."
rm -rf "$ROOT_DIR/frontend/node_modules"

echo ""
echo "Clean complete."
