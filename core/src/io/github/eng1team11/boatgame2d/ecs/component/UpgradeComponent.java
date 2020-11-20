package io.github.eng1team11.boatgame2d.ecs.component;

public class UpgradeComponent extends Component {
    float _staMod = 1.0f;
    float _durMod = 1.0f;
    float _velMod = 1.0f;

    /**
     * Default ctor for a component
     *
     * @param id the ID of the a component
     */
    public UpgradeComponent(int id) {
        super(id);
    }

    void SetStaMod(float sta) {
        _staMod = sta;
    }

    void SetDurMod(float dur) {
        _durMod = dur;
    }

    void SetVelMod(float vel) {
        _velMod = vel;
    }

    public float GetStaMod() {
        return _staMod;
    }

    public float GetDurMod() {
        return _durMod;
    }

    public float GetVelMod() {
        return _velMod;
    }
}
