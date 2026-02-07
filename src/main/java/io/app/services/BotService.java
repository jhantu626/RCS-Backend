package io.app.services;

import io.app.dto.ApiResponse;
import io.app.model.Bot;
import org.springframework.data.domain.Page;

public interface BotService {
    public ApiResponse createBot(Bot bot);
    public Page<Bot> getBots();
}
