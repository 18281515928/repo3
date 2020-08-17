package com.gift.domain.operator.error;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>todo</P>
 *
 * @author zhuzh@belink.com
 * @version 0.0.1
 * @className ErrorCodeMsgEnum
 * @sine 2020/3/12 16:22
 */
public enum  ErrorCodeMsgEnum {

    SUCCESS("0","交易成功"),
    FAIL("2","交易失败"),
    operator_NOT_EXIST("3","该用户不存在"),
    OPERATOR_ALREADY_EXIST("4","用户已存在");

    private final Map<String, String> errorMap = new HashMap<String, String>();

    ErrorCodeMsgEnum(String code, String msg) {
        this.errorMap.put("errorCode", code);
        this.errorMap.put("errorMsg", msg);
    }
    public String code(){
        return this.errorMap.get("errorCode");
    }
    public String msg(){
        return this.errorMap.get("errorMsg");
    }

    public static ErrorCodeMsgEnum geterrorCodeMsgEnum(String code){
        for(ErrorCodeMsgEnum errorEnum : ErrorCodeMsgEnum.values()){
            if(code.equals(errorEnum.code())){
                return errorEnum;
            }
        }
        return null;
    }
}
