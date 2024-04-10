package pt.iade.andrecustodio.casaapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity {
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


       prefs = getSharedPreferences("CASAapp", MODE_PRIVATE);
        if (prefs.getBoolean("firstrun", false)) {
            Intent s = new Intent(WelcomeActivity.this, VoluntarioActivity.class);
            s.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(s);
        }
        else {
            setContentView(R.layout.welcomelayout);
            Background_DoWork();
        }
    }
    SharedPreferences prefs = null;
    private void Background_DoWork(){
        (findViewById(R.id.btnVoluntariar)).setOnClickListener(this::Onclick);
        (findViewById(R.id.btnVoluntario)).setOnClickListener(this::Onclick);

    }

    private void Onclick(View v){
        int s = v.getId();

        if(s == R.id.btnVoluntario){
            prefs.edit().putBoolean("firstrun", true).commit();
            Intent ss = new Intent(WelcomeActivity.this, VoluntarioActivity.class);
            startActivity(ss);
        }else {
            Intent ss = new Intent(WelcomeActivity.this, NewVoluntarioActivity.class);
            ss.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(ss);
        }
    }
}

