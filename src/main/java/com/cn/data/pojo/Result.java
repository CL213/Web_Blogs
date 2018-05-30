package com.cn.data.pojo;
/**
 * Auto-generated: 2018-05-22 16:55:41
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Result {

    private Flight_order_list flight_order_list;
    private int result_code;
    private boolean success;
    public void setFlight_order_list(Flight_order_list flight_order_list) {
         this.flight_order_list = flight_order_list;
     }
     public Flight_order_list getFlight_order_list() {
         return flight_order_list;
     }

    public void setResult_code(int result_code) {
         this.result_code = result_code;
     }
     public int getResult_code() {
         return result_code;
     }

    public void setSuccess(boolean success) {
         this.success = success;
     }
     public boolean getSuccess() {
         return success;
     }

}