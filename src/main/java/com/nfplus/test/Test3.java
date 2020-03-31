package com.nfplus.test;

import com.nfplus.util.GenEntityUtil;

/**
 * @author zac
 * @description:测试生成对应表的实体类
 * @data 2020 2020/3/10 16:35
 */
public class Test3 {

    public static void main(String[] args){
        GenEntityUtil.genEntity("nfplus","student_assess");
    }
}
