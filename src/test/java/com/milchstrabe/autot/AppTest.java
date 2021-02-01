package com.milchstrabe.autot;

import org.junit.Test;

import java.io.IOException;

public class AppTest 
{

    @Test
    public void test() throws IOException {
        TaskContainer taskContainer = TaskContainer.INSTANCE();
//        taskContainer.put(new TTL(30, TimeUnit.SECONDS), new AbstractTask() {
//            @Override
//            public void job() {
//                System.out.println("30s, start running");
//            }
//        });

        taskContainer.put(new CustomizedFunctionTimeType<CustomizedFunctionParam>(new CustomizedFunctionParam(5,System.currentTimeMillis() + 5 * 1000),
            a -> {
                if(a.timestamp < System.currentTimeMillis()){
                    System.out.println("current index:" + a.index);
                    return new CustomizedFunctionResult(true,System.currentTimeMillis() + 1000 * 15);
                }else {
                    return new CustomizedFunctionResult(false);
                }

            }),new AbstractTask() {
            @Override
            public void job() {
                System.out.println(" start running");
            }
        });
        System.in.read();
    }
}
