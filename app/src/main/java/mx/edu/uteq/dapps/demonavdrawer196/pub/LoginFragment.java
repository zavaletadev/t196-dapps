package mx.edu.uteq.dapps.demonavdrawer196.pub;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mx.edu.uteq.dapps.demonavdrawer196.MainActivity;
import mx.edu.uteq.dapps.demonavdrawer196.R;
import mx.edu.uteq.dapps.demonavdrawer196.databinding.FragmentLoginBinding;

public class LoginFragment extends Fragment {

    /*
    Creamos un atributo de tipo Binding para vincular
    la vista con el controlador
     */
    private FragmentLoginBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        /*
        Anclamos la vista al controlador
         */
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        /*
        Programamos el click de login
         */
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(
                        new Intent(
                                getActivity(),
                                MainActivity.class
                        )
                );
            }
        });

        return root;
    }

    /*
    Desvinculamos a binding cuando la vista sea desechada
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}