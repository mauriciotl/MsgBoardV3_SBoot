package com.mau.test.service;

import com.mau.msgbaordV3_SBoot.app.dataaccess.MessageDaoV2;
import com.mau.msgbaordV3_SBoot.app.dataaccess.MessageDaoMysqlV2;
import com.mau.msgbaordV3_SBoot.app.model.Message;
import com.mau.msgbaordV3_SBoot.app.service.MessageServiceMysql;
import com.mau.test.spring.config.TestConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {MessageServiceMysql.class, MessageDaoV2.class, MessageDaoMysqlV2.class, TestConfig.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)  // Use real DB
public class messageservicemysqlvDosTest {

    @Autowired
    private MessageServiceMysql messageService;

    @Autowired
    @Qualifier("MessageDaoMysqlV2") // Use the specific bean name and correct implementation.
    private MessageDaoV2 messageDao;

    private Message testMessage;

    @BeforeEach
    public void setUp() {
//        testMessage = new Message(1, "Test message from service implementation", new Timestamp(System.currentTimeMillis()));
//
//        // Optionally clear DB before tests or insert test data
//        messageDao.delete(testMessage.getMessageId());
//        int messageId = messageDao.save(testMessage);  // Save the test message
    }

    @Test
    public void testSaveMessage() {
        // Given
//        testMessage.setContent("Updated test message");

        testMessage = new Message(1, "Test message from service implementation", new Timestamp(System.currentTimeMillis()));

        // When
        int result = messageService.saveMessage(testMessage);
        testMessage.setMessageId(result);

        // Then
//        assertEquals(1, result);
        assertNotNull(messageDao.findById(testMessage.getMessageId()));
    }

//    @Test
//    public void testGetMessageById() {
//        // Given
//        Message savedMessage = messageDao.findById(1);
//
//        // When
//        Message result = messageService.getMessageById(savedMessage.getMessageId());
//
//        // Then
//        assertNotNull(result);
//        assertEquals(savedMessage.getMessageId(), result.getMessageId());
//    }

    @Test
    public void testGetAllMessages() {
//        // Given
//        List<Message> messages = Collections.singletonList(testMessage);
//        messageDao.save(testMessage);

        // When
        List<Message> result = messageService.getAllMessages();

        // Then
        assertNotNull(result);
        assertFalse(result.isEmpty());
//        assertEquals(1, result.size());
    }

    @Test
    public void testUpdateMessage() {
        // Given
//        testMessage.setContent("Updated message");
//        messageDao.save(testMessage);

        testMessage = new Message(1, "Test message from service implementation", new Timestamp(System.currentTimeMillis()));

        int messageId = messageService.saveMessage(testMessage);

        // When

        Message message = messageService.getMessageById(messageId);

        message.setContent("Test message UPDATED from service implementation");

        boolean result = messageService.updateMessage(message);

        // Then
        assertTrue(result);
        assertEquals("Test message UPDATED from service implementation", messageDao.findById(message.getMessageId()).getContent());
    }

    @Test
    public void testDeleteMessage() {
//        // Given
//        messageDao.save(testMessage);

        testMessage = new Message(1, "Test message from service implementation", new Timestamp(System.currentTimeMillis()));

        int messageId = messageService.saveMessage(testMessage);

        // When
        boolean result = messageService.deleteMessage(messageId);

        // Then
        assertTrue(result);
//        assertNull(messageDao.findById(testMessage.getMessageId()));
    }
}
