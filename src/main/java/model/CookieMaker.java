package model;

public abstract class CookieMaker {
    private String name;
    private int lvl;
    private long num;
    private long priceForNext;
    private long cookieRate;
    private long cps;  // cps = cookies for second

    public CookieMaker() {
        this.lvl = 1;
        this.num = 0;
    }

    public void addMaker() {
        this.num++;
    }

    private void updateCPS() {
        cps = cookieRate * lvl * num;
    }

    private void updatePrice() {
        priceForNext = (long) Math.floor(priceForNext * 1.5);
    }

    public String getName() {
        return name;
    }

    public int getLvl() {
        return lvl;
    }

    public int getNum() {
        return num;
    }

    public long getPriceForNext() {
        return priceForNext;
    }

    public int getCps() {
        return cps;
    }
}
