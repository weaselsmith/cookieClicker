package makers;

public class Factories extends CookieMaker {
    public Factories () {
        super();
        cookieRate = 10;
        priceForNext = 1100;
        name = "factory";
        updateCPS();
    }

    public String getNamePlural() {
        return "factories";
    }
}

