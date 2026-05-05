package makers;

public abstract class CookieMaker {
    protected String name;
    protected int lvl;
    protected int num;
    protected long priceForNext;
    protected int cookieRate;
    protected int cps;  // cps = cookies for second

    public CookieMaker() {
        this.lvl = 1;
        this.num = 0;
    }

    public CookieMaker(int lvl, int num) {
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

    /**
     * used to load data on a saved game to get correct unit prices
     * @param initialPrice the price for the first of that item
     */
    protected void loadPrice(long initialPrice) {
        this.priceForNext = initialPrice;
        for (int i = 0; i < num; i++) {
            updatePrice();
        }
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

    public long getCps() {
        return cps;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void setLvl(int lvl) {
        this.lvl = lvl;
    }
}
