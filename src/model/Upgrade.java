public class Upgrade {
    private int upgradeId;
    private String name;
    private int cost;

    public Upgrade() {
    }

    public Upgrade(int upgradeId, String name, int cost) {
        this.upgradeId = upgradeId;
        this.name = name;
        this.cost = cost;
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

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}