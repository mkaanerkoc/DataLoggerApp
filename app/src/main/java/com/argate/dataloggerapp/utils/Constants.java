package com.argate.dataloggerapp.utils;

/**
 * Created by Kaan on 8/26/2016.
 */
public class Constants {

    public static final int STATE_DISCONNECTED = 0;
    public static final int STATE_CONNECTING = 1;
    public static final int STATE_CONNECTED = 2;


    public final static String ACTION_GATT_CONNECTED =
            "com.example.bluetooth.le.ACTION_GATT_CONNECTED";
    public final static String ACTION_GATT_DISCONNECTED =
            "com.example.bluetooth.le.ACTION_GATT_DISCONNECTED";
    public final static String ACTION_GATT_SERVICES_DISCOVERED =
            "com.example.bluetooth.le.ACTION_GATT_SERVICES_DISCOVERED";
    public final static String ACTION_DATA_AVAILABLE =
            "com.example.bluetooth.le.ACTION_DATA_AVAILABLE";
    public final static String EXTRA_DATA =
            "com.example.bluetooth.le.EXTRA_DATA";

    public final static String deviceName = "Kaan";
    public final static String deviceName2 = "CC41-A";
    public final static String BASE_URL ="http://94.54.83.236/ogm/";
    public final static String FILE_UPLOAD_URL ="getlogs.php";


    public final static int PH_SENSOR = 10;
    public final static int OXY_SENSOR = 20;
    public final static int DIST_SENSOR = 30;
    public final static int NTU_SENSOR = 40;
    public final static int COND_SENSOR = 50;

    public final static int PH_SLAVE_ID  = 5;
    public final static int OXY_SLAVE_ID  = 10;
    public final static int DIST_SLAVE_ID  = 36;
    public final static int NTU_SLAVE_ID   =40;
    public final static int COND_SLAVE_ID  =18;


    public final static int PH_REQUEST =1;
    public final static int OXY_REQUEST =2;
    public final static int DIST_REQUEST =3;
    public final static int NTU_REQUEST = 4;
    public final static int COND_REQUEST = 5;
    public final static int SCAN_REQUEST = 10;

    public final static int PH_DEV_ID   = 32328;
    public final static int OXY_DEV_ID   = 32335;
    public final static int DIST_DEV_ID  =  85;
    public final static int NTU_DEV_ID   = 31018;
    public final static int COND_DEV_ID  =  17231;


    //public final static byte    DEVICE_CONFIGURATION




    public final static byte    CALIBRATION = 0x50;


    public final static byte    FILE_TRANSFER = 0x20;
    public final static byte    SEND_FILE_NAMES = 0x02;
    public final static byte    SEND_FILE = 0x03;
    public final static byte    SEND_DATETIME = 0x04;

    public final static byte    READ_ALL_SENSORS = 0x40;

    public final static byte    SAMPLER_COMMAND = 0x75;
    public final static byte    SAMPLER_START = 0x01;
    public final static byte    SAMPLER_STOP = 0x02;


    public final static byte    PH_CALIBRATION = 0x00;
    public final static byte    OXY_CALIBRATION = 0x01;
    public final static byte    TURBIDITY_CALIBRATION = 0x02;
    public final static byte    CONDUCTIVITY_CALIBRATION = 0x03;

    public final static byte    READ_DISCRETE_INPUTS = 0x30;
    public final static byte    WRITE_SINGLE_COIL = 0x30;
    public final static byte    WRITE_MULTIPLE_COILS= 0x30;
    public final static byte    READ_INPUT_REGISTER= 0x30;

    public final static byte    HELLO=0x50;
    public final static byte    GOOD_BYE=0x51;

    // DEVICE CONFIGURATION //
    public final static byte    DEVICE_CONFIGURATION = 0x00;

    public final static byte    CONFIG_PARAMS = 0x00;
    public final static byte    SAMPLING_TIMES_PARAMS = 0x10;
    public final static byte    DATE_TIME_PARAMS = 0x20;
    public final static byte    SAMPLING_PERC_PARAMS  = 0x30;



    public final static byte    ALL_PARAMS =  0x00;
    public final static byte    SINGLE_DEVICE_PARAM = 0x01;

    public final static byte    DEVICE_ID_REQ  = 0;
    public final static byte    SITE_ID_REQ =  4;
    public final static byte    PH_LOWER_REQ  = 20;
    public final static byte    PH_HIGHER_REQ  = 24;
    public final static byte    CONDUCTIVITY_LOWER_REQ  = 28;
    public final static byte    CONDUCTIVITY_HIGHER_REQ  = 32;
    public final static byte    TURBIDITY_LOWER_REQ  = 36;
    public final static byte    TURBIDITY_HIGHER_REQ  = 40;
    public final static byte    OXYGEN_LOWER_REQ  = 44;
    public final static byte    OXYGEN_HIGHER_REQ  = 48;
    public final static byte    RAIN_HIGHER_REQ  = 52;
    public final static byte    TIMEOUT_EVENT_REQ  = 56;
    public final static byte    REFERENCE_HEIGHT_REQ  = 60;
    public final static byte    READ_PERIOD_REQ  = 64;

    public final static byte    TIME_BASED_TOTAL_REQ  = 80;
    public final static byte    TIME_BASED_PERC_REQ  = 84;
    public final static byte    EVENT_BASED_TOTAL_REQ  = 88;
    public final static byte    EVENT_BASED_PERC_REQ  = 92;

    public final static byte    READ_CONFIG = 0x01;
    public final static byte    WRITE_CONFIG = 0x02;


    // PUMPS MOTOR COMMANDS //

    public final static byte   MOTOR_COMMANDS = 0x60;
    public final static byte   MOTOR_1_ID = 0x01;
    public final static byte   MOTOR_2_ID = 0x02;
    public final static byte   MOTOR_3_ID = 0x03;
    public final static byte   MOTOR_4_ID = 0x04;
    public final static byte   RELAY_ID = 0x05;
    public final static byte   TURN_ON = 0x01;
    public final static byte   TURN_OFF = 0x00;
    public final static byte   TURN_INSIDE = 0x00;
    public final static byte   TURN_OUTSIDE = 0x01;



}
