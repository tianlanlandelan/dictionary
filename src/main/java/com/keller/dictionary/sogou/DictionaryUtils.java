package com.keller.dictionary.sogou;

import java.io.IOException;

public class DictionaryUtils {
    public static void main(String[] args) {
        //单个scel文件转化
//        parseFile();
        parseFiles();
    }


    public static void parseFile() {
        SougouScelFileProcessing scel = new SougouScelFileProcessing();

        scel.parseFile("/Users/yangkaile/MyProject/IM/im/common-util/src/main/java/com/keller/commonutil/util/sogou/成语词条.scel",
                "/Users/yangkaile/MyProject/IM/im/common-util/src/main/java/com/keller/commonutil/util/sogou/成语词条.txt",
                true);
    }

    public static void parseFiles() {
        //多个scel文件转化为一个txt (格式：拼音字母 词)
        SougouScelFileProcessing scel = new SougouScelFileProcessing();

        try {
            scel.parseFiles("/Users/yangkaile/MyProject/IM/im/dictionary/file",
                    "/Users/yangkaile/MyProject/IM/im/dictionary/file/汇总.txt",
                    false);

        } catch (IOException e) {
            e.printStackTrace();
        }
//
//        //多个scel文件转化为多个txt文件
//
//        scel.setTargetDir("/Users/ST_iOS/Desktop/test/ciku/多对多");//转化后文件的存储位置
//
//        scel.parseFile("/Users/ST_iOS/Desktop/test/ciku",false);
    }

}
