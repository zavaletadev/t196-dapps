<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Productos_morales extends CI_Controller {

    /**
     * [get_productos description]
     * @example [GET]
     * @return [type] [description]
     */
    function get_productos()
    {
        header('Content-Type: application/json; charset=utf-8;');

        //Modelo
        $this->load->model('productosmorales_model');

        $productos = $this->productosmorales_model->get_productos();

        echo json_encode(
            array(
                "code" => 200, 
                "data" => $productos
            )
        );

    }

}

/* End of file Productos_morales.php */
/* Location: ./application/controllers/api/Productos_morales.php */
