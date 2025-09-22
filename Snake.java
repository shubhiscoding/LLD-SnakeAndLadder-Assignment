public class Snake implements Transport {
    private int start;
    private int end;
    
    public Snake(int start, int end) {
        this.start = start;
        this.end = end;
    }
    
    @Override
    public int getStart() {
        return start;
    }
    
    @Override
    public int getEnd() {
        return end;
    }
    
    @Override
    public String getEntityType() {
        return "Snake";
    }
}
