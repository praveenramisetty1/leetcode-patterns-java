# 🚀 LeetCode Patterns Java Web Executor

A beautiful web interface to execute and test all LeetCode pattern implementations in this project.

## 📋 Features

- **Interactive UI**: Select any pattern from a dropdown menu
- **Real-time Execution**: Execute Java code and see results instantly
- **Pattern Information**: View problem descriptions, difficulty, and complexity
- **Beautiful Design**: Modern, responsive interface with gradient styling
- **All Patterns Included**:
  - Two Pointers (3 problems)
  - Sliding Window (2 problems)
  - Fast & Slow Pointers (2 problems)
  - Binary Search (2 problems)
  - Top K Elements (2 problems)
  - Tree BFS/DFS (2 problems)
  - Dynamic Programming (2 problems)

## 🛠️ Setup Instructions

### Prerequisites

1. **Java 17 or higher** - Already installed for this project
2. **Maven** - Already configured in this project
3. **Node.js** - Required for the web server
   - Download from: https://nodejs.org/
   - Recommended: LTS version (v18 or v20)

### Installation Steps

1. **Install Node.js dependencies**:
   ```bash
   npm install
   ```

2. **Build the Java project** (if not already built):
   ```bash
   mvn clean compile
   ```

3. **Start the web server**:
   ```bash
   npm start
   ```

4. **Open your browser**:
   - Navigate to: http://localhost:3000
   - The web interface will load automatically

## 🎯 How to Use

1. **Select a Pattern**: Choose any pattern from the dropdown menu
2. **View Information**: See the problem description, difficulty, and complexity
3. **Execute Code**: Click the "🚀 Execute Java Code" button
4. **View Results**: See the output in the console-style output section

## 📁 Files Created

- `index.html` - Single-page web interface with embedded CSS and JavaScript
- `server.js` - Node.js Express server to execute Java commands
- `package.json` - Node.js dependencies configuration
- `WEB_EXECUTOR_README.md` - This file

## 🔧 Technical Details

### Frontend (index.html)
- Pure HTML, CSS, and JavaScript (no frameworks)
- Responsive design with gradient styling
- Real-time command execution via REST API
- Console-style output display

### Backend (server.js)
- Express.js server on port 3000
- CORS enabled for cross-origin requests
- Executes Maven commands to run Java classes
- Returns stdout/stderr to the frontend

### Execution Flow
1. User selects a pattern in the browser
2. Frontend sends POST request to `/execute` endpoint
3. Backend executes: `mvn exec:java -Dexec.mainClass="..."`
4. Java code runs and produces output
5. Output is sent back to frontend and displayed

## 🎨 Patterns Available

### Two Pointers
- ✅ Two Sum II - Input Array Is Sorted (Medium)
- ✅ Valid Palindrome (Easy)
- ✅ Container With Most Water (Medium)

### Sliding Window
- ✅ Maximum Sum Subarray of Size K (Easy)
- ✅ Longest Substring Without Repeating Characters (Medium)

### Fast & Slow Pointers
- ✅ Linked List Cycle (Easy)
- ✅ Middle of Linked List (Easy)

### Binary Search
- ✅ Binary Search (Easy)
- ✅ Search in Rotated Sorted Array (Medium)

### Top K Elements
- ✅ Kth Largest Element in an Array (Medium)
- ✅ Top K Frequent Elements (Medium)

### Tree BFS/DFS
- ✅ Maximum Depth of Binary Tree (Easy)
- ✅ Binary Tree Level Order Traversal (Medium)

### Dynamic Programming
- ✅ Climbing Stairs (Easy)
- ✅ House Robber (Medium)

## 🐛 Troubleshooting

### Server won't start
- Make sure Node.js is installed: `node --version`
- Install dependencies: `npm install`
- Check if port 3000 is available

### Java execution fails
- Ensure Java 17+ is installed: `java --version`
- Build the project first: `mvn clean compile`
- Check Maven is installed: `mvn --version`

### No output displayed
- Check browser console for errors (F12)
- Verify the server is running on port 3000
- Check server logs in the terminal

## 🚀 Alternative: Direct Execution

If you prefer not to use the web interface, you can execute patterns directly:

```bash
# Execute any pattern directly with Maven
mvn exec:java -Dexec.mainClass="com.leetcode.patterns.twopointers.TwoSumII"
mvn exec:java -Dexec.mainClass="com.leetcode.patterns.slidingwindow.MaximumSumSubarray"
mvn exec:java -Dexec.mainClass="com.leetcode.patterns.binarysearch.BinarySearch"
# ... and so on
```

## 📝 Notes

- The web interface executes the `main()` method of each Java class
- Each pattern includes example test cases with expected outputs
- Execution timeout is set to 30 seconds
- Output buffer is limited to 10MB

## 🎓 Learning Tips

1. **Start with Easy problems** to understand the pattern
2. **Read the problem description** before viewing the output
3. **Try to predict the output** before executing
4. **Examine the code** in the Java files to understand the implementation
5. **Modify test cases** in the Java files to experiment

## 📚 Additional Resources

- Main README: See `README.md` for pattern explanations
- Java Source Code: Located in `src/main/java/com/leetcode/patterns/`
- LeetCode: https://leetcode.com/

---

**Made with ☕ and ❤️**