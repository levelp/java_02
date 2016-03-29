package annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Сколько поставить '#' перед полем при выводе
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface PrintHashesBefore {
    int value() default 2;
}
