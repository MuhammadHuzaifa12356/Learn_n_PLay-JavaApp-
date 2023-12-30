package com.example.learn_n_play;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.net.Uri;

public class AppSetting extends AppCompatActivity {
    private Button backButton;
    private Button airplaneModeButton;

    private static final int ACTION_MANAGE_WRITE_SETTINGS_PERMISSION_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_setting);

        backButton = findViewById(R.id.backbutton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AppSetting.this, HomeScreen.class));
                finish();
            }
        });

        airplaneModeButton = findViewById(R.id.airplane);
        airplaneModeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Check if the application has the WRITE_SETTINGS permission
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                        !Settings.System.canWrite(getApplicationContext())) {
                    // Request the WRITE_SETTINGS permission
                    Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS);
                    intent.setData(Uri.parse("package:" + getPackageName()));
                    startActivityForResult(intent, ACTION_MANAGE_WRITE_SETTINGS_PERMISSION_REQUEST_CODE);
                } else {
                    // Toggle airplane mode
                    toggleAirplaneMode();
                }
            }
        });

        // Register the BroadcastReceiver dynamically in your code
        IntentFilter filter = new IntentFilter("com.example.learn_n_play.AIRPLANE_MODE_CHANGED");
        registerReceiver(new AirplaneModeReceiver(), filter);
    }

    // BroadcastReceiver to listen for airplane mode changes
    public class AirplaneModeReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if ("com.example.learn_n_play.AIRPLANE_MODE_CHANGED".equals(intent.getAction())) {
                boolean isAirplaneModeOn = intent.getBooleanExtra("isAirplaneModeOn", false);

                // Show toast when airplane mode is enabled
                if (isAirplaneModeOn) {
                    Toast.makeText(context, "Airplane Mode Enabled", Toast.LENGTH_SHORT).show();
                }

                // Do something with the airplane mode state
            }
        }
    }

    // Handle the result of the WRITE_SETTINGS permission request
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ACTION_MANAGE_WRITE_SETTINGS_PERMISSION_REQUEST_CODE) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                    Settings.System.canWrite(getApplicationContext())) {
                // The user granted the WRITE_SETTINGS permission, toggle airplane mode
                toggleAirplaneMode();
            } else {
                Toast.makeText(this, "WRITE_SETTINGS permission not granted", Toast.LENGTH_SHORT).show();
            }
        }
    }

    // Toggle airplane mode
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void toggleAirplaneMode() {
        // Check if airplane mode is currently enabled
        boolean isAirplaneModeOn = Settings.System.getInt(
                getContentResolver(),
                Settings.Global.AIRPLANE_MODE_ON, 0) != 0;

        // Toggle airplane mode
        Settings.System.putInt(
                getContentResolver(),
                Settings.Global.AIRPLANE_MODE_ON,
                isAirplaneModeOn ? 0 : 1);

        // Broadcast an intent indicating that the airplane mode has changed
        Intent intent = new Intent("com.example.learn_n_play.AIRPLANE_MODE_CHANGED");
        intent.putExtra("isAirplaneModeOn", !isAirplaneModeOn);
        sendBroadcast(intent);
    }
}
