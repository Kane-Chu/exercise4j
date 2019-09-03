package kane.exercise.commons.helper;

import java.lang.reflect.Array;
import java.util.*;
import java.util.function.Function;

import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;

public abstract class NullHelper {

    private static final boolean[] EMPTY_BOOLEAN_ARRAY = new boolean[0];
    private static final byte[] EMPTY_BYTE_ARRAY = new byte[0];
    private static final char[] EMPTY_CHAR_ARRAY = new char[0];
    private static final short[] EMPTY_SHORT_ARRAY = new short[0];
    private static final int[] EMPTY_INT_ARRAY = new int[0];
    private static final long[] EMPTY_LONG_ARRAY = new long[0];
    private static final float[] EMPTY_FLOAT_ARRAY = new float[0];
    private static final double[] EMPTY_DOUBLE_ARRAY = new double[0];

    private static final Object[] EMPTY_OBJECT_ARRAY = new Object[0];
    private static final String[] EMPTY_STRING_ARRAY = new String[0];
    private static final Boolean[] EMPTY_BOOLEAN_OBJECT_ARRAY = new Boolean[0];
    private static final Byte[] EMPTY_BYTE_OBJECT_ARRAY = new Byte[0];
    private static final Character[] EMPTY_CHARACTER_ARRAY = new Character[0];
    private static final Short[] EMPTY_SHORT_OBJECT_ARRAY = new Short[0];
    private static final Integer[] EMPTY_INTEGER_ARRAY = new Integer[0];
    private static final Long[] EMPTY_LONG_OBJECT_ARRAY = new Long[0];
    private static final Float[] EMPTY_FLOAT_OBJECT_ARRAY = new Float[0];
    private static final Double[] EMPTY_DOUBLE_OBJECT_ARRAY = new Double[0];

    //region -------- isEmpty --------

    public static <T> boolean isEmpty(@Nullable T[] array) {
        return array == null || array.length == 0;
    }

    public static boolean isEmpty(@Nullable boolean[] array) {
        return array == null || array.length == 0;
    }

    public static boolean isEmpty(@Nullable byte[] array) {
        return array == null || array.length == 0;
    }

    public static boolean isEmpty(@Nullable char[] array) {
        return array == null || array.length == 0;
    }

    public static boolean isEmpty(@Nullable short[] array) {
        return array == null || array.length == 0;
    }

    public static boolean isEmpty(@Nullable int[] array) {
        return array == null || array.length == 0;
    }

    public static boolean isEmpty(@Nullable long[] array) {
        return array == null || array.length == 0;
    }

    public static boolean isEmpty(@Nullable float[] array) {
        return array == null || array.length == 0;
    }

    public static boolean isEmpty(@Nullable double[] array) {
        return array == null || array.length == 0;
    }

