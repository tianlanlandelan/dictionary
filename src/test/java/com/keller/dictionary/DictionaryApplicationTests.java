package com.keller.dictionary;

import com.code4j.mybatisutil.mybatis.BasePage;
import com.code4j.mybatisutil.mybatis.BaseQuery;
import com.code4j.mybatisutil.util.*;
import com.code4j.mybatisutil.util.Console;
import com.keller.dictionary.mapper.IdiomMapper;
import com.keller.dictionary.mapper.TagGroupMapper;
import com.keller.dictionary.mapper.WordMapper;
import com.keller.dictionary.po.Idiom;
import com.keller.dictionary.pinyin.PinyinFormat;
import com.keller.dictionary.pinyin.PinyinHelper;
import com.keller.dictionary.po.TagGroup;
import com.keller.dictionary.po.Word;
import com.keller.dictionary.util.HttpKit;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

@SpringBootTest
class DictionaryApplicationTests {

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private IdiomMapper idiomMapper;

    @Resource
    private WordMapper wordMapper;

    @Resource
    private TagGroupMapper tagGroupMapper;

    @Test
    void contextLoads() {
    }

    @Test
    void init() throws Exception{
        String encoding = "UTF-8";
        File file = new File("/Users/yangkaile/MyProject/IM/im/dictionary/file/成语.txt");
            if(!file.exists() || !file.isFile()){
                return;
            }

         InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);// 考虑到编码格式
         BufferedReader bufferedReader = new BufferedReader(read);
         String lineTxt = null;

