package io.github.eng1team11.boatgame2d.ecs.entity;

import io.github.eng1team11.boatgame2d.ecs.component.Sprite;

public class Lane extends Entity {
    public int _sprite;
    public int _position;

    /**
     * Default ctor of the Entity
     *
     * @param id The ID to assign to the entity
     */
    public Lane(int id) {
        super(id);
    }
}
