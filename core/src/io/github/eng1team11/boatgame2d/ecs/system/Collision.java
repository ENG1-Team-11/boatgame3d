package io.github.eng1team11.boatgame2d.ecs.system;

import io.github.eng1team11.boatgame2d.ecs.component.*;
import io.github.eng1team11.boatgame2d.util.CollisionData;

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
    CollisionData checkAABB(ColliderComponent cA, ColliderComponent cB, PositionComponent pA, PositionComponent pB) {
        // Get the position of B relative to A
        float deltaX = pB.getX() - pA.getX();
        float deltaY = pB.getY() - pA.getY();

        // A is clearly above, no collision
        if (pA.getY() - cA.getHeight() > pB.getY()) {
            return CollisionData.None;
        }
        // A is clearly to the left, no collision
        if (pA.getX() + cA.getWidth() < pB.getX()) {
            return CollisionData.None;
        }
        // A is clearly below, no collision
        if (pA.getY() < pB.getY() - cB.getHeight()) {
            return CollisionData.None;
        }
        // A is clearly to the right, no collision
        if (pA.getX() > pB.getX() + cB.getWidth()) {
            return CollisionData.None;
        }

        // By deduction, must be a collision so create a new collision data struct
        CollisionData cd = new CollisionData();
        cd.collision = true;

        // Figure out where we collided
        // Did we collide on the left or right?
        if ((pA.getX() < pB.getX() + cB.getWidth()) && (pA.getX() + cA.getWidth() * 0.1f > pB.getX() + cB.getWidth())) {
            cd.lr = CollisionData.HorizontalType.Right;
        } else if ((pA.getX() + cA.getWidth() > pB.getX()) && (pA.getX() + cA.getWidth() < pB.getX() + cB.getWidth() * 0.1f)) {
            cd.lr = CollisionData.HorizontalType.Left;
        }
        // Did we collide on the top or bottom?
        if ((pA.getY() - cA.getHeight() < pB.getY()) && (pA.getY() - cA.getHeight() > pB.getY() - cB.getHeight() * 0.1f)) {
            cd.tb = CollisionData.VerticalType.Top;
        } else if ((pA.getY() > pB.getY() - cB.getWidth()) && (pA.getY() < pB.getY() - cB.getHeight() * 0.9f)) {
            cd.tb = CollisionData.VerticalType.Bottom;
        }
        return cd;
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
            DurabilityComponent durabilityA = (DurabilityComponent) _affectedComponents.get(1).get(id);
            VelocityComponent velocityA = (VelocityComponent) _affectedComponents.get(2).get(id);
            SpriteComponent spriteA = (SpriteComponent) _affectedComponents.get(3).get(id);
            PositionComponent positionA = (PositionComponent) _affectedComponents.get(4).get(id);

            // If we're missing some components then skip this entity
            if (durabilityA == null || velocityA == null || velocityA == null || spriteA == null || positionA == null)
                continue;

            // Iterate over all other entities
            for (Map.Entry<Integer, IComponent> other : _affectedComponents.get(0).entrySet()) {
                // Get the ID of the other entity
                int oId = other.getKey();
                // If this entity has already been checked, skip it
                if (id >= oId) continue;
                // Get the components of the other entity
                ColliderComponent colliderB = (ColliderComponent) other.getValue();
                DurabilityComponent durabilityB = (DurabilityComponent) _affectedComponents.get(1).get(oId);
                VelocityComponent velocityB = (VelocityComponent) _affectedComponents.get(2).get(oId);
                PositionComponent positionB = (PositionComponent) _affectedComponents.get(4).get(oId);

                // If this entity is missing some components, skip it
                if (colliderB == null || durabilityB == null || velocityB == null || positionB == null) continue;

                // If there is a collision, do something...
                CollisionData collision = checkAABB(colliderA, colliderB, positionA, positionB);
                if (collision != CollisionData.None) {

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

                    // Move Boat B
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
