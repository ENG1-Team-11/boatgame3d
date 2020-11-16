package io.github.eng1team11.boatgame2d.ecs.component;

public class Component implements IComponent {

    final int _id;
    final int _type;

    /**
     * Default ctor for a component
     * @param id the ID of the a component
     */
    public Component(int id, int type) {
        _id = id;
        _type = type;
    }

    /**
     * Get the ID of the component
     *
     * @return the ID of the component as an integer
     */
    @Override
    public int GetID() {
        return _id;
    }

    /**
     * Get the type of the component
     *
     * @return the type of the component as an integer
     */
    @Override
    public int GetType() {
        return _type;
    }
}
