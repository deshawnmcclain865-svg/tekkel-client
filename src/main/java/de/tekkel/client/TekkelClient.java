package de.tekkel.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.List;

public class TekkelClient {
    private static final Logger LOGGER = LoggerFactory.getLogger("tekkel");
    private static final TekkelClient INSTANCE = new TekkelClient();
    private final List<Object> modules = new ArrayList<>();
    private final List<Object> hudElements = new ArrayList<>();

    public static TekkelClient getInstance() {
        return INSTANCE;
    }

    public static Logger getLogger() {
        return LOGGER;
    }

    public void addModule(Object module) {
        modules.add(module);
        LOGGER.info("Module added: " + module.getClass().getSimpleName());
    }

    public void addHUDElement(Object element) {
        hudElements.add(element);
        LOGGER.info("HUD Element added: " + element.getClass().getSimpleName());
    }

    public void shutdown() {
        LOGGER.info("Tekkel Client shutting down...");
        modules.clear();
        hudElements.clear();
    }
}
