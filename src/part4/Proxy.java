package part4;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class Proxy implements InvocationHandler {

    private Object target = null;

    private Proxy(Object target){
        this.target=target;
    }

    public static  Object newInstance(Object target){
        var targetClass = target.getClass();
        var interfaces = targetClass.getInterfaces();

        return java.lang.reflect.Proxy.newProxyInstance(targetClass.getClassLoader(),
                interfaces, new Proxy(target));
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object invocationResult = null;
        System.out.println("S'ha cridat a " + method.getName());
        invocationResult = method.invoke(this.target, args);
        return invocationResult;
    }
}
