package makers;

import model.Game;

public class Factories extends CookieMaker {
    public Factories () {
        super();
        cookieRate = 10;
        priceForNext = 1100;
        name = "factory";
        updateCPS();
    }

    public Factories (Game tuple) {
        super(tuple.getFactoryLvl(), tuple.getNumFactories());
        cookieRate = 10;
        loadPrice(1100);
        name = "factory";
        updateCPS();
    }

    public String getNamePlural() {
        return "factories";
    }
}

