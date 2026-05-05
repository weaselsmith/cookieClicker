import model.Game;

import java.util.concurrent.*;

public class AutoSaveScheduler {
    private static final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    private static final int INTERVAL = 30;
    private static final SaveManager sm = SaveManager.getInstance();

    public static void start(Game game) {
        // Runs save() every 30 seconds, with no initial delay
        scheduler.scheduleAtFixedRate(() -> sm.saveGame(game), 0, INTERVAL, TimeUnit.SECONDS);
    }

    public void stop() {
        scheduler.shutdown();
    }
}
