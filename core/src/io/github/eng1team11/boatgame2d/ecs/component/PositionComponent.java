package io.github.eng1team11.boatgame2d.ecs.component;

public class PositionComponent extends Vector2 {
    /**
     * Default ctor for a component
     *
     * @param id the ID of the a component
     */
    public PositionComponent(int id, float x, float y) {
        super(id);
        SetX(x);
        SetY(y);
    }
}
