package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.spring5.context.webflux.ReactiveDataDriverContextVariable;

@Controller
public class ThymeleafController {

    @Autowired
    private TemperatureSensor temperatureSensor;

    @RequestMapping("/play-list-view")
    public String view(final Model model) {
        model.addAttribute(
                "playList",
                new ReactiveDataDriverContextVariable(temperatureSensor.temperatureStream(),
                        1, 1)
        );
        return "thymeleaf/play-list-view";
    }

    @GetMapping("/")
    public String main(Model model) {
        model.addAttribute(
                "temperatureList",
                new ReactiveDataDriverContextVariable(temperatureSensor.temperatureStream(),
                        1, 1)
        );

        return "welcome"; //view
    }
}
