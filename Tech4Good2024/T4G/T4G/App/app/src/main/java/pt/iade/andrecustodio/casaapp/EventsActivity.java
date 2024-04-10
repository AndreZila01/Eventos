package pt.iade.andrecustodio.casaapp;

import android.content.Intent;
import android.media.metrics.Event;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import pt.iade.andrecustodio.casaapp.Modelos.Eventos;
import pt.iade.andrecustodio.casaapp.adpter.adpatarEventos;

public class EventsActivity extends AppCompatActivity {

    protected RecyclerView itemsListView;

    protected ArrayList<Eventos> itemsList;

    protected adpatarEventos ntcItemRowAdpdater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.eventslayout);
        Background_doWork();
    }

    private void Background_doWork(){
        itemsList = new ArrayList<Eventos>();

        String path = "http://10.0.2.2:9990/lstEventos";

        try {
            Request rq = new Request.Builder().url(path).build();
            OkHttpClient client = new OkHttpClient();

            client.newCall(rq).enqueue(new Callback() {
                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                    Log.d("Error", "" + e);
                }

                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
//Log.d("Message", "" + response.body().string());

                    try {
                        JsonArray arr = new Gson().fromJson(response.body().string(), JsonArray.class);
                        for (JsonElement elem : arr) {
                            itemsList.add(new Eventos(elem.getAsJsonObject()));
                        }

                    } catch (Exception ex) {
                        Log.d("", "" + ex.getMessage());
                    }
                    finally {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                collect(itemsList);
                            }
                        });
                    }
                }
            });
        } catch (Exception ex) {
            Log.d("s", "" + ex);
        }

        ((ImageButton)findViewById(R.id.imgBDM)).setOnClickListener(this::OnClick);
        ((ImageButton)findViewById(R.id.imgBHome)).setOnClickListener(this::OnClick);
        ((ImageButton)findViewById(R.id.imgBCalendario)).setOnClickListener(this::OnClick);
    }

    private void OnClick(View v){

        int s= v.getId();
        if(s == R.id.imgBDM)
        {

            /*Intent ss = new Intent(.this, NewVoluntarioActivity.class);
            ss.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(ss);*/
        }
        else if(s == R.id.imgBHome){

            Intent ss = new Intent(EventsActivity.this, HomeActivity.class);
            ss.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(ss);
        }
        else if(s==R.id.imgBCalendario){

            Intent ss = new Intent(EventsActivity.this, EventsActivity.class);
            ss.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(ss);
        }
    }
    private void collect(ArrayList<Eventos> items) {

        ntcItemRowAdpdater = new adpatarEventos(EventsActivity.this, items);

        itemsListView = (RecyclerView) findViewById(R.id.pnlEventos);
        itemsListView.setLayoutManager(new LinearLayoutManager(EventsActivity.this));
        itemsListView.setAdapter(ntcItemRowAdpdater);
        ntcItemRowAdpdater.setOnClickListener(new adpatarEventos.ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(EventsActivity.this, DetalhesEventosActivity.class);
                intent.putExtra("idNew", position);
                intent.putExtra("Evento", new Gson().toJson(itemsList.get(position)));

                startActivityForResult(intent, 1);
                return;
            }
        });

    }

}
