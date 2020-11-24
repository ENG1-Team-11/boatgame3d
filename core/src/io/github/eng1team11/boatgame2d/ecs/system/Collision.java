package io.github.eng1team11.boatgame2d.ecs.system;

import io.github.eng1team11.boatgame2d.ecs.component.*;

import java.util.Map;

public class Collision extends System {
    /**
     * Called during the input phase of the game engine loop
     *
     * @param delta The time since the completion of the last frame in seconds
     */
    @Override
    public void input(float delta) {

    }

    /**
     * Check whether two colliders are intersecting, and return where if they are
     *
     * @param cA The collider of object A
     * @param cB The collider of object B
     * @param pA The position of object A
     * @param pB The position of object B
     * @return A CollisionData struct describing the collision
     */
    boolean checkAABB(ColliderComponent cA, ColliderComponent cB, PositionComponent pA, PositionComponent pB) {
        // A is clearly above, no collision
        if (pA.getY() >  pB.getY() + cB.getHeight()) {
            return false;
        }
        // A is clearly below, no collision
        if (pA.getY() + cB.getHeight() < pB.getY()) {
            return false;
        }
        // A is clearly to the left, no collision
        if (pA.getX() + cA.getWidth() < pB.getX()) {
            return false;
        }
        // A is clearly to the right, no collision
        if (pA.getX() > pB.getX() + cB.getWidth()) {
            return false;
        }

        // By deduction, there must have been a collision
        return true;
    }

    /**
     * Called during the update phase of the game engine loop
     *
     * @param delta The time since the completion of the last frame in seconds
     */
    @Override
    public void update(float delta) {
        for (Map.Entry<Integer, IComponent> kv : _affectedComponents.get(0).entrySet()) {
            int id = kv.getKey();
            // Get components of the entity
            ColliderComponent colliderA = (ColliderComponent) kv.getValue();

            // If the entity doesn't collide, skip it
            if (!colliderA.isActive()) continue;

            DurabilityComponent durabilityA = (DurabilityComponent) _affectedComponents.get(1).get(id);
            VelocityComponent velocityA = (VelocityComponent) _affectedComponents.get(2).get(id);
//            SpriteComponent spriteA = (SpriteComponent) _affectedComponents.get(3).get(id);
            PositionComponent positionA = (PositionComponent) _affectedComponents.get(4).get(id);

            // Iterate over all other entities
            for (Map.Entry<Integer, IComponent> other : _affectedComponents.get(0).entrySet()) {
                // Get the ID of the other entity
                int oId = other.getKey();
                // If this entity has already been checked, skip it
                if (id >= oId) continue;
                // Get the components of the other entity
                ColliderComponent colliderB = (ColliderComponent) other.getValue();

                // If the entity doesn't collide, skip it
                if (!colliderB.isActive()) continue;

                DurabilityComponent durabilityB = (DurabilityComponent) _affectedComponents.get(1).get(oId);
                VelocityComponent velocityB = (VelocityComponent) _affectedComponents.get(2).get(oId);
                PositionComponent positionB = (PositionComponent) _affectedComponents.get(4).get(oId);

                // If there is a collision, do something...
                boolean collision = checkAABB(colliderA, colliderB, positionA, positionB);
                if (collision) {

                    // Calculate momentum by adding velocities [p=mv] (assume similar weight)
                    float momentumX = (velocityA.getX() + velocityB.getX()) / 2;
                    float momentumY = (velocityA.getY() + velocityB.getY()) / 2;

                    // Calculate the transfer of energy
                    float bumpXA = momentumX * 0.5f;
                    float bumpXB = momentumX;
                    float bumpYA = -momentumY * 0.5f;
                    float bumpYB = -momentumY;

                    // Modify Boat A
                    velocityA.set(bumpXA, -bumpYA);
                    durabilityA.setShouldReduce(true);

                    // Modify Boat B
                    velocityB.set(bumpXB, -bumpYB);
                    durabilityB.setShouldReduce(true);
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
    public void render(float delta) {

    }
}
