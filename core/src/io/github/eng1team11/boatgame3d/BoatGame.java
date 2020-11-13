package io.github.eng1team11.boatgame3d;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BoatGame extends Game {

	private SpriteBatch _spriteBatch;
	private BitmapFont _font;

	/**
	 * Called when the {@link Application} is first created.
	 */
	@Override
	public void create() {
		// Create a sprite batch
		_spriteBatch = new SpriteBatch();
		// Load the font (defaults to Arial)
		_font = new BitmapFont();

		// When the game instance is created, go to the main menu screen
		this.setScreen(new MenuScreen(this));

	}

	@Override
	public void render() {
		// Call render in the superclass (makes sure the screen gets rendered)
		super.render();
	}

	@Override
	public void dispose() {
		// Call dispose in the superclass
		super.dispose();
	}

	public SpriteBatch GetSpriteBatch() {
		return _spriteBatch;
	}

	public BitmapFont GetFont() {
		return _font;
	}
}
