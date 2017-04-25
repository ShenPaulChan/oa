package cn.com.bizunited.cp.oa.utils;

import org.apache.commons.collections.MapUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;

import javax.net.ssl.SSLContext;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class SimpleHttpClient {

    public static final String UTF_8 = "utf-8";

    private SSLContext ctx;

    public SimpleHttpClient() {

    }

    public SimpleHttpClient(SSLContext ctx) {
        this.ctx = ctx;
    }

    public String doPost(final String url, final Map<String, String> params)
        throws Exception {

        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        if (ctx != null) {
            httpClientBuilder.disableAuthCaching().disableCookieManagement().setSslcontext(ctx);
        }
        CloseableHttpClient httpClient = httpClientBuilder.build();
        HttpPost httpPost = new HttpPost(url);
        CloseableHttpResponse httpResponse = null;
        try {
            
        	httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
			List<BasicNameValuePair> list = new ArrayList<BasicNameValuePair>();
			if(MapUtils.isNotEmpty(params)){
	    		Iterator<String> it = params.keySet().iterator();
	    		while (it.hasNext()) {
	    			String key = it.next();
	    			BasicNameValuePair data = new BasicNameValuePair(key, params.get(key));
	    			list.add(data);
				}
	    	}
			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list,"utf-8");
			httpPost.setEntity(entity);
			RequestConfig requestConfig = RequestConfig.custom()  
			        .setConnectTimeout(40000).setConnectionRequestTimeout(20000)  
			        .setSocketTimeout(40000).build();  
			httpPost.setConfig(requestConfig);
			
            httpResponse = httpClient.execute(httpPost);
            return getResponseBody(httpResponse);
        } finally {
            try {
                if (httpResponse != null) {
                    httpResponse.close();
                }
            } catch (IOException ignored) {
            }
            try {
                if (httpClient != null) {
                    httpClient.close();
                }
            } catch (IOException ignored) {
            }
        }

    }

    private String getResponseBody(HttpResponse resp) throws IOException {
        HttpEntity httpEntity = resp.getEntity();
        Long length = httpEntity.getContentLength();
        ByteArrayOutputStream buffer = (length > 0) ?
            new ByteArrayOutputStream(length.intValue()) :
            new ByteArrayOutputStream();
        resp.getEntity().writeTo(buffer);
        return buffer.toString(UTF_8);
    }
    
    public JSONObject doPostJson(final String url, final Map<String, String> params)
            throws Exception {

            HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
            if (ctx != null) {
                httpClientBuilder.disableAuthCaching().disableCookieManagement().setSslcontext(ctx);
            }
            CloseableHttpClient httpClient = httpClientBuilder.build();
            HttpPost httpPost = new HttpPost(url);
            CloseableHttpResponse httpResponse = null;
            try {
                
            	httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
    			List<BasicNameValuePair> list = new ArrayList<BasicNameValuePair>();
    			if(MapUtils.isNotEmpty(params)){
    	    		Iterator<String> it = params.keySet().iterator();
    	    		while (it.hasNext()) {
    	    			String key = it.next();
    	    			BasicNameValuePair data = new BasicNameValuePair(key, params.get(key));
    	    			list.add(data);
    				}
    	    	}
    			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list,"utf-8");
    			httpPost.setEntity(entity);
    			RequestConfig requestConfig = RequestConfig.custom()  
    			        .setConnectTimeout(40000).setConnectionRequestTimeout(20000)  
    			        .setSocketTimeout(40000).build();  
    			httpPost.setConfig(requestConfig);
    			
                httpResponse = httpClient.execute(httpPost);
                return getJsonResponseBody(httpResponse);
            } finally {
                try {
                    if (httpResponse != null) {
                        httpResponse.close();
                    }
                } catch (IOException ignored) {
                }
                try {
                    if (httpClient != null) {
                        httpClient.close();
                    }
                } catch (IOException ignored) {
                }
            }

        }
    
    private JSONObject getJsonResponseBody(HttpResponse resp) throws IOException {
        HttpEntity httpEntity = resp.getEntity();
        return JSONObject.parseObject(httpEntity.getContent(), Charset.forName(UTF_8), null, Feature.AutoCloseSource);
    }
}
