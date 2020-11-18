package io.github.eng1team11.boatgame2d.ecs.system;

import io.github.eng1team11.boatgame2d.ecs.component.IComponent;

import java.util.ArrayList;
import java.util.HashMap;

public class System implements ISystem {

    // The list of components that the system operates on
    ArrayList<HashMap<Integer, IComponent>> _affectedComponents = new ArrayList<>();

    /**
     * Called during the input phase of the game engine loop
     *
     * @param delta The time since the completion of the last frame in seconds
     */
    @Override
    public void Input(float delta) {

    }

    /**
     * Called during the update phase of the game engine loop
     *
     * @param delta The time since the completion of the last frame in seconds
     */
    @Override
    public void Update(float delta) {

    }

    /**
     * Called during the render phase of the game engine loop
     *
     * @param delta The time since the completion of the last frame in seconds
     */
    @Override
    public void Render(float delta) {

    }

    /**
     * Add components of a specific type for the system to manage
     *
     * @param components An ArrayList of all the components the system will manage
     */
    @Override
    public void RegisterComponents(HashMap<Integer, IComponent> components) {
        _affectedComponents.add(components);
    }
}
