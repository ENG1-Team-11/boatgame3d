package io.github.eng1team11.boatgame2d.ecs;

import io.github.eng1team11.boatgame2d.ecs.component.IComponent;
import io.github.eng1team11.boatgame2d.ecs.system.ISystem;

import java.util.HashMap;

public class SystemManager {

    HashMap<Integer, ISystem> _systems = new HashMap<>();

    public void registerComponentsToSystem(HashMap<Integer, IComponent> components, int system) {
        _systems.get(system).registerComponents(components);
    }


    /**
     * Make all systems run their input functions
     * @param delta The time since the last frame
     */
    public void input(float delta) {
        for (ISystem sys : _systems.values()) {
            sys.input(delta);
        }
    }

    /**
     * Make all systems rin their update functions
     *
     * @param delta The time since the last frame
     */
    public void update(float delta) {
        for (ISystem sys : _systems.values()) {
            sys.update(delta);
        }
    }

    /**
     * Make all systems run their render functions
     * @param delta The time since the last frame
     */
    public void render(float delta) {
        for (ISystem sys : _systems.values()) {
            sys.render(delta);
        }
    }

    /**
     * Add a system to the systems list
     *
     * @param system The system to add
     * @return The System ID of the system just added
     */
    public int addSystem(ISystem system) {
        int index = _systems.size();
        _systems.put(index, system);
        return index;
    }

    /**
     * Delete all systems
     */
    public void clear() {
        _systems.clear();
    }

}
