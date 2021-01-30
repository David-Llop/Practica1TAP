package part4;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Defines the {@code annotation} Config, used as mail system configuration
 * @author David Llop Roig
 * @author Anna Julia Naval
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Config {
    boolean log() default false;
    String store() default  "part3.JedisAdapter";
}
