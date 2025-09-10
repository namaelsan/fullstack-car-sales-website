package com.staj.CarCommerceApp.services;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class LoggingService {
    private static final Logger logger = LoggerFactory.getLogger(LoggingService.class);

    public void logInfo(String message) {
//        logger.debug("This is a DEBUG message");
        logger.info(message);
//        logger.warn("This is a WARN message");
    }

    public void logError(String message) {
        logger.error(message);
    }
}
