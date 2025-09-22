public class Difficulty {
    private int numberOfSnakes;
    private int numberOfLadders;
    private Level level;
    private int boardSize;
    
    public Difficulty(Level level, int boardSize) {
        this.level = level;
        this.boardSize = boardSize;
        setDifficultyParams();
    }
    
    private void setDifficultyParams() {
        int totalCells = boardSize * boardSize;
        
        // Calculate base numbers based on board size
        // For a 10x10 board (100 cells), we want reasonable numbers
        // Scale proportionally for other sizes
        double scaleFactor = totalCells / 100.0; // 100 is our reference (10x10)
        
        switch (level) {
            case EASY:
                numberOfSnakes = Math.max(1, (int)(3 * scaleFactor));
                numberOfLadders = Math.max(1, (int)(5 * scaleFactor));
                break;
            case MEDIUM:
                numberOfSnakes = Math.max(1, (int)(5 * scaleFactor));
                numberOfLadders = Math.max(1, (int)(4 * scaleFactor));
                break;
            case HARD:
                numberOfSnakes = Math.max(1, (int)(7 * scaleFactor));
                numberOfLadders = Math.max(1, (int)(3 * scaleFactor));
                break;
        }
        
        // Ensure we don't exceed reasonable limits for small boards
        int maxEntities = Math.max(1, totalCells / 8); // At most 1/8 of cells have entities
        numberOfSnakes = Math.min(numberOfSnakes, maxEntities / 2);
        numberOfLadders = Math.min(numberOfLadders, maxEntities / 2);
        
        // Ensure minimum of 1 for very small boards
        numberOfSnakes = Math.max(1, numberOfSnakes);
        numberOfLadders = Math.max(1, numberOfLadders);
    }
    
    public int getNumberOfSnakes() {
        return numberOfSnakes;
    }
    
    public int getNumberOfLadders() {
        return numberOfLadders;
    }
    
    public Level getLevel() {
        return level;
    }
    
    public int getBoardSize() {
        return boardSize;
    }
}