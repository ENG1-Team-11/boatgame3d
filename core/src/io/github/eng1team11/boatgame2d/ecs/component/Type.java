package io.github.eng1team11.boatgame2d.ecs.component;

public class Type extends Component{
    // This might work better as an enum?
    String type = "";

    /**
     * Default ctor for a component
     *
     * @param id the ID of the a component
     */
    public Type(int id, int type) {
        super(id, type);
    }
}
