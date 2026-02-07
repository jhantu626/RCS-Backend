package io.app.services.impl;

import io.app.dto.ApiResponse;
import io.app.model.Bot;
import io.app.repository.BotRepository;
import io.app.services.BotService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class BotServiceImpl implements BotService {
    private final BotRepository repository;
    public BotServiceImpl(BotRepository repository){
        this.repository=repository;
    }


    @Override
    public ApiResponse createBot(Bot bot) {
        return null;
    }

    @Override
    public Page<Bot> getBots() {
        return null;
    }
}
