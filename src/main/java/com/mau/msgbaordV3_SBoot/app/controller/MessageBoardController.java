package com.mau.msgbaordV3_SBoot.app.controller;

import com.mau.msgbaordV3_SBoot.app.model.Message;
import com.mau.msgbaordV3_SBoot.app.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@Controller
@RequestMapping("/messages")
public class MessageBoardController {

    @Autowired
    private MessageService messageService;

    @GetMapping("/board")
    public String showMessageBoard(Model model) {
        List<Message> messages = messageService.getAllMessages();
        model.addAttribute("messages", messages);
        return "app/messageBoard";
    }

    @PostMapping("/add")
    public String addMessage(@RequestParam("userId") int userId,
                             @RequestParam("content") String content) {
        Message message = new Message(userId, content, new Timestamp(System.currentTimeMillis()));
        messageService.saveMessage(message);
        return "redirect:/messages/board";
    }

    @GetMapping("/edit/{id}")
    public String editMessageForm(@PathVariable("id") int messageId, Model model) {
        Message message = messageService.getMessageById(messageId);
        model.addAttribute("message", message);
        return "app/editMessage";
    }

    @PostMapping("/update")
    public String updateMessage(@RequestParam("messageId") int messageId,
                                @RequestParam("content") String content) {
        Message message = messageService.getMessageById(messageId);
        if (message != null) {
            message.setContent(content);
            messageService.updateMessage(message);
        }
        return "redirect:/messages/board";
    }

    @PostMapping("/delete/{id}")
    public String deleteMessage(@PathVariable("id") int messageId) {
        messageService.deleteMessage(messageId);
        return "redirect:/messages/board";
    }
}
