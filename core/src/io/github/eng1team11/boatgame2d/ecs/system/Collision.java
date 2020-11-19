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
    public void Input(float delta) {

    }

    CollisionData AABB(ColliderComponent cA, ColliderComponent cB, PositionComponent pA, PositionComponent pB) {
        // Get the position of B relative to A
        float deltaX = pB.GetX() - pA.GetX();
        float deltaY = pB.GetY() - pA.GetY();

        // A is clearly above, no collision
        if (pA.GetY() - cA.GetHeight() > pB.GetY()) {
            return CollisionData.None;
        }
        // A is clearly to the left, no collision
        if (pA.GetX() + cA.GetWidth() < pB.GetX()) {
            return CollisionData.None;
        }
        // A is clearly below, no collision
        if (pA.GetY() < pB.GetY() - cB.GetHeight()) {
            return CollisionData.None;
        }
        // A is clearly to the right, no collision
        if (pA.GetX() > pB.GetX() + cB.GetWidth()) {
            return CollisionData.None;
        }

        // By deduction, must be a collision so create a new collision data struct
        CollisionData cd = new CollisionData();
        cd.collision = true;

        // Figure out where we collided
        // Did we collide on the left?
        if ((pA.GetX() < pB.GetX() + cB.GetWidth()) && (pA.GetX() + cA.GetWidth() * 0.1f > pB.GetX() + cB.GetWidth())) {
            cd.lr = CollisionData.HorizontalType.Right;
        }
        else if ((pA.GetX() + cA.GetWidth() > pB.GetX()) && (pA.GetX() + cA.GetWidth() < pB.GetX() + cB.GetWidth() * 0.1f)) {
            cd.lr = CollisionData.HorizontalType.Left;
        }
        if ((pA.GetY() - cA.GetHeight() < pB.GetY()) && (pA.GetY() - cA.GetHeight() > pB.GetY() - cB.GetHeight() * 0.1f)) {
            cd.tb = CollisionData.VerticalType.Top;
        }
        else if ((pA.GetY() > pB.GetY() - cB.GetWidth()) && (pA.GetY() < pB.GetY() - cB.GetHeight() * 0.9f)) {
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
    public void Update(float delta) {
        for (Map.Entry<Integer, IComponent> kv : _affectedComponents.get(0).entrySet()) {
            int id = kv.getKey();
            ColliderComponent colliderA = (ColliderComponent) kv.getValue();
            DurabilityComponent durabilityA = (DurabilityComponent) _affectedComponents.get(1).get(id);
            VelocityComponent velocityA = (VelocityComponent) _affectedComponents.get(2).get(id);
            SpriteComponent spriteA = (SpriteComponent) _affectedComponents.get(3).get(id);
            PositionComponent positionA = (PositionComponent) _affectedComponents.get(4).get(id);

            for (Map.Entry<Integer, IComponent> other : _affectedComponents.get(0).entrySet()) {
                int oId = other.getKey();
                if (id >= oId) continue;
                ColliderComponent ColliderB = (ColliderComponent) other.getValue();
                VelocityComponent VelocityB = (VelocityComponent) _affectedComponents.get(2).get(oId);
                PositionComponent PositionB = (PositionComponent) _affectedComponents.get(4).get(oId);

                // If there is a collision, do something...
                CollisionData collision = AABB(colliderA, ColliderB, positionA, PositionB);
                if (collision != CollisionData.None) {

                    // Calculate momentum by adding velocities [p=mv] (assume similar weight)
                    float momentumX = (velocityA.GetX() + VelocityB.GetX()) / 2;
                    float momentumY = (velocityA.GetY() + VelocityB.GetY()) / 2;

                    // Calculate the transfer of energy
                    float bumpXA = momentumX * 0.5f;
                    float bumpXB = momentumX;
                    float bumpYA = momentumY * 0.5f;
                    float bumpYB = momentumY;

                    // Modify Boat A
                    velocityA.Set(bumpXA, -bumpYA);
                    durabilityA.AddDurability(-1);

                    // Move Boat B
                    VelocityB.Set(bumpXB, -bumpYB);
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

    }
}
