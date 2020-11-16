package io.github.eng1team11.boatgame2d.ecs;

import io.github.eng1team11.boatgame2d.ecs.entity.Entity;
import io.github.eng1team11.boatgame2d.ecs.entity.IEntity;

import java.util.HashMap;

public class EntityManager {

    HashMap<Integer, IEntity> _entities;

    public int CreateEntity() {
        int index = _entities.size();
        _entities.put(index, new Entity(index));
        return index;
    }

    public IEntity GetEntity(int id) {
        return _entities.get(id);
    }

    public void DeleteEntity(int id) {
        if (_entities.get(id) != null) {
            _entities.remove(id);
        }
    }

}
