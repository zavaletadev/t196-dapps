package mx.edu.uteq.dapps.demonavdrawer196.ui;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import mx.edu.uteq.dapps.demonavdrawer196.databinding.FragmentEjemplowebserviceBinding;

public class EjemplowebserviceFragment extends Fragment {

    private FragmentEjemplowebserviceBinding binding;

    /*
    Asi como en web usamos jQuery para facilitarnos el uso de Javascrip y ajax, en
    Android hacemos lo mismo utilizando Volley
     */
    private RequestQueue conexionServ;
    private StringRequest peticionServ;

    //Model de espera (loader)
    private ProgressDialog progress;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentEjemplowebserviceBinding.inflate(inflater, container, false);

        //Inicializamos la variable Progress
        progress = new ProgressDialog(getActivity());

        /*
        Confguramos el progress
         */
        progress.setTitle("Cargando datos");
        progress.setMessage("Por favor espera...");
        progress.setIndeterminate(true);
        progress.setCancelable(false);

        //Inicializamos la conexión al servidor por medio del conexto
        conexionServ = Volley.newRequestQueue(getActivity());

        binding.btnEjecutaWs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Mostramos el loader
                progress.show();
                /*
                 * Para invocar a un servicio web desde android mediante Volley necesitas:
                 * 1.- Indicar el método de envío (POST/GET)
                 * 2.– Url destino del servicio
                 * 3.- Programar el procedimeinto para cuando el servicio contesta
                 *     correctamente
                 * 4.- Programar el procedimiento para cuando el servicio NO contesta
                 *     o contesta con errores ajenos a json
                 * 5.- (SOLO PARA POST) Adicionar los parámetros de envio de la petición
                 *     usando un HashMap
                 */
                peticionServ = new StringRequest(
                        //1
                        Request.Method.GET,
                        //2
                        "https://zavaletazea.dev/labs/awos-dapps196/api/usuarios/datos_personales",
                        //3
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                progress.hide();
                                /*Toast.makeText(getActivity(), response,
                                        Toast.LENGTH_SHORT).show();*/

                                /*
                                Creamos un objeto JSON a partir de la respuesta del
                                servicio
                                 */
                                try {
                                    JSONObject objResultado = new JSONObject(response);
                                    /*
                                    Para tomar los valores d eun objeto json neceistamos
                                    conocer el nombre de a clave y el tipo de dato del valor
                                     */
                                    String nombre = objResultado.getString("nombre");
                                    String apellidos = objResultado.getString("apellidos");
                                    binding.tvNombre.setText(nombre + " " + apellidos);

                                    JSONObject direccion = objResultado.getJSONObject("direccion");

                                    binding.tietCalle.setText(
                                            direccion.getString("calle")
                                    );

                                    binding.tietColonia.setText(
                                            direccion.getString("colonia")
                                    );

                                    binding.tietNumero.setText(
                                            direccion.getString("numero")
                                    );

                                    //Cambiar numero a String de forma fifi
                                    //binding.tietCp.setText(
                                    //        String.valueOf(direccion.getInt("cp"))
                                    //);

                                    //Cambiar numero a String de forma chaka
                                    binding.tietCp.setText(
                                            direccion.getInt("cp") + ""
                                    );

                                    binding.tietMuni.setText(
                                            direccion.getString("municipio")
                                    );

                                    JSONArray telefonos = objResultado.getJSONArray("telefonos");
                                    binding.tietTel1.setText(
                                            telefonos.getString(0)
                                    );

                                    binding.tietTel2.setText(
                                            telefonos.getString(1)
                                    );



                                    JSONArray calificaciones = objResultado.getJSONArray("calificaciones");

                                    //Tomamos cada pos del arreglo en un objeto json
                                    JSONObject calif1 = calificaciones.getJSONObject(0);
                                    binding.tvCalif1.setText(
                                            calif1.getString("materia") + ": " +
                                            calif1.getString("calificacion") + "\n" +
                                            calif1.getString("profesor") + ", " +
                                            calif1.getString("grupo")
                                    );

                                    JSONObject calif2 = calificaciones.getJSONObject(1);
                                    binding.tvCalif2.setText(
                                            calif2.getString("materia") + ": " +
                                            calif2.getString("calificacion") + "\n" +
                                            calif2.getString("profesor") + ", " +
                                            calif2.getString("grupo")
                                    );

                                    JSONObject calif3 = calificaciones.getJSONObject(2);
                                    binding.tvCalif3.setText(
                                            calif3.getString("materia") + ": " +
                                            calif3.getString("calificacion") + "\n" +
                                            calif3.getString("profesor") + ", " +
                                            calif3.getString("grupo")
                                    );

                                }

                                catch(Exception e) {
                                    Toast.makeText(getActivity(), e.getMessage(),
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                progress.hide();
                                Toast.makeText(getActivity(), error.toString(),
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                );
                //Ejecutamos la petición al servidor desde
                //la conexión al servidor
                conexionServ.add(peticionServ);
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