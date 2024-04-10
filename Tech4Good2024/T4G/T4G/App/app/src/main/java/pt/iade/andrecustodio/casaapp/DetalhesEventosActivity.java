package pt.iade.andrecustodio.casaapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import pt.iade.andrecustodio.casaapp.Modelos.ClassLogin;
import pt.iade.andrecustodio.casaapp.Modelos.Eventos;

public class DetalhesEventosActivity extends AppCompatActivity {
private int idEvento = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalheseventoslayout);
        Background_doWork();
    }

    private void Background_doWork(){
        Intent intent = getIntent();
        idEvento = intent.getIntExtra("position", 1);
        String ss = intent.getStringExtra("Evento");
        Eventos eventos = new Gson().fromJson(ss, Eventos.class);

        ((TextView)findViewById(R.id.pppppp)).setText(eventos.getDataEvento() + " "+eventos.getNomeRua());
        ((TextView)findViewById(R.id.textoDetalhes)).setText(eventos.getDetalhesExtras());
        (findViewById(R.id.btnAderir)).setOnClickListener(this::onClick);
        (findViewById(R.id.pctBack)).setOnClickListener(this::onClick);

    }

    SharedPreferences prefs = null;
    public static final MediaType aaa = MediaType.parse("application/json; charset=utf-8");

    private void onClick( View v){
        int i = v.getId();

        if(i == R.id.btnAderir){
            prefs = getSharedPreferences("idUser", MODE_PRIVATE);
            String url = "http://10.0.2.2:9990/Account/RegistarEvento";




            Request request = new Request.Builder()
                    .url(url)
                    .post(RequestBody.create(aaa, "[{\"idEstado\":1, \"idLogin\":"+prefs.getInt("idUser", MODE_PRIVATE)+",\"idEvento\":"+idEvento+"}]"))
                    .build();

            new OkHttpClient().newCall(request).enqueue(new Callback() {

                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                    new AlertDialog.Builder(DetalhesEventosActivity.this).setTitle("Campos em Falta!").setMessage("Os campos acima, não podem estar vazios!!").setPositiveButton(android.R.string.ok, null).setIcon(android.R.drawable.ic_dialog_alert).show();
                }


                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                    if (response.isSuccessful()) {

                        // TODO: response has id.
                        String value = response.body().string().toString();
                        if (value.contains(" fez login ")) {

                            Intent intent = new Intent(DetalhesEventosActivity.this, HomeActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivityForResult(intent, 1);
                        } else
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(), "O utilizador está com os dados invalidos!", Toast.LENGTH_LONG).show();
                                }
                            });


                    }
                }
            });
        }
        else if(i == R.id.pctBack)
            finish();
    }
}
