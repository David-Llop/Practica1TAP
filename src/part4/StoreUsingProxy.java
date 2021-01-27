package part4;

import part1.IMailStore;
import part1.MailSystem;
import part3.IMailStoreFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;

@Config(store = "PART3.RedisMailStore", log = true)

public class StoreUsingProxy extends MailSystem {
    public StoreUsingProxy() {
        super(getReflectionClass());
    }

    private static IMailStore getReflectionClass() {
        try {
            Class<StoreUsingProxy> obj = StoreUsingProxy.class;
            Annotation annotation= obj.getAnnotation(Config.class);
            Config con= (Config) annotation;
            Class classe = null;

            classe = Class.forName(con.store());

            if (classe.toString().equals("class PART_3.RedisMailStore")){
                java.lang.reflect.Method a;
                a = classe.getMethod("getInstance");

                if (con.log()){
                    return (IMailStore) Proxy.newInstance(a.invoke(classe));
                }else{
                    return (IMailStore) a.invoke(classe);
                }

            }else{
                if (con.log()){
                    return (IMailStore) Proxy.newInstance(classe.getConstructor().newInstance());
                }else{
                    return  (IMailStore) classe.getConstructor().newInstance();
                }

            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return null;
    }
}
