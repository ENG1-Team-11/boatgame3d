package io.github.eng1team11.boatgame2d.ecs.system;

import io.github.eng1team11.boatgame2d.ecs.component.AIComponent;
import io.github.eng1team11.boatgame2d.ecs.component.IComponent;

public class AIControl extends System {

    /**
     * Called during the input phase of the game engine loop
     *
     * @param delta The time since the completion of the last frame in seconds
     */
    @Override
    public void input(float delta) {
        // No steps performed during input phase, AI takes no human input
    }

    /**
     * Called during the update phase of the game engine loop
     *
     * @param delta The time since the completion of the last frame in seconds
     */
    @Override
    public void update(float delta) {
        for (IComponent comp : _affectedComponents.get(0).values()) {
            AIComponent ai = (AIComponent) comp;
            ai.setRight(true);
        }
    }

    /**
     * Called during the render phase of the game engine loop
     *
     * @param delta The time since the completion of the last frame in seconds
     */
    @Override
    public void render(float delta) {

    }
}
