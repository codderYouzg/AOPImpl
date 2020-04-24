package youzg.proxy.core.test;

import com.mec.util.proxy.MecProxy;

/**
 * @Author: Youzg
 * @CreateTime: 2020-04-22 18:29
 * @PROJECT: AOPStudy
 * @PACKAGE: edu.youzg.proxy.test
 * @Description:
 */
public class SimpleClass implements ISimpleInterface {
    MecProxy mecProxy = new MecProxy();

    @Override
    public boolean hello() {
        System.out.println("hello MecProxy!");
        return true;
    }

}