    public static boolean isEmpty(@Nullable Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    public static boolean isEmpty(@Nullable Map<?, ?> map) {
        return map == null || map.isEmpty();
    }

    public static boolean isEmpty(@Nullable Properties properties) {
        return properties == null || properties.isEmpty();
    }

    public static boolean isEmpty(@Nullable String string) {
        return string == null || string.isEmpty();
    }

    //endregion

    //region -------- nullToEmpty --------

    public static boolean[] nullToEmpty(@Nullable boolean[] array) {
        return array == null ? EMPTY_BOOLEAN_ARRAY : array;
    }

    public static byte[] nullToEmpty(@Nullable byte[] array) {
        return array == null ? EMPTY_BYTE_ARRAY : array;
    }

    public static char[] nullToEmpty(@Nullable char[] array) {
        return array == null ? EMPTY_CHAR_ARRAY : array;
    }

    public static short[] nullToEmpty(@Nullable short[] array) {
        return array == null ? EMPTY_SHORT_ARRAY : array;
    }

    public static int[] nullToEmpty(@Nullable int[] array) {
        return array == null ? EMPTY_INT_ARRAY : array;
    }

    public static long[] nullToEmpty(@Nullable long[] array) {
        return array == null ? EMPTY_LONG_ARRAY : array;
    }

    public static float[] nullToEmpty(@Nullable float[] array) {
        return array == null ? EMPTY_FLOAT_ARRAY : array;
    }

    public static double[] nullToEmpty(@Nullable double[] array) {
        return array == null ? EMPTY_DOUBLE_ARRAY : array;
    }

    public static Object[] nullToEmpty(@Nullable Object[] array) {
        return array == null ? EMPTY_OBJECT_ARRAY : array;
    }

    public static String[] nullToEmpty(@Nullable String[] array) {
        return array == null ? EMPTY_STRING_ARRAY : array;
    }

    public static Boolean[] nullToEmpty(@Nullable Boolean[] array) {
        return array == null ? EMPTY_BOOLEAN_OBJECT_ARRAY : array;
    }

    public static Byte[] nullToEmpty(@Nullable Byte[] array) {
        return array == null ? EMPTY_BYTE_OBJECT_ARRAY : array;
    }

    public static Character[] nullToEmpty(@Nullable Character[] array) {
        return array == null ? EMPTY_CHARACTER_ARRAY : array;
    }

    public static Short[] nullToEmpty(@Nullable Short[] array) {
        return array == null ? EMPTY_SHORT_OBJECT_ARRAY : array;
    }

    public static Integer[] nullToEmpty(@Nullable Integer[] array) {
        return array == null ? EMPTY_INTEGER_ARRAY : array;
    }

    public static Long[] nullToEmpty(@Nullable Long[] array) {
        return array == null ? EMPTY_LONG_OBJECT_ARRAY : array;
    }

    public static Float[] nullToEmpty(@Nullable Float[] array) {
        return array == null ? EMPTY_FLOAT_OBJECT_ARRAY : array;
    }

    public static Double[] nullToEmpty(@Nullable Double[] array) {
        return array == null ? EMPTY_DOUBLE_OBJECT_ARRAY : array;
    }

    @SuppressWarnings("unchecked")
    public static <T> T[] nullToEmpty(@Nullable T[] array, Class<T> clazz) {
        return array == null ? (T[]) Array.newInstance(clazz, 0) : array;
    }

    //endregion

    //region -------- emptyToNull --------

    @Nullable
    public static boolean[] emptyToNull(@Nullable boolean[] array) {
        return isEmpty(array) ? null : array;
    }

    @Nullable
    public static byte[] emptyToNull(@Nullable byte[] array) {
        return isEmpty(array) ? null : array;
    }

    @Nullable
    public static char[] emptyToNull(@Nullable char[] array) {
        return isEmpty(array) ? null : array;
    }

    @Nullable
    public static short[] emptyToNull(@Nullable short[] array) {
        return isEmpty(array) ? null : array;
    }

    @Nullable
    public static int[] emptyToNull(@Nullable int[] array) {
        return isEmpty(array) ? null : array;
    }

    @Nullable
    public static long[] emptyToNull(@Nullable long[] array) {
        return isEmpty(array) ? null : array;
    }

    @Nullable
    public static float[] emptyToNull(@Nullable float[] array) {
        return isEmpty(array) ? null : array;
    }

    @Nullable
    public static double[] emptyToNull(@Nullable double[] array) {
        return isEmpty(array) ? null : array;
    }

    @Nullable
    public static <T> T[] emptyToNull(@Nullable T[] array) {
        return isEmpty(array) ? null : array;
    }

    //endregion

    public static <T> List<T> nullToEmpty(@Nullable List<T> list) {
        return list == null ? Collections.emptyList() : list;
    }

    @Nullable
    public static <T> List<T> emptyToNull(@Nullable List<T> list) {
        return isEmpty(list) ? null : list;
    }

    public static <T> Set<T> nullToEmpty(@Nullable Set<T> set) {
        return set == null ? Collections.emptySet() : set;
    }

    @Nullable
    public static <T> Set<T> emptyToNull(@Nullable Set<T> set) {
        return isEmpty(set) ? null : set;
    }

    public static <K, V> Map<K, V> nullToEmpty(@Nullable Map<K, V> map) {
        return map == null ? Collections.emptyMap() : map;
    }

    @Nullable
    public static <K, V> Map<K, V> emptyToNull(@Nullable Map<K, V> map) {
        return isEmpty(map) ? null : map;
    }

    public static Properties nullToEmpty(@Nullable Properties properties) {
        return properties == null ? new Properties() : properties;
    }

    @Nullable
    public static Properties emptyToNull(@Nullable Properties properties) {
        return isEmpty(properties) ? null : properties;
    }

    public static String nullToEmpty(@Nullable String string) {
        return string == null ? "" : string;
    }

    @Nullable
    public static String emptyToNull(@Nullable String string) {
        return isEmpty(string) ? null : string;
    }

    public static String trimToEmpty(@Nullable String string) {
        return nullToEmpty(transform(string, String::trim));
    }

    @Nullable
    public static String trimToNull(@Nullable String string) {
        return emptyToNull(transform(string, String::trim));
    }

    public static String stripToEmpty(@Nullable String string) {
        return nullToEmpty(transform(string, StringUtils::trimWhitespace));
    }

    @Nullable
    public static String stripToNull(@Nullable String string) {
        return emptyToNull(transform(string, StringUtils::trimWhitespace));
    }

    @Nullable
    private static String transform(@Nullable String string, Function<String, String> function) {
        if (string != null) {
            string = function.apply(string);
        }
        return string;
    }
}
