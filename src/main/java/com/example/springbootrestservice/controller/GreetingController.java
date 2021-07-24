package com.example.springbootrestservice.controller;

import java.util.concurrent.atomic.AtomicLong;

import com.example.springbootrestservice.entity.Greeting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {
    @Autowired
    Greeting m_greeting;

    AtomicLong counter = new AtomicLong();

    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name") String name) {

        m_greeting.setContent("I am learning Spring Boot - " + name);
        m_greeting.setId(counter.incrementAndGet());

        return m_greeting;

    }
}
