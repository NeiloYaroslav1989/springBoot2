package com.neilo.springboot2.controller;

import com.neilo.springboot2.dao.Message;
import com.neilo.springboot2.dao.User;
import com.neilo.springboot2.repositories.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class MainController {

    @Autowired
    private MessageRepo messageRepo;

    @GetMapping("/")
    public String index(Map<String, Object> model) {
        return "index";
    }


    @GetMapping("/main")
    public String main(Map<String, Object> model) {
        Iterable<Message> messages = messageRepo.findAll();
        model.put("messages", messages);
        return "main";
    }

    @PostMapping("message")
    public String add(
            @AuthenticationPrincipal User user,
            @RequestParam(name="text", required = false, defaultValue = "Empty message") String text,
            @RequestParam(name="tag", required = false, defaultValue = "Empty tag") String tag,
            Map<String, Object> model) {
        Message message = new Message(text, tag, user);
        messageRepo.save(message);

        Iterable<Message> messages = messageRepo.findAll();
        model.put("messages", messages);

        return "main";
    }

    @PostMapping("filter")
    public String filter(@RequestParam String filter,
                         Map<String, Object> model) {
        Iterable<Message> messages; //Общий знаменатель для List and Iterable
        if (filter != null && !filter.isEmpty()) {
            messages = messageRepo.findByTag(filter); //Возвращает List
        } else {
            messages = messageRepo.findAll(); //Возвращает Iterable
        }
        model.put("messages", messages);
        return "main";
    }


    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name,
                           Map<String, Object> model) {
        model.put("name", name);
        return "greeting";
    }

}