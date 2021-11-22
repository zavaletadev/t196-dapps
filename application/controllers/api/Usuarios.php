<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Usuarios extends CI_Controller {

    /*
    Los constructores son el método de la clase que se inicializa 
    al principio, aqui puedes agregar elementos que necesites a lo largo
    de toda la clase
     */
    function __construct() {
        //Invocar al constructor de CI_Controller
        parent::__construct();

        //Invocamos al modelo que contiene 
        //nuestra consulta
        $this->load->model('usuario_model');
    }
    

    function index()
    {

    }

    /**
     * [lista_usuarios description]
     * @return [type] [description]
     */
    function lista_usuarios() {
        //Tomamos la lista de usuarios y la guardamos
        //en una variable
        //
        /*Lista de usuarios con SQL*/
        //$lista_usuarios = $this->usuario_model->lista_usuarios_sql();
        
        ///*Lista de usuarios con Active Record Class*/
        $lista_usuarios = $this->usuario_model->lista_usuarios_arc();

        //Para visualizar toda la información de una variable
        //SIN IMPORTAR EL TIPO DE DATO utilizamos var_dump($_VAR_)
        var_dump_chidote($lista_usuarios);
    }

    /**
     * [lista_clientes description]
     * @return [type] [description]
     */
    function lista_clientes() {
        //Invocamos al modelo de usuarios
        //Por medio de SQL
        $lista_clientes = $this->usuario_model->lista_clientes_arc();

        var_dump_chidote($lista_clientes);
    }

    /**
     * [hola_json description]
     * @return [type] [description]
     */
    function hola_json()
    {
        /*
        Los objetos JSON se generan a partir de arreglos
        asociativos
         */        
        $arr = array(
            'mensaje' => 'Hola mundo desde un objetos json'
        );

        /*
        Convertimos el arreglo de PHP a un 
        objeto json
         */
        var_dump_chidote($arr);        
        echo json_encode($arr);
    }

    function json_php()
    {
        $json = '{"nombre":"Raúl"}';
        //Decode convierte de JSON a php
        $arr_php = json_decode($json);

        var_dump_chidote($arr_php);
    }

    /**
     * [datos_personales description]
     * @return [type] [description]
     * @example [GET] https://zavaletazea.dev/labs/awos-dapps196/api/usuarios/datos_personales
     */
    function datos_personales()
    {
        //esperar 2 segundos para responder
        sleep(0);
        /**
         * Para consumir el servicio desde Android necesitamos:
         * 1.- Libreria Volley en app.gradle
         * 2.- Habilitar los permisos de internet y trafico http 
         *     en el manifest.xml
         * 3.- Generar una peticion asíncrona (Ajax) por medio de un 
         *     RequestQueue
         * 4.- Ejecutar la petición del tipo de dato especificado (GET/POST)
         */

        /*
        Convertimos el retorno de HTML a JSON
         */
        header('Content-Type: application/json; charset=utf-8');

        $datos_personales = array(
            'nombre'    => 'Raúl', 
            'apellidos' => 'Zavaleta Zea', 
            'matricula' => '2007313035',
            'direccion' => array(
                'calle'     => 'Av. Marmota',
                'numero'    => '62 int 8', 
                'colonia'   => 'La Pradera', 
                'cp'        => 76902, 
                'municipio' => 'El Marqués'
            ),
            //otra forma de visualizar un arreglo
            'telefonos'      => ['(442) 204 8329', '(442) 129 8927'], 
            'calificaciones' => array(
                array(
                    'materia'      => 'Diseño de Apps', 
                    'calificacion' => 'SA', 
                    'profesor'     => 'Raúl Zavaleta', 
                    'grupo'        => 'T-196'
                ), 
                array(
                    'materia'      => 'Métricas de Software', 
                    'calificacion' => 'DE', 
                    'profesor'     => 'María Auxilio Pérez', 
                    'grupo'        => 'T-196'
                ), 
                array(
                    'materia'      => 'Aplicaciones Web Orientadas a Servicios', 
                    'calificacion' => 'AU', 
                    'profesor'     => 'Jorge Morales', 
                    'grupo'        => 'T-196'
                )
            )
        );

        echo json_encode($datos_personales);
    }

}

/* End of file Usuarios.php */
/* Location: ./application/controllers/api/Usuarios.php */
