package mx.edu.uteq.dapps.demonavdrawer196;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import mx.edu.uteq.dapps.demonavdrawer196.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editorSp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedPreferences = getSharedPreferences("t196", MODE_PRIVATE);
        editorSp = sharedPreferences.edit();

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);
        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home,
                R.id.nav_gallery,
                R.id.nav_slideshow,
                R.id.nav_cuenta,
                R.id.nav_listadeseos
        ).setOpenableLayout(drawer).build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_listadeseos) {
            /*
            Para navegar dentro de un elemento fuera del fragment
            a un fragmento necesitamos:
            1.- un NavController que haga referencia al Fragmento Host
            principal.
            2.- Eliminar todos los elementos de navegación anteriores
            3.- anclar el nuevo elemento al fragmento
             */
            NavController navController = Navigation.findNavController(
                    MainActivity.this,
                    R.id.nav_host_fragment_content_main
            );
            navController.navigateUp();
            navController.navigate(R.id.nav_listadeseos);
        }

        if (id == R.id.action_salir) {
            salir();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration) || super.onSupportNavigateUp();
    }

    /*
    Al dar click en Back preguntamos si deseamos salir
     */

    @Override
    public void onBackPressed() {
        salir();
    }

    /*
    Creamos un metodo que pregunte si desemaos salir
    y en caso positivo enviamos de vuelta al home
     */
    public void salir() {
        AlertDialog.Builder alerta = new AlertDialog.Builder(MainActivity.this);
        alerta.setTitle("Cerrar sesión");
        alerta.setMessage("¿Seguro deseas salir?");
        alerta.setNegativeButton("No", null);
        alerta.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Borramos los valores almacenados en nuestro espacio
                //t196 de SharedPreferences
                editorSp.remove("id");
                editorSp.remove("user_key");
                editorSp.commit();

                //Eliminar esta pantalla del stack de navegacion
                finish();

                startActivity(
                        new Intent(
                                MainActivity.this,
                                InicioActivity.class
                        )
                );
            }
        });
        alerta.setIcon(R.drawable.alerta196);
        alerta.setCancelable(false);
        alerta.show();
    }
}