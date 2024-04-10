package pt.iade.andrecustodio.casaapp.Modelos;

import android.widget.DatePicker;
import android.widget.TextView;

import pt.iade.andrecustodio.casaapp.R;

public class NewUser {

    private String txtNomeApelido;
    private String txtEmail;
    private String txtNascimento;
    private String txtPass;

    public NewUser() {
        this("", "", "", "");
    }

    public NewUser(String NomeApelido, String Email, String Password, String Nascimento) {
        this.txtNomeApelido = NomeApelido;
        this.txtEmail = Email;
        this.txtNascimento = Nascimento;
        this.txtPass = Password;
    }


    public void setNomeApelido(String NomeApelido) {
        this.txtNomeApelido = NomeApelido;
    }

    public void setEmail(String Email) {
        this.txtEmail = Email;
    }

    public void setPassword(String password) {
        this.txtPass = password;
    }

    public void setNascimento(String Nascimento) {
        this.txtNascimento = Nascimento;
    }

    public String getId() {
        return this.txtNascimento;
    }

    public String getUsername() {
        return this.txtEmail;
    }

    public String getPassword() {
        return this.txtPass;
    }
    public String getNomeApelido() {
        return this.txtNomeApelido;
    }
}
