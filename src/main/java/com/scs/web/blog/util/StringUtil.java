package com.scs.web.blog.util;/*@ClassName StringUtil
 *@Description:todo
 *@author yc_shang
 *@Date2019/11/20
 *@Version 1.0
 **/

import java.util.Random;
public class StringUtil {
    public static String getRandomString(int length){
        String str = "abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUWVXYZ";
        Random random = new Random();
        StringBuffer stringBuffer = new StringBuffer();

        for(int i=0;i<4;i++) {
            int number = random.nextInt(62);
            stringBuffer.append(str.charAt(number));
        }
        return stringBuffer.toString();
    }

    public static void main(String[] args) {
        System.out.println(StringUtil.getRandomString(4));
    }


}

//public class StringUtil {
//    final static int MAX =4;
//    public static String getRandomString(int length){
//        String str = "abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUWVXYZ";
//        Random random = new Random();
//        StringBuffer stringBuffer = new StringBuffer();
//        int digitalBound = 10;
//        int charBound = 26;
//        int index;
//        int len =4;
//        char c;
//        for(int i=0;i<MAX;i++) {
//            index = random.nextInt(digitalBound);
//            c = (char)('0'+index);
//            stringBuffer.append(c);
//        }
//        return stringBuffer.toString();
//    }
//
//
//}
