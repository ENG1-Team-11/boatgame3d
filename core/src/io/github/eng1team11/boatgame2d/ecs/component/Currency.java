package io.github.eng1team11.boatgame2d.ecs.component;

public class Currency extends Component  {
    // Amount of currency
    int amount = 0;

    /**
     * Default ctor for a component
     *
     * @param id the ID of the a component
     */
    public Currency(int id, int type) {
        super(id, type);
    }
}
