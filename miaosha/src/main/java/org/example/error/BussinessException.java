package org.example.error;

public class BussinessException extends Exception implements CommonError{

    private CommonError commonError;

    //直接接受EmBussinessError的传参用于构造业务异常
    public BussinessException(CommonError commonError){
        super();
        this.commonError = commonError;
    }

    //接收自定义errMsg的方式构造业务异常
    public BussinessException(CommonError commonError,String errMsg){
        super();
        this.commonError = commonError;
        this.commonError.setErrMsg(errMsg);
    }

    @Override
    public int getCode() {
        return this.commonError.getCode();
    }

    @Override
    public String getErrMsg() {
        return this.commonError.getErrMsg();
    }

    @Override
    public CommonError setErrMsg(String errMsg) {
         this.commonError.setErrMsg(errMsg);
        return this;
    }
}
