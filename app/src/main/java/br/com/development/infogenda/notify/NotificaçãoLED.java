package br.com.development.infogenda.notify;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import br.com.development.infogenda.R;

public class NotificaçãoLED extends Activity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            setContentView(R.layout.activity_main);

            NotificationManager notif = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

            for (int i = 0; i < 8; i++) {
                notif.cancel(1); // clear previous notification
                final Notification notification = new Notification();
                if (i == 0){
                    notification.ledARGB = Color.MAGENTA;
                }else if (i == 1){
                    notification.ledARGB = Color.BLUE;
                }else if (i == 2){
                    notification.ledARGB = Color.CYAN;
                }else if (i == 3){
                    notification.ledARGB = Color.GRAY;
                }else if (i == 4){
                    notification.ledARGB = Color.GREEN;
                }else if (i == 5){
                    notification.ledARGB = Color.RED;
                }else if (i == 6){
                    notification.ledARGB = Color.WHITE;
                }else if (i == 7){
                    notification.ledARGB = Color.YELLOW;
                }
                notification.ledOnMS = 1000;
                notification.flags |= Notification.FLAG_SHOW_LIGHTS;
                notif.notify(1, notification);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }



    }
}
