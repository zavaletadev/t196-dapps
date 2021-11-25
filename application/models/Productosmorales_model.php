<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Productosmorales_model extends CI_Model {

    function get_productos()
    {
        $query = $this->db->get('producto_morales');

        return $query->num_rows() > 0 ? $query->result() : NULL;
    }

}

/* End of file Productosmorales_model.php */
/* Location: ./application/models/Productosmorales_model.php */
