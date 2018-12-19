package com.dev.infogenda.notify;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.GestureDetector.SimpleOnGestureListener;

import com.dev.infogenda.R;
import com.dev.infogenda.controller.GerenciadorAvaliacoes;

import static com.dev.infogenda.database.DatabaseController.PREFS1;

public class RingTonePlayingService extends Service implements View.OnTouchListener {

    MediaPlayer media_song;
    boolean isRunning;
    int startId;
    private Context context;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String extras[] = intent.getExtras().getString("extra").split("-");
        String state = extras[0];
        System.out.println(state);
        assert state != null;
        switch (state) {
            case "alarm on":
                startId = 1;
                break;
            case "alarm off":
                startId = 0;
                break;
            default:
                startId = 0;
                break;
        }

        String tipoAlarme = extras[1];
        assert tipoAlarme != null;

        if (!tipoAlarme.equals("a")) {
            switch (tipoAlarme) {
                case "Sonoro":
                    if (!this.isRunning && startId == 1) {

                        //Notification Sound
                        SharedPreferences e1234 = getSharedPreferences(PREFS1, 0);
                        String rt = e1234.getString("ringtone", "not found");
                        if (rt.equals("not found")) {

                            // create an instace of an media player
                            media_song = MediaPlayer.create(this, R.raw.analog_watch_alarm);
                        } else {
                            Uri uri = Uri.parse(rt);
                            media_song = MediaPlayer.create(this, uri);
                        }

                        media_song.setLooping(true);

                        // start the ringtone
                        media_song.start();


                        this.isRunning = true;
                        this.startId = 0;
                    }

                    //Comando para alarme sobrepor musica do usuário caso esteja ouvindo
                    if (this.isRunning && startId == 0) {
                        // stop the ringtone
                        media_song.stop();
                        media_song.reset();

                        this.isRunning = false;
                        this.startId = 0;
                    }
                    if (!this.isRunning && startId == 0) {
                        this.isRunning = false;
                        this.startId = 0;
                    }

                    if (this.isRunning && startId == 1) {
                        this.isRunning = true;
                        this.startId = 1;
                    }

                    break;
                case "Mensagem":

                    // Notification PUSH
                    NotificationManager notify_manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                    Intent intent_main_activity = new Intent(this.getApplicationContext(), GerenciadorAvaliacoes.class);
                    PendingIntent pending_intent_main_activity = PendingIntent.getActivity(this, 0, intent_main_activity, 0);
                    this.context = this;

                    Notification notification_popup = new Notification.Builder(this)
                            .setSmallIcon(R.drawable.icon)
                            .setLargeIcon(BitmapFactory.decodeResource(context.getResources(),
                                    R.drawable.icon))
                            .setContentTitle("INFOGENDA")
                            .setContentText("Toque para abrir o app para desligar o alarme.")
                            .setContentIntent(pending_intent_main_activity)
                            .setAutoCancel(true)
                            .build();

                    notification_popup.flags |= Notification.FLAG_AUTO_CANCEL | Notification.FLAG_SHOW_LIGHTS;
                    notification_popup.defaults |= Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE;
                    notification_popup.ledARGB = 0xFFFFA500;
                    notification_popup.ledOnMS = 800;
                    notification_popup.ledOffMS = 1000;

                    // set up a notification call command
                    notify_manager.notify(0, notification_popup);
                    break;
            }
        }


        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        // Tell the user we stopped.

        super.onDestroy();
        this.isRunning = false;
    }

    private GestureDetector gestureDetector;

    public void OnSwipeTouchListener(Context ctx) {
        gestureDetector = new GestureDetector(ctx, new GestureListener());
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        return gestureDetector.onTouchEvent(motionEvent);
    }

    private final class GestureListener extends SimpleOnGestureListener {

        private static final int SWIPE_THRESHOLD = 100;
        private static final int SWIPE_VELOCITY_THRESHOLD = 100;

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            boolean result = false;
            try {
                float diffY = e2.getY() - e1.getY();
                float diffX = e2.getX() - e1.getX();
                if (Math.abs(diffX) > Math.abs(diffY)) {
                    if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                        if (diffX > 0) {
                            onSwipeRight();
                        } else {
                            onSwipeLeft();
                        }
                        result = true;
                    }
                } else if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                    if (diffY > 0) {
                        onSwipeBottom();
                    } else {
                        onSwipeTop();
                    }
                    result = true;
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            return result;
        }

    }

    public void onSwipeRight() {
        Intent intentXXX = new Intent(context, GerenciadorAvaliacoes.class);
        context.startActivity(intentXXX);
    }

    public void onSwipeLeft() {
        Intent intentXXY = new Intent(context, GerenciadorAvaliacoes.class);
        context.startActivity(intentXXY);
    }

    public void onSwipeTop() {
    }

    public void onSwipeBottom() {
    }
}
