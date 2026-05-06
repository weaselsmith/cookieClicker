public enum NavButtonType {
    FILE("load", SceneType.FILE),
    COOKIE("click", SceneType.COOKIE),
    STORE("shop", SceneType.STORE),
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

