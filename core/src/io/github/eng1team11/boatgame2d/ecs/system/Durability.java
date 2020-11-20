package io.github.eng1team11.boatgame2d.ecs.system;

import io.github.eng1team11.boatgame2d.ecs.component.DurabilityComponent;
import io.github.eng1team11.boatgame2d.ecs.component.IComponent;
import io.github.eng1team11.boatgame2d.ecs.component.VelocityComponent;

import java.util.Map;

public class Durability extends System {

    /**
     * Called during the input phase of the game engine loop
     *
     * @param delta The time since the completion of the last frame in seconds
     */
    @Override
    public void Input(float delta) {
        super.Input(delta);
    }

    /**
     * Called during the update phase of the game engine loop
     *
     * @param delta The time since the completion of the last frame in seconds
     */
    @Override
    public void Update(float delta) {
        delta = 0.1f;
        super.Update(delta);
        for (Map.Entry<Integer, IComponent> comp : _affectedComponents.get(0).entrySet()) {
            int id = comp.getKey();
            DurabilityComponent durability = (DurabilityComponent) comp.getValue();
            VelocityComponent velocity = (VelocityComponent) _affectedComponents.get(1).get(id);

            durability.DecayGracePeriod(delta);

            if (durability.ShouldReduce()) {
                if (durability.GetGracePeriod() < 0.0f) {
                    durability.AddDurability(-1);
                    durability.SetGracePeriod(1.0f);
                    durability.SetShouldReduce(false);
                    velocity.SetDurModifier(durability.GetRemainingDurability());
                }
            }
        }
    }

    /**
     * Called during the render phase of the game engine loop
     *
     * @param delta The time since the completion of the last frame in seconds
     */
    @Override
    public void Render(float delta) {
        super.Render(delta);
    }
}
