package makers;

public abstract class CookieMaker {
    protected String name;
    protected int lvl;
    protected long num;
    protected long priceForNext;
    protected int cookieRate;
    protected long cps;  // cps = cookies for second

    public CookieMaker() {
        this.lvl = 1;
        this.num = 0;
    }

    public CookieMaker(int lvl, long num) {
        this.lvl = lvl;
        this.num = num;
    }

    public void add() {
        this.num++;
        updatePrice();
        updateCPS();
    }

    public void levelUp() {
        this.lvl++;
        updateCPS();
    }

    protected void updateCPS() {
        cps = cookieRate * lvl * num;
    }

    protected void updatePrice() {
        priceForNext = (long) Math.floor(priceForNext * 1.1);
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

    public void setNum(long num) {
        this.num = num;
    }

    public void setLvl(int lvl) {
        this.lvl = lvl;
    }
}
