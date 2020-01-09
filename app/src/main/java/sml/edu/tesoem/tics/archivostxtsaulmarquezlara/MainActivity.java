package sml.edu.tesoem.tics.archivostxtsaulmarquezlara;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
EditText txtnombre;
TextView lblmostrar;

private final String nomarch = "datossml.txt";
private ArrayList<String> TextoCompleto = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtnombre = findViewById(R.id.txtnombre);
        lblmostrar = findViewById(R.id.lblmostrar);


    }

    public void MGrabar(View v) {
        ManejoDeArchivosTXT controlador = new ManejoDeArchivosTXT();
        String Texto = "";
        try {
            Texto = txtnombre.getText().toString();
            controlador.agregar(Texto, TextoCompleto);
            TextoCompleto = controlador.getContenido();

            if (controlador.grabar(TextoCompleto, this, nomarch)) {
                Toast.makeText(this, "Se grabo correctamente", Toast.LENGTH_LONG).show();
                CargarInfo();
            } else {
                Toast.makeText(this, "No se grabo correctamente", Toast.LENGTH_LONG).show();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void  MLeer(View v){
        CargarInfo();
    }

    private void CargarInfo(){
        ManejoDeArchivosTXT objmanarch = new ManejoDeArchivosTXT();
        if (objmanarch.verificar(this, nomarch)) {
            Toast.makeText(this, "Existe el archivo...", Toast.LENGTH_LONG).show();
            if (objmanarch.leer(this,nomarch)){
                TextoCompleto = objmanarch.getContenido();
                String cadena="";
                for (String micadena : TextoCompleto){
                    cadena+="\n" +micadena;
                }
                lblmostrar.setText(cadena);
            }
        } else {
            Toast.makeText(this, "No existe el archivo...", Toast.LENGTH_LONG).show();
        }
    }
}
