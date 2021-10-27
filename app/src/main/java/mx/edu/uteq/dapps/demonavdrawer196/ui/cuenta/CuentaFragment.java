package mx.edu.uteq.dapps.demonavdrawer196.ui.cuenta;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import mx.edu.uteq.dapps.demonavdrawer196.R;
import mx.edu.uteq.dapps.demonavdrawer196.databinding.FragmentCuentaBinding;

public class CuentaFragment extends Fragment {

    /*
    Creamos un objeto de tipo binding para enlazar de manera
    automatica el layout del fragmento (vista) a la
    clase Java (Controlador)
     */
    private FragmentCuentaBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /*
        Inicializamos el enlace entre la vista y el controlador
         */
        binding = FragmentCuentaBinding.inflate(
                inflater, container, false
        );

        /*
        Creamos un componente que nos permita acceder a todo el contenido
        de la vista
         */
        View root = binding.getRoot();

        return root;

    } // onCreateView

    /*
    Cuando dejemos de visualizar el fragmento, lo destruimos
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
} // Clase