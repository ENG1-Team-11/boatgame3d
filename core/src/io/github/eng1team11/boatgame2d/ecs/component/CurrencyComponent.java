package io.github.eng1team11.boatgame2d.ecs.component;

public class CurrencyComponent extends Component  {
    // Amount of currency
    int amount = 0;

    /**
     * Default ctor for a component
     *
     * @param id the ID of the a component
     */
    public CurrencyComponent(int id) {
        super(id);
    }
}
