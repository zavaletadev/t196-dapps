package mx.edu.uteq.dapps.demonavdrawer196.pub;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

import org.json.JSONArray;
import org.json.JSONObject;

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
    private AlertDialog.Builder alerta;

    /*
    Invocamos al espacio de SharedPreferences
     */
    private SharedPreferences sharedPreferences;
    /*
    Editor para mi espacio de trabajo
     */
    SharedPreferences.Editor spEditor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        /*
        Indicamos nuestro espacio de trabajo dentro de
        SharedPreferences

        Diversos modos de uso del espacio
        MODE_PRIVATE ---------> solo mi app lo usa
        MODE_WORLD_READABLE --> solo mi app escribe, cualquier app lee
        MODE_WORLD_WRITABLE --> todos leen y escriben

        NEcesitamos 2 parámetros:
        1.- nombre del espacio
            t196
        2.- Tipo de apertura
         */
        sharedPreferences = getActivity().getSharedPreferences(
                "t196",
                Context.MODE_PRIVATE
        );

        /*
        Agregmos un edito a nuestro espacio de trabajo
         */
        spEditor = sharedPreferences.edit();


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
                                /*Toast.makeText(getActivity(), response,
                                        Toast.LENGTH_LONG).show();*/

                                //Convertimos la respuesta un objeto JSON
                                try {
                                    JSONObject objRespuesta = new JSONObject(response);

                                    int codigo = objRespuesta.getInt("code");

                                    //Verificamos el estatus del usuario
                                    //Si el usuario existe y su contraseña coincide
                                    if (codigo == 200) {

                                        //Tomamos el objeto con los datos del usuario
                                        JSONObject datosUsuario = objRespuesta.getJSONObject("datos_usuario");
                                        int usuarioId = datosUsuario.getInt("usuario_id");
                                        /*
                                        Agregamos a SharedPReferences en nuestro espacio
                                        t196 el id y la contraseña encriptada del usuario
                                        El método put me permite agregar valores
                                         */
                                        //Agregar e id del usuario
                                        //encriptado
                                        spEditor.putString("id", md5(String.valueOf(usuarioId)));

                                        //guardamos la contraseña encriptada
                                        spEditor.putString("user_key", md5(password));
                                        
                                        //Guardamos los cambios
                                        spEditor.commit();

                                        /*
                                        Redireccionamos al home de la app
                                         */
                                        startActivity(
                                                new Intent(
                                                        getActivity(),
                                                        MainActivity.class
                                                )
                                        );
                                    }

                                    //Si el usuario o la contraseña no coinciden
                                    else if (codigo == 404) {
                                        alerta = new AlertDialog.Builder(getActivity());
                                        alerta.setTitle("¡Hey!");
                                        alerta.setMessage("Usuario / contraseña incorrectos\nPor favor intenta de nuevo");
                                        alerta.setIcon(R.drawable.noencontrado);
                                        alerta.setPositiveButton("Aceptar", null);
                                        alerta.setCancelable(false);
                                        alerta.show();

                                    }

                                    else if (codigo == 403) {
                                        alerta = new AlertDialog.Builder(getActivity());
                                        alerta.setTitle("Cuenta deshabilitada");
                                        alerta.setMessage("Tu cuenta se encuentra temporalmente deshabilitada.\n\nPor favor contacta con un administrador para más información");
                                        alerta.setIcon(R.drawable.deshabilitado);
                                        alerta.setPositiveButton("Aceptar", null);
                                        alerta.setCancelable(false);
                                        alerta.show();
                                    }
                                }

                                catch(Exception e) {
                                    Toast.makeText(getActivity(), e.getMessage(),
                                            Toast.LENGTH_SHORT).show();
                                }
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

        /*
        Click al btn_ejemplo_productos
         */
        binding.btnEjemploProductos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                peticionServ = new StringRequest(
                        //1 Tipo
                        Request.Method.GET,
                        //2 url
                        "https://zavaletazea.dev/labs/awos-dapps196/api/productos_morales/get_productos",
                        //3 Si salio bien
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Toast.makeText(getActivity(), response,
                                        Toast.LENGTH_SHORT).show();

                                try {
                                    JSONObject objRespuesta = new JSONObject(response);

                                    JSONArray productos = objRespuesta.getJSONArray("data");

                                    for (int i = 0; i < productos.length(); i++) {
                                        JSONObject prod = productos.getJSONObject(i);
                                    }



                                }
                                catch (Exception e) {

                                }
                            }
                        },

                        // 4 Cuando error
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        }
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