package io.github.eng1team11.boatgame2d.ecs;

import io.github.eng1team11.boatgame2d.ecs.system.ISystem;

import java.util.HashMap;

public class SystemManager {

    HashMap<Integer, ISystem> _systems = new HashMap<Integer, ISystem>();

    /**
     * Update all systems in the systems list
     * @param delta The time since the last frame
     */
    public void Update(float delta) {
        for (ISystem sys : _systems.values()) {
            sys.Input(delta);
            sys.Update(delta);
            sys.Render(delta);
        }
    }

    /**
     * Add a system to the systems list
     * @param system The system to add
     * @return The System ID of the system just added
     */
    public int AddSystem(ISystem system) {
        int index = _systems.size();
        _systems.put(index, system);
        return index;
    }

    /**
     * Delete all systems
     */
    public void Clear() {
        _systems.clear();
    }

}
