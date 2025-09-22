public class Difficulty {
    private int numberOfSnakes;
    private int numberOfLadders;
    private Level level;
    
    public Difficulty(Level level) {
        this.level = level;
        setDifficultyParams();
    }
    
    private void setDifficultyParams() {
        switch (level) {
            case EASY:
                numberOfSnakes = 3;
                numberOfLadders = 5;
                break;
            case MEDIUM:
                numberOfSnakes = 5;
                numberOfLadders = 4;
                break;
            case HARD:
                numberOfSnakes = 7;
                numberOfLadders = 3;
                break;
        }
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
}