package io.github.eng1team11.boatgame2d.ecs;

import io.github.eng1team11.boatgame2d.ecs.component.Component;
import io.github.eng1team11.boatgame2d.ecs.component.IComponent;
import io.github.eng1team11.boatgame2d.ecs.entity.IEntity;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("rawtypes")   // Suppress warnings about using `Class` directly (Need to find a way to not use `Class`)
public class ComponentManager {

    // 1:1 mapping of class to id, id to class
    HashMap<Class, Integer> _componentTypes = new HashMap<>();
    HashMap<Integer, Class> _typesComponent = new HashMap<>();

    HashMap<Integer, HashMap<Integer, IComponent>> _components = new HashMap<>();

    /**
     * Register a component type to the component manager
     *
     * @param component A component of the type to be registered
     * @return The component type ID as an integer
     */
    public int registerComponentTypeID(Component component) {
        if (!_componentTypes.containsKey(component.getClass())) {
            int id = _componentTypes.size();
            _componentTypes.put(component.getClass(), id);
            _typesComponent.put(id, component.getClass());
        }
        return _componentTypes.get(component.getClass());
    }

    /**
     * Register a component type to the component manager
     *
     * @param component The class which should be registered (use `Component.class`)
     * @return The component type ID as an integer
     */
    public int registerComponentTypeID(Class component) {
        if (!_componentTypes.containsKey(component)) {
            int id = _componentTypes.size();
            _componentTypes.put(component, id);
            _typesComponent.put(id, component);
            _components.put(id, new HashMap<Integer, IComponent>());
        }
        return _componentTypes.get(component);
    }

    /**
     * Get the component type ID of a component
     *
     * @param component A component of the type to be looked up
     * @return The component type ID as an integer
     */
    public int getComponentTypeID(Component component) {
        return _componentTypes.get(component.getClass());
    }

    /**
     * Get the component class which corresponds to a specific type ID
     *
     * @param id The ID to be looked up
     * @return The component type as a Java `Class` object
     */
    public Class getIDComponentType(int id) {
        return _typesComponent.get(id);
    }

    /**
     * Add a component to the component lists
     *
     * @param entity    The entity to add the component to
     * @param component The component to be added
     */
    public void addComponent(IEntity entity, Component component) {
        int cType = getComponentTypeID(component);
        _components.get(cType).put(entity.getID(), component);
    }

    /**
     * Add a component to the components lists
     *
     * @param entity    The entity ID to add the component to
     * @param component The component to be added
     */
    public void addComponent(int entity, Component component) {
        int cType = getComponentTypeID(component);
        _components.get(cType).put(entity, component);
    }

    /**
     * Delete a component from an entity
     *
     * @param type   The type of component
     * @param entity The entity ID
     */
    public void deleteComponent(int type, int entity) {
        _components.get(type).remove(entity);
    }

    /**
     * Delete all components of one type
     *
     * @param id The component type ID to delete
     */
    public void deleteComponentsOfType(int id) {
        _components.remove(id);
    }

    /**
     * Get all components of a specific type
     *
     * @param id The component type ID
     * @return An `ArrayList` containing all components of a specific type
     */
    public HashMap<Integer, IComponent> getComponentsOfType(int id) {
        return _components.get(id);
    }

    /**
     * Get all components of a specific type
     *
     * @param type The component type (`Component.class`)
     * @return An `ArrayList` containing all components of a specific type
     */

    public HashMap<Integer, IComponent> getComponentsOfType(Class type) {
        int id = _componentTypes.get(type);
        return _components.get(id);
    }

    /**
     * Delete all existing components
     */
    public void clear() {
        for (Map.Entry<Integer, HashMap<Integer, IComponent>> x : _components.entrySet()) {
            x.getValue().clear();
        }
    }

}
