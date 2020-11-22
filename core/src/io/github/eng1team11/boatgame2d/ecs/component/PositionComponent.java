package io.github.eng1team11.boatgame2d.ecs.component;

public class PositionComponent extends Vector2Component {
    /**
     * Default ctor for a component
     *
     * @param id the ID of the a component
     */
    public PositionComponent(int id, float x, float y) {
        super(id);
        setX(x);
        setY(y);
    }

}
