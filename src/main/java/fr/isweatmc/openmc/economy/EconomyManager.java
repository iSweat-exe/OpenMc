package fr.isweatmc.openmc.economy;

import java.util.HashMap;
import java.util.UUID;

public class EconomyManager {
    private static final HashMap<UUID, Double> balances = new HashMap<>();

    public static double getBalance(UUID playerId) {
        return balances.getOrDefault(playerId, 0.0);
    }

    public static void addMoney(UUID playerId, double amount) {
        balances.put(playerId, getBalance(playerId) + amount);
    }

    public static boolean removeMoney(UUID playerId, double amount) {
        double currentBalance = getBalance(playerId);
        if (currentBalance >= amount) {
            balances.put(playerId, currentBalance - amount);
            return true;
        }
        return false;
    }

    public static void setBalance(UUID playerId, double amount) {
        balances.put(playerId, amount);
    }
}
