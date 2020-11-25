package io.github.eng1team11.boatgame2d.ecs.component;

public class PositionComponent extends Vector2Component {
    /**
     * Default ctor for a component
     *
     * @param id The ID of the component
     * @param x  The x component of the position
     * @param y  The y component of the position
     */
    public PositionComponent(int id, float x, float y) {
        super(id);
        setX(x);
        setY(y);
    }

}
