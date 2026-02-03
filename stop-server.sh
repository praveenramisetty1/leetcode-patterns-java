#!/bin/bash

# LeetCode Patterns Java - Stop Server Script
# This script stops the web server by killing processes on ports 3000 and 3443

echo "🛑 LeetCode Patterns Java - Stopping Web Server"
echo "================================================"
echo ""

# Find and kill processes on port 3000
PIDS_3000=$(lsof -ti :3000 2>/dev/null || true)
if [ ! -z "$PIDS_3000" ]; then
    echo "🔍 Found process on port 3000 (PID: $PIDS_3000)"
    kill -9 $PIDS_3000 2>/dev/null || true
    echo "✅ Killed process on port 3000"
else
    echo "ℹ️  No process found on port 3000"
fi

echo ""

# Find and kill processes on port 3443
PIDS_3443=$(lsof -ti :3443 2>/dev/null || true)
if [ ! -z "$PIDS_3443" ]; then
    echo "🔍 Found process on port 3443 (PID: $PIDS_3443)"
    kill -9 $PIDS_3443 2>/dev/null || true
    echo "✅ Killed process on port 3443"
else
    echo "ℹ️  No process found on port 3443"
fi

echo ""
echo "================================================"
echo "✅ Server stopped successfully"
echo ""

# Made with Bob
