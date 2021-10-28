package mx.edu.uteq.dapps.demonavdrawer196;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import mx.edu.uteq.dapps.demonavdrawer196.databinding.ActivityInicioBinding;

public class InicioActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityInicioBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityInicioBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_inicio);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_inicio);
        return NavigationUI.navigateUp(navController, appBarConfiguration) || super.onSupportNavigateUp();
    }

    /*
    Invocamos al método que muestra el menu en AppBar
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //1.- layout del menu
        //2.- instancia de menu activa
        getMenuInflater().inflate(R.menu.pub_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /*
    Invocamos al método que nos permite cachar ele vento click
    de un item del menu
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        NavController navController = Navigation.findNavController(
                //Contexto
                InicioActivity.this,
                //Contenedor de los fragmentos para navegar
                R.id.nav_host_fragment_content_inicio
        );

        if (id == R.id.action_login) {
            navController.navigateUp();
            navController.navigate(R.id.LoginFragment);
        }

        return super.onOptionsItemSelected(item);
    }
}