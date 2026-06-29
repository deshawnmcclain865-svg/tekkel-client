package de.tekkel.client.event;

import java.lang.annotation.*;

/**
 * Annotation for event handler methods
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface EventHandler {
}
