package mx.edu.uteq.dapps.holamundo196;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import mx.edu.uteq.dapps.holamundo196.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

     binding = ActivityMainBinding.inflate(getLayoutInflater());
     setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    /*
    Método encargado de mostrar el menu superior lateral
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /*
    Método encargado de gestionar el clic / touch de un
    elemento del menú

    La ejecusión de cada acción del menú se lleva a cabo por medio
    del id asigando a cada elemento del menú
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        /*
        Programamos el botón salir
         */
        if (id == R.id.menu_salir) {
            /*
            Toast son el sistem de alarmas básicas
            de Android (similar de un alert)
            Toast utiliza 3 parámetros para funcionar
            1.- Contexto
                * Desde un fragment --> getActivity()
                * Desde un Activity --> NOMBRE_CLASE.this
            2.- Texto a mostrar
            3.- Duración (LENGTH_LONG / LENGTH_SHORT)

            Ejecutar al metodo .show();
             */

            Toast.makeText(
                    MainActivity.this,
                    "Hola desde un toast",
                    Toast.LENGTH_LONG
            ).show();

        }

        /*
        Acciones del menu curp
         */
        if (id == R.id.menu_curp) {
            /*
            Por medio de startActiviy e intent
            vamos a la siguinete pantalla (actividad / controlador)
             */
            startActivity(
                    new Intent(
                            MainActivity.this,
                            CurpActivity.class
                    )
            );
        }

        /*
        Link desde el menu a los grafmentos
         */
        if (id == R.id.action_f2) {
            AlertDialog.Builder alerta = new AlertDialog.Builder(MainActivity.this);
            alerta.setTitle("¿Al fragmento?");
            alerta.setMessage("¡Amonoooos!");
            alerta.setIcon(R.drawable.alerta);
            alerta.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    /*
                    Para navegar un elemento fuera del gragmento necesitamos:
                    1.- Indicar el componente de navegación
                            **Contexto
                            **Elemtno de navegación
                    2.- Borrar la pila de elmentos navegados
                    3.- navegar al fragmento requerido (por medio del ID)
                     */
                    NavController navController = Navigation.findNavController(
                            MainActivity.this,
                            R.id.nav_host_fragment_content_main
                    );
                    navController.navigateUp();
                    navController.navigate(R.id.SecondFragment);
                }
            });
            alerta.setNeutralButton("Cancelar", null);
            alerta.setCancelable(false);
            alerta.show();
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}