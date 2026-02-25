#!/bin/bash

# Exit immediately if a command exits with a non-zero status
set -e

# Define paths
FRONTEND_DIR="frontend"
BACKEND_STATIC_DIR="backend/src/main/resources/static"

# Navigate to the frontend directory
echo "Navigating to the frontend directory..."
cd "../$FRONTEND_DIR"

# Install dependencies
echo "Installing frontend dependencies..."
npm install

# Build the frontend
echo "Building the frontend..."
npm run build

# Navigate back to the root directory
cd ..

# Remove old static files from the backend
echo "Cleaning up old static files in the backend..."
rm -rf "$BACKEND_STATIC_DIR"/*

# Copy the new build files to the backend static directory
echo "Copying new build files to the backend static directory..."
cp -r "$FRONTEND_DIR/build/"* "$BACKEND_STATIC_DIR/"

echo "Frontend build and copy process completed successfully."
