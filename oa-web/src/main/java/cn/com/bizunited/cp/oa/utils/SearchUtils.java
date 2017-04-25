package cn.com.bizunited.cp.oa.utils;

import cn.com.bizunited.cp.common.web.utils.AdminLTEParseUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @Description: 搜索的工具类
 * @author: Paul Chan
 * @date: 2017/3/24-13:10
 */
public class SearchUtils {

    public static Map<String, Object> getColumnsSearch(HttpServletRequest request, Map<String, AdminLTEParseUtils.SearchType> searchTypeMap){
        Map<String, Object> columnsSearch = new HashMap<String, Object>();
        try {
            String searchVal = request.getParameter("columnsSearch[value]");
            if(StringUtils.isEmpty(searchVal))return new HashMap<String, Object>();
            searchVal = URLDecoder.decode(searchVal, "ISO8859-1");
            searchVal = AdminLTEParseUtils.parseToUTF8(searchVal);
            String[] splitKeyValueStrs = StringUtils.split(searchVal, "&");
            for (String keyVal: splitKeyValueStrs ) {
                String[] strs = StringUtils.splitByWholeSeparatorPreserveAllTokens(keyVal,"=");
                if(strs.length == 1) {
                    continue;
                }
                String key = strs[0];
                String value = strs[1];
                value = StringUtils.trim(value);
                if(StringUtils.isNotBlank(value)) {
                    AdminLTEParseUtils.SearchType searchType  = (searchTypeMap==null?null:searchTypeMap.get(key));
                    if(searchType != null) {
                        if(searchType == AdminLTEParseUtils.SearchType.DATE) {
                            columnsSearch.put(key, cn.com.bizunited.cp.common.utils.DateUtils.stringParseDate(value, "yyyy-MM-dd HH:mm:ss"));
                        }else if(searchType == AdminLTEParseUtils.SearchType.LIST) {
                            Object objVal = columnsSearch.get(key);
                            if(objVal != null) {
                                if(objVal instanceof List) {
                                    List tempObjValList = (List) objVal;
                                    tempObjValList.add(value);
                                    columnsSearch.put(key, tempObjValList);
                                }
                            }else {
                                columnsSearch.put(key, CollectionUtils.asList(value));
                            }
                        }
                    }else {
                        columnsSearch.put(key, value);
                    }
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return  columnsSearch;
    }

}
