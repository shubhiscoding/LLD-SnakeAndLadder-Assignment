import java.util.List;

public class DefaultKillingStrategy implements KillingStrategy {
    public void applyStrategy(Player currentPlayer, List<Player> allPlayers) {
        // No killing - multiple players can occupy the same position
        // This is the default behavior where players don't interfere with each other
    }
}
