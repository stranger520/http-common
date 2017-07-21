package com.zuicoding.platform.common.http;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

/**
 * Created by Stephen.lin on 2017/7/21.
 * <p>
 * Description :<p></p>
 */
public class HttpExecutor {




    private HttpClient client = new builder().build();



    public String  execute(HttpRequestBuilder builder) throws Exception {

        HttpResponse response = client.execute(builder.build());
        if (response != null && response.getEntity() != null){
            return EntityUtils.toString(response.getEntity(),builder.getCharset());
        }
        return null;
    }



    private static class builder{
        private PoolingHttpClientConnectionManager httpClientConnectionManager;
        private HttpClient client;
        public  builder(){
            httpClientConnectionManager = new PoolingHttpClientConnectionManager();
            //最大连接数
            httpClientConnectionManager.setMaxTotal(200);
            //设置每个主机地址的并发数
            httpClientConnectionManager.setDefaultMaxPerRoute(200);
            httpClientConnectionManager.setValidateAfterInactivity(3000);
            RequestConfig config =RequestConfig.custom()
                    .setConnectTimeout(
                            3000)
                    .setConnectionRequestTimeout(
                            3000)
                    .setSocketTimeout(3000).build()
                    //.setStaleConnectionCheckEnabled()
                    ;
            client = HttpClientBuilder.create()
                    .setDefaultRequestConfig(config)
                    .setConnectionManager(httpClientConnectionManager)
                    .build();
        }

        public HttpClient build(){

            return client;
        }
    }




}
