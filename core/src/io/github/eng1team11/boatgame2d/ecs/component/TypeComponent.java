package io.github.eng1team11.boatgame2d.ecs.component;

public class TypeComponent extends Component{
    // This might work better as an enum?
    String type = "";

    /**
     * Default ctor for a component
     *
     * @param id the ID of the a component
     */
    public TypeComponent(int id) {
        super(id);
    }
}
