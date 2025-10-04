package com.example.chefkart.util;

public class ConstantData {

    //otp
    public static final String CUSTOMER_ID="C-C0EFD6C13A1247C";
    public static final String AUTH_TOKEN="eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJDLUMwRUZENkMxM0ExMjQ3QyIsImlhdCI6MTczODY0Nzk0MiwiZXhwIjoxODk2MzI3OTQyfQ.cJoa6PJWiyEt_ZI45_OnVjDAOlaJ9jx1EAXm22cmvaeG72VR39MWujh4W51bsDxQwiuRCN0PjpWFywbXJ_wXcw";

    //api000
    public static final String SERVER_ADDRESS="http://192.168.1.7:8000/";

    public static final String SERVER_ADDRESS_IMG=SERVER_ADDRESS+"images/";
    public static final String REGISTER_METHOD=SERVER_ADDRESS+"register_user";
    public static final String CONFIRM=SERVER_ADDRESS+"register_user";
    public static final String LOGIN_METHOD=SERVER_ADDRESS+"login_user";
    public static final String DATA_METHOD=SERVER_ADDRESS+"api_data";
    public static final String BOOKING_METHOD=SERVER_ADDRESS+"api_addbooking";
    public static final String APPLY_COUPON_METHOD=SERVER_ADDRESS+"api_applycoupon";
    public static final String ORDER_HISTORY_METHOD=SERVER_ADDRESS+"api_getOrderhistory";



    //shared preference
    public static final String SP_NAME="SP";
    public static final String KEY_USERNAME="NAME";
    public static final String KEY_EMAIL="EMAIL";
    public static final String KEY_CONTACT="CONTACT";
    public static final String KEY_USER_ID="ID";
    public static final String KEY_SP_ISLOGIN="IS_LOGIN";

}
