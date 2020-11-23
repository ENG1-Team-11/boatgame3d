package io.github.eng1team11.boatgame2d.ecs;

import io.github.eng1team11.boatgame2d.ecs.entity.Entity;
import io.github.eng1team11.boatgame2d.ecs.entity.IEntity;

import java.util.HashMap;

public class EntityManager {

    HashMap<Integer, IEntity> _entities = new HashMap<>();
    int _entityCount;

    ComponentManager _cm;

    public EntityManager(ComponentManager cm) {
        _cm = cm;
    }


    /**
     * Create a new entity
     *
     * @return The ID of the new entity
     */
    public int createEntity() {
        ++_entityCount;
        _entities.put(_entityCount, new Entity(_entityCount));
        return _entityCount;
    }

    /**
     * Get an Entity
     *
     * @param id The ID of the entity
     * @return An IEntity object representing the entity
     */
    public IEntity getEntity(int id) {
        return _entities.get(id);
    }

    /**
     * Delete an entity
     *
     * @param id The ID of the entity to delete
     */
    public void deleteEntity(int id) {
        if (_entities.get(id) != null) {
            _entities.remove(id);
        }
        _cm.deleteComponentsOfId(id);
    }

    /**
     * Delete all existing entities
     */
    public void clear() {
        _entities.clear();
    }

}
