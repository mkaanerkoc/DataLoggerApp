package com.argate.dataloggerapp.utils;

/**
 * Created by Kaan on 10/18/2016.
 */

public class Params {
    private String name;
    private String param;
    private byte operationCode;



    private byte deviceEEPROMaddr;
    private int type;

    public Params(String prop1, String prop2,byte operationCode,int type) {
        this.name = prop1;
        this.param = prop2;
        this.operationCode=operationCode;
        this.type=type;
    }

    public String getName() {
        return name;
    }

    public String getParam() {
        return param;
    }
    public void setName(String str){
        this.name=str;
    }
    public void setParam(String str){
        this.param=str;
    }
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
    public byte getOperationCode() {
        return operationCode;
    }

    public void setOperationCode(byte operationCode) {
        this.operationCode = operationCode;
    }
}
