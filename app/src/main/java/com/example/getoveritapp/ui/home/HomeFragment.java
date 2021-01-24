package com.example.getoveritapp.ui.home;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.getoveritapp.R;

import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

public class HomeFragment extends Fragment {

    private static final UUID mUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    private static final String ADDRESS = "98:D3:71:F6:11:BE";
    private static final int INT_SENT = 1;

    private HomeViewModel homeViewModel;

    private BluetoothAdapter bluetoothAdapter;
    BluetoothSocket bluetoothSocket = null;
    private OutputStream outputStream;

    private Button connect;
    private ImageView backWheelIcon, frontWheelIcon;

    private Animation rotateAnimation;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);


        connect = root.findViewById(R.id.bluetooth_connect);

        backWheelIcon = root.findViewById(R.id.back_wheel_icon);
        frontWheelIcon = root.findViewById(R.id.front_wheel_icon);

        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                rotateAnimation();
                bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

                try {
                    BluetoothDevice hc05 = bluetoothAdapter.getRemoteDevice(ADDRESS);
                    bluetoothSocket = hc05.createRfcommSocketToServiceRecord(mUUID);
                    bluetoothAdapter.cancelDiscovery();
                    bluetoothSocket.connect();
                    System.out.println(bluetoothSocket.isConnected());
                    outputStream = bluetoothSocket.getOutputStream();
                    write(INT_SENT);

                }
                catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        return root;
    }

    public void write(int n) {
        try {
            bluetoothSocket.getOutputStream().write(n);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void rotateAnimation() {
        rotateAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.wheel_animation);
        backWheelIcon.startAnimation(rotateAnimation);
        frontWheelIcon.startAnimation(rotateAnimation);
    }
}