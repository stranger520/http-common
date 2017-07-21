package com.zuicoding.platform;

import com.zuicoding.platform.common.http.HttpExecutor;
import com.zuicoding.platform.common.http.HttpRequestBuilder;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception {
        System.out.println( new HttpExecutor().execute(new HttpRequestBuilder().setUrl("http://www.google.com")));
    }
}
