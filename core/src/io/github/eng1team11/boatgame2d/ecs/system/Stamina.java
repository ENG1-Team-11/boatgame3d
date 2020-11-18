package io.github.eng1team11.boatgame2d.ecs.system;

import io.github.eng1team11.boatgame2d.ecs.component.IComponent;
import io.github.eng1team11.boatgame2d.ecs.component.StaminaComponent;
import io.github.eng1team11.boatgame2d.ecs.component.VelocityComponent;

import java.util.HashMap;
import java.util.Map;

public class Stamina extends System {
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
        for (Map.Entry<Integer, IComponent> kv : _affectedComponents.get(0).entrySet()) {
            int id = kv.getKey();
            StaminaComponent stamina = (StaminaComponent) kv.getValue();
            VelocityComponent velocity = (VelocityComponent) _affectedComponents.get(1).get(id);

            stamina.DecayStamina(delta);

            if (velocity == null) continue;

            velocity.SetDurModifier(stamina.GetRemainingStamina());
        }
    }

    /**
     * Called during the render phase of the game engine loop
     *
     * @param delta The time since the completion of the last frame in seconds
     */
    @Override
    public void Render(float delta) {

    }
}
