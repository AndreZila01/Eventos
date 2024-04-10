package pt.iade.andrecustodio.casaapp.Modelos;

import android.provider.ContactsContract;

import com.google.gson.JsonObject;

public class Eventos {
    private int idEventos;
    private String DetalhesExtras;
    private String NomeRua;
    private String Coordenadas;
    private int idOrganizador;
    private String DataEvento;
    private String Requesitos;
    public Eventos(JsonObject json) {
        this.idEventos = json.get("idEvento").getAsInt();
        this.DataEvento = json.get("DataEvento").getAsString();
        this.NomeRua = json.get("NomeRua").getAsString();
        this.idOrganizador = Integer.parseInt(json.get("IdOrganizador").getAsString());
        this.DetalhesExtras = json.get("DetalhesExtras").getAsString();

        if(json.get("Coordenadas").toString()==null)
        this.Coordenadas = json.get("Coordenadas").getAsString();
    }
    public void Eventos(){
        Eventos(0, "", "", "", 0, "", "");
    }
    public void Eventos(int idEventos, String DetalhesExtras, String NomeRua, String Coordenadas, int idOrganizador, String DataEvento, String Requesitos ) {
        this.idEventos = idEventos;
        this.DetalhesExtras = DetalhesExtras;
        this.NomeRua = NomeRua;
        this.Coordenadas = Coordenadas;
        this.idOrganizador = idOrganizador;
        this.DataEvento = DataEvento;
        this.Requesitos = Requesitos;
    }

    public int getidEventos(){
        return this.idEventos;
    }
    public void setidEventos(int idEventos){
        this.idEventos =  idEventos;
    }
    public String getDetalhesExtras(){
        return this.DetalhesExtras;
    }
    public void setDetalhesExtras(String DetalhesExtras){
        this.DetalhesExtras = DetalhesExtras;
    }
    public String getNomeRua(){
        return this.NomeRua;
    }
    public void setNomeRua(String NomeRua){
        this.NomeRua = NomeRua;
    }
    public String getCoordenadas(){
        return this.Coordenadas;
    }
    public void setCoordenadas(String Coordenadas){
        this.Coordenadas = Coordenadas;
    }
    public String getDataEvento(){
        return this.DataEvento;
    }
    public void setDataEvento(String DataEvento){
        this.DataEvento = DataEvento;
    }
    public String getRequesitos(){
        return this.Requesitos;
    }
    public void setRequesitos(String Requesitos){
        this.Requesitos = Requesitos;
    }
}
