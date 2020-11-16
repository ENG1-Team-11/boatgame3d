package io.github.eng1team11.boatgame2d;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.utils.Timer;
import io.github.eng1team11.boatgame2d.ecs.ComponentManager;
import io.github.eng1team11.boatgame2d.ecs.EntityManager;
import io.github.eng1team11.boatgame2d.ecs.SystemManager;

public class BoatGame extends Game {

	private SpriteBatch _spriteBatch;
	private BitmapFont _font;

	public EntityManager _entityManager;
	public ComponentManager _componentManager;
	public SystemManager _systemManager;

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

	// Render is essentially treated as the update loop
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
