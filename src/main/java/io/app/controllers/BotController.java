package io.app.controllers;

import io.app.dto.ApiResponse;
import io.app.model.Bot;
import io.app.services.impl.BotServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Bot>> allBots(){
        return ResponseEntity.ok(service.getBots());
    }

}
