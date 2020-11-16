package io.github.eng1team11.boatgame2d.ecs;

import io.github.eng1team11.boatgame2d.ecs.component.Component;
import io.github.eng1team11.boatgame2d.ecs.component.IComponent;

import java.lang.reflect.Constructor;
import java.util.HashMap;

public class ComponentManager {

    // Easily convert between components<->ids
    HashMap<Integer, Class> _componentTypes;
    HashMap<Class, Integer> _typesComponent;

    HashMap<Integer, IComponent> _components;

    public int GetTypeID(Class type) {
        if (_typesComponent.containsKey(type)) {
            return _typesComponent.get(type);
        }
        else {
            int index = _componentTypes.size();
            _componentTypes.put(index, type);
            _typesComponent.put(type, index);
            return index;
        }
    }

    IComponent CreateComponent(int id, int type) {
//        Class comp = _componentTypes.get(type);
//        Constructor<Component> ctor = comp.newInstance();
//        IComponent component = (IComponent) ctor.newInstance(id, type);
//        return component;
        return new Component(0, 0);
    }

    public int AddComponent(int type) {
        int id = _components.size();
        IComponent component = CreateComponent(id, type);
        _components.put(id, component);
        return id;
    }

    public int AddComponent(Class type) {
        int iType = GetTypeID(type);
        int id = _components.size();
        IComponent component = CreateComponent(id, iType);
        _components.put(id, component);
        return id;
    }

    public void DeleteComponent(int id) {
        _components.remove(id);
    }

    IComponent GetComponent(int id) {
        return _components.get(id);
    }

}
