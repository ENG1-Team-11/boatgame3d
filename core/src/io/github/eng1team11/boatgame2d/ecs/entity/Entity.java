package io.github.eng1team11.boatgame2d.ecs.entity;

public class Entity implements IEntity {

    final int _id;      // The ID of the entity
    boolean _active;    // Whether or not to update the entity

    /**
     * Default ctor of the Entity
     *
     * @param id The ID to assign to the entity
     */
    public Entity(int id) {
        _id = id;
        _active = true;
    }

    /**
     * Get the ID of the entity
     *
     * @return The ID of the entity as an int
     */
    @Override
    public int getID() {
        return _id;
    }

    /**
     * Get the active state of the entity
     *
     * @return The active state of the entity as a boolean
     */
    @Override
    public boolean getActive() {
        return _active;
    }

    /**
     * Se the active state of the entity
     *
     * @param state The boolean state to set the entity to
     */
    @Override
    public void setActive(boolean state) {
        _active = state;
    }
}
