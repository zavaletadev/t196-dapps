package mx.edu.uteq.dapps.holamundo196;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import mx.edu.uteq.dapps.holamundo196.databinding.FragmentFirstBinding;

public class FirstFragment extends Fragment {

private FragmentFirstBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

      binding = FragmentFirstBinding.inflate(inflater, container, false);
      return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });

        /*
        Los fragmentos (controlador) se ligan a las
        vistas (layout .xml) por medio del objeto
        binding (SOLO APLICA PARA FRAGMENTOS)

        binding crea un componente en el controlador
        con el mismo nombre que la vista (layout .xml)
        solo que en --notación camello--
         */
        binding.btnCurp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                Las pantallas completas se navegan por medio
                del método startActivity() usando un objeto
                de tipo intent como parámetro

                Los intent son un objeto que generarn una referencia
                para indicar donde estás y dónde vas

                1.- Crear un intent
                        Param 1 = Contexto
                            - Fragmento = getActivity()
                            - Activity = NOMBRE_CLASE.this
                        Param 2 = A donde voy
                            - NOMBRE_CLASE.class
                 */

                //Realizamos la referencia de donde estamos
                //a donde vamos
                Intent intentCurp = new Intent(
                        getActivity(),
                        CurpActivity.class
                );

                //Nos vamos a la siguiente pantalla
                startActivity(intentCurp);
            }
        });

    }

@Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}