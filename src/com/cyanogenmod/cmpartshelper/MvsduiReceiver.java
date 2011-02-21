package com.cyanogenmod.smpartshelper;

import android.os.Bundle;
import android.os.Environment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;
import java.io.IOException;
import java.io.File;
import java.io.FileInputStream;
import java.io.DataInputStream;

import com.cyanogenmod.smpartshelper.R;


public class MvsduiReceiver extends BroadcastReceiver {

    public static final String mvSdUi = "com.cyanogenmod.smparts.RESTORE_CMPARTS_UI";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(mvSdUi)) {
            Bundle extras = intent.getExtras();
            String filename = extras.getString("filename");
            File xmlFile = new File(Environment.getExternalStorageDirectory() + "/CMTheme/" + filename);
            if (xmlFile.exists()) {
                try {
                    FileInputStream infile = new FileInputStream(xmlFile);
                    DataInputStream in = new DataInputStream(infile);
                    byte[] b = new byte[in.available()];
                    in.readFully(b);
                    in.close();
                    String result = new String(b, 0, b.length);
                    Intent UiSd = new Intent("com.cyanogenmod.smpartshelper.RESTORE_CMPARTS_UI");
                    UiSd.putExtra("xmldataret", result);
                    context.sendBroadcast(UiSd);
                } catch (Exception e) { }
            }
        }
    }
}
