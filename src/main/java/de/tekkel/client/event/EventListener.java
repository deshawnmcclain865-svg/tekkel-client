package de.tekkel.client.event;

import java.lang.reflect.Method;

/**
 * Represents an event listener
 */
public class EventListener {
    
    private final Object object;
    private final Method method;
    
    public EventListener(Object object, Method method) {
        this.object = object;
        this.method = method;
        this.method.setAccessible(true);
    }
    
    public void call(Event event) throws Exception {
        method.invoke(object, event);
    }
    
    public Object getObject() { return object; }
}
