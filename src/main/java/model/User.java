package model;

public class User {
    private int id;
    private String name;
    private int curGameId;
    private long highScore;
    private int maxGrandmas;
    private int maxFactories;
    private int maxWizards;
    private double money;

    public User() {
    }

    public User(int id, String name, int curGameId, long highScore,
                int maxGrandmas, int maxFactories, int maxWizards, double money) {
        this.id = id;
        this.name = name;
        this.curGameId = curGameId;
        this.highScore = highScore;
        this.maxGrandmas = maxGrandmas;
        this.maxFactories = maxFactories;
        this.maxWizards = maxWizards;
        this.money = money;
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

    public Integer getCurGameId() {
        return curGameId;
    }

    public void setCurGameId(Integer curGameId) {
        this.curGameId = curGameId;
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

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }
}