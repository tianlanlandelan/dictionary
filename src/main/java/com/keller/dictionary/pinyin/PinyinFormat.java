package com.keller.dictionary.pinyin;

/**
 * 拼音格式
 *
 * @author stuxuhai (dczxxuhai@gmail.com)
 * @version 1.0
 * @created 2013-5-15
 */
public class PinyinFormat {
    private String name;

    /**
     * 没有声调
     */
    public static final PinyinFormat WITH_TONE_MARK = new PinyinFormat("WITH_TONE_MARK");

    /**
     * 声调用数字表示
     */
    public static final PinyinFormat WITHOUT_TONE = new PinyinFormat("WITHOUT_TONE");

    /**
     * 原声调表示
     */
    public static final PinyinFormat WITH_TONE_NUMBER = new PinyinFormat("WITH_TONE_NUMBER");

    protected PinyinFormat(String name) {
        this.name = name;
    }

    protected String getName() {
        return this.name;
    }
}
