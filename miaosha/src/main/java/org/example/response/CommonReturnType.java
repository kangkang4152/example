package org.example.response;

public class CommonReturnType {

    private String status;

    private Object object;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public   static CommonReturnType create(Object object){
        return CommonReturnType.create(object,"success");
    }

    public static CommonReturnType create(Object object, String status) {
        CommonReturnType  type = new CommonReturnType();
        type.setObject(object);
        type.setStatus(status);
        return type;
    }


}
