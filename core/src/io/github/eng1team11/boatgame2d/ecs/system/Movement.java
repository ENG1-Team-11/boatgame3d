package io.github.eng1team11.boatgame2d.ecs.system;

import io.github.eng1team11.boatgame2d.ecs.component.*;

import java.util.Map;

public class Movement extends System {

    // Arbitrary drag value (should be really small [<0.05])
    final float _drag = 0.05f;

    /**
     * Default c'tor
     */
    public Movement() {

    }

    /**
     * Linear interpolate between two values
     *
     * @param a The value to interpolate from
     * @param b The value to interpolate to
     * @param t The amount to interpolate by each step
     * @return The partially interpolated value
     */
    float Lerp(float a, float b, float t) {
        return a + (b - a) * t;
    }

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
            // Get the other components of the entity
            PositionComponent position = (PositionComponent) kv.getValue();
            VelocityComponent velocity = (VelocityComponent) _affectedComponents.get(1).get(id);
            AccelerationComponent acceleration = (AccelerationComponent) _affectedComponents.get(2).get(id);

            // Try to get the PlayerInput and cast to a generic controller
            ControllerComponent controller = (ControllerComponent) _affectedComponents.get(3).get(id);
            // If it's not a PlayerInput, try looking for an AI instead
            if (controller == null) {
                controller = (ControllerComponent) _affectedComponents.get(4).get(id);
            }

            if (controller != null) {
                // Add velocity to the entity based on what input it's given
                if (controller.getLeft()) {
                    velocity.add(-acceleration.getAccelerationModified(), 0.0f);
                }
                if (controller.getRight()) {
                    velocity.add(acceleration.getAccelerationModified(), 0.0f);
                }
                if (controller.getForwards()) {
                    velocity.add(0.0f, acceleration.getAccelerationModified());
                }
                if (controller.getBackwards()) {
                    velocity.add(0.0f, -acceleration.getAccelerationModified());
                }
                // If we're not moving, apply drag
                if (!(controller.getLeft() | controller.getRight())) {
                    velocity.setX(Lerp(velocity.getX(), 0.0f, _drag));
                    velocity.setY(Lerp(velocity.getY(), 0.0f, _drag));
                }
            }

            // If we're missing any components, skip this object
            if (velocity == null  || position == null) continue;

            // Add velocity to position, accounting for the durability
            position.add(velocity.getXModified() * delta, velocity.getYModified() * delta);
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
