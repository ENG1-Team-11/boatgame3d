package io.github.eng1team11.boatgame2d.ecs.component;

public class Durability extends Component  {
    // Current durability value
    int value = 0;
    // Max durability value
    int maxValue = 0;


    /**
     * Default ctor for a component
     *
     * @param id the ID of the a component
     */
    public Durability(int id) {
        super(id);
    }
}
