package yuanchieh.ble;

import android.app.Activity;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.zip.Inflater;

/**
 * Created by zhengyuanjie on 15/8/19.
 */
public class LeDeviceListAdapter extends BaseAdapter {
    private ArrayList<BluetoothDevice> mLeDevices;
    private LayoutInflater mInflator;
    private TextView deviceName;
    private TextView deviceAddress;

    public LeDeviceListAdapter(Context context){
        super();
        mLeDevices = new ArrayList<BluetoothDevice>();
        mInflator = (LayoutInflater)LayoutInflater.from(context);
    }

    public void addDevice(BluetoothDevice device){
        if(!mLeDevices.contains(device)){
            mLeDevices.add(device);
        }
    }

    public BluetoothDevice getDevice(int position){
        return mLeDevices.get(position);
    }

    //clean up the listview
    public void clear(){
        mLeDevices.clear();
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount(){
        return mLeDevices.size();
    }

    @Override
    public Object getItem(int i) {
        return mLeDevices.get(i);
    }

    @Override
    public long getItemId(int i){
        return i;
    }

    @Override
    public View getView(int i,View view,ViewGroup viewGroup){
        if(view == null){
            view = mInflator.inflate(R.layout.listitem_device,viewGroup,false);
            deviceName = (TextView)view.findViewById(R.id.device_name);
            deviceAddress = (TextView)view.findViewById(R.id.device_address);
        }

        BluetoothDevice device = mLeDevices.get(i);
        final String currDeviceName = device.getName();
        if(currDeviceName != null && currDeviceName.length() > 0)
            deviceName.setText(currDeviceName);
        else
            deviceName.setText("Unknown device");
        deviceAddress.setText(device.getAddress());

        return view;
    }

}