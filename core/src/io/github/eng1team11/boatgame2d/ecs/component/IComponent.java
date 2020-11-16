package io.github.eng1team11.boatgame2d.ecs.component;

public interface IComponent {

    /**
     * Get the ID of the component
     * @return the ID of the component as an integer
     */
    public int GetID();

    /**
     * Get the type of the component
     * @return the type of the component as an integer
     */
    public int GetType();

}
