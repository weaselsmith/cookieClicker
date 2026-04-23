package model;

public class PurchasedUpgrade {
    private int purchaseId;
    private int userId;
    private int gameId;
    private int upgradeId;
    private String name;

    public PurchasedUpgrade() {
    }

    public PurchasedUpgrade(int purchaseId, int userId, int gameId, int upgradeId, String name) {
        this.purchaseId = purchaseId;
        this.userId = userId;
        this.gameId = gameId;
        this.upgradeId = upgradeId;
        this.name = name;
    }

    public int getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(int purchaseId) {
        this.purchaseId = purchaseId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public int getUpgradeId() {
        return upgradeId;
    }

    public void setUpgradeId(int upgradeId) {
        this.upgradeId = upgradeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}