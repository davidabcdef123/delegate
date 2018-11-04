package com.leader;

/**
 * Created by sc on 2018/11/4.
 */
public class TargetB implements ITarget {
    @Override
    public void doing(String command) {
        System.out.println("我是targetB:正在执行"+command);
    }
}
