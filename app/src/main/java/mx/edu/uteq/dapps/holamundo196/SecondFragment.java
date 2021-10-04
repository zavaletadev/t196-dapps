package mx.edu.uteq.dapps.holamundo196;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import mx.edu.uteq.dapps.holamundo196.databinding.FragmentSecondBinding;

public class SecondFragment extends Fragment {

private FragmentSecondBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

      binding = FragmentSecondBinding.inflate(inflater, container, false);
      return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
            }
        });

        /*
        binding es una variable que permite conectar a la vista
        con el controlador por medio de los id's de los elementos
         */
        binding.btnLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                Mandamos llamar a la accion con el id
                @id/action_SF_to_LF para ir a esa ventana

                El metodo navigate te utiliza 2 parámetros para funcionar
                1.- Es la referencia a esta clase
                2.- El id de la acción a donde quiero ir
                 */
                NavHostFragment.findNavController(
                        SecondFragment.this
                ).navigate(
                        R.id.action_SF_to_LF
                );
            }
        });
    }

@Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}