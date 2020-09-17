package com.task.restApp.controller;

import com.task.restApp.models.User;
import com.task.restApp.repo.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;

@Controller
@RequestMapping("/")
public class MainController {

    private final MessageRepository messageRepository;

    @Value("${spring.profiles.active}")
    private String profile;

    @Autowired
    public MainController(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }


    @GetMapping
    public String main(Model model, @AuthenticationPrincipal User user){
        HashMap<Object, Object> data = new HashMap<>();

        if (user != null){
            data.put("profile", user);
            data.put("messages", messageRepository.findAll());
        }

        model.addAttribute("frontendData", data);

        //Добавляем атрибут isDevMode, по которому определяем девелоперская сборка или продакшн.
        //От этого будет зависеть откуда будут тянуться скрипты: с сервера или со статики
        //При проверке условия использована йода-запись, когда константное поле находится с левой стороны
        //Такая запись позволяет избежать NullPointerException, если по какой то причине profile не задан
        model.addAttribute("isDevMode", "dev".equals(profile));
        return "index";
    }
}
