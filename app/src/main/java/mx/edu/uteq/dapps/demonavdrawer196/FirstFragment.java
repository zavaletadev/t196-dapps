package mx.edu.uteq.dapps.demonavdrawer196;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavHostController;
import androidx.navigation.fragment.NavHostFragment;

import mx.edu.uteq.dapps.demonavdrawer196.databinding.FragmentFirstBinding;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;
    private ProgressDialog progress;
    private SharedPreferences sharedPreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);

        progress = new ProgressDialog(getActivity());
        progress.setTitle("Inicializando app");
        progress.setMessage("Por favor espera...");
        progress.setIndeterminate(true);
        progress.setCancelable(false);
        progress.show();

        /*
        Consultamos nuestro espacio
        t196
         */
        sharedPreferences = getActivity().getSharedPreferences(
                "t196",
                Context.MODE_PRIVATE
        );

        //Verificamos si tenemos los valores en nuestro espacio
        String idSp = sharedPreferences.getString("id", null);
        String userKeySp = sharedPreferences.getString("user_key", null);

        /*
        Si antes se había iniciado sesión, ninguno d elos valores serían nulos

        TODO: Crear un servicio que verifique si ese id encriptado y ese pass
         encriptado pertenecen a un usuario tipo cliente con estatus 1
         */
        if (idSp != null && userKeySp != null) {
            //Redireccionamos al home
            startActivity(
                    new Intent(
                            getActivity(),
                            MainActivity.class
                    )
            );
        }

        //Si no existen los valores
        else {
            progress.hide();
        }


        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this).navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });

        /*
        Click a navigation drawer
         */
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(
                        getActivity(),
                        MainActivity.class
                ));
            }
        });

        binding.btnWs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FF_to_WSF);
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}