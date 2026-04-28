package pages;

import org.shriniwas.keywords.ElementActions;

public class CobiusHomePage {

    public CobiusHomePage waitForPageToLoad() {
        ElementActions.waitForTitle("Cobius Applications");
        return this;
    }
}
