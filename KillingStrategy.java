import java.util.List;

public interface KillingStrategy {
    // Method to handle actual killing logic
    default void applyStrategy(Player currentPlayer, List<Player> allPlayers) {
        // Default implementation does nothing
    }
}
