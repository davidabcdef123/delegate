package com.leader;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sc on 2018/11/4.
 */
public class Leader {

    public Map<String, ITarget> targets = new HashMap<>();

    public Leader(){
        targets.put("加密", new TargetA());
        targets.put("登录", new TargetB());
    }

    public void doing(String command){
        targets.get(command).doing(command);
    }
}
