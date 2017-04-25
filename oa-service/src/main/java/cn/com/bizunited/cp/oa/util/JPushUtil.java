package cn.com.bizunited.cp.oa.util;

//import cn.jiguang.common.ClientConfig;
//import cn.jiguang.common.resp.APIConnectionException;
//import cn.jiguang.common.resp.APIRequestException;
//import cn.jpush.api.JPushClient;
//import cn.jpush.api.push.PushResult;
//import cn.jpush.api.push.model.PushPayload;


/**
 * @Description: 极光推送工具
 * @author: Paul Chan
 * @date: 2017/3/23-11:32
 */
public class JPushUtil {

//    private static  final Logger logger = Logger.getLogger(JPushUtil.class);
//
//    private  static ResourceBundle bundle = ResourceBundle.getBundle("jpush");
//    private static  final  String APP_KEY = bundle.getString("appKey");
//    private static  final  String MASTER_SECRET = bundle.getString("masterSecret");
//    private static JPushClient jpushClient = new JPushClient(MASTER_SECRET, APP_KEY, null, ClientConfig.getInstance());
//
//    public static void main(String[] args) {
//        try {
//            alertAll("啦啦啦啦");
//        } catch (APIConnectionException e) {
//            e.printStackTrace();
//        } catch (APIRequestException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    public static PushResult sendPush(PushPayload payload) throws APIConnectionException, APIRequestException{
//        PushResult result = jpushClient.sendPush(payload);
//        logger.info(result.toString());
//        return result;
//    }
//
//    public static  PushResult alertAll(String message) throws APIConnectionException, APIRequestException{
//        // For push, all you need do is to build PushPayload object.
//        PushPayload payload = buildAlertAllPayload(message);
//        return sendPush(payload);
//    }
//
//    public static PushPayload buildAlertAllPayload(String message) {
//        return PushPayload.alertAll(message);
//    }

}
