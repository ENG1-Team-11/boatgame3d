package io.github.eng1team11.boatgame2d.ecs.system;

import io.github.eng1team11.boatgame2d.ecs.EntityManager;
import io.github.eng1team11.boatgame2d.ecs.component.DurabilityComponent;
import io.github.eng1team11.boatgame2d.ecs.component.IComponent;
import io.github.eng1team11.boatgame2d.ecs.component.VelocityComponent;

import java.util.Map;

public class Durability extends System {

    EntityManager _em;

    public Durability(EntityManager em) {
        _em = em;
    }

    /**
     * Called during the input phase of the game engine loop
     *
     * @param delta The time since the completion of the last frame in seconds
     */
    @Override
    public void input(float delta) {
        super.input(delta);
    }

    /**
     * Called during the update phase of the game engine loop
     *
     * @param delta The time since the completion of the last frame in seconds
     */
    @Override
    public void update(float delta) {
        super.update(delta);
        // Iterate over all entities
        for (Map.Entry<Integer, IComponent> comp : _affectedComponents.get(0).entrySet()) {
            int id = comp.getKey();
            // Get the components of the entity
            DurabilityComponent durability = (DurabilityComponent) comp.getValue();
            VelocityComponent velocity = (VelocityComponent) _affectedComponents.get(1).get(id);

            // If the entity is missing a component, skip it
            if (velocity == null) continue;

            // Decay the damage grace period of the entity
            durability.decayGracePeriod(delta);

            // If the durability should be reduced it, set the durability to durability - 1
            if (durability.shouldReduce()) {
                if (durability.getGracePeriod() < 0.0f) {
                    durability.addDurability(-1);
                    durability.setGracePeriod(1.0f);
                    durability.setShouldReduce(false);
                    velocity.setDurModifier(durability.getRemainingDurability());
                }
            }

            if (durability.getDurability() <= 0.0f) {
                _em.deleteEntity(id);
            }
        }
    }

    /**
     * Called during the render phase of the game engine loop
     *
     * @param delta The time since the completion of the last frame in seconds
     */
    @Override
    public void render(float delta) {
        super.render(delta);
    }
}
