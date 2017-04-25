package cn.com.bizunited.cp.oa.commons;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Map;

public class AccessStatus {

    @Description(value = "操作成功", key = 1000)
    public static int SERVER_SUCCESS = 1000;
    @Description(value = "服务器错误", key = 1001)
    public static int SERVER_GENERIC_ERROR = 1001;
    @Description(value = "缺少参数", key = 1002)
    public static int INVALID_REQUEST_PARAMETER = 1002;
    @Description(value = "登录过期,请重新登录", key = 1003)
    public static int INVALID_SECRET_TOKEN = 1003;
    @Description(value = "密码错误", key = 1004)
    public static int ACCOUNT_INVALID = 1004;
    @Description(value = "签名错误", key = 1005)
    public static int SIGN_ILLEGAL = 1005;
    @Description(value = "客户不存在", key = 1006)
    public static int NOT_FIND_CUSTOMER = 1006;
    @Description(value = "自选分类不存在", key = 1007)
    public static int NOT_FIND_GROUP = 1007;



    protected static Map<Integer, String> descriptionMap = new LinkedHashMap<>();

    static {
        Field[] fields = AccessStatus.class.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Description.class)) {
                Description description = field.getAnnotation(Description.class);
                descriptionMap.put(description.key(), description.value());
            }
        }
    }

    public static String getDescription(int errorCode) {
        return descriptionMap.get(errorCode);
    }
}
