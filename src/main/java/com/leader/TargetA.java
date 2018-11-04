package com.leader;

/**
 * Created by sc on 2018/11/4.
 */
public class TargetA implements ITarget {
    @Override
    public void doing(String command) {
        System.out.println("我是targetA正在执行："+command);
    }
}
