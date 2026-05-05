import model.Game;
import model.PurchasedUpgrade;

public class SaveManager {
    private static SaveManager instance;
    private DatabaseManager db;

    private SaveManager(DatabaseManager db) {
        this.db = db;
    }

    public static void init(DatabaseManager db) {
        if (instance == null) instance = new SaveManager(db);
    }

    public static SaveManager getInstance() {
        if (instance == null) {
            throw new IllegalStateException
                    ("SaveManager not initialised");
        }
        return instance;
    }

    public void saveGame(Game toSave) {
        db.updateGame(toSave);
    }

    // all purchased upgrades should add new row to table
    public void savePurchase(PurchasedUpgrade toSave) {
        db.addPurchase(toSave.getPurchaseId(), toSave.getUpgradeId(), toSave.getName());
    }
}
