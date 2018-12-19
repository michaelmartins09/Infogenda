package com.dev.infogenda.custom;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.dev.infogenda.R;
import com.dev.infogenda.database.DatabaseController;
import com.dev.infogenda.model.Avaliacao;
import com.dev.infogenda.notify.AlarmReceiver;

import java.util.List;

import static android.content.Context.ALARM_SERVICE;
import static com.dev.infogenda.database.DatabaseController.PREFS;

public class CustomListAvaliacoes extends ArrayAdapter<Avaliacao> implements View.OnClickListener {

    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 0;
    private List<Avaliacao> listAvaliacao;
    Context mContext;

    AlarmManager alarm_manager;
    PendingIntent pending_intent;
    LayoutInflater mInflator;
    Activity mActivity;

    private static class ViewHolder {
        TextView tvDataAvaliacao;
        TextView tvNomeNotificacao;
        Button alarm_on, alarm_off, delete_row;
    }

    public CustomListAvaliacoes(List<Avaliacao> listAvaliacao, Context context, LayoutInflater mInflator, Activity mActivity) {
        super(context, R.layout.custom_list_avaliacoes, listAvaliacao);
        this.listAvaliacao = listAvaliacao;
        this.mInflator = mInflator;
        this.mActivity = mActivity;

    }

    @Override
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void onClick(View v) {
        int position = (Integer) v.getTag();
        Object object = getItem(position);
        Avaliacao avaliacao = (Avaliacao) object;
    }

    private int lastPosition = -1;

    @Override
    @RequiresApi(api = Build.VERSION_CODES.N)
    public View getView(final int position, View convertView, ViewGroup parent) {

        alarm_manager = (AlarmManager) getContext().getSystemService(ALARM_SERVICE);

        final Avaliacao avaliacao = getItem(position);
        final ViewHolder viewHolder;
        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.custom_list_avaliacoes, parent, false);
            viewHolder.tvDataAvaliacao = (TextView) convertView.findViewById(R.id.tvDataAvaliacao);
            viewHolder.tvNomeNotificacao = (TextView) convertView.findViewById(R.id.tvNomeAvaliacao);
            viewHolder.alarm_on = (Button) convertView.findViewById(R.id.alarm_on);
            viewHolder.alarm_off = (Button) convertView.findViewById(R.id.alarm_off);
            viewHolder.delete_row = (Button) convertView.findViewById(R.id.delete);

