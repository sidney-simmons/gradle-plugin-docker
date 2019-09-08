package com.sidneysimmons.gradleplugindocker.util;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import org.gradle.api.logging.Logger;
import org.junit.Test;

/**
 * Unit tests for the {@link PluginUtil} class.
 * 
 * @author Sidney Simmons
 */
public class PluginUtilTest {

    /**
     * Test that we log each not null/blank line in an input stream from a logger.
     * 
     * @throws IOException thrown if something crazy happens
     */
    @Test
    public void testLogProcessOutput() throws IOException {
        // Mock a process and a logger
        Process mockProcess = mock(Process.class);
        Logger mockLogger = mock(Logger.class);
        when(mockProcess.getInputStream())
                .thenReturn(new ByteArrayInputStream("Should\nbe\ncalled\n     \n5\ntimes.".getBytes()));

        // Assert that the logger logs 5 lines
        PluginUtil.logProcessOutput(mockProcess, mockLogger);
        verify(mockLogger, times(5)).lifecycle(any());
    }

}
