<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Auth_model extends CI_Model {

    function login($usuario, $rol_id = NULL)
    {
        /*
        SELECT * FROM usuario 
        WHERE 
        (email_auth LIKE BINARY '4421298927' OR telefono_auth LIKE BINARY '4421298927') 
        AND 
        rol_id = 3
         */
        $this->db->where("(email_auth LIKE BINARY '$usuario' OR telefono_auth LIKE BINARY '$usuario')");

        //Si el rol NO es nulo, lo agregamos a la clausula where
        if (!is_null($rol_id)) {
            $this->db->where('rol_id', $rol_id);
        }
        
        $query = $this->db->get('usuario');

        //Retornamos al usuario (SOLO UNO)
        return ($query->num_rows() === 1) ? $query->row() : NULL;
    }    

}

/* End of file Auth_model.php */
/* Location: ./application/models/Auth_model.php */
