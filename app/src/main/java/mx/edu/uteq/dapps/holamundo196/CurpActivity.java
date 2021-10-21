package mx.edu.uteq.dapps.holamundo196;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/*
Un Activity es un screnn completo, a diferencia
de una fragmento que solo repinta una porción
de la pantalla, un Activity es una pantalla
completa que se superpone de la pantalla anterior
 */
public class CurpActivity extends AppCompatActivity {

    /*
    Atributos del controlador para poder interactuar con los elementos
    de la vista (layout)
     */
    private TextView tvTitulo;
    private EditText etNombres;
    private Button btnCancelar;
    private Button btnContinuar;

    private AlertDialog.Builder alerta;

    /*
    El metodo oncreate se eejcuta al ejecutarse un
    Activity es el equivalente al método
    --public static void main--
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Aqui indicamos cual es la vista (.XML)
        //ligada a este Activity (Controlador .JAVA)
        setContentView(R.layout.activity_curp);

        /*
        Enlazar los elementos del controlador con los elementos de la vista
        por medio de su identificador
        findViewById(R.id.elem_id)
         */
        tvTitulo = findViewById(R.id.tv_titulo);
        etNombres = findViewById(R.id.et_nombres);
        btnContinuar = findViewById(R.id.btn_continuar);
        btnCancelar = findViewById(R.id.btn_cancelar);

        /*
        Las laeras en Android don un tipo de mesaje modal que permite
        interacruar de diversas maneras

        Para inicializar una alerta es necesario agregar el contexto
            **Desde un Fragmento (getActivity())
            **Desde un Activity (NOMBRE_CLASE.this)
         */
        alerta = new AlertDialog.Builder(CurpActivity.this);

        /*
        Una vez ligado, es posible realizar acciones sobre los componentes
        de la vista
         */
        //etNombres.setText("Raúl");

        /*
        Existen 2 formas de ejecutar el evento click
            1.- Por medio de la implementación de la interfaz genérica
            OnclickListener

            2.- Por medio de un método propio con un parámetro de tipo View
         */

        /*
        Click Forma 1 (para el bootn cancelar)
         */
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                Los Alert.Builder con componentes que permite mostrar
                un mensaje en una ventana modal, las secciones
                para configurar son:
                Titulo
                Icono
                Text
                Botones (max3)
                    - NeutralButton
                        Acciones que no generan ningún cambio
                    - PositiveButton
                        Acciones que adicionan o modifican
                    - NegativeButton
                        Acciones que eliminan, deshabilitan o cancelan
                Comportamiento
                Visibilidad
                 */
                alerta.setTitle("¡Espera!");
                alerta.setMessage("¿Seguro deseas regresar?");
                alerta.setIcon(R.drawable.alerta);
                alerta.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        /*
                        Regresamos a MainActivity
                         */
                        startActivity(new Intent(
                                CurpActivity.this,
                                MainActivity.class
                        ));
                    }
                });
                alerta.setNegativeButton("No", null);
                alerta.setNeutralButton("Ok", null);
                alerta.setCancelable(false); //Oblica a usar los botones
                alerta.show(); //Hace visible la alerta
            }
        });

    } //OnCreate (Método)

    /*
    Click Forma 2 (para el boton conitnuar)
    En esta forma, crearemos un metodo llamado
    clickContinuar con un parámetro de tipo view y esete método lo ligaremos
    al boton desde el layout
     */
    public void clickContinuar(View v) {

        /*
        Validaciones:
        1.- Nombres: min 3 letras
         */

        //Tomar el valor de nombres
        String valorNombres = etNombres.getText().toString();

        if (valorNombres.trim().length() < 3) {
            Toast.makeText(
                    CurpActivity.this,
                    "Nombre inválido",
                    Toast.LENGTH_SHORT
            ).show();

            //Borramos los campos
            etNombres.setText("");

            //Ponemos el cursor en el campo
            etNombres.requestFocus();
        }

        else {
            //Cambiar el título
            tvTitulo.setText("¡Hola "+valorNombres+"!");
        }
    }

} //CurpActivity.class