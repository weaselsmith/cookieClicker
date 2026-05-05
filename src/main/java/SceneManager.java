import javafx.scene.Scene;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.Map;
import java.util.EnumMap;

public class SceneManager {
    private static SceneManager instance;
    private DatabaseManager db;
    private Context context;
    private final Stage stage;
    private final Map<SceneType, Scene> cache = new EnumMap<>(SceneType.class);

    private GameDriver currentGame;

    private SceneManager(Stage stage, DatabaseManager db, Context context) {
        this.db = db;
        this.stage = stage;
        this.context = context;
    }

    public static void init(Stage stage, DatabaseManager db, Context context) {
        if (instance == null) instance = new SceneManager(stage, db, context);
    }

    public static SceneManager getInstance() {
        if (instance == null) {
            throw new IllegalStateException
                    ("SceneManager not initialised");
        }
        return instance;
    }

    public void navigateTo(SceneType type) {
        Scene scene = cache.computeIfAbsent(type,
                t -> SceneFactory.create(t, stage, db, context));
        stage.setScene(scene);
    }

}