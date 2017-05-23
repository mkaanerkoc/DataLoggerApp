package com.argate.dataloggerapp.utils;

import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothProfile;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.argate.dataloggerapp.utils.Constants.ACTION_DATA_AVAILABLE;
import static com.argate.dataloggerapp.utils.Constants.EXTRA_DATA;
import static com.argate.dataloggerapp.utils.Constants.STATE_DISCONNECTED;

public class BluetoothLeService extends Service {

    private final static String TAG = BluetoothLeService.class.getSimpleName();

    private int mConnectionState = STATE_DISCONNECTED;
    private BluetoothGattCharacteristic mNotifyCharacteristic;
    private BluetoothGattCharacteristic bluetoothGattCharacteristicHM_10;
    private BluetoothAdapter mBluetoothAdapter;
    private int REQUEST_ENABLE_BT = 1;
    private BluetoothLeScanner mLEScanner;
    private BluetoothGatt mGatt;
    private ScanSettings settings;
    private List<ScanFilter> filters;
    private Handler mHandler;
    private static final long SCAN_PERIOD = 30000;
    public final static UUID UUID_HEART_RATE_MEASUREMENT = UUID.fromString(SampleGattAttributes.HM_10);

    public BluetoothLeService() {

    }

    public class LocalBinder extends Binder {
        BluetoothLeService getService() {
            return BluetoothLeService.this;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.v("LoggerApp","Service onCreate");
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i("LoggerApp","Service onBind");
        mHandler = new Handler();
        final BluetoothManager bluetoothManager = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
        mBluetoothAdapter = bluetoothManager.getAdapter();

        if (Build.VERSION.SDK_INT >= 21) {
            mLEScanner = mBluetoothAdapter.getBluetoothLeScanner();
            settings = new ScanSettings.Builder()
                    .setScanMode(ScanSettings.SCAN_MODE_LOW_LATENCY)
                    .build();
            filters = new ArrayList<ScanFilter>();
        }
        scanLeDevice(true);
        return mBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        // After using a given device, you should make sure that BluetoothGatt.close() is called
        // such that resources are cleaned up properly.  In this particular example, close() is
        // invoked when the UI is disconnected from the Service.
        //close();
        return super.onUnbind(intent);
    }

    private final IBinder mBinder = new LocalBinder();

    private void scanLeDevice(final boolean enable) {
        if (enable) {
            mLEScanner.startScan(filters, settings, mScanCallback);
        } else {
            mLEScanner.stopScan(mScanCallback);
        }
    }

    private ScanCallback mScanCallback = new ScanCallback() {
        @Override
        public void onScanResult(int callbackType, ScanResult result) {
            BluetoothDevice btDevice = result.getDevice();
            if(btDevice.getName()!=null){
                if(btDevice.getName().contains("Site")){
                    Log.i("LoggerApp","Connecting to Device : "+btDevice.getName());
                    connectToDevice(btDevice);
                }
            }
        }

        @Override
        public void onBatchScanResults(List<ScanResult> results) {
            for (ScanResult sr : results) {
                Log.i("ScanResult - Results", sr.toString());
            }
        }

        @Override
        public void onScanFailed(int errorCode) {
            Log.e("Scan Failed", "Error Code: " + errorCode);
        }
    };

    public void connectToDevice(BluetoothDevice device) {
        if (mGatt == null) {
            mGatt = device.connectGatt(this, false, gattCallback);
            scanLeDevice(false);// will stop after first device detection
        }
    }

    private void broadcastUpdate(final String action) {
        final Intent intent = new Intent(action);
        sendBroadcast(intent);
    }

    private void broadcastUpdate(final String action,
                                 final BluetoothGattCharacteristic characteristic) {
        final Intent intent = new Intent(action);

        final byte[] data = characteristic.getValue();
        Log.i("LoggerApp", "New Data : data.length: " + data.length);
        if (data != null && data.length > 0) {
            /*final StringBuilder stringBuilder = new StringBuilder(data.length);
            for(byte byteChar : data) {
                stringBuilder.append(String.format("%02X ", byteChar));
            }*/
            String comingData = new String(data);
            Log.i("LoggerApp","Data is : "+comingData);
            intent.putExtra(EXTRA_DATA, comingData);
            if(comingData.contains("Hello")){
                SendToDevice("My Name is Kaan.");
            }
        }
        sendBroadcast(intent);
    }

