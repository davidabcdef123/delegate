package com.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sc on 2018/11/4.
 */
public class ServletDispatcher {

    private List<Handler> handlermapping = new ArrayList<>();

    public ServletDispatcher(){
        Class<?> memberActionClass = MemberAction.class;
        try {
            Handler handler= new Handler();
            handler.setController(MemberAction.class.newInstance());
            handler.setMethod(memberActionClass.getMethod("getMemberById",new Class[]{String.class}));
            handlermapping.add(handler);
        } catch (InstantiationException | NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public void  doSerivce(HttpServletRequest request, HttpServletResponse response){
        doDispatch(request,response);

    }

    private void doDispatch(HttpServletRequest request, HttpServletResponse response) {
        //1、获取用户请求的url
        //   如果按照J2EE的标准、每个url对对应一个Serlvet，url由浏览器输入
        String uri=request.getRequestURI();
        //2、Servlet拿到url以后，要做权衡（要做判断，要做选择）
        //   根据用户请求的URL，去找到这个url对应的某一个java类的方法


        //3、通过拿到的URL去handlerMapping（我们把它认为是策略常量）
        Handler handler=null;
        for (Handler h : handlermapping) {
            if(h.getUrl().equals(uri)){
                handler=h;
                break;
            }
        }
        //4、将具体的任务分发给Method（通过反射去调用其对应的方法）
        Object object=null;
        try {
            object = handler.getMethod().invoke(handler.getController(),request.getParameter("mid"));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


    }


    static class Handler{
        private Object controller;
        private Method method;
        private String url;

        public Object getController() {
            return controller;
        }

        public void setController(Object controller) {
            this.controller = controller;
        }

        public Method getMethod() {
            return method;
        }

        public void setMethod(Method method) {
            this.method = method;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
