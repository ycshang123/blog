package com.scs.web.blog.util;
/*@ClassName DataUtil
 *@Description:数据生成工具，用来生成用户的一些数据
 *@author yc_shang
 *@Date2019/11/9
 *@Version 1.0
 **/

import org.apache.commons.codec.digest.DigestUtils;

import java.time.LocalDate;
import java.util.Random;

public class DataUtil {
    /*
    * 获得电话号码
    * */
    public static  String getMobile(){
        StringBuilder stringBuilder = new StringBuilder("139");
        Random random = new Random();
            //生成数字的位数
        for(int i =0;i<8;i++){
            //随机生成数字的范围
            int number = random.nextInt(10);
            //自动在后面追加
            stringBuilder.append(number);
        }
        return stringBuilder.toString();
    }

    /**
     *
     * @return
     */
    public  static  String getPassword(){
        StringBuilder password = new StringBuilder();
        Random random = new Random();
        for(int i = 0;i<6;i++){
            int number = random.nextInt(6);
            password.append(number);
        }
        //
        return  DigestUtils.md5Hex(password.toString());
    }

    public static String getGender(){
        String[] genders = new String[]{"男","女"};
        Random random = new Random();
        int index = random.nextInt(2);
        //当前日期的前bound天；
        return  genders[index];
    }

    public static LocalDate getBirthday(){
        LocalDate now = LocalDate.now();
        Random random = new Random();
        int bound = random.nextInt(9999);
        return  now.minusDays(bound);

    }

//    public static void main(String[] args) {
//        for(int i = 0;i<10;i++) {
//            System.out.println(DataUtil.getArticleTime());
//        }
//    }
}
