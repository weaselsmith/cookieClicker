package makers;

import model.Game;

public class Wizards extends CookieMaker {
    public Wizards () {
        super();
        cookieRate = 100;
        priceForNext = 12000;
        name = "wizard";
        updateCPS();
    }

    public Wizards (Game tuple) {
        super(tuple.getWizardsLvl(), tuple.getNumWizards());
        cookieRate = 100;
        loadPrice(12000);
        name = "wizard";
        updateCPS();
    }
}
