package org.example.baseController;

import org.example.error.BussinessException;
import org.example.error.EmBussinessError;
import org.example.response.CommonReturnType;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class BaseController {

    public static final String CONTENT_TYPE_FORMED = "application/x-www-form-urlencoded";

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Object handlerException(HttpServletRequest request, Exception ex){
        Map<String, Object> responseData = new HashMap<String, Object>();
        if(ex instanceof BussinessException) {
            BussinessException bussinessException = (BussinessException) ex;
            responseData.put("errCode", bussinessException.getCode());
            responseData.put("errMsg", bussinessException.getErrMsg());
        }else{
            responseData.put("errCode", EmBussinessError.UNKNOW_ERROR.getCode());
            responseData.put("errMsg", EmBussinessError.UNKNOW_ERROR.getErrMsg());
        }
        return CommonReturnType.create(responseData,"fail");
    }
}
