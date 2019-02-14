package com.relativebits.gtfs.common;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

public class Config {

    private static Properties properties;

    static {
        InputStream inputStream = null;
        try {
            properties = new Properties();
            inputStream = Config.class.getResourceAsStream("/config.properties");
            properties.load(inputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(inputStream);
        }
    }

    private Config() {
    }

    private static String get(String key) {
        return properties.getProperty(key);
    }

    public static Integer GtfsImportThreads() {
        try {
            return Integer.parseInt(get("gtfs.import.threads"));
        } catch (NumberFormatException ex) {
            return 1;
        }
    }

    public static String GtfsImportFolder() {
        String value = get("gtfs.import.folder");
        return StringUtils.isNotBlank(value) ? value : File.separator + "tmp" + File.separator + "gtfs";
    }

}
