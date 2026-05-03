package makers;

import model.Game;

public class Grandmas extends CookieMaker {
    public Grandmas () {
        super();
        cookieRate = 1;
        priceForNext = 100;
        name = "grandma";
        updateCPS();
    }

    public Grandmas(Game tuple) {
        super(tuple.getGrandmaLvl(), tuple.getNumGrandmas());
        cookieRate = 1;
        loadPrice(100);
        name = "grandma";
        updateCPS();
    }
}
