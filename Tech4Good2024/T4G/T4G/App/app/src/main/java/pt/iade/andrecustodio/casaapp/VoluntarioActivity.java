package pt.iade.andrecustodio.casaapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import org.w3c.dom.Text;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import pt.iade.andrecustodio.casaapp.Modelos.ClassLogin;

public class VoluntarioActivity  extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.voluntariolayout);
        background_doWork();
    }

    SharedPreferences prefs = null;
    private void background_doWork(){

        prefs = getSharedPreferences("Username", MODE_PRIVATE);
        if (prefs.getBoolean("Username", false)){

           ((TextView)(findViewById(R.id.lblBemVindo))).setText("Sê Bem vind"+prefs.toString());
        }

        ((Button)findViewById(R.id.btnVoluntariar)).setOnClickListener(this::OnClick);
    }

    private void OnClick(View v){
        String txtPass = ((TextView)(findViewById(R.id.txtPass))).getText().toString();
        String txtUserEm = ((TextView)(findViewById(R.id.txtUsernameEmail))).getText().toString();
        if (!txtPass.toString().isEmpty() && !txtUserEm.toString().isEmpty()) {
            // TODO : Checkar valores e se estão na database!
            CheckDataBase(txtUserEm.toString(), txtPass.toString());

        } else
            new AlertDialog.Builder(VoluntarioActivity.this).setTitle("Campos em Falta!").setMessage("Os campos acima, não podem estar vazios!!").setPositiveButton(android.R.string.ok, null).setIcon(android.R.drawable.ic_dialog_alert).show();
    }
    public static final MediaType aaa = MediaType.parse("application/json; charset=utf-8");
    private void CheckDataBase(String username, String password) {

        ((TextView)(findViewById(R.id.txtPass))).requestFocus();
        String url = "http://10.0.2.2:9990/Account/Login";


        ClassLogin login = new ClassLogin(0, username, password);

        String json = new Gson().toJson(login);

        Request request = new Request.Builder()
                .url(url)
                .post(RequestBody.create(aaa, json))
                .build();

        new OkHttpClient().newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                new AlertDialog.Builder(VoluntarioActivity.this).setTitle("Campos em Falta!").setMessage("Os campos acima, não podem estar vazios!!").setPositiveButton(android.R.string.ok, null).setIcon(android.R.drawable.ic_dialog_alert).show();
            }


            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()) {

                    // TODO: response has id.
                    String value = response.body().string().toString();
                    if (value.contains(" fez login ")) {

                        prefs = getSharedPreferences("idUser", MODE_PRIVATE);
                        prefs.edit().putInt("idUser", Integer.parseInt(value.split("-")[1].split("-")[0])).commit();
                        Intent intent = new Intent(VoluntarioActivity.this, HomeActivity.class);
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
}