        List<Idiom> list = new ArrayList<>();
        int id = 0;
         while ((lineTxt = bufferedReader.readLine()) != null) {
             String newStr = new String(lineTxt.getBytes(encoding));
             String split[] = newStr.split(" ");
             Idiom idiom = new Idiom();

             if(split.length != 2){
                 continue;
             }

             idiom.setId(id + 1);
             idiom.setPhoneticSearch(split[0]);
             idiom.setName(split[1]);

             list.add(idiom);

             if(id > 0 && id % 100 == 0){
                 System.out.println(id + "---" + list.size());
                 idiomMapper.baseInsertList(list);
                 Thread.sleep(1000L);
                 list = new ArrayList<>();
             }

             id ++;
         }
        if(list.size() > 0){
            System.out.println(id + "---" + list.size());
            idiomMapper.baseInsertList(list);
        }
         bufferedReader.close();
         read.close();
    }

    @Test
    public void insertList(){
        List<TagGroup> list = new ArrayList<> ();
        for (int i = 0; i < 10; i++) {
            TagGroup tagGroup = new TagGroup();
            tagGroup.setName("TagName" + (i + 1));

            list.add(tagGroup);
        }
        tagGroupMapper.baseInsertList(list);
    }

    @Test
    public void selectList(){
        BaseQuery<TagGroup> query = new BaseQuery<> (new TagGroup());
        List<TagGroup> list = tagGroupMapper.baseSelectList(query);
        System.out.println(list);
    }

    @Test
    public void selectPage(){
        Idiom idiom = new Idiom();
//        idiom.setName("阿猫阿狗");

        BaseQuery<Idiom> query = new BaseQuery<> (idiom);

        BasePage<Idiom> page = idiomMapper.baseSelectPage(query);
        System.out.println(page);
        if(page.getList() != null){
            for (Idiom item : page.getList()) {
                System.out.println(item);
            }
        }
    }

    @Test
    public void initPinyin(){
        BaseQuery<Idiom> query = new BaseQuery<> (new Idiom());
        List<Idiom> list = idiomMapper.baseSelectList(query);

        for (Idiom idiom : list) {
            String pinyin = PinyinHelper.convertToPinyinString(idiom.getName()," ",PinyinFormat.WITH_TONE_MARK);
            idiom.setPhoneticNotation(pinyin);
            System.out.println(idiom);
            idiomMapper.baseUpdateById(idiom);
        }

//        String pinyin = PinyinHelper.convertToPinyinString("逋逃之薮"," ",PinyinFormat.WITH_TONE_MARK);
//        System.out.println(pinyin);

    }

    @Test
    public void initChinese() throws Exception{

        File file = new File("/Users/yangkaile/MyProject/IM/im/dictionary/src/main/resources/file/Pinyin.txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));

        String line = null;
        List<String> list = new ArrayList<>();
        while ((line = reader.readLine()) != null) {
            String unicode = line.split("=")[0];
            unicode = unicode.replace("\\u","");
            String[] array = StringUnicodeUtil.unicodeStr2String(line).split("=");
            String word = array[0];
            String pinyin = array[1];
            String sort = PinyinHelper.convertToPinyinString(word, "", PinyinFormat.WITH_TONE_NUMBER);
            String pinyinNoTone = PinyinHelper.convertToPinyinString(word, "", PinyinFormat.WITHOUT_TONE);

            String pinyinNum = sort.substring(sort.length()-1,sort.length());

            int strokeCount = ChineseUtils.getChineseStrokeCount(word);

            if(strokeCount == 0){
                strokeCount = 99;
            }

            sort += strokeCount;

            // 排序（拼音+笔划数） : 汉字 : 笔划数 : 拼音 : 拼音无声调 : 声调 : unicode
            String str = sort
                    + ":" + word
                    + ":" + strokeCount
                    + ":" + pinyin
                    + ":" + pinyinNoTone
                    + ":" + pinyinNum
                    + ":" + unicode;
            list.add(str);
        }
        list = list.stream().sorted().collect(Collectors.toList());

        List<Word> wordList = new ArrayList<>();

        int i = 1;
        for (String s : list) {

            System.out.println(s);
            String[] arr = s.split(":");

            Word word = new Word();
            word.setId(i);
            word.setPhoneticCount(arr[3].split(",").length);
            word.setPhoneticNotation(arr[3]);
            word.setPhoneticSearch(arr[4]);
            word.setStrokeCount(Integer.parseInt(arr[2]));
            if(word.getStrokeCount() == 99){
                word.setStrokeCount(null);
                word.setFrequentlyUsed(0);
            }else {
                word.setFrequentlyUsed(1);
            }
            try {
                word.setTone(Integer.parseInt(arr[5]));
            }catch (Exception e){
            }
            word.setUnicode(arr[6]);
            word.setWord(arr[1]);

            if(i >= 20858){
                word.setPhoneticNotation(null);
                word.setPhoneticSearch(null);
                word.setFrequentlyUsed(0);
            }

            System.out.println(word);

            wordList.add(word);

            if(i % 100 == 0){
                wordMapper.baseInsertList(wordList);
                Thread.sleep(1000L);
                wordList = new ArrayList<>();
            }

            i++;
        }

        if(wordList.size() > 0){
            wordMapper.baseInsertList(wordList);
        }

    }


    public String get(String name){
        String url = "https://hanyu.baidu.com/s?wd="+name+"&from=zici";
        String response = HttpKit.get(url);
        StringBuffer buffer = new StringBuffer();
        buffer.append(name);
        try{


            String str = null;
            //拼音
            try{
                str = response.substring(response.indexOf("\"pinyin\">[") + 10,response.indexOf("]</dt>") ).trim();
                buffer.append("|--|").append(str);

            }catch (Exception e){
                buffer.append("|--|").append("");
            }
//            Console.println("拼音",str);

            //解释
            try{
                str = response.substring(response.indexOf("\"pinyin\">["),response.indexOf("more-button"));
                str = str.substring(str.indexOf("<p>") + 3,str.indexOf("</p>")).trim();
                buffer.append("|--|").append(str);
            }catch (Exception e){
                buffer.append("|--|").append("");
            }

//            Console.println("解释",str);

            //出处
            try{
                str = response.substring(response.indexOf("出  处"),response.indexOf("例句"));
                str = str.substring(str.indexOf("<p>") + 3,str.indexOf("</p>")).trim();
                buffer.append("|--|").append(str);
            }catch (Exception e){
                buffer.append("|--|").append("");

            }

//            Console.println("出处",str);


            List<String> list = new ArrayList<>();
            String[] array ;

            try{
                //近义词
                str = response.substring(response.indexOf("<label>近义词"),response.indexOf("<label>反义词"));
                array = str.split("<a");


                for (int i = 1; i < array.length; i++) {
                    String s = array[i];
                    s = s.substring(s.indexOf("wd=") + 3,s.indexOf("&cf"));
                    list.add(s);
                }

                buffer.append("|--|").append(String.join(",",list));
            }catch (Exception e){
                buffer.append("|--|").append("");
            }

//            Console.println("近义词",list);


            //反义词
            try{
                str = response.substring(response.indexOf("<label>反义词"),response.indexOf("典故"));
                array = str.split("<a");
                list = new ArrayList<>();
                for (int i = 1; i < array.length; i++) {
                    String s = array[i];
                    s = s.substring(s.indexOf("wd=") + 3,s.indexOf("&cf"));
                    list.add(s);
                }

                buffer.append("|--|").append(String.join(",",list));
            }catch (Exception e){
                buffer.append("|--|").append("");
            }

//            Console.println("反义词",list);

            try{
                //典故
                str = response.substring(response.indexOf("典  故"),response.indexOf("成语接龙"));
                str = str.substring(str.indexOf("<p>") + 3,str.indexOf("</p>")).trim();
                buffer.append("|--|").append(str);
            }catch (Exception e){
                buffer.append("|--|").append("");
            }

//            Console.println("典故",str);


            try{
                //百科解释
                str = response.substring(response.indexOf("百科解释部分"),response.indexOf("查看百科"));
                str = str.substring(str.indexOf("<p>") + 3,str.indexOf("<a")).trim();
                buffer.append("|--|").append(str);
            }catch (Exception e){
                buffer.append("|--|").append("");
            }

//            Console.println("百科解释",str);


        }catch(Exception e){
            e.printStackTrace();
            Console.println("出错了",response);
        }
        return buffer.toString();
    }

    @Test
    public void b(){
        String file = "/Users/yangkaile/MyProject/Current/Projects/dictionary/src/main/resources/file/成语解释.txt";
        List<String> list = Utils.readFromFile("/Users/yangkaile/MyProject/Current/Projects/dictionary/src/main/resources/file/成语.txt");
        List<String> resultList = new ArrayList<>();
        int index = 0;
        for (String idiom : list) {

            index ++;

            if(index <= 17400) {
                continue;
            }
            idiom = idiom.substring(idiom.indexOf(" ") + 1);

            try {
                Thread.sleep(10L);
                String result = get(idiom);
                if(StringUtils.isEmpty(result)){
                    continue;
                }
//                System.out.println(result);
                resultList.add(result);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            if(index % 10 == 0){
                Utils.write2File(file,true,resultList);
                resultList = new ArrayList<>();
            }
            System.out.println(index + idiom);
        }
        if(resultList.size() > 0){
            Utils.write2File(file,true,resultList);
        }
    }

    @Test
    public void aa(){
        String file = "/Users/yangkaile/MyProject/Current/Projects/dictionary/src/main/resources/file/成语解释.txt";
        List<String> list = Utils.readFromFile(file);

        Map<Integer,Idiom>  map = idiomMapper.baseSelectList(new BaseQuery<>(new Idiom()))
                .stream()
                .collect(Collectors.toMap(Idiom::getId,idiom -> idiom));

        BaseQuery<Idiom> query = new BaseQuery<Idiom>(new Idiom());

        int id = 0;
        int count = 0;
        for (String str : list) {

            id ++;

            String[] array = str.split("=");

            IdiomVO vo = new IdiomVO();
            vo.setId(id);
            vo.setName(array[0]);
            vo.setPinyin(array[1]);
            vo.setDescription(array[2]);
            vo.setChuchu(array[3]);
            vo.setJinyi(array[4]);
            vo.setFanyi(array[5]);
            vo.setDiangu(array[6]);
            if(array.length == 8){
                vo.setBaike(array[7]);
            }
            if(isNull(vo.getPinyin())){

//                System.out.println(count + ":" + vo);

                continue;
            }
            Idiom idiom = map.get(id);

            if(idiom.getName().equals(vo.getName()) && !idiom.getPhoneticNotation().equals(vo.getPinyin())){
                count ++;
                Console.println(vo.getName(),idiom.getPhoneticNotation(),vo.getPinyin(),count);
            }
        }
    }

    public boolean isNull(String str){
        return str == null || ",".equals(str) || str.isEmpty();
    }
}
