package pt.iade.andrecustodio.casaapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homelayout);
        Background_DoWork();

    }

    private void Background_DoWork(){

        ((ImageButton)findViewById(R.id.imgBHome)).setOnClickListener(this::onClick);
        ((ImageButton)findViewById(R.id.imgBDM)).setOnClickListener(this::onClick);
        ((ImageButton)findViewById(R.id.imgBCalendario)).setOnClickListener(this::onClick);
    }

    private void onClick(View v){
        int s = v.getId();


        if(s== R.id.imgBCalendario){
            Intent intent = new Intent(HomeActivity.this, EventsActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
        else if(s == R.id.imgBDM){
            //Intent intent = new Intent(HomeActivity.this, HomeActivity.class);
            //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            //startActivity(intent);
        }
    }
}
