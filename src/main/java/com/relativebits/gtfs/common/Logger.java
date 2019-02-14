package com.relativebits.gtfs.common;

import org.apache.commons.lang3.exception.ExceptionUtils;

public class Logger {

    private static org.apache.logging.log4j.Logger getLogger(Class<?> clazz) {
        return org.apache.logging.log4j.LogManager.getLogger(clazz);
    }

    public static void debug(Class<?> clazz, String message) {
        getLogger(clazz).debug(message);
    }

    public static void error(Class<?> clazz, String message) {
        getLogger(clazz).error(message);
    }

    public static void error(Class<?> clazz, String message, Exception exception) {
        getLogger(clazz).error(message + ". " + ExceptionUtils.getStackTrace(exception));
    }

    public static void error(Class<?> clazz, Exception exception) {
        getLogger(clazz).error(ExceptionUtils.getStackTrace(exception));
    }

    public static void info(Class<?> clazz, String message) {
        getLogger(clazz).info(message);
    }

    public static void warn(Class<?> clazz, String message) {
        getLogger(clazz).warn(message);
    }
}
