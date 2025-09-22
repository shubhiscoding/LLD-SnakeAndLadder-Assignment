import java.util.*;

public class Board {
    private int size;
    private Cell[][] grid;
    private Map<Integer, Transport> entities;
    
    public Board(int size) {
        this.size = size;
        this.entities = new HashMap<>();
        this.initializeBoard();
    }

    private void initializeBoard() {
        grid = new Cell[size][size];
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                grid[i][j] = new Cell();
            }
        }
    }
    
    public void addSnake(int start, int end) {
        Snake snake = new Snake(start, end);
        entities.put(start, snake);
        
        // Convert position to grid coordinates
        int[] coords = getCoordinates(start);
        grid[coords[0]][coords[1]].setTransport(snake);
    }
    
    public void addLadder(int start, int end) {
        Ladder ladder = new Ladder(start, end);
        entities.put(start, ladder);
        
        // Convert position to grid coordinates
        int[] coords = getCoordinates(start);
        grid[coords[0]][coords[1]].setTransport(ladder);
    }
    
    public Transport getTransport(int position) {
        return entities.get(position);
    }
    
    public int getSize() {
        return size;
    }
    
    public Cell getCell(int position) {
        int[] coords = getCoordinates(position);
        return grid[coords[0]][coords[1]];
    }
    
    private int[] getCoordinates(int position) {
        // Convert linear position to 2D coordinates
        // Assuming snake-like pattern starting from bottom-left
        int row = size - 1 - (position - 1) / size;
        int col;
        
        // Alternate direction for each row (snake pattern)
        if ((size - 1 - row) % 2 == 0) {
            col = (position - 1) % size;
        } else {
            col = size - 1 - ((position - 1) % size);
        }
        
        return new int[]{row, col};
    }
    
    public void printBoard(List<Player> players) {
        System.out.println("\n=== BOARD ===");
        
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int position = getPositionFromCoords(i, j);
                
                // Check if any player is at this position
                String playerMarker = "";
                for (Player player : players) {
                    if (player.getPosition() == position) {
                        playerMarker += player.getName().charAt(0);
                    }
                }
                
                // Check for snakes and ladders
                Transport transport = getTransport(position);
                String cellContent = String.format("%2d", position);
                
                if (transport != null) {
                    if (transport.getEntityType().equals("Snake")) {
                        cellContent += "S";
                    } else {
                        cellContent += "L";
                    }
                } else {
                    cellContent += " ";
                }
                
                if (!playerMarker.isEmpty()) {
                    cellContent += "(" + playerMarker + ")";
                } else {
                    cellContent += "   ";
                }
                
                System.out.print(cellContent + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
    
    private int getPositionFromCoords(int row, int col) {
        // Convert 2D coordinates back to linear position
        int boardRow = size - 1 - row;
        
        if (boardRow % 2 == 0) {
            return boardRow * size + col + 1;
        } else {
            return boardRow * size + (size - col);
        }
    }
}
