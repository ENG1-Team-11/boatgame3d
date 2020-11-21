package io.github.eng1team11.boatgame2d.ecs.system;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import io.github.eng1team11.boatgame2d.ecs.component.ClickableComponent;
import io.github.eng1team11.boatgame2d.ecs.component.ColliderComponent;
import io.github.eng1team11.boatgame2d.ecs.component.IComponent;
import io.github.eng1team11.boatgame2d.ecs.component.PositionComponent;

import javax.swing.text.Position;
import java.util.HashMap;
import java.util.Map;

public class Clickable extends System {


    boolean CheckPointerOn(PositionComponent position, ColliderComponent collider, int mouseX, int mouseY) {
        if (mouseX < position.GetX()) return false;
        if (mouseX > position.GetX() + collider.GetWidth()) return false;
        if (mouseY > position.GetY()) return false;
        if (mouseY < position.GetY() - collider.GetHeight()) return false;
        return true;
    }

    /**
     * Called during the input phase of the game engine loop
     *
     * @param delta The time since the completion of the last frame in seconds
     */
    @Override
    public void Input(float delta) {
        super.Input(delta);

        int mouseX = Gdx.input.getX();
        int mouseY = Gdx.input.getY();

        for (Map.Entry<Integer, IComponent> kv : _affectedComponents.get(0).entrySet()) {
            int id = kv.getKey();
            ClickableComponent clickable = (ClickableComponent) kv.getValue();
            PositionComponent position = (PositionComponent) _affectedComponents.get(1).get(id);
            ColliderComponent collider = (ColliderComponent) _affectedComponents.get(2).get(id);

            if (CheckPointerOn(position, collider, mouseX, mouseY)) {
                clickable.RunFunction();
            }
        }
    }

    /**
     * Called during the update phase of the game engine loop
     *
     * @param delta The time since the completion of the last frame in seconds
     */
    @Override
    public void Update(float delta) {
        super.Update(delta);
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
