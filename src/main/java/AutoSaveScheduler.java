import model.Game;

import java.util.concurrent.*;

public class AutoSaveScheduler {
    private static final ScheduledExecutorService scheduler =
            Executors.newSingleThreadScheduledExecutor();

    private static final int INTERVAL = 30;
    private static final SaveManager sm = SaveManager.getInstance();

    private static ScheduledFuture<?> task;

    public static synchronized void start(Game game) {
        // If already running, stop the existing task first
        if (task != null && !task.isCancelled()) {
            task.cancel(false);
        }

        task = scheduler.scheduleAtFixedRate(
                () -> sm.saveGame(game),
                0,
                INTERVAL,
                TimeUnit.SECONDS
        );
    }

    public static synchronized void stop() {
        if (task != null) {
            task.cancel(false);
            task = null;
        }
    }

    public static void shutdown() {
        scheduler.shutdown(); // call this only when the app exits
    }
}
