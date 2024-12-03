package com.example.demo.config;

import java.util.List;
import java.util.logging.Logger;

import org.instancio.Instancio;
import org.instancio.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

import com.example.demo.model.db.TBUser;
import com.example.demo.repository.IUserRepository;

import jakarta.annotation.PostConstruct;

@Configuration
@ConditionalOnProperty(value = "app.generateData", havingValue = "true")
public class AppConfig {
    private static final Logger LOGGER = Logger.getLogger(AppConfig.class.getSimpleName());

    @Autowired
    private IUserRepository iUserRepository;

    @PostConstruct
    void generateDB() {
        LOGGER.info("Generating DB data...");
        List<TBUser> users = Instancio.ofList(TBUser.class)
                .size(100)
                .generate(Select.field(TBUser::getUsername), gen -> gen.string().length(5, 8))
                .generate(Select.field(TBUser::getEmail), gen -> gen.net().email())
                .generate(Select.field(TBUser::getAge), gen -> gen.ints().min(10).max(80))
                .create();

        iUserRepository.saveAllAndFlush(users);
    }
}
