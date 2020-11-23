package io.github.eng1team11.boatgame2d.ecs;


import com.badlogic.gdx.graphics.Texture;
import io.github.eng1team11.boatgame2d.ecs.component.*;

public class EntityFactory {

    ComponentManager _cm;
    EntityManager _em;

    public static EntityFactory ef = new EntityFactory();   // Static object

    /**
     * Private c'tor
     */
    private EntityFactory() {

    }

    /**
     * Singleton getter method
     *
     * @return The single object
     */
    public static EntityFactory get() {
        return ef;
    }

    /**
     * Set the component manager to use
     * @param cm The current component manager instance
     */
    public void setComponentManager(ComponentManager cm) {
        _cm = cm;
    }

    /**
     * Set the entity manager to use
     * @param em The current entity manager instance
     */
    public void setEntityManager(EntityManager em) {
        _em = em;
    }

    /**
     * Create a new drawable entity
     *
     * @param x       The x position of the object
     * @param y       The y position of the object
     * @param width   The width of the object
     * @param height  The height of the object
     * @param texture The texture to give the object
     * @return The ID of the new entity
     */
    public int createDrawableEntity(float x, float y, int width, int height, Texture texture) {
        int object = _em.createEntity();
        _cm.addComponent(object, new SpriteComponent(object, texture, width, height));
        _cm.addComponent(object, new PositionComponent(object, x, y));
        return object;
    }

    /**
     * Create a new boat entity
     *
     * @param x       The x position of the object
     * @param y       The y position of the object
     * @param width   The width of the object
     * @param height  The height of the object
     * @param texture The texture to give the object
     * @return The ID of the new entity
     */
    public int createBoatEntity(float x, float y, int width, int height, Texture texture) {
        int boat = createDrawableEntity(x, y, width, height, texture);
        _cm.addComponent(boat, new ColliderComponent(boat, width, height));
        _cm.addComponent(boat, new UpgradeComponent(boat));
        _cm.addComponent(boat, new AccelerationComponent(boat));
        _cm.addComponent(boat, new DurabilityComponent(boat, 1000.0f));
        _cm.addComponent(boat, new StaminaComponent(boat));
        _cm.addComponent(boat, new VelocityComponent(boat));
        return boat;
    }

    /**
     * Create a new player boat entity
     *
     * @param x       The x position of the object
     * @param y       The y position of the object
     * @param width   The width of the object
     * @param height  The height of the object
     * @param texture The texture to give the object
     * @return The ID of the new entity
     */
    public int createPlayerEntity(float x, float y, int width, int height, Texture texture) {
        int player = createBoatEntity(x, y, width, height, texture);
        _cm.addComponent(player, new PlayerInputComponent(player));
        _cm.addComponent(player, new CurrencyComponent(player));
        return player;
    }

    /**
     * Create a new AI boat entity
     *
     * @param x       The x position of the object
     * @param y       The y position of the object
     * @param width   The width of the object
     * @param height  The height of the object
     * @param texture The texture to give the object
     * @return The ID of the new entity
     */
    public int createAIEntity(float x, float y, int width, int height, Texture texture) {
        int ai = createBoatEntity(x, y, width, height, texture);
        _cm.addComponent(ai, new AIComponent(ai));
        return ai;
    }

    /**
     * Create a new obstacle entity
     *
     * @param x       The x position of the object
     * @param y       The y position of the object
     * @param width   The width of the object
     * @param height  The height of the object
     * @param texture The texture to give the object
     * @return The ID of the new entity
     */
    public int createObstacleEntity(float x, float y, int width, int height, Texture texture) {
        int obstacle = createDrawableEntity(x, y, width, height, texture);
        _cm.addComponent(obstacle, new ColliderComponent(obstacle, width, height));
        _cm.addComponent(obstacle, new DurabilityComponent(obstacle, 0.01f));
        _cm.addComponent(obstacle, new VelocityComponent(obstacle, -10.0f, 0.0f));
        return obstacle;
    }

}
