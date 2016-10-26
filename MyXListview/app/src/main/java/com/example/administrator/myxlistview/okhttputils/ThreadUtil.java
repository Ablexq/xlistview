package com.example.administrator.myxlistview.okhttputils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by idea on 2016/6/25.
 */
public class ThreadUtil {

    private static ExecutorService executorService;

    public static ExecutorService getExecutor(){
        if(executorService==null){
            executorService = Executors.newFixedThreadPool(10);
        }
        return executorService;
    }

}
