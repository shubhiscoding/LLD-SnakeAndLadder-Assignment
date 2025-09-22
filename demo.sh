#!/bin/bash
# Demo script to run Snake and Ladder game with predefined inputs

echo "Running Snake and Ladder Game Demo..."
echo "This will automatically input:"
echo "- Board size: 6"
echo "- Difficulty: Easy"
echo "- Winning strategy: Default" 
echo "- 2 players: Alice (human) and Bob (bot)"
echo ""

# Create input file with demo inputs
cat > demo_input.txt << EOF
6
1
1
2
Alice
n
Bob
y

EOF

# Run the game with the demo inputs
java Play < demo_input.txt

# Clean up
rm demo_input.txt

echo ""
echo "Demo completed!"
