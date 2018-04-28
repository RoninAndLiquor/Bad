package high.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

public class Assert {

    private static final Logger LOG = LoggerFactory.getLogger(Assert.class);

    private static final String OBJECT_ERR = "object is null";

    private static final String STRING_ERR = "type{String} is empty";

    private static final String MAP_ERR = "type{Map} is empty";

    private static final String LONG_ERR = "type{Long} is empty";

    private static final String INTEGER_ERR = "type{Integer} is empty";

    private static final String LIST_ERR = "type{List} is empty";

    private static final String SET_ERR = "type{Set} is empty";

    private static final String BIGDECIMAL_ERR = "type{Bigdecimal} is empty";

    private static final String ARRAY_ERR = "type{Array} is empty";

    private static final String CLASS_TYPE_ERR = "class type different";

    private static final String VAL_DIFFERENT = "value different";

    private static final String CLASS_TYPE_SUPPORT = "class type no support";

    private Assert() {

    }

    public static Boolean hasRepeat(Object arg) {
        notNull(arg);
        if (arg.getClass().isArray()) {
            return arrayRepeat(arg);
        }
        if (arg instanceof List<?>) {
            return listRepeat(arg);
        }
        return false;
    }

    public static void hasRepeat(Object arg, BaseException exception) {
        if (hasRepeat(arg)) {
            throw exception;
        }
    }

    public static void hasRepeat(String msg, Object arg) {
        if (hasRepeat(arg)) {
            throw error(msg);
        }
    }

    private static Boolean arrayRepeat(Object arg) {
        Set<Object> arg0 = new HashSet<>();
        Object[] arg1 = (Object[]) arg;
        for (Object obj : arg1) {
            arg0.add(obj);
        }
        return arg0.size() != arg1.length;
    }

    private static Boolean listRepeat(Object arg) {
        Set<Object> arg0 = new HashSet<>();
        List<?> arg1 = (List<?>) arg;
        for (Object obj : arg1) {
            arg0.add(obj);
        }
        return arg0.size() != arg1.size();

    }

    private static void execute(Boolean check, BaseException exception, String msg, Object arg) {
        LOG.debug("param:{}", JSON.toJSONString(arg));
        if (arg == null) {
            throw !checkParam(exception) ? error(StringUtils.isBlank(msg) ? OBJECT_ERR : msg) : exception;
        }
        if (arg instanceof String && StringUtils.isBlank(arg.toString())) {
            throw !checkParam(exception) ? error(StringUtils.isBlank(msg) ? STRING_ERR : msg) : exception;
        }
        if (arg instanceof Map<?, ?> && ((Map<?, ?>) arg).isEmpty()) {
            throw !checkParam(exception) ? error(StringUtils.isBlank(msg) ? MAP_ERR : msg) : exception;
        }
        if (arg instanceof HashMap<?, ?> && ((HashMap<?, ?>) arg).isEmpty()) {
            throw !checkParam(exception) ? error(StringUtils.isBlank(msg) ? MAP_ERR : msg) : exception;
        }
        if (arg instanceof List<?> && ((List<?>) arg).isEmpty()) {
            throw !checkParam(exception) ? error(StringUtils.isBlank(msg) ? LIST_ERR : msg) : exception;
        }
        if (arg instanceof ArrayList<?> && ((ArrayList<?>) arg).isEmpty()) {
            throw !checkParam(exception) ? error(StringUtils.isBlank(msg) ? LIST_ERR : msg) : exception;
        }
        if (arg instanceof Set<?> && ((Set<?>) arg).isEmpty()) {
            throw !checkParam(exception) ? error(StringUtils.isBlank(msg) ? SET_ERR : msg) : exception;
        }
        if (arg instanceof HashSet<?> && ((HashSet<?>) arg).isEmpty()) {
            throw !checkParam(exception) ? error(StringUtils.isBlank(msg) ? SET_ERR : msg) : exception;
        }
        if (check) {
            if (arg instanceof BigDecimal && ((BigDecimal) arg).compareTo(BigDecimal.ZERO) <= 0) {
                throw !checkParam(exception) ? error(StringUtils.isBlank(msg) ? BIGDECIMAL_ERR : msg) : exception;
            }
            if (arg instanceof Long && Long.compare((Long) arg, 0L) <= 0) {
                throw !checkParam(exception) ? error(StringUtils.isBlank(msg) ? LONG_ERR : msg) : exception;
            }
            if (arg instanceof Integer && Integer.compare((Integer) arg, 0) <= 0) {
                throw !checkParam(exception) ? error(StringUtils.isBlank(msg) ? INTEGER_ERR : msg) : exception;
            }
        }

        if (arg.getClass().isArray()) {
            Object[] arg1 = (Object[]) arg;
            if (arg1.length <= 0) {
                throw !checkParam(exception) ? error(StringUtils.isBlank(msg) ? ARRAY_ERR : msg) : exception;
            }
        }
    }

