package com.example.getoveritapp.messages;

import android.content.Context;
import android.widget.Toast;

public class MessageDisplayer {
    public static void message(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
}
