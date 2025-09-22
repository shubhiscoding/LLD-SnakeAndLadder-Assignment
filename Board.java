public class Board {
    private int size;
    Cell[][] grid;
    Board(int size){
        this.size = size;
        this.initializeBoard();
    }

    private void initializeBoard(){
        grid = new Cell[size][size];
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                grid[i][j] = new Cell();
            }
        }
    }
}
