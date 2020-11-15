package io.github.eng1team11.boatgame2d.ecs.system;

public interface ISystem {
    /**
     * Called during the input phase of the game engine loop
     * @param delta The time since the completion of the last frame in seconds
     */
    void Input(float delta);

    /**
     * Called during the update phase of the game engine loop
     * @param delta The time since the completion of the last frame in seconds
     */
    void Update(float delta);
    /**
     * Called during the render phase of the game engine loop
     * @param delta The time since the completion of the last frame in seconds
     */
    void Render(float delta);
}
