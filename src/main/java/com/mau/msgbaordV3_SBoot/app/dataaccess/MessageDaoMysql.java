package com.mau.msgbaordV3_SBoot.app.dataaccess;

import com.mau.msgbaordV3_SBoot.app.model.Message;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;

@Repository
public class MessageDaoMysql implements MessageDao {

    private final JdbcTemplate jdbcTemplate;
    private static final Logger logger = LoggerFactory.getLogger(MessageDaoMysql.class);

    public MessageDaoMysql(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

//    @Override
//    public void save(Message message) {
//        String sql = "INSERT INTO Message (user_id, content, creation_date) VALUES (?, ?, ?)";
//        logger.info("Saving message: userId={}, content={}, creationDate={}", message.getUserId(), message.getContent(), message.getCreationDate());
//        jdbcTemplate.update(sql, message.getUserId(), message.getContent(), message.getCreationDate());
//        logger.info("Message saved successfully.");
//    }

    @Override
    public int save(Message message) {
        String sql = "INSERT INTO Message (user_id, content, creation_date) VALUES (?, ?, ?)";
        logger.info("Saving message: userId={}, content={}, creationDate={}", message.getUserId(), message.getContent(), message.getCreationDate());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, message.getUserId());
            ps.setString(2, message.getContent());
            ps.setTimestamp(3, message.getCreationDate());
            return ps;
        }, keyHolder);
        int generatedId = Objects.requireNonNull(keyHolder.getKey()).intValue();
        logger.info("Message saved successfully with ID: {}", generatedId);
        return generatedId;
    }



//    @Override
//    public Message findById(int messageId) {
//        String sql = "SELECT * FROM Message WHERE message_id = ?";
//        logger.info("Finding message by ID: {}", messageId);
//        Message message = jdbcTemplate.queryForObject(sql, new MessageRowMapper(), messageId);
//        logger.info("Message found: {}", message);
//        return message;
//    }

    @Override
    public Message findById(int messageId) {
        String sql = "SELECT * FROM Message WHERE message_id = ?";
        logger.info("Finding message by ID: {}", messageId);
        Message message = null;
        try {
            message = jdbcTemplate.queryForObject(sql, new MessageRowMapper(), messageId);
            logger.info("Message found: {}", message);
        } catch (EmptyResultDataAccessException e) {
            logger.info("No message found for ID: {}", messageId);
        }
        return message;
    }


//    @Override
//    public Message findById(int messageId) {
//        String sql = "SELECT * FROM Message WHERE message_id = ?";
//        logger.info("Finding message by ID: {}", messageId);
//        List<Message> messages = jdbcTemplate.query(sql, new MessageRowMapper(), messageId);
//        if (messages.isEmpty()) {
//            logger.warn("No message found with ID: {}", messageId);
//            return null;
//        }
//        return messages.get(0);
//    }




    @Override
    public List<Message> findAll() {
        String sql = "SELECT * FROM Message";
        logger.info("Fetching all messages.");
        List<Message> messages = jdbcTemplate.query(sql, new MessageRowMapper());
        logger.info("Number of messages fetched: {}", messages.size());
        return messages;
    }

    @Override
    public void update(Message message) {
        String sql = "UPDATE Message SET content = ?, creation_date = ? WHERE message_id = ?";
        logger.info("Updating message with ID: {}", message.getMessageId());
        jdbcTemplate.update(sql, message.getContent(), message.getCreationDate(), message.getMessageId());
        logger.info("Message with ID {} updated successfully.", message.getMessageId());
    }

    @Override
    public void delete(int messageId) {
        String sql = "DELETE FROM Message WHERE message_id = ?";
        logger.info("Deleting message with ID: {}", messageId);
        jdbcTemplate.update(sql, messageId);
        logger.info("Message with ID {} deleted successfully.", messageId);
    }

    private static class MessageRowMapper implements RowMapper<Message> {
        @Override
        public Message mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Message(
                    rs.getInt("message_id"),
                    rs.getInt("user_id"),
                    rs.getString("content"),
                    rs.getTimestamp("creation_date") // Ensures compatibility with MySQL DATETIME
            );
        }
    }
}
