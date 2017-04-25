package cn.com.bizunited.cp.oa.utils;

import java.lang.reflect.Field;
/**
 * Created by Scutum on 2017/3/28.
 */
public class BeanUtils {
    public static void copyPropertysWithoutNull(Object des, Object src){
        Class<?> clazz = des.getClass();
        Field[] srcfields=src.getClass().getDeclaredFields();
        for(Field field:srcfields){
            if(field.getName().equals("serialVersionUID"))
                continue;
            Field f;
            try {
                f = clazz.getDeclaredField(field.getName());
                f.setAccessible(true);
                field.setAccessible(true);
                Object obj = field.get(src);
                if(obj!=null)
                    f.set(des,obj);
            } catch (SecurityException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
