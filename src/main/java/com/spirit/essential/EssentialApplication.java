package com.spirit.essential;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class EssentialApplication {

    public static void main(String[] args) {
        log.info("EssentialApplication ...");

        SpringApplication.run(EssentialApplication.class, args);
    }

}
