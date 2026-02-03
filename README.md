# LeetCode Patterns with Java 17/21 Examples

A comprehensive collection of common LeetCode problem-solving patterns implemented in Java 17/21 with modern features.

## 📚 Patterns Covered

### 1. Two Pointers
**When to use:** Problems involving sorted arrays, palindromes, or pair finding.

**Key Characteristics:**
- Two pointers moving towards each other or in the same direction
- Often used with sorted arrays
- Time complexity: O(n), Space complexity: O(1)

**Common Problems:**
- Two Sum II (sorted array)
- Container With Most Water
- Valid Palindrome
- Remove Duplicates from Sorted Array

### 2. Sliding Window
**When to use:** Problems involving contiguous subarrays or substrings.

**Key Characteristics:**
- Maintain a window that slides through the array/string
- Track window state (sum, count, frequency map)
- Time complexity: O(n), Space complexity: O(k) where k is window size

**Common Problems:**
- Maximum Sum Subarray of Size K
- Longest Substring Without Repeating Characters
- Minimum Window Substring
- Permutation in String

### 3. Fast & Slow Pointers (Floyd's Cycle Detection)
**When to use:** Linked list cycle detection, finding middle element.

**Key Characteristics:**
- Two pointers moving at different speeds
- Useful for cycle detection
- Time complexity: O(n), Space complexity: O(1)

**Common Problems:**
- Linked List Cycle
- Find Middle of Linked List
- Happy Number
- Palindrome Linked List

### 4. Binary Search
**When to use:** Searching in sorted arrays, finding boundaries.

**Key Characteristics:**
- Divide and conquer approach
- Works on sorted or rotated sorted arrays
- Time complexity: O(log n), Space complexity: O(1)

**Common Problems:**
- Binary Search
- Search in Rotated Sorted Array
- Find First and Last Position
- Search Insert Position

### 5. Top K Elements
**When to use:** Finding k largest/smallest elements.

**Key Characteristics:**
- Use heap (PriorityQueue) or QuickSelect
- Time complexity: O(n log k) with heap, O(n) average with QuickSelect
- Space complexity: O(k)

**Common Problems:**
- Kth Largest Element
- Top K Frequent Elements
- K Closest Points to Origin
- Find K Pairs with Smallest Sums

### 6. Tree BFS/DFS
**When to use:** Tree/graph traversal problems.

**Key Characteristics:**
- BFS: Level-order traversal using queue
- DFS: Pre/In/Post-order using recursion or stack
- Time complexity: O(n), Space complexity: O(h) for DFS, O(w) for BFS

**Common Problems:**
- Binary Tree Level Order Traversal
- Maximum Depth of Binary Tree
- Validate Binary Search Tree
- Path Sum

### 7. Dynamic Programming
**When to use:** Optimization problems with overlapping subproblems.

**Key Characteristics:**
- Break problem into smaller subproblems
- Store results to avoid recomputation
- Bottom-up (tabulation) or top-down (memoization)

**Common Problems:**
- Climbing Stairs
- House Robber
- Longest Common Subsequence
- Coin Change

## 🚀 Getting Started

### Prerequisites
- Java 17 or Java 21
- Maven 3.8+
- Node.js 16+ (for web interface)
- npm (comes with Node.js)

### Quick Start - Web Interface (Recommended)

#### Option 1: Using Scripts (Easiest)
```bash
# Make scripts executable (first time only)
chmod +x start-server.sh stop-server.sh

# Start the server
./start-server.sh

# Stop the server (in another terminal)
./stop-server.sh
```

#### Option 2: Manual Setup
```bash
# 1. Install Node.js dependencies
npm install

# 2. Compile Java code
mvn clean compile

# 3. Start the server
npm start
```

#### Access the Application
Open your browser to:
- **HTTP**: http://localhost:3000
- **HTTPS**: https://localhost:3443 (requires SSL certificates)

#### Using the Web Interface
1. Select a pattern from the dropdown (e.g., "Two Sum II")
2. View the problem description and visual explanation
3. Click "🚀 Execute Java Code"
4. See the output in the terminal below

### Alternative: Command Line Execution

```bash
# Build the project
mvn clean install

# Run tests
mvn test

# Run a specific pattern example
mvn exec:java -Dexec.mainClass="com.leetcode.patterns.twopointers.TwoSumII"

# Or use java directly
java -cp target/classes com.leetcode.patterns.twopointers.TwoSumII
```

### Development Mode
```bash
# Auto-restart server on file changes
npm run dev
```

### Troubleshooting

#### Port Already in Use
```bash
# Use the stop script
./stop-server.sh

# Or manually check and kill processes
lsof -i :3000 -i :3443 | grep LISTEN
kill <PID>
```

#### Java Compilation Errors
```bash
# Check Java version (needs 17+)
java -version

# Clean and rebuild
mvn clean compile -X
```

#### Missing Dependencies
```bash
# Reinstall Node.js dependencies
rm -rf node_modules package-lock.json
npm install

# Rebuild Java
mvn clean install
```

### Optional: SSL Certificates for HTTPS
```bash
openssl req -x509 -newkey rsa:4096 -keyout key.pem -out cert.pem -days 365 -nodes \
  -subj "/C=US/ST=State/L=City/O=Organization/CN=localhost"
```

## 📁 Project Structure
```
leetcode-patterns-java/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/
│   │           └── leetcode/
│   │               └── patterns/
│   │                   ├── twopointers/
│   │                   ├── slidingwindow/
│   │                   ├── fastslowpointers/
│   │                   ├── binarysearch/
│   │                   ├── topkelements/
│   │                   ├── tree/
│   │                   └── dynamicprogramming/
│   └── test/
│       └── java/
│           └── com/
│               └── leetcode/
│                   └── patterns/
├── pom.xml
└── README.md
```

## 🎯 Java 17/21 Features Used

- **Records**: Immutable data carriers for problem inputs/outputs
- **Pattern Matching**: Enhanced switch expressions and instanceof
- **Text Blocks**: Multi-line string literals for test cases
- **Sealed Classes**: Restricted class hierarchies for tree nodes
- **Virtual Threads** (Java 21): For concurrent problem solving
- **Sequenced Collections** (Java 21): Improved collection APIs

## 📖 Learning Resources

- [LeetCode Patterns](https://seanprashad.com/leetcode-patterns/)
- [14 Patterns to Ace Any Coding Interview](https://hackernoon.com/14-patterns-to-ace-any-coding-interview-question-c5bb3357f6ed)
- [Java 17 Features](https://openjdk.org/projects/jdk/17/)
- [Java 21 Features](https://openjdk.org/projects/jdk/21/)

## 🤝 Contributing

Feel free to add more patterns or improve existing implementations!

## 📝 License

MIT License - feel free to use this for learning and practice.