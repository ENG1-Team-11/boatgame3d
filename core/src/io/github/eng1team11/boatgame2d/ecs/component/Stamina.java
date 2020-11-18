package io.github.eng1team11.boatgame2d.ecs.component;

public class Stamina extends Component {

    float _maxStamina = 0.0f;
    float _stamina = 0.0f;

    /**
     * Default ctor for a component
     *
     * @param id   the ID of the a component
     * @param type
     */
    public Stamina(int id) {
        super(id);
    }
}
