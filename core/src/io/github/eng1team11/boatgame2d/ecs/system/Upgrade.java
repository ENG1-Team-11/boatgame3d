package io.github.eng1team11.boatgame2d.ecs.system;

import io.github.eng1team11.boatgame2d.ecs.component.*;

import java.util.Map;

public class Upgrade extends System {
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
        for (Map.Entry<Integer, IComponent> comp : _affectedComponents.get(0).entrySet()) {
            int id = comp.getKey();
            // Get any other required components
            UpgradeComponent upgrade = (UpgradeComponent) comp.getValue();
            StaminaComponent sta = (StaminaComponent) _affectedComponents.get(1).get(id);
            DurabilityComponent dur = (DurabilityComponent) _affectedComponents.get(2).get(id);
            VelocityComponent vel = (VelocityComponent) _affectedComponents.get(3).get(id);

            // Set the modifiers
            sta.setDecayModifier(upgrade.getStaMod());
            dur.setDurModifier(upgrade.getDurMod());
            vel.setModifier(upgrade.getVelMod());
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