    private final BluetoothGattCallback gattCallback = new BluetoothGattCallback() {
        @Override
        public void onConnectionStateChange(BluetoothGatt gatt, int status, int newState) {
            Log.i("onConnectionStateChange", "Status: " + status);
            switch (newState) {
                case BluetoothProfile.STATE_CONNECTED:
                    Log.i("LoggerApp", "gattCallback  "+"STATE_CONNECTED");
                    gatt.discoverServices();
                    break;
                case BluetoothProfile.STATE_DISCONNECTED:
                    Log.i("LoggerApp", "gattCallback  "+"STATE_DISCONNECTED");
                    break;
                default:
                    Log.i("LoggerApp", "gattCallback  "+"STATE_OTHER");
            }
        }
        @Override
        public void onServicesDiscovered(BluetoothGatt gatt, int status) {

            List<BluetoothGattService> services = gatt.getServices();

            Log.i("LoggerApp","Services Discovered :"+services.toString());
            connectGattService(services);

        }

        @Override
        public void onCharacteristicRead(BluetoothGatt gatt,
                                         BluetoothGattCharacteristic
                                                 characteristic, int status) {
            Log.i("LoggerApp","Characteristics Read :"+characteristic.toString());

        }
        @Override
        public void onCharacteristicChanged(BluetoothGatt gatt,
                                            BluetoothGattCharacteristic characteristic) {
            Log.i("LoggerApp","New Data");
            broadcastUpdate(ACTION_DATA_AVAILABLE, characteristic);
        }
    };

    public void readCharacteristic(BluetoothGattCharacteristic characteristic) {
        if (mBluetoothAdapter == null || mGatt == null) {
            Log.w(TAG, "BluetoothAdapter not initialized");
            return;
        }
        mGatt.readCharacteristic(characteristic);
    }


    public void writeCharacteristic(BluetoothGattCharacteristic characteristic) {
        if (mBluetoothAdapter == null || mGatt == null) {
            Log.w(TAG, "BluetoothAdapter not initialized");
            return;
        }
        mGatt.writeCharacteristic(characteristic);
    }

    public void setCharacteristicNotification(BluetoothGattCharacteristic characteristic,
                                              boolean enabled) {
        if (mBluetoothAdapter == null || mGatt == null) {
            Log.w(TAG, "BluetoothAdapter not initialized");
            return;
        }
        mGatt.setCharacteristicNotification(characteristic, enabled);

        if (UUID_HEART_RATE_MEASUREMENT.equals(characteristic.getUuid())) {
            BluetoothGattDescriptor descriptor = characteristic.getDescriptor(
                    UUID.fromString(SampleGattAttributes.CLIENT_CHARACTERISTIC_CONFIG));
            descriptor.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
            mGatt.writeDescriptor(descriptor);
        }
    }
    private void connectGattService(List<BluetoothGattService> gattServices) {
        UUID UUID_HM_10_Service =UUID.fromString(SampleGattAttributes.HM_10_Service);
        UUID UUID_HM_10 =UUID.fromString(SampleGattAttributes.HM_10);
        for(int i = 0 ; i < gattServices.size();i++){
            BluetoothGattService sv = gattServices.get(i);
            if(sv.getUuid().equals(UUID_HM_10_Service)){
                List<BluetoothGattCharacteristic> gattCharacteristics = sv.getCharacteristics();
                for(int j = 0 ; j < gattCharacteristics.size(); j++){
                    BluetoothGattCharacteristic ch = gattCharacteristics.get(j);
                    if(ch.getUuid().equals(UUID_HM_10)) {
                        final int charaProp = ch.getProperties();
                        Log.i("LoggerApp ","Characteristics : "+ch.getUuid().toString());
                        if ((charaProp | BluetoothGattCharacteristic.PROPERTY_READ) > 0) {
                            if (mNotifyCharacteristic != null) {
                                setCharacteristicNotification(mNotifyCharacteristic, false);
                                mNotifyCharacteristic = null;
                            }
                            readCharacteristic(ch);
                        }
                        if((charaProp | BluetoothGattCharacteristic.PROPERTY_WRITE) > 0){
                            bluetoothGattCharacteristicHM_10 = ch;
                        }
                        if ((charaProp | BluetoothGattCharacteristic.PROPERTY_NOTIFY) > 0) {
                            mNotifyCharacteristic = ch;
                            setCharacteristicNotification( ch, true);
                        }
                    }
                }
            }
        }
    }
    private void SendToDevice(String sb){
        bluetoothGattCharacteristicHM_10.setValue(sb);
        writeCharacteristic(bluetoothGattCharacteristicHM_10);
        setCharacteristicNotification(bluetoothGattCharacteristicHM_10, true);
    }
    private void SendToDevice(byte[] data){

    }

}
