package io.app.repository;

import io.app.model.Bot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BotRepository extends JpaRepository<Bot,Long> {
    boolean existsBotByBotKey(String botKey);
}
