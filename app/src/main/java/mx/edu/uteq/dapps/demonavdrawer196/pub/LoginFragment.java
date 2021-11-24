package mx.edu.uteq.dapps.demonavdrawer196.pub;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;

import mx.edu.uteq.dapps.demonavdrawer196.MainActivity;
import mx.edu.uteq.dapps.demonavdrawer196.R;
import mx.edu.uteq.dapps.demonavdrawer196.databinding.FragmentLoginBinding;

public class LoginFragment extends Fragment {

    /*
    Creamos un atributo de tipo Binding para vincular
    la vista con el controlador
     */
    private FragmentLoginBinding binding;

    /*
    Instancia de Volley
    Instancia de petición al servidor
    ProgressDailog
     */
    private RequestQueue conexionServ;
    private StringRequest peticionServ;
    private ProgressDialog progress;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        /*
        Anclamos la vista al controlador
         */
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        conexionServ = Volley.newRequestQueue(getActivity());

        progress = new ProgressDialog(getActivity());
        progress.setTitle("Autenticando");
        progress.setMessage("Por favor espere...");
        progress.setCancelable(false);
        progress.setIndeterminate(true);

        /*
        Programamos el click de login
         */
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Creamos valores finales (constantes) con el usuario y contrseña
                final String usuario = binding.tietUsuario.getText().toString();
                final String password = binding.tietPassword.getText().toString();

                /*
                Te falta validar
                 */
                progress.show();

                peticionServ = new StringRequest(
                        //1 Tipo de envio
                        Request.Method.POST,
                        //2 Url del servicio
                        "https://zavaletazea.dev/labs/awos-dapps196/api/auth/login",
                        //3 Codigo cuando todo sale bien
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                progress.hide();
                                Toast.makeText(getActivity(), response,
                                        Toast.LENGTH_LONG).show();
                            }
                        },
                        //4 Codigo cuando existen errores
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                progress.hide();
                                Toast.makeText(getActivity(), error.toString(),
                                        Toast.LENGTH_LONG).show();
                            }
                        }
                //Agregamos las variables post
                ){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        //Creamos un mapa para agregar los nombres de las variables
                        //y sus valores
                        Map<String, String> parametros = new HashMap<>();

                        //Agregamos cada nombre de variable y su valor
                        parametros.put("usuario", usuario);
                        parametros.put("password", md5(password));

                        //Retornamos los parámetros del servicio
                        return parametros;
                    }
                };
                //Agregamos la petición al servidor
                conexionServ.add(peticionServ);
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

    public String md5(String texto) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(StandardCharsets.UTF_8.encode(texto));
            return String.format("%032x", new BigInteger(1, md5.digest()));
        } catch (java.security.NoSuchAlgorithmException e) {
            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return null;
    }

}