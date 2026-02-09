package io.app.services.impl;

import io.app.dto.ApiResponse;
import io.app.exceptions.RequiredFieldException;
import io.app.model.Bot;
import io.app.repository.BotRepository;
import io.app.services.BotService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BotServiceImpl implements BotService {
    private final BotRepository repository;
    public BotServiceImpl(BotRepository repository){
        this.repository=repository;
    }


    @Override
    public ApiResponse createBot(Bot bot) {
        if (bot.getBotName()==null) {
            throw new RequiredFieldException("Bot Name is Required");
        }else if (bot.getBotKey()==null){
            throw new RequiredFieldException("Bot Key Is Required");
        }

        repository.save(bot);

        return ApiResponse.builder()
                .message("Bot Created Successfully")
                .status(true)
                .build();
    }

    @Override
    public List<Bot> getBots() {
        List<Bot> allBots=repository.findAll();
        return allBots;
    }
}
