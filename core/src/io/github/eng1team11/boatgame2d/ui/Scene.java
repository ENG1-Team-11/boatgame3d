package io.github.eng1team11.boatgame2d.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Scene {

    Canvas _currentCanvas;


    public Scene() {
        _currentCanvas = new Canvas();
    }

    /**
     * Get the current working canvas
     *
     * @return The current canvas object
     */
    public Canvas getCanvas() {
        return _currentCanvas;
    }

    /**
     * Set the current working canvas
     *
     * @param canvas The canvas to draw
     */
    public void setCanvas(Canvas canvas) {
        _currentCanvas = canvas;
    }

    /**
     * Update all objects in the current scene
     *
     * @param delta The time since the last frame in seconds
     */
    public void update(float delta) {
        if (_currentCanvas == null) return;

        float mouseX = Gdx.input.getX();
        float mouseY = Gdx.input.getX();
        boolean click = Gdx.input.isButtonJustPressed(0);
        _currentCanvas.update(mouseX, mouseY, click);
    }

    /**
     * Draw all objects in the current scene
     *
     * @param delta The time since the last frame in seconds
     * @param batch The sprite batch to draw to
     */
    public void draw(float delta, SpriteBatch batch) {
        if (_currentCanvas == null) return;
        _currentCanvas.draw(batch);
    }

}
