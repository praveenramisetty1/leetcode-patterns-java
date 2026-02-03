#!/bin/bash

# LeetCode Patterns Java - Start Server Script
# This script installs dependencies, compiles Java code, and starts the web server

set -e  # Exit on error

echo "🚀 LeetCode Patterns Java - Starting Web Server"
echo "================================================"
echo ""

# Check if Node.js is installed
if ! command -v node &> /dev/null; then
    echo "❌ Error: Node.js is not installed"
    echo "   Please install Node.js from https://nodejs.org/"
    exit 1
fi

# Check if Maven is installed
if ! command -v mvn &> /dev/null; then
    echo "❌ Error: Maven is not installed"
    echo "   Please install Maven from https://maven.apache.org/"
    exit 1
fi

# Check if Java is installed
if ! command -v java &> /dev/null; then
    echo "❌ Error: Java is not installed"
    echo "   Please install Java 17+ from https://adoptium.net/"
    exit 1
fi

echo "✅ Prerequisites check passed"
echo ""

# Install Node.js dependencies if node_modules doesn't exist
if [ ! -d "node_modules" ]; then
    echo "📦 Installing Node.js dependencies..."
    npm install
    echo "✅ Node.js dependencies installed"
    echo ""
else
    echo "✅ Node.js dependencies already installed"
    echo ""
fi

# Compile Java code if target/classes doesn't exist
if [ ! -d "target/classes" ]; then
    echo "☕ Compiling Java code..."
    mvn clean compile
    echo "✅ Java code compiled"
    echo ""
else
    echo "✅ Java code already compiled"
    echo ""
fi

# Kill any existing processes on ports 3000 and 3443
echo "🔍 Checking for port conflicts..."
PIDS_3000=$(lsof -ti :3000 2>/dev/null || true)
PIDS_3443=$(lsof -ti :3443 2>/dev/null || true)

if [ ! -z "$PIDS_3000" ]; then
    echo "   Killing process on port 3000 (PID: $PIDS_3000)"
    kill -9 $PIDS_3000 2>/dev/null || true
fi

if [ ! -z "$PIDS_3443" ]; then
    echo "   Killing process on port 3443 (PID: $PIDS_3443)"
    kill -9 $PIDS_3443 2>/dev/null || true
fi

echo "✅ Ports are clear"
echo ""

# Start the server
echo "🚀 Starting web server..."
echo "================================================"
echo ""
echo "   HTTP:  http://localhost:3000"
echo "   HTTPS: https://localhost:3443 (if SSL certs exist)"
echo ""
echo "   Press Ctrl+C to stop the server"
echo ""
echo "================================================"
echo ""

npm start

# Made with Bob