    public static void notNull(Boolean check, BaseException exception, Object... arg) {
        for (Object param : arg) {
            execute(check, exception, null, param);
        }
    }

    public static void notNull(Boolean check, String msg, Object... arg) {
        for (Object param : arg) {
            execute(check, null, msg, param);
        }
    }

    public static void notNull(Boolean check, Object... arg) {
        for (Object param : arg) {
            execute(check, null, null, param);
        }
    }

    public static void notNull(BaseException exception, Object... arg) {
        for (Object param : arg) {
            execute(true, exception, null, param);
        }
    }

    public static void notNull(String msg, Object... arg) {
        for (Object param : arg) {
            execute(true, null, msg, param);
        }
    }

    public static void notNull(Object... arg) {
        for (Object param : arg) {
            execute(true, null, null, param);
        }
    }

    public static void notNull(String msg, Object arg) {
        execute(true, null, msg, arg);
    }

    public static Boolean equals(String arg0, String arg1) {
        return StringUtils.equals(arg0, arg1);
    }

    public static Boolean equals(Long arg0, Long arg1) {
        return Long.compare(arg0, arg1) == 0;
    }

    public static Boolean equals(Integer arg0, Integer arg1) {
        return Integer.compare(arg0, arg1) == 0;
    }

    public static Boolean equals(BigDecimal arg0, BigDecimal arg1) {
        return arg0.compareTo(arg1) == 0;
    }

    public static void notEquals(String msg, Object arg0, Object arg1) {
        execute(null, msg, arg0, arg1);
    }

    public static void notEquals(BaseException exception, Object arg0, Object arg1) {
        execute(exception, null, arg0, arg1);
    }

    public static void notEquals(Object arg0, Object arg1) {
        execute(null, null, arg0, arg1);
    }

    public static void notEquals(BaseException exception, Object arg0, Object... arg) {
        for (Object arg_ : arg) {
            if (execute(arg0, arg_)) {
                return;
            }
        }
        throw exception;
    }

    public static void notEquals(String msg, Object arg0, Object... arg) {
        notNull(msg, arg0, arg);
        for (Object arg_ : arg) {
            if (execute(arg0, arg_)) {
                return;
            }
        }
        throw error(msg);
    }

    public static void notEquals(Object arg0, Object... arg) {
        notNull(arg0, arg);
        for (Object arg_ : arg) {
            if (execute(arg0, arg_)) {
                return;
            }
        }
        throw error(VAL_DIFFERENT);
    }

    public static Boolean equals(Object arg0, Object... arg) {
        for (Object arg_ : arg) {
            if (execute(arg0, arg_)) {
                return true;
            }
        }
        return false;
    }

