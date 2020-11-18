package io.github.eng1team11.boatgame2d.ecs.component;

public class Component implements IComponent {

    final int _id;

    /**
     * Default ctor for a component
     * @param id the ID of the a component
     */
    public Component(int id) {
        _id = id;
    }

    /**
     * Get the ID of the component
     *
     * @return the ID of the component as an integer
     */
    @Override
    final public int GetID() {
        return _id;
    }
}
