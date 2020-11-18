package io.github.eng1team11.boatgame2d.ecs.system;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import io.github.eng1team11.boatgame2d.ecs.component.PlayerInput;

import java.util.ArrayList;

public class PlayerControl implements ISystem {

    // Should only be one, but we implement this as a list
    ArrayList<PlayerInput> _playerInputs = new ArrayList<PlayerInput>();

    /**
     * Default ctor for PlayerControl
     */
    public PlayerControl() {

    }

    /**
     * Create a new PlayerControl and return its ID
     * @return The ID of the newly created PlayerControl as an int
     */
    public int CreatePlayerInput() {
        int index = _playerInputs.size();
//        _playerInputs.add(new PlayerInput(index, ));
        return index;
    }

    /**
     * Called during the input phase of the game engine loop
     *
     * @param delta The time since the completion of the last frame in seconds
     */
    @Override
    public void Input(float delta) {

        // Temporary variables to assign left/right values to
        boolean left = false;
        boolean right = false;

        // Check if any key is pressed
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            left = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            right = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            left = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            right = true;
        }

        // Iterate over all player inputs attached to the system and update them
        for (PlayerInput pi : _playerInputs) {
            pi.SetLeft(left);
            pi.SetRight(right);
        }
    }

    /**
     * Called during the update phase of the game engine loop
     *
     * @param delta The time since the completion of the last frame in seconds
     */
    @Override
    public void Update(float delta) {

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
