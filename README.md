# Snake and Ladder Game 🐍🪜

A Java implementation of the classic Snake and Ladder board game with customizable features and CLI interface.

## Class Diagram
<img width="1412" height="682" alt="image" src="https://github.com/user-attachments/assets/dbbb1860-0266-43f3-a426-a1088461f524" />

## Features

- **Customizable Board Size**: Choose your preferred board dimensions (e.g., 6x6, 10x10)
- **Multiple Difficulty Levels**:
  - Easy: 3 snakes, 5 ladders
  - Medium: 5 snakes, 4 ladders  
  - Hard: 7 snakes, 3 ladders
- **Winning Strategies**:
  - Default: Win by reaching or exceeding the final position
  - Exact: Win only by landing exactly on the final position
- **Player Types**: Support for both human and bot players
- **Visual Board Display**: See the current game state with player positions

## How to Run

### Prerequisites
- Java Development Kit (JDK) 8 or higher

### Steps

1. **Compile the game**:
   ```bash
   javac *.java
   ```

2. **Run the game**:
   ```bash
   java Play
   ```

3. **Follow the prompts**:
   - Enter board size (recommended: 6-10)
   - Select difficulty level (1-3)
   - Choose winning strategy (1-2)
   - Enter number of players (2-4)
   - For each player, enter name and choose human/bot

## How to Play

1. **Game Setup**: The game will randomly place snakes and ladders on the board based on your chosen difficulty.

2. **Turn System**: Players take turns rolling a dice (1-6) and moving their position.

3. **Special Squares**:
   - **Snakes (S)**: If you land on a snake's head, you slide down to its tail
   - **Ladders (L)**: If you land on a ladder's bottom, you climb up to its top

4. **Winning**: First player to reach the final position wins (based on chosen strategy)

## Game Display

The board shows:
- Numbers: Position on the board
- S: Snake head position
- L: Ladder bottom position
- (Player Initial): Current player positions

Example board:
```
36   35L  34   33   32   31   
25   26   27   28S  29   30   
24   23   22   21   20   19   
13   14   15L  16   17   18   
12   11   10   9    8    7    
1(A) 2    3    4    5    6    
```

## Game Rules

- Roll dice to move forward
- Land on snake head → slide down to tail
- Land on ladder bottom → climb up to top
- First to reach/exceed final position wins (or land exactly, depending on strategy)
- Multiple players can occupy the same square

## File Structure

- `Play.java` - Main game launcher
- `Game.java` - Game logic and controller
- `Board.java` - Board management and display
- `Player.java` - Abstract player class
- `HumanPlayer.java` / `BotPlayer.java` - Player implementations
- `Transport.java` - Interface for snakes and ladders
- `Snake.java` / `Ladder.java` - Transport implementations
- `Dice.java` - Dice interface
- `SixSidedDice.java` - Standard dice implementation
- `WinningStrategy.java` - Winning condition interface
- `Difficulty.java` - Game difficulty settings

## Quick Demo

For a quick test, try these settings:
- Board size: 6
- Difficulty: Easy
- Strategy: Default
- 2 players (one human, one bot)

Enjoy the game! 🎲
