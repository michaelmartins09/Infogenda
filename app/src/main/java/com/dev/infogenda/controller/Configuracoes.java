package com.dev.infogenda.controller;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.dev.infogenda.R;

import static com.dev.infogenda.database.DatabaseController.PREFS1;

public class Configuracoes extends AppCompatActivity {

    Button ringtone,number,message;
    String chosenRingtone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracoes);

        ringtone=(Button)findViewById(R.id.ringtonepicker);
        ringtone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RingtoneManager.ACTION_RINGTONE_PICKER);
                intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TYPE, RingtoneManager.TYPE_ALL);
                intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TITLE, "Select Tone");
                intent.putExtra(RingtoneManager.EXTRA_RINGTONE_EXISTING_URI, (Uri) null);
                startActivityForResult(intent, 5);
            }
        });

    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent intent)
    {
        String title="";
        if (resultCode == Activity.RESULT_OK && requestCode == 5)
        {
            Uri uri = intent.getParcelableExtra(RingtoneManager.EXTRA_RINGTONE_PICKED_URI);

            if (uri != null)
            {
                this.chosenRingtone = uri.toString();
                SharedPreferences x1 = getSharedPreferences(PREFS1,0);
                SharedPreferences.Editor editor = x1.edit();
                editor.putString("ringtone",this.chosenRingtone);
                editor.commit();
                Ringtone ringtone = RingtoneManager.getRingtone(this, Uri.parse(this.chosenRingtone));
                title = ringtone.getTitle(this);
            }
            else
            {
                this.chosenRingtone = null;
            }
        }
        Toast.makeText(Configuracoes.this,"Ringtone Selected: "+title,Toast.LENGTH_SHORT).show();
    }


}
