package io.github.eng1team11.boatgame2d.ecs;

import io.github.eng1team11.boatgame2d.ecs.system.ISystem;

import java.util.HashMap;

public class SystemManager {

    HashMap<Integer, ISystem> _systems;

    public void Update(float delta) {
        for (ISystem sys : _systems.values()) {
            sys.Input(delta);
            sys.Update(delta);
            sys.Render(delta);
        }
    }

    public int AddSystem(ISystem system) {
        int index = _systems.size();
        _systems.put(index, system);
        return index;
    }

    public void Clear() {
        _systems.clear();
    }

}
