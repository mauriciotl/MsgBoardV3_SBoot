package com.mau.test.dataaccess;

import com.mau.msgbaordV3_SBoot.app.dataaccess.MessageDaoMysql;
import com.mau.msgbaordV3_SBoot.app.model.Message;
import com.mau.test.spring.config.TestConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/*
NOTE. This class' methods used the four field constructor.
 */

@Disabled("This test class is currently skipped") // Use @Disabled for JUnit 5
@SpringBootTest(classes = {MessageDaoMysql.class, TestConfig.class})
@Transactional
public class MessageDaoMysqlTest {

    @Autowired
    private MessageDaoMysql messageDaoMysql;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    public void setUp() {
        // Clear the Message table before each test
        jdbcTemplate.update("DELETE FROM Message");
    }

//    @Disabled("This test method is currently skipped")
    @Test
    public void testSaveMessage() {
        Message message = new Message(0, 1, "Test Content", new Timestamp(System.currentTimeMillis()));


        // Retrieve the auto-generated message_id
        int messageId = messageDaoMysql.save(message);

        Message savedMessage = messageDaoMysql.findById(messageId);
        assertNotNull(savedMessage);
        assertEquals("Test Content", savedMessage.getContent());
    }

    @Test
    public void testFindById() {
        Message message = new Message(0, 1, "Test Content", new Timestamp(System.currentTimeMillis()));


        // Retrieve the auto-generated message_id
        int messageId = messageDaoMysql.save(message);

        Message foundMessage = messageDaoMysql.findById(messageId);
        assertNotNull(foundMessage);
        assertEquals(messageId, foundMessage.getMessageId());
        assertEquals("Test Content", foundMessage.getContent());
    }

    @Test
    public void testFindAll() {
        Message message1 = new Message(0, 1, "Test Content 1", new Timestamp(System.currentTimeMillis()));
        Message message2 = new Message(0, 2, "Test Content 2", new Timestamp(System.currentTimeMillis()));
        messageDaoMysql.save(message1);
        messageDaoMysql.save(message2);

        List<Message> messages = messageDaoMysql.findAll();
        assertEquals(2, messages.size());
    }

    @Test
    public void testUpdateMessage() {
        Message message = new Message(0, 1, "Test Content", new Timestamp(System.currentTimeMillis()));


        // Retrieve the auto-generated message_id
        int messageId = messageDaoMysql.save(message);

        message.setContent("Updated Content");
        message.setMessageId(messageId);
        messageDaoMysql.update(message);

        Message updatedMessage = messageDaoMysql.findById(messageId);
        assertEquals("Updated Content", updatedMessage.getContent());
    }

    @Test
    public void testDeleteMessage() {
        Message message = new Message(0, 1, "Test Content", new Timestamp(System.currentTimeMillis()));


        // Retrieve the auto-generated message_id
        int messageId = messageDaoMysql.save(message);

        messageDaoMysql.delete(messageId);

        Message deletedMessage = messageDaoMysql.findById(messageId);

        assertNull(deletedMessage);
    }
}