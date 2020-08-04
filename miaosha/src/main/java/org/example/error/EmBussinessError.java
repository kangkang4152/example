package org.example.error;

public enum EmBussinessError implements CommonError{

    PARAMETER_VALIDATION_ERROR(10001,"参数不合法"),

    UNKNOW_ERROR(10002,"未知错误"),
    //20000 开头为用户相关错误
    USER_NOT_EXIST(20001,"用户不存在"),
    //20000 开头为用户相关错误
    USER_LOGIN_FAIL(20002,"登录失败")
    ;

    private EmBussinessError(int code, String errMsg){
        this.code = code;
        this.errMsg = errMsg;
    }

    private int code;

    private String errMsg;

    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public String getErrMsg() {
        return this.errMsg;
    }

    @Override
    public CommonError setErrMsg(String errMsg) {
        this.errMsg = errMsg;
        return this;
    }
}
