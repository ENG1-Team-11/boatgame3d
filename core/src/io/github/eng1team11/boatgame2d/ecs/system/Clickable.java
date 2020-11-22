package io.github.eng1team11.boatgame2d.ecs.system;

import com.badlogic.gdx.Gdx;
import io.github.eng1team11.boatgame2d.ecs.component.ClickableComponent;
import io.github.eng1team11.boatgame2d.ecs.component.ColliderComponent;
import io.github.eng1team11.boatgame2d.ecs.component.IComponent;
import io.github.eng1team11.boatgame2d.ecs.component.PositionComponent;

import java.util.Map;

public class Clickable extends System {

    /**
     * Check if the mouse is inside a collider
     *
     * @param position The position of the collider
     * @param collider The collider
     * @param mouseX   The x position of the mouse
     * @param mouseY   The y position of the mouse
     * @return Whether or not the mouse is inside the collider, as a boolean
     */
    boolean checkPointerOn(PositionComponent position, ColliderComponent collider, int mouseX, int mouseY) {
        if (mouseX < position.getX()) return false;
        if (mouseX > position.getX() + collider.getWidth()) return false;
        if (mouseY > position.getY()) return false;
        if (mouseY < position.getY() - collider.getHeight()) return false;
        return true;
    }

    /**
     * Called during the input phase of the game engine loop
     *
     * @param delta The time since the completion of the last frame in seconds
     */
    @Override
    public void input(float delta) {
        super.input(delta);

        int mouseX = Gdx.input.getX();
        int mouseY = Gdx.input.getY();

        for (Map.Entry<Integer, IComponent> kv : _affectedComponents.get(0).entrySet()) {
            int id = kv.getKey();
            ClickableComponent clickable = (ClickableComponent) kv.getValue();
            PositionComponent position = (PositionComponent) _affectedComponents.get(1).get(id);
            ColliderComponent collider = (ColliderComponent) _affectedComponents.get(2).get(id);

            if (checkPointerOn(position, collider, mouseX, mouseY)) {
                clickable.runFunction();
            }
        }
    }

    /**
     * Called during the update phase of the game engine loop
     *
     * @param delta The time since the completion of the last frame in seconds
     */
    @Override
    public void update(float delta) {
        super.update(delta);
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
