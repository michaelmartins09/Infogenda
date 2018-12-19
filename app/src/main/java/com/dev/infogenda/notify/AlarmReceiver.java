package com.dev.infogenda.notify;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AlarmReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        String get_your_string = intent.getExtras().getString("extra");
        Intent service_intent = new Intent(context, RingTonePlayingService.class);
        service_intent.putExtra("extra", get_your_string);
        context.startService(service_intent);

    }
}