    private static Boolean execute(Object arg0, Object arg1) {
        if (!arg0.getClass().equals(arg1.getClass())) {
            throw error(CLASS_TYPE_ERR);
        }
        if (arg0 instanceof String) {
            return equals(arg0.toString(), arg1.toString());
        }
        if (arg0 instanceof Long) {
            return equals((Long) arg0, (Long) arg1);
        }
        if (arg0 instanceof Integer) {
            return equals((Integer) arg0, (Integer) arg1);
        }
        if (arg0 instanceof BigDecimal) {
            return equals((BigDecimal) arg0, (BigDecimal) arg1);
        }
        if (arg0.getClass().isEnum()) {
            return equals(arg0.toString(), arg1.toString());
        }
        throw error(CLASS_TYPE_SUPPORT);
    }

    private static void execute(BaseException exception, String msg, Object arg0, Object arg1) {
        if (!arg0.getClass().equals(arg1.getClass())) {
            throw !checkParam(exception) ? error(StringUtils.isBlank(msg) ? CLASS_TYPE_ERR : msg) : exception;
        }
        if (arg0 instanceof String && !equals(arg0.toString(), arg1.toString())) {
            throw !checkParam(exception) ? error(StringUtils.isBlank(msg) ? VAL_DIFFERENT : msg) : exception;
        }
        if (arg0 instanceof Long && !equals((Long) arg0, (Long) arg1)) {
            throw !checkParam(exception) ? error(StringUtils.isBlank(msg) ? VAL_DIFFERENT : msg) : exception;
        }
        if (arg0 instanceof Integer && !equals((Integer) arg0, (Integer) arg1)) {
            throw !checkParam(exception) ? error(StringUtils.isBlank(msg) ? VAL_DIFFERENT : msg) : exception;
        }
        if (arg0 instanceof BigDecimal && !equals((BigDecimal) arg0, (BigDecimal) arg1)) {
            throw !checkParam(exception) ? error(StringUtils.isBlank(msg) ? VAL_DIFFERENT : msg) : exception;
        }
        if (arg0.getClass().isEnum() && !equals(arg0.toString(), arg1.toString())) {
            throw !checkParam(exception) ? error(StringUtils.isBlank(msg) ? VAL_DIFFERENT : msg) : exception;
        }
    }

    private static BaseException error(String msg_) {
        LOG.error(msg_);
        return new BaseException(msg_);
    }

    public static Boolean checkParam(Object arg) {
        return execute(arg);
    }

    public static Boolean checkParam(Object... arg) {
        for (Object param : arg) {
            if (!execute(param)) {
                return false;
            }
        }
        return true;
    }

    public static Boolean checkParam(Boolean arg0, Object... arg) {
        for (Object param : arg) {
            if (execute(param) != arg0) {
                return false;
            }
        }
        return true;
    }

    private static Boolean execute(Object arg) {
        if (arg == null) {
            return false;
        }
        if (arg instanceof String && StringUtils.isBlank(arg.toString())) {
            return false;
        }
        if (arg instanceof HashMap<?, ?> && ((HashMap<?, ?>) arg).isEmpty()) {
            return false;
        }
        if (arg instanceof Map<?, ?> && ((Map<?, ?>) arg).isEmpty()) {
            return false;
        }
        if (arg instanceof Long && Long.compare((Long) arg, 0L) <= 0) {
            return false;
        }
        if (arg instanceof Integer && Integer.compare((Integer) arg, 0) <= 0) {
            return false;
        }
        if (arg instanceof List<?> && ((List<?>) arg).isEmpty()) {
            return false;
        }
        if (arg instanceof ArrayList<?> && ((ArrayList<?>) arg).isEmpty()) {
            return false;
        }
        if (arg instanceof Set<?> && ((Set<?>) arg).isEmpty()) {
            return false;
        }
        if (arg instanceof HashSet<?> && ((HashSet<?>) arg).isEmpty()) {
            return false;
        }
        if (arg instanceof BigDecimal && ((BigDecimal) arg).compareTo(BigDecimal.ZERO) <= 0) {
            return false;
        }
        if (arg.getClass().isArray()) {
            Object[] arg1 = (Object[]) arg;
            return arg1.length > 0;
        }
        return true;
    }
}
