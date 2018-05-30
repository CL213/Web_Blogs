package com.cn.data.controller;

import java.util.Iterator;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class test{
	
	public test a(){
		test dfc = null;
	    String aString="{\"alitrip_btrip_flight_order_search_response\":{\"result\":{\"flight_order_list\":{\"open_flight_order_rs\":[{\"apply_id\":3579470,\"arr_airport\":\"萧山机场\",\"arr_city\":\"杭州\",\"cabin_class\":\"经济舱\",\"contact_name\":\"李成功\",\"contact_phone\":\"18618463133\",\"corp_id\":\"ding1dcc961be42b50fe35c2f4657eb6378f\",\"corp_name\":\"中建投信托有限责任公司\",\"cost_center\":{\"corp_id\":\"ding1dcc961be42b50fe35c2f4657eb6378f\",\"name\":\"中建投信托有限责任公司\"},\"dep_airport\":\"首都机场\",\"dep_city\":\"北京\",\"dep_date\":\"2018-05-17 11:00:00\",\"depart_id\":\"54404696\",\"depart_name\":\"北京业务四部\",\"discount\":\"100.00%\",\"flight_no\":\"CA1702\",\"gmt_create\":\"2018-05-16 16:39:27\",\"gmt_modified\":\"2018-05-16 16:40:23\",\"id\":575879892399,\"insure_info_list\":{},\"invoice\":{\"id\":68916,\"title\":\"中建投信托有限责任公司\"},\"passenger_count\":1,\"passenger_name\":\"李成功\",\"price_info_list\":{\"open_price_info\":[{\"category\":\"机票预订\",\"pay_type\":4,\"price\":\"2250.0\",\"type\":1}]},\"ret_date\":\"2018-05-17 13:15:00\",\"status\":5,\"trip_type\":0,\"user_id\":\"511\",\"user_name\":\"李成功\"},{\"apply_id\":3579470,\"arr_airport\":\"流亭机场\",\"arr_city\":\"青岛\",\"cabin_class\":\"经济舱\",\"contact_name\":\"李成功\",\"contact_phone\":\"18618463133\",\"corp_id\":\"ding1dcc961be42b50fe35c2f4657eb6378f\",\"corp_name\":\"中建投信托有限责任公司\",\"cost_center\":{\"corp_id\":\"ding1dcc961be42b50fe35c2f4657eb6378f\",\"name\":\"中建投信托有限责任公司\"},\"dep_airport\":\"萧山机场\",\"dep_city\":\"杭州\",\"dep_date\":\"2018-05-17 18:10:00\",\"depart_id\":\"54404696\",\"depart_name\":\"北京业务四部\",\"discount\":\"100.00%\",\"flight_no\":\"GS6608\",\"gmt_create\":\"2018-05-16 16:41:15\",\"gmt_modified\":\"2018-05-16 16:42:12\",\"id\":575881012399,\"insure_info_list\":{},\"invoice\":{\"id\":68916,\"title\":\"中建投信托有限责任公司\"},\"passenger_count\":1,\"passenger_name\":\"李成功\",\"price_info_list\":{\"open_price_info\":[{\"category\":\"机票预订\",\"pay_type\":4,\"price\":\"1040.0\",\"type\":1}]},\"ret_date\":\"2018-05-17 19:50:00\",\"status\":5,\"trip_type\":0,\"user_id\":\"511\",\"user_name\":\"李成功\"},{\"apply_id\":3579470,\"arr_airport\":\"首都机场\",\"arr_city\":\"北京\",\"cabin_class\":\"经济舱\",\"contact_name\":\"李成功\",\"contact_phone\":\"18618463133\",\"corp_id\":\"ding1dcc961be42b50fe35c2f4657eb6378f\",\"corp_name\":\"中建投信托有限责任公司\",\"cost_center\":{\"corp_id\":\"ding1dcc961be42b50fe35c2f4657eb6378f\",\"name\":\"中建投信托有限责任公司\"},\"dep_airport\":\"流亭机场\",\"dep_city\":\"青岛\",\"dep_date\":\"2018-05-18 12:45:00\",\"depart_id\":\"54404696\",\"depart_name\":\"北京业务四部\",\"discount\":\"90.54%\",\"flight_no\":\"CA4653\",\"gmt_create\":\"2018-05-18 10:47:01\",\"gmt_modified\":\"2018-05-18 10:48:07\",\"id\":576829194399,\"insure_info_list\":{},\"invoice\":{\"id\":68916,\"title\":\"中建投信托有限责任公司\"},\"passenger_count\":1,\"passenger_name\":\"李成功\",\"price_info_list\":{\"open_price_info\":[{\"category\":\"机票预订\",\"pay_type\":4,\"price\":\"1390.0\",\"type\":1}]},\"ret_date\":\"2018-05-18 14:15:00\",\"status\":5,\"trip_type\":0,\"user_id\":\"511\",\"user_name\":\"李成功\"}]},\"result_code\":0,\"success\":true},\"request_id\":\"3gj5tscwbdo8\"}}";
	
	    //将字符串转换为json字符串
	    JSONObject object = JSON.parseObject(aString);
	    //获取key并迭代循环
	    Iterator<String> iter = object.keySet().iterator(); 
	
	    while(iter.hasNext()){
	        //获取key
	        String key = (String) iter.next(); //111,3213,123412
	        //根据key获取对应的json串并将其转换成java对象
	        dfc=JSON.parseObject(object.get(key)+"",test.class);
	        System.out.println(dfc.toString());
	    }
	    return dfc;
	}
    
}