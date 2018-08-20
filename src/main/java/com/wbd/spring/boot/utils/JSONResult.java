package com.wbd.spring.boot.utils;

import com.alibaba.fastjson.JSONObject;

/**
 * 返回值的处理
* <p>Title: JSONResult.java</p>  
* <p>Description: </p>  
* @author 朱光和 
* @date 2018年8月14日
 */
public class JSONResult {
	public static String fillResultString(Integer status, String message, Object result){
        JSONObject jsonObject = new JSONObject(){/** serialVersionUID*/ 
			private static final long serialVersionUID = 1L;

		{
            put("status", status);
            put("message", message);
            put("result", result);
        }};
        return jsonObject.toString();
    }
}
