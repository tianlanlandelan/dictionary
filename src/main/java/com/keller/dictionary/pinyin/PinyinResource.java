package com.keller.dictionary.pinyin;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipInputStream;

/**
 * 拼音资源文件管理
 *
 * @author stuxuhai (dczxxuhai@gmail.com)
 * @version 1.0
 * @created 2013-5-15
 */
@Slf4j
public class PinyinResource {
    private static final Logger LOGGER = Logger.getLogger(PinyinResource.class.getName());

    public static Properties getResource(String resourceName) {
        ZipInputStream zip = new ZipInputStream(PinyinResource.class.getResourceAsStream(resourceName));

        try {
            zip.getNextEntry();
            Properties p = new Properties();
            p.load(zip);
            zip.close();
            return p;
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, "Exception in loading PinyinResource", e);
        }
        return null;
    }

    public static Properties getPinyinTable() {
        String resourceName = "/data/pinyin.db";
        return getResource(resourceName);
    }

    public static Properties getMutilPintinTable() {
        String resourceName = "/data/mutil_pinyin.db";
        return getResource(resourceName);
    }

    public static Properties getChineseTable() {
        String resourceName = "/data/chinese.db";
        return getResource(resourceName);
    }
}
