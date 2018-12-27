package com.server.server.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Taras Koloshmatin on 20.08.2018
 */
public class LoggerUtils {

    private Logger LOGGER = null;

    public <S> LoggerUtils(Class<S> logClass) {
        LOGGER = LoggerFactory.getLogger(logClass.getName());
    }

//    public static final <S> Logger classs(Class<S> logClass) {
//        return LOGGER = LoggerFactory.getLogger(logClass.getName());
//    }

    public void info(String message) {
        LOGGER.info("[INFO] ---> [" + message + "]");
    }

    public void debug(String message) {
        LOGGER.debug("[DEBUG] ---> [" + message + "]");
    }

    public void error(String message) {
        LOGGER.error("[ERROR] ---> [" + message + "]");
    }
}
