package io.github.eng1team11.boatgame2d.ecs;

import io.github.eng1team11.boatgame2d.ecs.component.Component;
import io.github.eng1team11.boatgame2d.ecs.component.IComponent;
import io.github.eng1team11.boatgame2d.ecs.entity.IEntity;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;

public class ComponentManager {

    // 1:1 mapping of class to id, id to class
    HashMap<Class, Integer> _componentTypes = new HashMap<Class, Integer>();
    HashMap<Integer, Class> _typesComponent = new HashMap<Integer, Class>();

    HashMap<Integer, ArrayList<IComponent>> _components = new HashMap<Integer, ArrayList<IComponent>>();

    /**
     * Register a component type to the component manager
     * @param component A component of the type to be registered
     * @return The component type ID as an integer
     */
    public int RegisterComponentTypeID(Component component) {
        if (!_componentTypes.containsKey(component.getClass())) {
            int id = _componentTypes.size();
            _componentTypes.put(component.getClass(), id);
            _typesComponent.put(id, component.getClass());
        }
        return _componentTypes.get(component.getClass());
    }

    /**
     * Register a component type to the component manager
     * @param component The class which should be registered (use `Component.class`)
     * @return The component type ID as an integer
     */
    public int RegisterComponentTypeID(Class component) {
        if (!_componentTypes.containsKey(component)) {
            int id = _componentTypes.size();
            _componentTypes.put(component, id);
            _typesComponent.put(id, component);
            _components.put(id, new ArrayList<IComponent>());
        }
        return _componentTypes.get(component);
    }

    /**
     * Get the component type ID of a component
     * @param component A component of the type to be looked up
     * @return The component type ID as an integer
     */
    public int GetComponentTypeID(Component component) {
        return _componentTypes.get(component.getClass());
    }

    /**
     * Get the component class which corresponds to a specific type ID
     * @param id The ID to be looked up
     * @return The component type as a Java Class object
     */
    public Class GetIDComponentType(int id) {
        return _typesComponent.get(id);
    }

    /**
     * Add a component to the component lists
     * @param component The component to be added
     */
    public void AddComponent(Component component) {
        int cType = GetComponentTypeID(component);
        _components.get(cType).add(component);
    }

    /**
     * Delete a component from an entity
     * @param type The type of component
     * @param entity The entity ID
     */
    public void DeleteComponent(int type, int entity) {
        _components.get(type).remove(entity);
    }

    /**
     * Delete all components of one type
     * @param id the component type ID to delete
     */
    public void DeleteComponentsOfType(int id) {
        _components.remove(id);
    }

    /**
     * Get all components of a specific type
     * @param id The component type ID
     * @return An ArrayList containing all components of a specific type
     */
    public ArrayList<IComponent> GetComponentsOfType(int id) {
        return _components.get(id);
    }

}
