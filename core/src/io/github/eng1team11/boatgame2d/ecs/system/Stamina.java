package io.github.eng1team11.boatgame2d.ecs.system;

import io.github.eng1team11.boatgame2d.ecs.component.IComponent;
import io.github.eng1team11.boatgame2d.ecs.component.StaminaComponent;
import io.github.eng1team11.boatgame2d.ecs.component.VelocityComponent;

import java.util.Map;

public class Stamina extends System {
    /**
     * Called during the input phase of the game engine loop
     *
     * @param delta The time since the completion of the last frame in seconds
     */
    @Override
    public void input(float delta) {

    }

    /**
     * Called during the update phase of the game engine loop
     *
     * @param delta The time since the completion of the last frame in seconds
     */
    @Override
    public void update(float delta) {
        // Iterate over all relevant components
        for (Map.Entry<Integer, IComponent> kv : _affectedComponents.get(0).entrySet()) {
            int id = kv.getKey();
            // Get any other required components
            StaminaComponent stamina = (StaminaComponent) kv.getValue();
            VelocityComponent velocity = (VelocityComponent) _affectedComponents.get(1).get(id);

            // Decay the stamina
            stamina.decayStamina(delta);

            // If we don't have a velocity component, skip this
            if (velocity == null) continue;

            // Set the stamina modifier of velocity to the remaining stamina
            velocity.setStaModifier(stamina.getRemainingStamina());
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
