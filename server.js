const express = require('express');
const https = require('https');
const http = require('http');
const { exec } = require('child_process');
const cors = require('cors');
const path = require('path');
const fs = require('fs').promises;
const fsSync = require('fs');

const app = express();
const PORT = 3000;
const HTTPS_PORT = 3443;

// Middleware
app.use(cors());
app.use(express.json());

// API Routes (must be before static middleware)
// Get source code endpoint
app.get('/source/:pattern/:className', async (req, res) => {
    const { pattern, className } = req.params;
    const filePath = path.join(__dirname, 'src', 'main', 'java', 'com', 'leetcode', 'patterns', pattern, `${className}.java`);

    console.log(`Fetching source: ${filePath}`);

    try {
        const sourceCode = await fs.readFile(filePath, 'utf-8');
        res.json({
            success: true,
            sourceCode: sourceCode,
            filePath: filePath
        });
    } catch (error) {
        console.error(`Error reading file: ${error.message}`);
        res.status(404).json({
            success: false,
            error: 'Source file not found',
            filePath: filePath
        });
    }
});

// Execute Java code endpoint
app.post('/execute', (req, res) => {
    const { className } = req.body;

    if (!className) {
        return res.status(400).json({
            success: false,
            error: 'No className provided'
        });
    }

    // Build the Java command using classpath
    const command = `cd /Users/praveenramisetty/Desktop/leetcode-patterns-java && java -cp target/classes ${className}`;

    console.log(`Executing: ${command}`);

    exec(command, {
        maxBuffer: 1024 * 1024 * 10, // 10MB buffer
        timeout: 30000 // 30 second timeout
    }, (error, stdout, stderr) => {
        if (error) {
            console.error(`Error: ${error.message}`);
            return res.json({
                success: false,
                error: stderr || error.message,
                output: stdout
            });
        }

        if (stderr && !stdout) {
            console.error(`Stderr: ${stderr}`);
            return res.json({
                success: false,
                error: stderr,
                output: ''
            });
        }

        console.log('Execution successful');
        res.json({
            success: true,
            output: stdout,
            error: stderr || ''
        });
    });
});

// Static files middleware (after API routes)
app.use(express.static(__dirname));

// Serve the HTML page
app.get('/', (req, res) => {
    res.sendFile(path.join(__dirname, 'index.html'));
});

// Load SSL certificates
let httpsServer;
try {
    const privateKey = fsSync.readFileSync(path.join(__dirname, 'key.pem'), 'utf8');
    const certificate = fsSync.readFileSync(path.join(__dirname, 'cert.pem'), 'utf8');
    const credentials = { key: privateKey, cert: certificate };

    // Create HTTPS server
    httpsServer = https.createServer(credentials, app);
    httpsServer.listen(HTTPS_PORT, () => {
        console.log(`🔒 HTTPS Server running at https://localhost:${HTTPS_PORT}`);
    });
} catch (error) {
    console.warn('⚠️  HTTPS certificates not found. HTTPS server not started.');
    console.warn('   Run: openssl req -x509 -newkey rsa:4096 -keyout key.pem -out cert.pem -days 365 -nodes -subj "/C=US/ST=State/L=City/O=Organization/CN=localhost"');
}

// Create HTTP server (for backward compatibility)
const httpServer = http.createServer(app);
httpServer.listen(PORT, () => {
    console.log(`🚀 HTTP Server running at http://localhost:${PORT}`);
    console.log(`📂 Serving files from: ${__dirname}`);
    if (httpsServer) {
        console.log(`\n✨ Open https://localhost:${HTTPS_PORT} in your browser to execute Java code (HTTPS)`);
        console.log(`   Or http://localhost:${PORT} for HTTP\n`);
    } else {
        console.log(`\n✨ Open http://localhost:${PORT} in your browser to execute Java code\n`);
    }
});

// Made with Bob
