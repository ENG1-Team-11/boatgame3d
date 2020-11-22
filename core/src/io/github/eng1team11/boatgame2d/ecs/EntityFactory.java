package io.github.eng1team11.boatgame2d.ecs;


import com.badlogic.gdx.graphics.Texture;
import io.github.eng1team11.boatgame2d.ecs.component.*;

public class EntityFactory {

    public static EntityFactory ef = new EntityFactory();   // Static object

    /**
     * Private c'tor
     */
    private EntityFactory() {
    }

    ;

    /**
     * Singleton getter method
     *
     * @return The single object
     */
    public static EntityFactory get() {
        return ef;
    }

    /**
     * Create a new drawable entity
     *
     * @param cm      The component manager
     * @param em      The entity manager
     * @param x       The x position of the object
     * @param y       The y position of the object
     * @param width   The width of the object
     * @param height  The height of the object
     * @param texture The texture to give the object
     * @return The ID of the new entity
     */
    public int createDrawableEntity(ComponentManager cm, EntityManager em, float x, float y, int width, int height, Texture texture) {
        int object = em.createEntity();
        cm.addComponent(object, new SpriteComponent(object, texture, width, height));
        cm.addComponent(object, new PositionComponent(object, x, y));
        return object;
    }

    /**
     * Create a new boat entity
     *
     * @param cm      The component manager
     * @param em      The entity manager
     * @param x       The x position of the object
     * @param y       The y position of the object
     * @param width   The width of the object
     * @param height  The height of the object
     * @param texture The texture to give the object
     * @return The ID of the new entity
     */
    public int createBoatEntity(ComponentManager cm, EntityManager em, float x, float y, int width, int height, Texture texture) {
        int boat = createDrawableEntity(cm, em, x, y, width, height, texture);
        cm.addComponent(boat, new ColliderComponent(boat, width, height));
        cm.addComponent(boat, new UpgradeComponent(boat));
        cm.addComponent(boat, new AccelerationComponent(boat));
        cm.addComponent(boat, new DurabilityComponent(boat));
        cm.addComponent(boat, new StaminaComponent(boat));
        cm.addComponent(boat, new VelocityComponent(boat));
        return boat;
    }

    /**
     * Create a new player boat entity
     *
     * @param cm      The component manager
     * @param em      The entity manager
     * @param x       The x position of the object
     * @param y       The y position of the object
     * @param width   The width of the object
     * @param height  The height of the object
     * @param texture The texture to give the object
     * @return The ID of the new entity
     */
    public int createPlayerEntity(ComponentManager cm, EntityManager em, float x, float y, int width, int height, Texture texture) {
        int player = createBoatEntity(cm, em, x, y, width, height, texture);
        cm.addComponent(player, new PlayerInputComponent(player));
        cm.addComponent(player, new CurrencyComponent(player));
        return player;
    }

    /**
     * Create a new AI boat entity
     *
     * @param cm      The component manager
     * @param em      The entity manager
     * @param x       The x position of the object
     * @param y       The y position of the object
     * @param width   The width of the object
     * @param height  The height of the object
     * @param texture The texture to give the object
     * @return The ID of the new entity
     */
    public int createAIEntity(ComponentManager cm, EntityManager em, float x, float y, int width, int height, Texture texture) {
        int ai = createBoatEntity(cm, em, x, y, width, height, texture);
        cm.addComponent(ai, new AIComponent(ai));
        return ai;
    }

    /**
     * Create a new obstacle entity
     *
     * @param cm      The component manager
     * @param em      The entity manager
     * @param x       The x position of the object
     * @param y       The y position of the object
     * @param width   The width of the object
     * @param height  The height of the object
     * @param texture The texture to give the object
     * @return The ID of the new entity
     */
    public int createObstacleEntity(ComponentManager cm, EntityManager em, float x, float y, int width, int height, Texture texture) {
        int obstacle = createDrawableEntity(cm, em, x, y, width, height, texture);
        cm.addComponent(obstacle, new ColliderComponent(obstacle, width, height));
        return obstacle;
    }

}
