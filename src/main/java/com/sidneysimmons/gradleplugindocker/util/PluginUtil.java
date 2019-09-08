package com.sidneysimmons.gradleplugindocker.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.gradle.api.logging.Logger;

/**
 * Utility methods for the plugin.
 * 
 * @author Sidney Simmons
 */
public class PluginUtil {

    private PluginUtil() {
        // No need to instantiate this
    }

    /**
     * Log output from the provided process. Any lines that are empty after trimming will be ignored.
     * 
     * @param process the process
     * @param logger  the logger
     * @throws IOException thrown if there is a problem reading the output
     */
    public static void logProcessOutput(Process process, Logger logger) throws IOException {
        try (BufferedReader inputReader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String line = null;
            while ((line = inputReader.readLine()) != null) {
                line = line.trim();
                if (line.length() > 0) {
                    logger.lifecycle(line);
                }
            }
        }
    }

}
