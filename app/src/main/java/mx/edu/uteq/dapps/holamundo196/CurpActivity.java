package mx.edu.uteq.dapps.holamundo196;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

/*
Un Activity es un screnn completo, a diferencia
de una fragmento que solo repinta una porción
de la pantalla, un Activity es una pantalla
completa que se superpone de la pantalla anterior
 */
public class CurpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Aqui indicamos cual es la vista (.XML)
        //ligada a este Activity (Controlador .JAVA)
        setContentView(R.layout.activity_curp);
    }
}