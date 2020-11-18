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
        if (deltaY < -cA.GetHeight()) return CollisionData.None;

        // A is clearly to the left, no collision
        if (deltaX > cA.GetWidth()) return CollisionData.None;

        // A is clearly below, no collision
        if (deltaY > cB.GetHeight()) return CollisionData.None;

        // A is clearly to the right, no collision
        if (deltaX < -cB.GetWidth()) return CollisionData.None;

        // By deduction, must be a collision
        return new CollisionData(true, (deltaX > 0.0f), (deltaY > 0.0f));
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
            ColliderComponent collider = (ColliderComponent) kv.getValue();
            DurabilityComponent durability = (DurabilityComponent) _affectedComponents.get(1).get(id);
            VelocityComponent velocity = (VelocityComponent) _affectedComponents.get(2).get(id);
            SpriteComponent sprite = (SpriteComponent) _affectedComponents.get(3).get(id);
            PositionComponent position = (PositionComponent) _affectedComponents.get(4).get(id);

            for (Map.Entry<Integer, IComponent> other : _affectedComponents.get(0).entrySet()) {
                int oId = other.getKey();
                if (id >= oId) continue;
                ColliderComponent oCollider = (ColliderComponent) other.getValue();
                VelocityComponent oVelocity = (VelocityComponent) _affectedComponents.get(2).get(oId);
                PositionComponent oPosition = (PositionComponent) _affectedComponents.get(4).get(oId);

                // If there is a collision, do something...
                CollisionData collision = AABB(collider, oCollider, position, oPosition);
                if (collision != CollisionData.None) {
                    // Calculate direction to bump boats off in
                    float bumpX = (collision.lr)?-5.0f:5.0f;
                    float bumpY = (collision.tb)?5.0f:-5.0f;

                    // Modify Boat A
                    velocity.Set(bumpX, bumpY);
                    position.Add(bumpX, bumpY);
                    // durability.Add(-1.0f);

                    // Move Boat B
                    oVelocity.Set(-bumpX, -bumpY);
                    oPosition.Add(-bumpX, -bumpY);
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
