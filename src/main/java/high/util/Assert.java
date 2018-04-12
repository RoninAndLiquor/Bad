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

public class Assert {

	public static Boolean checkParam(Object arg) {
        return execute(arg);
    }
	
	@SuppressWarnings("unused")
	private static Boolean execute(Object arg){
		String checkType = checkType(arg);
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
        if (arg instanceof Long && arg==null) {
            return false;
        }
        if (arg instanceof Integer && arg==null) {
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
        if(checkType==null){
        	Class<? extends Object> class1 = arg.getClass();
    		String typeName = class1.getTypeName();
    		Integer methodFlag = 0;
    		Integer booleanFlag = 0;
    		Method[] methods = class1.getDeclaredMethods();
    		for(int i=0;i<methods.length;i++){
    			if(methods[i].getName().startsWith("get")){
    				methodFlag ++;
    				Object s = null;
    				try {
    					s = methods[i].invoke(arg);
    				} catch (IllegalAccessException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				} catch (IllegalArgumentException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				} catch (InvocationTargetException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}
    				System.out.println(s+methods[i].getName());
    				Boolean execute = executeObj(s);
    				if(!execute){
    					booleanFlag++;
    				}
    			}
    		}
    		if(methodFlag == booleanFlag){
    			return false;
    		}
        }
		return true;
	}
	private static Boolean executeObj(Object arg){
		if(arg==null){
			return false;
		}
		if(arg instanceof String && StringUtils.isBlank(arg.toString())){
			return false;
		}
		if(arg instanceof Long && Long.compare((Long) arg, 0L)<=0){
			return false;
		}
		if(arg instanceof Integer && Integer.compare((Integer) arg, 0)<=0){
			return false;
		}
		if(arg instanceof Map<?,?> && ((Map<?,?>) arg).isEmpty()){
			return false;
		}
		if(arg instanceof HashMap<?,?> &&((HashMap<?,?>) arg).isEmpty()){
			return false;
		}
		if(arg instanceof List<?> && ((List<?>) arg).isEmpty()){
			return false;
		}
		if(arg instanceof ArrayList<?> && ((ArrayList<?>) arg).isEmpty()){
			return false;
		}
		if(arg instanceof Set<?> && ((Set<?>) arg).isEmpty()){
			return false;
		}
		if(arg.getClass().isArray()){
			Object[] obj1 = (Object[]) arg;
			return obj1.length>0;
		}
		return true;
	}
	private static String checkType(Object arg){
		if(arg instanceof String){
			return "String";
		}
		if(arg instanceof Long){
			return "Long";
		}
		if(arg instanceof Integer){
			return "Integer";
		}
		if(arg instanceof Map<?,?>){
			return "Map";
		}
		if(arg instanceof HashMap<?,?> ){
			return "HashMap";
		}
		if(arg instanceof List<?>){
			return "List";
		}
		if(arg instanceof ArrayList<?>){
			return "ArrayList";
		}
		if(arg instanceof Set<?>){
			return "Set";
		}
		if(arg.getClass().isArray()){
			return "Array";
		}
		return null;
	}
	
}
