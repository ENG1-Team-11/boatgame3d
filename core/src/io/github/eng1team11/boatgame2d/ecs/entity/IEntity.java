package io.github.eng1team11.boatgame2d.ecs.entity;

public interface IEntity {

    /**
     * Get the ID of the entity
     * @return The ID of the entity as an int
     */
    public int GetID();

    /**
     * Get the active state of the entity
     * @return The active state of the entity as a boolean
     */
    public boolean GetActive();

    /**
     * Se the active state of the entity
     * @param state The boolean state to set the entity to
     */
    public void SetActive(boolean state);
}
