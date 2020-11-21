package io.github.eng1team11.boatgame2d.ecs;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import io.github.eng1team11.boatgame2d.ecs.component.*;

public class EntityFactory {

    public static EntityFactory ef = new EntityFactory();

    private EntityFactory() {};

    public static EntityFactory Get() { return ef; }

    public int CreateDrawableObject(ComponentManager cm, EntityManager em, float x, float y, int width, int height, Texture texture) {
        int object = em.CreateEntity();
        cm.AddComponent(object, new SpriteComponent(object, texture, width, height));
        cm.AddComponent(object, new PositionComponent(object, x, y));
        return object;
    }

    public int CreateBoat(ComponentManager cm, EntityManager em, float x, float y, int width, int height, Texture texture) {
        int boat = CreateDrawableObject(cm ,em, x, y, width, height, texture);
        cm.AddComponent(boat, new ColliderComponent(boat, width, height));
        cm.AddComponent(boat, new UpgradeComponent(boat));
        cm.AddComponent(boat, new AccelerationComponent(boat));
        cm.AddComponent(boat, new DurabilityComponent(boat));
        cm.AddComponent(boat, new StaminaComponent(boat));
        cm.AddComponent(boat, new VelocityComponent(boat));
        return boat;
    }

    public int CreatePlayer(ComponentManager cm, EntityManager em, float x, float y, int width, int height, Texture texture) {
        int player = CreateBoat(cm, em, x, y, width, height, texture);
        cm.AddComponent(player, new PlayerInputComponent(player));
        cm.AddComponent(player, new CurrencyComponent(player));
        return player;
    }

    public int CreateAI(ComponentManager cm, EntityManager em, float x, float y, int width, int height, Texture texture) {
        int ai = CreateBoat(cm, em, x, y, width, height, texture);
        cm.AddComponent(ai, new AIComponent(ai));
        return ai;
    }

    public int CreateObstacle(ComponentManager cm, EntityManager em, float x, float y, int width, int height, Texture texture) {
        int obstacle = CreateDrawableObject(cm, em, x, y, width, height, texture);
        cm.AddComponent(obstacle, new ColliderComponent(obstacle, width, height));
        return obstacle;
    }

}
