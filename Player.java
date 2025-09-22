public abstract class Player {
    protected String name;
    protected int position;
    protected boolean hasWon;
    
    public Player(String name) {
        this.name = name;
        this.position = 0;
        this.hasWon = false;
    }
    
    public String getName() {
        return name;
    }
    
    public int getPosition() {
        return position;
    }
    
    public void setPosition(int position) {
        this.position = position;
    }
    
    public boolean hasWon() {
        return hasWon;
    }
    
    public void setHasWon(boolean hasWon) {
        this.hasWon = hasWon;
    }
}
