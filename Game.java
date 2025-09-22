import java.util.*;

public class Game {
    private Board board;
    private List<Player> players;
    private Dice dice;
    private WinningStrategy winningStrategy;
    private KillingStrategy killingStrategy;
    private Player currentPlayer;
    private int currentPlayerIndex;
    private boolean gameOver;
    
    public Game(Board board, List<Player> players, Dice dice, 
                WinningStrategy winningStrategy, KillingStrategy killingStrategy) {
        this.board = board;
        this.players = players;
        this.dice = dice;
        this.winningStrategy = winningStrategy;
        this.killingStrategy = killingStrategy;
        this.currentPlayerIndex = 0;
        this.currentPlayer = players.get(0);
        this.gameOver = false;
    }
    
    public void playGame() {
        System.out.println("🎲 Starting Snake and Ladder Game! 🎲");
        System.out.println("Players: ");
        for (Player player : players) {
            System.out.println("- " + player.getName());
        }
        System.out.println("Board size: " + board.getSize() + "x" + board.getSize());
        System.out.println("Goal: Reach position " + (board.getSize() * board.getSize()));
        
        while (!gameOver) {
            playTurn();
            board.printBoard(players);
            
            if (currentPlayer.hasWon()) {
                System.out.println("🎉 " + currentPlayer.getName() + " wins! 🎉");
                gameOver = true;
            } else {
                nextPlayer();
            }
            
            // Pause for better readability
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
    
    private void playTurn() {
        System.out.println("\n" + currentPlayer.getName() + "'s turn (Current position: " + 
                          currentPlayer.getPosition() + ")");
        
        int diceRoll = dice.roll();
        System.out.println("🎲 Rolled: " + diceRoll);
        
        int newPosition = currentPlayer.getPosition() + diceRoll;
        int boardSize = board.getSize();
        
        // Check if move is valid (not exceeding board size for exact win strategy)
        if (winningStrategy instanceof ExactNumberWinningStrategy && 
            newPosition > boardSize * boardSize) {
            System.out.println("❌ Cannot move! Need exact number to win.");
            return;
        }
        
        currentPlayer.setPosition(newPosition);
        System.out.println("→ Moved to position: " + newPosition);
        
        // Check for snakes and ladders
        Transport transport = board.getTransport(newPosition);
        if (transport != null) {
            if (transport.getEntityType().equals("Snake")) {
                System.out.println("🐍 Oh no! Bitten by a snake!");
                System.out.println("↓ Sliding down from " + transport.getStart() + 
                                 " to " + transport.getEnd());
                currentPlayer.setPosition(transport.getEnd());
            } else if (transport.getEntityType().equals("Ladder")) {
                System.out.println("🪜 Great! Found a ladder!");
                System.out.println("↑ Climbing up from " + transport.getStart() + 
                                 " to " + transport.getEnd());
                currentPlayer.setPosition(transport.getEnd());
            }
        }
        
        // Check winning condition
        if (winningStrategy.hasWon(currentPlayer.getPosition(), boardSize)) {
            currentPlayer.setHasWon(true);
        }
    }
    
    private void nextPlayer() {
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
        currentPlayer = players.get(currentPlayerIndex);
    }
    
    public Player getCurrentPlayer() {
        return currentPlayer;
    }
    
    public int getCurrentPlayerIndex() {
        return currentPlayerIndex;
    }
    
    public boolean isGameOver() {
        return gameOver;
    }
}
