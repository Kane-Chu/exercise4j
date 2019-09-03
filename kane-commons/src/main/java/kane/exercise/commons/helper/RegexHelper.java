package kane.exercise.commons.helper;

import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import org.intellij.lang.annotations.Language;
import org.springframework.lang.Nullable;

import static kane.exercise.commons.helper.NullHelper.isEmpty;

public abstract class RegexHelper {

    public static final String VARIABLE_PATTERN = "\\$\\{(?<name>[^${}]+)}";
    public static final String VARIABLE_GROUP_NAME = "name";
    public static final int VARIABLE_GROUP_NO = 1;

    @Nullable
    private static LoadingCache<PatternKey, Pattern> cache;

    public static Pattern compile(@Language("RegExp") String regex) {
        return compile(regex, 0);
    }

    public static Pattern compile(@Language("RegExp") String regex, int flags) {
        if (cache == null) {
            cache = Caffeine.newBuilder().maximumSize(256).build(
                    key -> Pattern.compile(key.regex, key.flags)
            );
        }
        //noinspection ConstantConditions
        return cache.get(new PatternKey(regex, flags));
    }

    public static Matcher matcher(CharSequence input, @Language("RegExp") String regex) {
        return matcher(input, regex, 0);
    }

    public static Matcher matcher(CharSequence input, @Language("RegExp") String regex, int flags) {
        return compile(regex, flags).matcher(input);
    }

    public static boolean matches(CharSequence input, @Language("RegExp") String regex) {
        return matches(input, regex, 0);
    }

    public static boolean matches(CharSequence input, @Language("RegExp") String regex, int flags) {
        return matcher(input, regex, flags).matches();
    }

    public static boolean lookingAt(CharSequence input, @Language("RegExp") String regex) {
        return lookingAt(input, regex, 0);
    }

    public static boolean lookingAt(CharSequence input, @Language("RegExp") String regex, int flags) {
        return matcher(input, regex, flags).lookingAt();
    }

    public static boolean find(CharSequence input, @Language("RegExp") String regex) {
        return find(input, regex, 0);
    }

    public static boolean find(CharSequence input, @Language("RegExp") String regex, int flags) {
        return matcher(input, regex, flags).find();
    }

    public static boolean find(CharSequence input, int start, @Language("RegExp") String regex) {
        return find(input, start, regex, 0);
    }

    public static boolean find(CharSequence input, int start, @Language("RegExp") String regex, int flags) {
        return matcher(input, regex, flags).find(start);
    }

    public static String replaceAll(CharSequence input, @Language("RegExp") String regex, String replace) {
        return replaceAll(input, regex, 0, replace);
    }

    public static String replaceAll(CharSequence input, @Language("RegExp") String regex, int flags, String replace) {
        return matcher(input, regex, flags).replaceAll(replace);
    }

    private static final class PatternKey {
        private final String regex;
        private final int flags;

        PatternKey(String regex, int flags) {
            this.regex = regex;
            this.flags = flags;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof PatternKey)) return false;
            PatternKey that = (PatternKey) o;
            return flags == that.flags &&
                    regex.equals(that.regex);
        }

        @Override
        public int hashCode() {
            return Objects.hash(regex, flags);
        }
    }

    public static String replaceVariables(String input, Properties properties) {
        return replaceVariables(input, VARIABLE_PATTERN, properties);
    }

    public static String replaceVariables(String input, @Language("RegExp") String regex, Properties properties) {
        return replaceVariables(input, regex, 0, properties);
    }

    public static String replaceVariables(String input, @Language("RegExp") String regex, int flags,
                                          Properties properties) {
        return replaceVariables(input, regex, flags, properties::getProperty);
    }

    public static String replaceVariables(String input, Map<String, ?> map) {
        return replaceVariables(input, VARIABLE_PATTERN, map);
    }

    public static String replaceVariables(String input, @Language("RegExp") String regex, Map<String, ?> map) {
        return replaceVariables(input, regex, 0, map);
    }

    public static String replaceVariables(String input, @Language("RegExp") String regex, int flags,
                                          Map<String, ?> map) {
        return replaceVariables(input, regex, flags, name -> map.get(name).toString());
    }

    public static String replaceVariables(String input, Function<String, String> function) {
        return replaceVariables(input, VARIABLE_PATTERN, function);
    }

    public static String replaceVariables(String input, @Language("RegExp") String regex,
                                          Function<String, String> function) {
        return replaceVariables(input, regex, 0, function);
    }

    public static String replaceVariables(String input, @Language("RegExp") String regex, int flags,
                                          Function<String, String> function) {
        Matcher matcher = matcher(input, regex, flags);

        StringBuilder builder = null;
        int start = 0;
        while (matcher.find()) {
            String name;

            try {
                name = matcher.group(VARIABLE_GROUP_NAME);
            } catch (IllegalArgumentException e) {
                name = matcher.group(VARIABLE_GROUP_NO);
            }

            if (builder == null) {
                builder = new StringBuilder();
            }

            String replacement = function.apply(name);
            if (isEmpty(replacement)) {
                builder.append(input, start, matcher.end());

            } else {
                builder.append(input, start, matcher.start());
                builder.append(replacement);
            }

            start = matcher.end();
        }

        if (builder == null) {
            return input;
        }

        return builder.append(input, start, input.length()).toString();
    }
}