            result = convertView;
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }

        lastPosition = position;

        final Calendar calendar = Calendar.getInstance();
        final Intent my_intent = new Intent(getContext(), AlarmReceiver.class);
        String horario = avaliacao.getHorarioNotificacao();

        int aux1 = horario.indexOf(':');
        int aux2 = horario.lastIndexOf(':');
        int i;

        for (i = aux1 + 1; i < horario.length(); i++)
            if (horario.charAt(i) == ':')
                break;

        String temp, button_on_or_off;

        final int hour = Integer.parseInt(horario.substring(0, aux1));
        if (aux2 == aux1) {
            temp = horario.substring(aux1 + 1);
            button_on_or_off = "";
        } else {
            temp = horario.substring(aux1 + 1, i);
            button_on_or_off = horario.substring(i + 1);
        }

        final int minute = Integer.parseInt(temp);

        String hour_string = String.valueOf(hour);
        String minute_string = String.valueOf(minute);

        Log.d("Tag button :: ", button_on_or_off);

        if (hour > 12) {
            hour_string = String.valueOf(hour - 12);
        }
        if (hour == 0) {
            hour_string = "00";
        }
        if (minute < 10) {
            minute_string = "0" + String.valueOf(minute);
        }

        final String str = hour_string + ':' + minute_string;

        viewHolder.tvDataAvaliacao.setText(avaliacao.getDataNotificacao() + " - " + str);

        viewHolder.tvNomeNotificacao.setText(avaliacao.getNomeAvaliacao());

        if (button_on_or_off.equals("ONN")) {
            viewHolder.alarm_on.setVisibility(View.INVISIBLE);
            viewHolder.alarm_off.setVisibility(View.VISIBLE);
        }

        viewHolder.alarm_on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                SimpleDateFormat df_check = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String formattedDate1 = df_check.format(cal.getTime());
                String xy[] = formattedDate1.split(" ");
                String xy1[] = xy[1].split(":");
                final int current_hour_2 = Integer.parseInt(xy1[0]);
                final int current_minutes_2 = Integer.parseInt(xy1[1]);

                Log.d("Tag :: ", current_hour_2 + " " + hour + " :: " + current_minutes_2 + " " + minute);

                if (current_hour_2 < hour || current_minutes_2 <= minute) {

                    Log.d("Tag :: ", "pressed alarm on button successfully !");

                    // setting calendar instance with the hour and minute we picked on tvDataAvaliacao picker
                    calendar.set(Calendar.HOUR_OF_DAY, hour);
                    calendar.set(Calendar.MINUTE, minute);
                    calendar.set(Calendar.SECOND, 0);
                    calendar.set(Calendar.MILLISECOND, 0);

                    // put in extra string in my intent to tell clock you pressed on button
                    my_intent.putExtra("extra", "alarm on-"+avaliacao.getTipoalerta());
                    my_intent.putExtra("extra1", "alarm on at tvDataAvaliacao :: " + str);

                    // create a pending intent that delays the intnet until the specified calendar tvDataAvaliacao
                    pending_intent = PendingIntent.getBroadcast(getContext(), position, my_intent, PendingIntent.FLAG_UPDATE_CURRENT);

                    // set alarm manager
                    alarm_manager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pending_intent);

                    Calendar c1 = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c1.getTime());
                    String y[] = formattedDate.split(" ");
                    String y1[] = y[1].split(":");
                    int current_hour = Integer.parseInt(y1[0]);
                    int current_minutes = Integer.parseInt(y1[1]);

                    String tyu = avaliacao.getHorarioNotificacao();
                    String j[] = tyu.split(":");
                    int hour = Integer.parseInt(j[0]);
                    int minute = Integer.parseInt(j[1]);

                    SharedPreferences q1 = getContext().getSharedPreferences(PREFS, 0);
                    String get_string1 = q1.getString("message", "not found");
                    String bc[] = get_string1.split(" ");
                    String put_string1 = "";
                    for (int bcd1 = 0; bcd1 < bc.length; bcd1++)
                        if (bc[bcd1].equals(tyu))
                            put_string1 = put_string1 + hour + ":" + minute + ":" + "ONN ";
                        else
                            put_string1 = put_string1 + bc[bcd1] + " ";

                    SharedPreferences.Editor e123 = q1.edit();
                    e123.putString("message", put_string1);
                    e123.commit();

                    int p = hour * 60 + minute;
                    int m = current_hour * 60 + current_minutes;

                    int diff = p - m;

                    if (diff < 60)
                        Snackbar.make(view, "O Alarme vai disparar daqui a " + diff + " minutos a partir de agora.", Snackbar.LENGTH_LONG)
                                .setAction("Sem ação", null).show();
                    else
                        Snackbar.make(view, "Alarme inserido para " + (diff / 60) + " Horas e " + (diff % 60) + " minutos a partir de agora.", Snackbar.LENGTH_LONG)
                                .setAction("Sem Ação", null).show();

                    viewHolder.alarm_on.setVisibility(View.INVISIBLE);
                } else {
                    Log.d("Tag :: ", "Alarm pressionado com sucesso !");

                    // setting calendar instance with the hour and minute we picked on tvDataAvaliacao picker
                    calendar.set(Calendar.HOUR_OF_DAY, hour + 24);
                    calendar.set(Calendar.MINUTE, minute);
                    calendar.set(Calendar.SECOND, 0);
                    calendar.set(Calendar.MILLISECOND, 0);

                    // put in extra string in my intent to tell clock you pressed on button
                    my_intent.putExtra("extra", "alarm on-"+avaliacao.getTipoalerta());
                    my_intent.putExtra("extra1", "alarm on at tvDataAvaliacao :: " + str);

                    // create a pending intent that delays the intnet until the specified calendar tvDataAvaliacao
                    pending_intent = PendingIntent.getBroadcast(getContext(), position, my_intent, PendingIntent.FLAG_UPDATE_CURRENT);

                    // set alarm manager
                    alarm_manager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pending_intent);

                    Calendar c1 = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c1.getTime());
                    String y[] = formattedDate.split(" ");
                    String y1[] = y[1].split(":");
                    int current_hour = Integer.parseInt(y1[0]);
                    int current_minutes = Integer.parseInt(y1[1]);

                    String tyu = avaliacao.getHorarioNotificacao();
                    String j[] = tyu.split(":");
                    int hour = Integer.parseInt(j[0]);
                    int minute = Integer.parseInt(j[1]);

                    SharedPreferences q1 = getContext().getSharedPreferences(PREFS, 0);
                    String get_string1 = q1.getString("message", "not found");
                    String bc[] = get_string1.split(" ");
                    String put_string1 = "";
                    for (int bcd1 = 0; bcd1 < bc.length; bcd1++)
                        if (bc[bcd1].equals(tyu))
                            put_string1 = put_string1 + hour + ":" + minute + ":" + "ONN ";
                        else
                            put_string1 = put_string1 + bc[bcd1] + " ";

                    SharedPreferences.Editor e123 = q1.edit();
                    e123.putString("message", put_string1);
                    e123.commit();

                    int p = hour * 60 + minute;
                    int m = current_hour * 60 + current_minutes;

                    int diff = p - m + (24 * 60);

                    if (diff < 60)
                        Snackbar.make(view, "Alarm set for " + diff + " minutes from now.", Snackbar.LENGTH_LONG)
                                .setAction("No action", null).show();
                    else
                        Snackbar.make(view, "Alarm set for " + (diff / 60) + " hours and " + (diff % 60) + " minutes from now.", Snackbar.LENGTH_LONG)
                                .setAction("No action", null).show();

                    viewHolder.alarm_on.setVisibility(View.INVISIBLE);
                    //viewHolder.alarm_off.setVisibility(View.VISIBLE);

                }
            }
        });

        viewHolder.alarm_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                SimpleDateFormat df_check = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String formattedDate1 = df_check.format(cal.getTime());
                String xy[] = formattedDate1.split(" ");
                String xy1[] = xy[1].split(":");
                final int current_hour_2 = Integer.parseInt(xy1[0]);
                final int current_minutes_2 = Integer.parseInt(xy1[1]);

                Log.d("Tag :: ", current_hour_2 + " " + hour + " :: " + current_minutes_2 + " " + minute);

                if (current_hour_2 == hour && (current_minutes_2 == minute || current_minutes_2 == ((minute + 1) % 60))) {
                    viewHolder.alarm_on.setVisibility(View.VISIBLE);

                    viewHolder.alarm_off.setVisibility(View.INVISIBLE);

                    my_intent.putExtra("extra", "alarm off-"+avaliacao.getTipoalerta());
                    my_intent.putExtra("extra1", "alarm off at tvDataAvaliacao :: " + str);


                    pending_intent = PendingIntent.getBroadcast(getContext(), position, my_intent, PendingIntent.FLAG_UPDATE_CURRENT);

                    // cancel the pending intent
                    alarm_manager.cancel(pending_intent);

                    // stop the ringtone

                    getContext().sendBroadcast(my_intent);

                    String tyu = avaliacao.getHorarioNotificacao();
                    String lkj[] = tyu.split(":");
                    SharedPreferences q1 = getContext().getSharedPreferences(PREFS, 0);
                    String get_string1 = q1.getString("message", "not found");
                    String bc[] = get_string1.split(" ");
                    String put_string1 = "";
                    for (int bcd1 = 0; bcd1 < bc.length; bcd1++)
                        if (bc[bcd1].equals(tyu))
                            put_string1 = put_string1 + hour + ":" + minute + " ";
                        else
                            put_string1 = put_string1 + bc[bcd1] + " ";

                    SharedPreferences.Editor e123 = q1.edit();
                    e123.putString("message", put_string1);
                    e123.commit();
                    Intent intent101 = mActivity.getIntent();
                    mActivity.finish();
                    mActivity.startActivity(intent101);
                }
            }
        });

        viewHolder.delete_row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                android.support.v7.app.AlertDialog alertDialog = new android.support.v7.app.AlertDialog.Builder(mActivity).create();
                alertDialog.setTitle("Remover alerta");
                alertDialog.setMessage("Deseja realmente remover este alerta?");
                alertDialog.setCancelable(false);
                alertDialog.setButton(android.support.v7.app.AlertDialog.BUTTON_POSITIVE, "SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        DatabaseController crud = new DatabaseController(getContext());
                        String response = crud.removerRegistro("avaliacao", avaliacao.getIdAvaliacao());

                        if (!response.contains("Erro")){
                            SharedPreferences ex1 = getContext().getSharedPreferences(PREFS, 0);
                            String c = ex1.getString("message", "empty");

                            String ghi = avaliacao.getHorarioNotificacao();
                            SharedPreferences.Editor editor12 = ex1.edit();
                            String lm[] = c.split(" ");
                            String fs = "";
                            for (int y5 = 0; y5 < lm.length; y5++) {
                                if (!lm[y5].equals(ghi))
                                    fs = fs + lm[y5] + " ";
                            }
                            editor12.putString("message", fs);
                            editor12.commit();

                            // put in extra string in my intent to tell clock you pressed off button
                            my_intent.putExtra("extra", "alarm off-a");
                            my_intent.putExtra("extra1", "alarm off at tvDataAvaliacao :: " + str);


                            pending_intent = PendingIntent.getBroadcast(getContext(), position, my_intent, PendingIntent.FLAG_UPDATE_CURRENT);

                            // cancel the pending intent
                            alarm_manager.cancel(pending_intent);

                            // stop the ringtone
                            getContext().sendBroadcast(my_intent);

                            listAvaliacao.remove(position);
                            notifyDataSetChanged();
                            Toast.makeText(getContext(), response, Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(getContext(), response, Toast.LENGTH_LONG).show();
                        }
                    }
                });
                alertDialog.setButton(android.support.v7.app.AlertDialog.BUTTON_NEGATIVE, "NÃO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                alertDialog.show();
            }
        });

        return convertView;
    }
}
