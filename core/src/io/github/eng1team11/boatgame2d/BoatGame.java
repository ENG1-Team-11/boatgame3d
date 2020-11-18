package io.github.eng1team11.boatgame2d;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.utils.Timer;
import io.github.eng1team11.boatgame2d.ecs.*;
import io.github.eng1team11.boatgame2d.ecs.component.*;

public class BoatGame extends Game {

	private SpriteBatch _spriteBatch;
	private BitmapFont _font;

	public EntityManager _entityManager = new EntityManager();
	public ComponentManager _componentManager = new ComponentManager();
	public SystemManager _systemManager = new SystemManager();

	void RegisterECSComponents() {
		_componentManager.RegisterComponentTypeID(AI.class);
		_componentManager.RegisterComponentTypeID(Collider.class);
		_componentManager.RegisterComponentTypeID(Currency.class);
		_componentManager.RegisterComponentTypeID(Controller.class);
		_componentManager.RegisterComponentTypeID(Durability.class);
		_componentManager.RegisterComponentTypeID(PlayerInput.class);
		_componentManager.RegisterComponentTypeID(Sprite.class);
		_componentManager.RegisterComponentTypeID(Stamina.class);
		_componentManager.RegisterComponentTypeID(Type.class);
		_componentManager.RegisterComponentTypeID(Velocity.class);
		_componentManager.RegisterComponentTypeID(Position.class);
	}

	/**
	 * Called when the {@link Application} is first created.
	 */
	@Override
	public void create() {
		// Create a sprite batch
		_spriteBatch = new SpriteBatch();
		// Load the font (defaults to Arial)
		_font = new BitmapFont();

		// Register all classes to the ECS (probably should make a helper for this)
		RegisterECSComponents();
		// When the game instance is created, go to the main menu screen
//		this.setScreen(new TestScreen(this));
		this.setScreen(new TestScreen(this));

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
