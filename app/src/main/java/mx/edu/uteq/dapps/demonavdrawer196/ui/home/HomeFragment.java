package mx.edu.uteq.dapps.demonavdrawer196.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import mx.edu.uteq.dapps.demonavdrawer196.R;
import mx.edu.uteq.dapps.demonavdrawer196.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    /*
    ListView necesita 4 elementos para funcionar
    1.- Un componente gráfico de tipo <LisView />
    2.- Una colección de elementos (Arreglo o una Lista)
    3.- Un adaptador que enlace al componente gráfico <ListView />
        con la colecció de datos
    4.- Indicar el diseño para cada elemento de la lista
    5.- Conectar el adaptador al componente gráfico <ListView />
     */

    //1
    private ListView lvSimple;
    //2
    private List<String> datos;
    //3
    private ArrayAdapter<String> adaptador;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //Inicializarlos elementos
        lvSimple = binding.lvSimple;
        datos = new ArrayList();
        /*
        Valores de la lista
         */

        for (int i = 0; i < 2; i++) {
            datos.add("Aculco, Estado de México");
            datos.add("Ajijic, Jalisco");
            datos.add("Álamos, Sonora");
            datos.add("Amealco de Bonfil, Querétaro");
            datos.add("Aquismón, San Luis Potosí");
            datos.add("Arteaga, Coahuila");
            datos.add("Atlixco, Puebla");
            datos.add("Bacalar, Quintana Roo");
            datos.add("Batopilas, Chihuahua");
            datos.add("Bernal, Querétaro");
            datos.add("Bustamante, Nuevo León");
            datos.add("Cadereyta de Montes, Querétaro");
            datos.add("Calvillo, Aguascalientes");
            datos.add("Candela, Coahuila");
            datos.add("Capulálpam de Méndez, Oaxaca");
            datos.add("Casas Grandes, Chihuahua");
            datos.add("Chiapa de Corzo, Chiapas");
            datos.add("Chignahuapan, Puebla");
            datos.add("Cholula, Puebla");
            datos.add("Coatepec, Veracruz");
            datos.add("Comala, Colima");
            /*datos.add("Comitán, Chiapas");
            datos.add("Comonfort, Guanajuato");
            datos.add("Compostela de Indias, Nayarit");
            datos.add("Cosalá, Sinaloa");
            datos.add("Coscomatepec, Veracruz");
            datos.add("Creel, Chihuahua");
            datos.add("Cuatro Ciénegas, Coahuila");
            datos.add("Cuetzalan del Progreso, Puebla");
            datos.add("Cuitzeo del Porvenir, Michoacán");
            datos.add("Dolores Hidalgo, Guanajuato");
            datos.add("El Oro, Estado de México");
            datos.add("El Rosario, Sinaloa");
            datos.add("El Fuerte, Sinaloa");
            datos.add("Guadalupe, Zacatecas");
            datos.add("Guerrero, Coahuila");
            datos.add("Huamantla, Tlaxcala");
            datos.add("Huasca de Ocampo, Hidalgo");
            datos.add("Huauchinango, Puebla");
            datos.add("Huautla de Jiménez, Oaxaca");
            datos.add("Huichapan, Hidalgo");
            datos.add("Isla Aguada, Campeche");
            datos.add("Isla Mujeres, Quintana Roo");
            datos.add("Ixtapan de la Sal, Estado de México");
            datos.add("Izamal, Yucatán");
            datos.add("Jala, Nayarit");
            datos.add("Jalpa de Cánovas, Guanajuato");
            datos.add("Jalpan de Serra, Querétaro");
            datos.add("Jerez de García Salinas, Zacatecas");
            datos.add("Jiquilpan de Juárez, Michoacán");
            datos.add("Lagos de Moreno, Jalisco");
            datos.add("Linares, Nuevo León");
            datos.add("Loreto, Baja California Sur");
            datos.add("Magdalena de Kino, Sonora");
            datos.add("Malinalco, Estado de México");
            datos.add("Maní, Yucatán");
            datos.add("Mapimí, Durango");
            datos.add("Mascota, Jalisco");
            datos.add("Mazamitla, Jalisco");
            datos.add("Mazunte, Oaxaca");
            datos.add("Melchor Múzquiz, Coahuila");
            datos.add("Metepec, Estado de México");
            datos.add("Mexcaltitán, Nayarit");
            datos.add("Mier, Tamaulipas");
            datos.add("Mineral de Angangueo, Michoacán");
            datos.add("Mineral de Pozos, Guanajuato");
            datos.add("Mineral del Chico, Hidalgo");
            datos.add("Mocorito, Sinaloa");
            datos.add("Nombre de Dios, Durango");
            datos.add("Nochistlán de Mejía, Zacatecas");
            datos.add("Orizaba, Veracruz");
            datos.add("Pahuatlán, Puebla");
            datos.add("Palenque, Chiapas");
            datos.add("Palizada, Campeche");
            datos.add("Papantla, Veracruz");
            datos.add("Paracho de Verduzco, Michoacán");
            datos.add("Parras de la Fuente, Coahuila");
            datos.add("Pátzcuaro, Michoacán");
            datos.add("Pinos, Zacatecas");
            datos.add("Real de Asientos, Aguascalientes");
            datos.add("Real de Catorce, San Luis Potosí");
            datos.add("Real de Monte, Hidalgo");
            datos.add("Salvatierra, Guanajuato");
            datos.add("San Cristóbal de las Casas, Chiapas");
            datos.add("San Joaquín, Querétaro");
            datos.add("San José de Gracia, Aguascalientes");
            datos.add("San Juan Teotihuacán y San Martín de las Pirámides, Edo de México");
            datos.add("San Pablo Villa de Mitla, Oaxaca");
            datos.add("San Pedro Tlaquepaque, Jalisco");
            datos.add("San Pedro y San Pablo Teposcolula, Oaxaca");
            datos.add("San Sebastián del Oeste, Jalisco");
            datos.add("Santa Catarina Juquila, Oaxaca");
            datos.add("Santa Clara del Cobre, Michoacán");
            datos.add("Santa María del Río, San Luis Potosí");
            datos.add("Santiago, Nuevo León");
            datos.add("Sayulita, Nayarit");
            datos.add("Sisal, Yucatán");
            datos.add("Sombrerete, Zacatecas");
            datos.add("Tacámbaro, Michoacán");
            datos.add("Talpa de Allende, Jalisco");
            datos.add("Tapalpa, Jalisco");
            datos.add("Tapijulapa, Tabasco");
            datos.add("Taxco de Alarcón, Guerrero");
            datos.add("Tecate, Baja California");
            datos.add("Tecozautla, Hidalgo");
            datos.add("Tepotzotlán, Estado de México");
            datos.add("Tepoztlán, Morelos");
            datos.add("Tequila, Jalisco");
            datos.add("Tequisquiapan, Querétaro");
            datos.add("Tetela de Ocampo, Puebla");
            datos.add("Teúl de González Ortega, Zacatecas");
            datos.add("Tlatlauquitepec, Puebla");
            datos.add("Tlayacapan, Morelos");
            datos.add("Tlalpujahua de Rayón, Michoacán");
            datos.add("Tlaxco, Tlaxcala");
            datos.add("Todos Santos, Baja California Sur");
            datos.add("Tonatico, Estado de México");
            datos.add("Tula, Tamaulipas");
            datos.add("Tulum, Quintana Roo");
            datos.add("Tzintzuntzan, Michoacán");
            datos.add("Valladolid, Yucatán");
            datos.add("Valle de Bravo, Estado de México");
            datos.add("Viesca, Coahuila");
            datos.add("Villa del Carbón, Estado de México");
            datos.add("Xico, Veracruz");
            datos.add("Xicotepec, Puebla");
            datos.add("Xilitla, San Luis Potosí");
            datos.add("Yuriria, Guanajuato");
            datos.add("Zacatlán de las Manzanas, Puebla");
            datos.add("Zempoala, Hidalgo");
            datos.add("Zimapán, Hidalgo");
            datos.add("Zozocolco de Hidalgo, Veracruz");*/
        }
        //3 Enlazar los elmentos dentro del adaptador
        /*
        Un adaptador necesita que el indiques 3 parámetros
        1.- Contexto
        2.- Diseño de cada elemento de la lista
        3.- La colección de dastos con todos los elementos
         */
        adaptador = new ArrayAdapter(
                //1
                getActivity(),
                //2
                android.R.layout.simple_list_item_1,
                //3
                datos
        );

        //4 CONECTAMOS EL ADAPTADOR CON EL LISTIVIEW
        lvSimple.setAdapter(adaptador);

        /*
        Agregamos el evento swipe (deslizar la lista)
         */
        binding.srlLvSimple.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                /*Toast.makeText(
                        getActivity(),
                        "Aqui en el Refresh...",
                        Toast.LENGTH_SHORT
                ).show();*/
                //Quitamos el loader del Swipe
                binding.srlLvSimple.setRefreshing(false);

                //Eliminamos un elemento de la lista
                datos.remove(0);

                //Indicamos al adaptador se actualizaron los valores
                adaptador.notifyDataSetChanged();


            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}