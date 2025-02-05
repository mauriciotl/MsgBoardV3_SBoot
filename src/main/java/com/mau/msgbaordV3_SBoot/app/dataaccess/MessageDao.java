package com.mau.msgbaordV3_SBoot.app.dataaccess;

import com.mau.msgbaordV3_SBoot.app.model.Message;
import java.util.List;

public interface MessageDao {

    // Create
    int save(Message message);

    // Read
    Message findById(int messageId);
    List<Message> findAll();

    // Update
    void update(Message message);

    // Delete
    void delete(int messageId);
}
