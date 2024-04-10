package pt.iade.andrecustodio.casaapp;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Debug;
import android.text.InputType;
import android.util.Patterns;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import pt.iade.andrecustodio.casaapp.Modelos.ClassLogin;
import pt.iade.andrecustodio.casaapp.Modelos.NewUser;

public class NewVoluntarioActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newvoluntariolayout);
        Background_DoWork();
    }

    private void Background_DoWork(){
        (findViewById(R.id.txtNascimento)).setFocusable(false);
        ((Button)findViewById(R.id.btnVoluntariar)).setOnClickListener(this::onClick);
        ((TextView)findViewById(R.id.txtNascimento)).setOnClickListener(this::onClick);
    }

    private void onClick(View v)
    {
        String txtNomeApelido = ((TextView)findViewById((R.id.txtNomeApelido))).getText().toString();
        String txtEmail = ((TextView)findViewById((R.id.txtEmail))).getText().toString();
        String txtNascimento = ((TextView)findViewById((R.id.txtNascimento))).getText().toString();
        String txtPass = ((TextView)findViewById((R.id.txtPassNew))).getText().toString();
        int s = v.getId();
        if(s == R.id.btnVoluntariar) {
            if (checkValues(txtNomeApelido, txtEmail, txtPass, txtNascimento))
                RequestPost(txtNomeApelido, txtEmail, txtPass, txtNascimento);
        }
        else {
            InputMethodManager imm = (InputMethodManager) this.getSystemService(this.INPUT_METHOD_SERVICE);
            //Find the currently focused view, so we can grab the correct window token from it.
            View view = this.getCurrentFocus();
            //If no view currently has focus, create a new one, just so we can grab a window token from it
            if (view == null) {
                view = new View(this);
            }
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

            final Calendar c = Calendar.getInstance();
            final int year = c.get(Calendar.YEAR);
            final int month = c.get(Calendar.MONTH);
            final int day = c.get(Calendar.DAY_OF_WEEK);
            DatePickerDialog datepick = new DatePickerDialog(NewVoluntarioActivity.this,  new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                    //dateWhatever = new GregorianCalendar(year, month, dayOfMonth);
                    ((TextView) findViewById(R.id.txtNascimento)).setText(String.format("%d-%02d-%02d", year, month + 1, dayOfMonth));
                }

            }, year, month, day);
            datepick.setTitle("select date");
            datepick.getDatePicker().setMaxDate(System.currentTimeMillis());
            datepick.show();

        }
    }

    private boolean checkValues(String txtNomeApelido, String txtEmail, String txtPass, String txtNascimento){

        if(txtNomeApelido.isEmpty()){
            ((TextView)findViewById((R.id.txtNomeApelido))).setError("O Nome não pode estar a vazio");
            return false;
        }

        if(!txtNomeApelido.contains(" "))
        {
            ((TextView)findViewById((R.id.txtNomeApelido))).setError("Deverá ter um Nome e Apelido");
            return false;
        }

        if (!(Patterns.EMAIL_ADDRESS).matcher(txtEmail).matches()) {
            ((TextView) findViewById(R.id.txtEmail)).setError("O Email não é valido!!");
            return false;
        }

        if (txtNascimento.equals("")) {
            ((TextView) findViewById(R.id.txtNascimento)).setError("A data de Nascimento não pode estar vazia. Clica em mim!!");
            return false;
        }

        if(txtPass.isEmpty()){

            ((TextView)findViewById((R.id.txtPass))).setError("Deverá ter uma password!!");
            return false;
        }

        if(txtPass.length()<=8){

            ((TextView)findViewById((R.id.txtPass))).setError("A password deverá ter 8 caracteres minimo!!");
            return false;
        }


        return true;
    }
    public static final MediaType aaa = MediaType.parse("application/json; charset=utf-8");

    private void RequestPost(String NomeApelido, String email, String pass, String Nascimento){
        String url = "http://10.0.2.2:9990/Account/Regist";


        NewUser login = new NewUser(NomeApelido, email, pass, Nascimento);

        String json = new Gson().toJson(login);

        Request request = new Request.Builder()
                .url(url)
                .post(RequestBody.create(aaa, json))
                .build();

        new OkHttpClient().newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                new AlertDialog.Builder(NewVoluntarioActivity.this).setTitle("Campos em Falta!").setMessage("Os campos acima, não podem estar vazios!!").setPositiveButton(android.R.string.ok, null).setIcon(android.R.drawable.ic_dialog_alert).show();
            }


            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()) {

                    // TODO: response has id.
                    String value = response.body().string().toString();
                    if (value.contains("Com sucesso")) {

                        Intent ss = new Intent(NewVoluntarioActivity.this, WelcomeActivity.class);
                        ss.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(ss);

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
