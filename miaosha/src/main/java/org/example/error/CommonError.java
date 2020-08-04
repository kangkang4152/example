package org.example.error;

public interface CommonError {

    public int getCode();

    public String getErrMsg();

    public CommonError setErrMsg(String errMsg);
}
