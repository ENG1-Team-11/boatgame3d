package io.github.eng1team11.boatgame2d.ecs;

import io.github.eng1team11.boatgame2d.ecs.entity.Entity;
import io.github.eng1team11.boatgame2d.ecs.entity.IEntity;

import java.util.HashMap;

public class EntityManager {

    HashMap<Integer, IEntity> _entities = new HashMap<Integer, IEntity>();

    /**
     * Create a new entity
     * @return The ID of the new entity
     */
    public int CreateEntity() {
        int index = _entities.size();
        _entities.put(index, new Entity(index));
        return index;
    }

    /**
     * Get an Entity
     * @param id The ID of the entity
     * @return An IEntity object representing the entity
     */
    public IEntity GetEntity(int id) {
        return _entities.get(id);
    }

    /**
     * Delete an entity
     * @param id The ID of the entity to delete
     */
    public void DeleteEntity(int id) {
        if (_entities.get(id) != null) {
            _entities.remove(id);
        }
    }

}
