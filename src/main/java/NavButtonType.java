public enum NavButtonType {
    MENU("menu", SceneType.MENU),
    FILE("game", SceneType.FILE), //edited by Daniel
    STORE("store", SceneType.STORE),
    STATS("stats", SceneType.STATS),
    LOGIN("log out", SceneType.LOGIN);

    private final String label;
    private final SceneType target;

    NavButtonType(String label, SceneType target) {
        this.label = label;
        this.target = target;
    }

    public String getLabel() {
        return label;
    }

    public SceneType getTarget() {
        return target;
    }
}

