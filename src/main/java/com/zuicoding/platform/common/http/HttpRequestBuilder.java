package com.zuicoding.platform.common.http;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stephen.lin on 2017/7/21.
 * <p>
 * Description :<p></p>
 */
public final class HttpRequestBuilder {

    private String charset = "UTF-8";

    private String url;

    private HttpMethod method = HttpMethod.GET;

    private List<NameValuePair> params = new ArrayList<>();

    private List<Header> headers = new ArrayList<>();

    public String getCharset() {
        return charset;
    }

    public HttpRequestBuilder setCharset(String charset) {
        this.charset = charset;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public HttpRequestBuilder setUrl(String url) {
        this.url = url;
        return this;
    }

    public List<NameValuePair> getParams() {
        return params;
    }

    public HttpRequestBuilder setParams(List<NameValuePair> params) {
        this.params = params;
        return this;
    }

    public List<Header> getHeaders() {
        return headers;
    }

    public HttpRequestBuilder setHeaders(List<Header> headers) {
        this.headers = headers;
        return this;
    }

    public HttpMethod getMethod() {
        return method;
    }

    public HttpRequestBuilder setMethod(HttpMethod method) {
        this.method = method;
        return this;
    }

    public HttpRequestBuilder addParam(String name, String value){
        this.params.add(new BasicNameValuePair(name, value));
        return this;
    }

    public HttpRequestBuilder addHeader(String name, String value){
        this.headers.add(new BasicHeader(name,value));
        return this;
    }

    public HttpRequestBase build(){
        if (url == null || "".equals(url.trim())){
            throw new IllegalArgumentException("url is empty!");
        }
        if (method == null){
            throw new IllegalArgumentException("http method is null");
        }
        HttpRequestBase http = null;
        HttpEntity entity = null;
        try {
            switch (method){
                case GET:
                    http = new HttpGet();
                    if (params != null && !params.isEmpty()){
                        entity = new UrlEncodedFormEntity(params,charset);
                        url += "?" + EntityUtils.toString(entity,charset);
                    }

                    break;

                case POST:
                    http = new HttpPost();
                    if (params != null && !params.isEmpty()){
                        entity = new UrlEncodedFormEntity(params,charset);
                        ((HttpPost)http).setEntity(entity);
                    }
                    break;
            }
            http.setURI(URI.create(url));
        }catch (Exception e){
            throw new RuntimeException(e);
        }

        return http;

    }
}
