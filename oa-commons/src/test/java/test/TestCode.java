package test;

import org.junit.Test;

import cn.com.bizunited.cp.oa.utils.GeneratorCodeUtils;





public class TestCode {

    @Test
    public void orderBackNumTest(){
	String code = GeneratorCodeUtils.code("back");
	System.out.println(code);
	
    }

    @Test
    public void regex(){
        /*String pattern = "<billitem>(\\S|\\s|[^(<billitem>)])*<material_name>中和</material_name>(\\S|\\s)*</billitem>";
        String content = "<billitem>1111111fd   sfsdfsdf1111<billitem>11\n sdfdsffsdf<material_name>中和</material_name>sdfsdfsfsddfsdfsf </billitem>2222   22222222";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(content);
        System.out.println(m.replaceAll(""));*/
    }
    
}
