package com.mau.test.dataaccess;

import com.mau.msgbaordV3_SBoot.app.dataaccess.MessageDaoMysqlV2;
import com.mau.msgbaordV3_SBoot.app.model.Message;
import com.mau.test.spring.config.TestConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;

//@Disabled("This test class is currently skipped") // Use @Disabled for JUnit 5
//@SpringBootTest
@SpringBootTest(classes = {MessageDaoMysqlV2.class, TestConfig.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestPropertySource(locations = "classpath:application-test.properties")
class MessageDaoMysqlV2Test {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private MessageDaoMysqlV2 messageDao;

    @BeforeEach
    void setUp() {
        jdbcTemplate.execute("DELETE FROM message where message_id > 0"); // Cleanup before each test
    }

    @Test
    void testSaveAndFindById() {
        // Use Timestamp instead of Date
        Timestamp creationTimestamp = new Timestamp(System.currentTimeMillis());
        Message message = new Message(1, "Test message", creationTimestamp);
        int generatedId = messageDao.save(message);

        assertTrue(generatedId > 0, "Message ID should be generated");

        Message retrievedMessage = messageDao.findById(generatedId);
        assertNotNull(retrievedMessage, "Message should be found");
        assertEquals("Test message", retrievedMessage.getContent(), "Content should match");
    }

    @Test
    void testUpdate() {
        // Use Timestamp instead of Date
        Timestamp creationTimestamp = new Timestamp(System.currentTimeMillis());
        Message message = new Message(1, "Initial message", creationTimestamp);
        int generatedId = messageDao.save(message);

        Message updatedMessage = new Message(generatedId, 1, "Updated message", creationTimestamp);
        boolean isUpdated = messageDao.update(updatedMessage);
        assertTrue(isUpdated, "Update should be successful");

        Message retrievedMessage = messageDao.findById(generatedId);
        assertEquals("Updated message", retrievedMessage.getContent(), "Updated content should match");
    }

    @Test
    void testDelete() {
        // Use Timestamp instead of Date
        Timestamp creationTimestamp = new Timestamp(System.currentTimeMillis());
        Message message = new Message(1, "Message to delete", creationTimestamp);
        int generatedId = messageDao.save(message);

        boolean isDeleted = messageDao.delete(generatedId);
        assertTrue(isDeleted, "Deletion should be successful");

        assertThrows(RuntimeException.class, () -> messageDao.findById(generatedId), "Message should not be found");
    }

}
