import java.util.*;

public class Play {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("🐍🪜 Welcome to Snake and Ladder Game! 🪜🐍");
        System.out.println("===========================================");
        
        // Get board size
        System.out.print("Enter board size (e.g., 10 for 10x10): ");
        int boardSize = scanner.nextInt();
        
        // Select difficulty
        System.out.println("\nSelect difficulty:");
        System.out.println("1. EASY (3 snakes, 5 ladders)");
        System.out.println("2. MEDIUM (5 snakes, 4 ladders)");
        System.out.println("3. HARD (7 snakes, 3 ladders)");
        System.out.print("Enter choice (1-3): ");
        int diffChoice = scanner.nextInt();
        
        Level level = Level.EASY;
        switch (diffChoice) {
            case 1: level = Level.EASY; break;
            case 2: level = Level.MEDIUM; break;
            case 3: level = Level.HARD; break;
            default: level = Level.EASY; break;
        }
        
        Difficulty difficulty = new Difficulty(level, boardSize);
        
        // Select winning strategy
        System.out.println("\nSelect winning strategy:");
        System.out.println("1. DEFAULT (win by reaching or exceeding final position)");
        System.out.println("2. EXACT (win only by landing exactly on final position)");
        System.out.print("Enter choice (1-2): ");
        int winChoice = scanner.nextInt();
        
        WinningStrategy winningStrategy = (winChoice == 2) ? 
            new ExactNumberWinningStrategy() : new DefaultWinningStrategy();
        
        // Select killing strategy
        System.out.println("\nSelect killing strategy:");
        System.out.println("1. DEFAULT (players can occupy same position)");
        System.out.println("2. START AGAIN (player landing on occupied position kills other player)");
        System.out.print("Enter choice (1-2): ");
        int killChoice = scanner.nextInt();
        
        KillingStrategy killingStrategy = (killChoice == 2) ? 
            new StartAgainKillingStrategy() : new DefaultKillingStrategy();
        
        // Get number of players
        System.out.print("\nEnter number of players (2-4): ");
        int numPlayers = Math.min(4, Math.max(2, scanner.nextInt()));
        scanner.nextLine(); // consume newline
        
        // Create players
        List<Player> players = new ArrayList<>();
        for (int i = 0; i < numPlayers; i++) {
            System.out.print("Enter name for Player " + (i + 1) + ": ");
            String name = scanner.nextLine();
            
            System.out.print("Is " + name + " a bot? (y/n): ");
            String isBot = scanner.nextLine();
            
            if (isBot.toLowerCase().startsWith("y")) {
                players.add(new BotPlayer(name));
            } else {
                players.add(new HumanPlayer(name));
            }
        }
        
        // Create board and game components
        Board board = new Board(boardSize);
        Dice dice = new SixSidedDice();
        
        // Setup snakes and ladders randomly
        System.out.println("\n📊 Game Configuration:");
        System.out.println("Board size: " + boardSize + "x" + boardSize + " (" + (boardSize * boardSize) + " cells)");
        System.out.println("Difficulty: " + difficulty.getLevel());
        System.out.println("Snakes: " + difficulty.getNumberOfSnakes());
        System.out.println("Ladders: " + difficulty.getNumberOfLadders());
        
        setupSnakesAndLadders(board, difficulty, boardSize);
        
        // Create and start game
        Game game = new Game(board, players, dice, winningStrategy, killingStrategy);
        
        System.out.println("\n🎮 Game setup complete! Starting game...");
        System.out.println("Press Enter to continue...");
        scanner.nextLine();
        
        game.playGame();
        
        scanner.close();
    }
    
    private static void setupSnakesAndLadders(Board board, Difficulty difficulty, int boardSize) {
        Random random = new Random();
        int totalCells = boardSize * boardSize;
        Set<Integer> usedPositions = new HashSet<>();
        
        // Add snakes
        for (int i = 0; i < difficulty.getNumberOfSnakes(); i++) {
            int start, end;
            do {
                start = random.nextInt(totalCells - 10) + 11; // Start from position 11 onwards
                end = random.nextInt(start - 1) + 1; // End before start position
            } while (usedPositions.contains(start) || usedPositions.contains(end) || 
                     start - end < 5); // Ensure minimum snake length
            
            board.addSnake(start, end);
            usedPositions.add(start);
            usedPositions.add(end);
            System.out.println("🐍 Snake placed: " + start + " → " + end);
        }
        
        // Add ladders
        for (int i = 0; i < difficulty.getNumberOfLadders(); i++) {
            int start, end;
            do {
                start = random.nextInt(totalCells - 20) + 2; // Start from position 2 onwards
                end = random.nextInt(totalCells - start) + start + 5; // End after start position
                if (end > totalCells) end = totalCells; // Don't exceed board
            } while (usedPositions.contains(start) || usedPositions.contains(end) || 
                     end - start < 5 || end > totalCells - 1); // Ensure minimum ladder length and don't end at final position
            
            board.addLadder(start, end);
            usedPositions.add(start);
            usedPositions.add(end);
            System.out.println("🪜 Ladder placed: " + start + " → " + end);
        }
    }
}