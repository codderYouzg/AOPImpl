package youzg.proxy.core.test;

import com.mec.util.proxy.IMethdInvoker;
import com.mec.util.proxy.MecProxy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Author: Youzg
 * @CreateTime: 2020-04-22 17:25
 * @PROJECT: AOPStudy
 * @PACKAGE: edu.youzg.proxy.test
 * @Description:
 */
public class Demo {

    public static void main(String[] args){
        MecProxy mecProxy = new MecProxy();
        mecProxy.setProxyType(MecProxy.JDK_PROXY);

        ISimpleInterface aClass = new SimpleClass();
        mecProxy.setMethodInvoker(new IMethdInvoker() {
            @Override
            public Boolean methodInvoke(Object object, Method method, Object[] args) {
                boolean res = true;
                try {
                    System.out.println("该方法被前置拦截... ...");
                    res = (Boolean) method.invoke(object, args);
                    System.out.println("该方法被后置拦截... ...");
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
                return res;
            }
        });
        ISimpleInterface proxy = mecProxy.getProxy(aClass);
        proxy.hello();
    }

}
