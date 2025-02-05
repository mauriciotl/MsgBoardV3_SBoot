package com.mau.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class HelloController {

    private static final Logger logger = LogManager.getLogger(HelloController.class);

    private final GreetingService greetingService;

    @Autowired
    public HelloController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @ResponseBody
    @RequestMapping("/helloSpring")
    public String helloWorld() {
        logger.info("Handling request to /");
        String message = "Hello, World! from Spring controller!!";
        logger.debug("Returning message: {}", message);
        return message;
    }

    @ResponseBody
    @RequestMapping(value = "/custom", params = {"name"})
    public String helloName(@RequestParam("name") String name) {
        logger.info("Handling request to /custom with name: {}", name);
        try {
            String greeting = this.greetingService.getGreeting(name);
            logger.debug("Greeting generated: {}", greeting);
            return greeting;
        } catch (Exception e) {
            logger.error("Error generating greeting for name: {}", name, e);
            return "Error generating greeting."; // Or handle the error differently
        }
    }


    @RequestMapping("/indexTest")
    public String index() {
        return "index_test";  // Resolves to /WEB-INF/jsp/index_test.jsp
        //NOT WORKING AS THERE CAN BE ONLY ONE CONFIGURED IN the WebConfig.java
        //resolver.setPrefix("/WEB-INF/jsp/");
    }


}