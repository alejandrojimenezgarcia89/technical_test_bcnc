package com.technicaltest.bcnc.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class FileUtilsTest {

    @InjectMocks
    private FileUtils fileUtils;

    @Test
    void testReadSql_Success() throws IOException {
        String sqlContent = fileUtils.readSql("test.sql");
        assertNotNull(sqlContent);
        assertFalse(sqlContent.isEmpty());
    }

    @Test
    void testReadSql_FileNotFound() {
        assertThrows(IOException.class, () -> fileUtils.readSql("nonexistent.sql"));
    }
}