package cn.com.bizunited.cp.oa.commons.enums;

/**
 * @Description:
 * @author: Paul Chan
 * @date: 2017/4/25-20:26
 */
public enum Position {

    ADMIN("zbgly"), ZZ("zz"), ZY("zy"), DZ("dz"), DY("dy");

    private String code;

    Position(String code){
        this.code = code;
    }

    public String getPCode(){
        return this.code;
    }

    public static boolean isLeader(String pCode){
        if(pCode.equals(ADMIN.getPCode())
                || pCode.equals(ZZ.getPCode())
                || pCode.equals(DZ.getPCode())){
            return true;
        }
        return false;
    }

}
