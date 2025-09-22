import java.util.List;

public class StartAgainKillingStrategy implements KillingStrategy {
    public void applyStrategy(Player currentPlayer, List<Player> allPlayers) {
        int currentPosition = currentPlayer.getPosition();
        
        for (Player otherPlayer : allPlayers) {
            if (otherPlayer != currentPlayer && 
                otherPlayer.getPosition() == currentPosition && 
                currentPosition > 0) {
                
                System.out.println("💀 " + currentPlayer.getName() + " killed " + 
                                 otherPlayer.getName() + " at position " + currentPosition + "!");
                System.out.println("🔄 " + otherPlayer.getName() + " starts over from position 0");
                otherPlayer.setPosition(0);
            }
        }
    }
}
