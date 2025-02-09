package com.mau.msgbaordV3_SBoot.app.service;

import com.mau.msgbaordV3_SBoot.app.model.Message;

import java.util.List;

public interface MessageService {

    int saveMessage(Message message);

    Message getMessageById(int messageId);

    List<Message> getAllMessages();

    boolean updateMessage(Message message);

    boolean deleteMessage(int messageId);
}
