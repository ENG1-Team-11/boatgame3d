package io.github.eng1team11.boatgame2d.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Scene {

    Canvas _currentCanvas;


    public Scene() {
        _currentCanvas = new Canvas();
    }

    /**
     * Set the current working canvas
     * @param canvas The canvas to draw
     */
    public void SetCanvas(Canvas canvas) {
        _currentCanvas = canvas;
    }

    /**
     * Get the current working canvas
     * @return The current canvas object
     */
    public Canvas GetCanvas() {
        return _currentCanvas;
    }

    public void Update(float delta) {
        if (_currentCanvas == null) return;

        float mouseX = Gdx.input.getX();
        float mouseY = Gdx.input.getX();
        boolean click = Gdx.input.isButtonJustPressed(0);
        _currentCanvas.Update(mouseX, mouseY, click);
    }

    public void Draw(float delta, SpriteBatch batch) {
        if (_currentCanvas == null) return;
        _currentCanvas.Draw(batch);
    }

}
