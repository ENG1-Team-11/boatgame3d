package io.github.eng1team11.boatgame2d.ecs.system;

import io.github.eng1team11.boatgame2d.ecs.component.Controller;
import io.github.eng1team11.boatgame2d.ecs.component.IComponent;
import io.github.eng1team11.boatgame2d.ecs.component.Position;
import io.github.eng1team11.boatgame2d.ecs.component.Velocity;

import java.util.Map;

public class Movement extends System {

    // Arbitrary drag value (should be really small [<0.05])
    final float _drag = 0.02f;

    float Lerp(float a, float b, float t) {
        return a + (b-a) * t;
    }

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
            Position position = (Position) kv.getValue();
            Velocity velocity = (Velocity) _affectedComponents.get(1).get(id);
            Controller controller = (Controller) _affectedComponents.get(2).get(id);
            // If it's not a PlayerInput, try looking for an AI instead
            if (controller == null) {
                controller = (Controller) _affectedComponents.get(3).get(id);
            }

            if (velocity == null || controller == null) continue;

            if (controller.GetLeft()) {
                velocity.Add(-1.0f * delta, 0.0f);
            }
            if (controller.GetRight()) {
                velocity.Add(1.0f * delta, 0.0f);
            }

            // If we're not moving, apply drag
            if (!(controller.GetLeft() | controller.GetRight())) {
                float[] xy = velocity.Get();
                velocity.SetX(Lerp(velocity.GetX(), 0.0f, _drag));
                velocity.SetY(Lerp(velocity.GetY(), 0.0f, _drag));
            }

            position.Add(velocity);
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
