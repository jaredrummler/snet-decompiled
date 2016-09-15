package javax.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.regex.Pattern;
import javax.annotation.meta.TypeQualifier;
import javax.annotation.meta.TypeQualifierValidator;
import javax.annotation.meta.When;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@TypeQualifier(applicableTo = String.class)
public @interface MatchesPattern {

    public static class Checker implements TypeQualifierValidator<MatchesPattern> {
        public When forConstantValue(MatchesPattern annotation, Object value) {
            if (Pattern.compile(annotation.value(), annotation.flags()).matcher((String) value).matches()) {
                return When.ALWAYS;
            }
            return When.NEVER;
        }
    }

    int flags() default 0;

    @RegEx
    String value();
}
