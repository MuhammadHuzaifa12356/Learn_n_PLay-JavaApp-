package com.example.learn_n_play;

// ReloadReceiver.java
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class ReloadReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        // Start the ColorsRecognition activity
        Intent reloadIntent = new Intent(context, ColorsRecognition.class);
        reloadIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(reloadIntent);
    }
}
