package com.example.PetPalHub.Backup;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BackUpDataBaseTest {

    @Test
    void testBackUpDb() {
        assertTrue(BackUpDataBase.backUpDb());
    }
    @Test
    void testBackUpDbForNoErrors() {
        assertDoesNotThrow(BackUpDataBase::backUpDb);
    }
    @Test
    void testRestoreDBForNoErrors() {
        assertDoesNotThrow(() -> BackUpDataBase.restoreDB());
    }
    @Test
    void testRestoreDB() {
        assertDoesNotThrow(BackUpDataBase::restoreDB);
    }
}
