package org.example.validator;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class ValidationResult {

    private boolean hasErrors = false;

    private Map<String,String> errorMsgMap = new HashMap<>();

    public void setHasErrors(boolean hasErrors) {
        this.hasErrors = hasErrors;
    }

    public Map<String, String> getErrorMsgMap() {
        return errorMsgMap;
    }

    public void setErrorMsgMap(Map<String, String> errorMsgMap) {
        this.errorMsgMap = errorMsgMap;
    }

    public boolean isHasErrors(){
        return hasErrors;
    }
    //通过通用的通过格式化的字符串信息

    public String getErrMsg(){
      return  StringUtils.join(errorMsgMap.values().toArray(),",");
    }
}
