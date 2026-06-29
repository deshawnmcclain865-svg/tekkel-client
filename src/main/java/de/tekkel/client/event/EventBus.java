package de.tekkel.client.event;

import java.lang.reflect.Method;
import java.util.*;

/**
 * Central event bus for dispatching events across the client
 */
public class EventBus {
    
    private final Map<Class<?>, List<EventListener>> listeners = new HashMap<>();
    
    public void register(Object object) {
        for (Method method : object.getClass().getMethods()) {
            if (method.isAnnotationPresent(EventHandler.class)) {
                if (method.getParameterCount() != 1) continue;
                
                Class<?> eventType = method.getParameterTypes()[0];
                if (!Event.class.isAssignableFrom(eventType)) continue;
                
                listeners.computeIfAbsent(eventType, k -> new ArrayList<>())
                    .add(new EventListener(object, method));
            }
        }
    }
    
    public void unregister(Object object) {
        listeners.values().forEach(list -> 
            list.removeIf(listener -> listener.getObject() == object)
        );
    }
    
    public void post(Event event) {
        List<EventListener> eventListeners = listeners.get(event.getClass());
        if (eventListeners != null) {
            for (EventListener listener : eventListeners) {
                try {
                    listener.call(event);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    public void clear() {
        listeners.clear();
    }
}
