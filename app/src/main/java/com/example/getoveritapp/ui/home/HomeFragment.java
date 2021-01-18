package com.example.getoveritapp.ui.home;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.getoveritapp.R;

import java.io.IOException;
import java.util.UUID;

public class HomeFragment extends Fragment {

    private static final UUID mUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    private static final String ADDRESS = "98:D3:71:F6:11:BE";

    private HomeViewModel homeViewModel;

    private BluetoothAdapter bluetoothAdapter;

    private ImageView connect;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);


        connect = root.findViewById(R.id.bluetooth_connect);
        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

                BluetoothSocket bluetoothSocket = null;
                try {
                    BluetoothDevice hc05 = bluetoothAdapter.getRemoteDevice(ADDRESS);
                    bluetoothSocket = hc05.createRfcommSocketToServiceRecord(mUUID);
                    bluetoothAdapter.cancelDiscovery();
                    bluetoothSocket.connect();
                    System.out.println(bluetoothSocket.isConnected());

                }
                catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        return root;
    }
}