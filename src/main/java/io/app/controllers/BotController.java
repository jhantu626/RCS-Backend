package io.app.controllers;

import io.app.dto.ApiResponse;
import io.app.model.Bot;
import io.app.services.impl.BotServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpResponse;

@RestController
@RequestMapping("/api/v1/bot")
public class BotController {
    private final BotServiceImpl service;
    public BotController(BotServiceImpl service){
        this.service=service;
    }

    @PostMapping
    public ResponseEntity<ApiResponse> create(@RequestBody Bot bot){
        return new ResponseEntity<>(service.createBot(bot), HttpStatus.CREATED);
    }

}
