package com.argate.dataloggerapp.utils;

/**
 * Created by Kaan on 5/22/2017.
 */

public class DataLog {
    private byte deviceID;
    private byte fieldID;
    private String logTime;
    private float pH;
    private float waterTemp;
    private float waterFlow;
    private float oxygen;
    private float turbidity;
    private float rain;
    private float cumulativeRain;

    public byte getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(byte deviceID) {
        this.deviceID = deviceID;
    }

    public byte getFieldID() {
        return fieldID;
    }

    public void setFieldID(byte fieldID) {
        this.fieldID = fieldID;
    }

    public String getLogTime() {
        return logTime;
    }

    public void setLogTime(String logTime) {
        this.logTime = logTime;
    }

    public float getpH() {
        return pH;
    }

    public void setpH(float pH) {
        this.pH = pH;
    }

    public float getWaterTemp() {
        return waterTemp;
    }

    public void setWaterTemp(float waterTemp) {
        this.waterTemp = waterTemp;
    }

    public float getWaterFlow() {
        return waterFlow;
    }

    public void setWaterFlow(float waterFlow) {
        this.waterFlow = waterFlow;
    }

    public float getOxygen() {
        return oxygen;
    }

    public void setOxygen(float oxygen) {
        this.oxygen = oxygen;
    }

    public float getTurbidity() {
        return turbidity;
    }

    public void setTurbidity(float turbidity) {
        this.turbidity = turbidity;
    }

    public float getRain() {
        return rain;
    }

    public void setRain(float rain) {
        this.rain = rain;
    }

    public float getCumulativeRain() {
        return cumulativeRain;
    }

    public void setCumulativeRain(float cumulativeRain) {
        this.cumulativeRain = cumulativeRain;
    }
}
