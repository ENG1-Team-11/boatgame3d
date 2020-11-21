package io.github.eng1team11.boatgame2d.ecs.system;

import io.github.eng1team11.boatgame2d.ecs.component.*;

import java.util.Map;

public class Movement extends System {

    // Arbitrary drag value (should be really small [<0.05])
    final float _drag = 0.05f;

    /**
     * Linear interpolate between two values
     * @param a The value to interpolate from
     * @param b The value to interpolate to
     * @param t The amount to interpolate by each step
     * @return The partially interpolated value
     */
    float Lerp(float a, float b, float t) {
        return a + (b-a) * t;
    }

    /**
     * Default c'tor
     */
    public Movement() {

    }

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
            PositionComponent position = (PositionComponent) kv.getValue();
            VelocityComponent velocity = (VelocityComponent) _affectedComponents.get(1).get(id);
            AccelerationComponent acceleration = (AccelerationComponent) _affectedComponents.get(2).get(id);


            ControllerComponent controller = (ControllerComponent) _affectedComponents.get(3).get(id);
            // If it's not a PlayerInput, try looking for an AI instead
            if (controller == null) {
                controller = (ControllerComponent) _affectedComponents.get(4).get(id);
            }

            if (velocity == null || controller == null) continue;

            if (controller.GetLeft()) {
                velocity.Add(-acceleration.GetAccelerationModified(), 0.0f);
            }
            if (controller.GetRight()) {
                velocity.Add(acceleration.GetAccelerationModified(), 0.0f);
            }
            if (controller.GetForwards()) {
                velocity.Add(0.0f, acceleration.GetAccelerationModified());
            }
            if (controller.GetBackwards()) {
                velocity.Add(0.0f, -acceleration.GetAccelerationModified());
            }

            // If we're not moving, apply drag
            if (!(controller.GetLeft() | controller.GetRight())) {
                velocity.SetX(Lerp(velocity.GetX(), 0.0f, _drag));
                velocity.SetY(Lerp(velocity.GetY(), 0.0f, _drag));
            }

            // Add velocity to position, accounting for the durability
            position.Add(velocity.GetXModified() * delta, velocity.GetYModified() * delta);
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
