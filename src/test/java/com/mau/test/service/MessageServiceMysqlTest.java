package com.mau.test.service;

import com.mau.msgbaordV3_SBoot.app.model.Message;
import com.mau.msgbaordV3_SBoot.app.service.MessageService;
import com.mau.msgbaordV3_SBoot.app.service.MessageServiceMysql;
import com.mau.test.spring.config.TestConfig;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


//@SpringBootTest(classes = {MessageServiceMysql.class, TestConfig.class})
@Disabled("This test class is currently skipped") // Use @Disabled for JUnit 5
@SpringBootTest(classes = {MessageServiceMysql.class, TestConfig.class})
@Transactional // Ensures the database rolls back after each test
public class MessageServiceMysqlTest {

//    @Autowired
//    private MessageServiceMysql messageService; // Uses the real implementation


    @Autowired
    private MessageService messageService; // Uses the real implementation

    private Message testMessage;

    @BeforeEach
    void setUp() {
        testMessage = new Message(1, "Integration test message", new Timestamp(System.currentTimeMillis()));
    }

    @Test
    public void testSaveMessage() {
        int messageId = messageService.saveMessage(testMessage);
        assertNotEquals(0, messageId, "Message ID should not be 0");

        Message retrievedMessage = messageService.getMessageById(messageId);
        assertNotNull(retrievedMessage, "Saved message should be retrievable");
        assertEquals("Integration test message", retrievedMessage.getContent());
    }

    @Test
    public void testGetAllMessages() {
        messageService.saveMessage(testMessage);
        List<Message> messages = messageService.getAllMessages();

        assertFalse(messages.isEmpty(), "Messages list should not be empty");
        assertTrue(messages.stream().anyMatch(m -> "Integration test message".equals(m.getContent())));
    }

    @Test
    public void testUpdateMessage() {
        int messageId = messageService.saveMessage(testMessage);
        testMessage.setMessageId(messageId);
        testMessage.setContent("Updated content");

        boolean updated = messageService.updateMessage(testMessage);
        assertTrue(updated, "Message should be updated");

        Message updatedMessage = messageService.getMessageById(messageId);
        assertEquals("Updated content", updatedMessage.getContent(), "Message content should be updated");
    }

    @Test
    public void testDeleteMessage() {
        int messageId = messageService.saveMessage(testMessage);

        boolean deleted = messageService.deleteMessage(messageId);
        assertTrue(deleted, "Message should be deleted");

        assertThrows(RuntimeException.class, () -> messageService.getMessageById(messageId),
                "Fetching a deleted message should throw an exception");
    }
}
