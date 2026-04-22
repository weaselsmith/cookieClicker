package model;

public abstract class CookieMaker {
    private String name;
    private int lvl;
    private long num;
    private long priceForNext;
    private int cookieRate;
    private long cps;  // cps = cookies for second

    public CookieMaker() {
        this.lvl = 1;
        this.num = 0;
    }

    public void addMaker() {
        this.num++;
    }

    public void levelUp() {
        this.lvl++;
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

    public long getNum() {
        return num;
    }

    public long getPriceForNext() {
        return priceForNext;
    }

    public long getCps() {
        return cps;
    }
}
