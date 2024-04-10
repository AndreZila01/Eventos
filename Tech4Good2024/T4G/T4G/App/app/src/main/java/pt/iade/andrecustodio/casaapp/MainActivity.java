package pt.iade.andrecustodio.casaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Background_doWork();
    }

    private void Background_doWork(){
        ((ImageButton)findViewById(R.id.imgBCalendario)).setOnClickListener(this::OnClick);
        ((ImageButton)findViewById(R.id.imgBDM)).setOnClickListener(this::OnClick);
        ((ImageButton)findViewById(R.id.imgBHome)).setOnClickListener(this::OnClick);
    }

    private void OnClick(View v){
        int s = v.getId();

        if(s == R.id.imgBCalendario){

        }
        else if(s == R.id.imgBDM){

        }
        else if(s == R.id.imgBHome){

        }
    }
}