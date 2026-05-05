package model;

public class User {
    private int id;
    private String name;
    private int curGameId;
    private long highScore;
    private int maxGrandmas;
    private int maxFactories;
    private int maxWizards;
    private String password;

    public User() {
    }

    public User(int id, String name, long highScore,
                int maxGrandmas, int maxFactories, int maxWizards) {
        this.id = id;
        this.name = name;
        this.highScore = highScore;
        this.maxGrandmas = maxGrandmas;
        this.maxFactories = maxFactories;
        this.maxWizards = maxWizards;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword(){
        return password;
    }
    public void setPassword(String password){
        this.password = password;
    }

    public long getHighScore() {
        return highScore;
    }

    public void setHighScore(long highScore) {
        this.highScore = highScore;
    }

    public int getMaxGrandmas() {
        return maxGrandmas;
    }

    public void setMaxGrandmas(int maxGrandmas) {
        this.maxGrandmas = maxGrandmas;
    }

    public int getMaxFactories() {
        return maxFactories;
    }

    public void setMaxFactories(int maxFactories) {
        this.maxFactories = maxFactories;
    }

    public int getMaxWizards() {
        return maxWizards;
    }

    public void setMaxWizards(int maxWizards) {
        this.maxWizards = maxWizards;
    }
}