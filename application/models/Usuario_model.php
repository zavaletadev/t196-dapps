<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Usuario_model extends CI_Model {

    /**
     * [lista_usuarios_sql description]
     * @return [type] [description]
     */
    function lista_usuarios_sql() {
        $cmd = "SELECT * FROM usuario";

        //Ejecutamos la consulta
        $query = $this->db->query($cmd);

        //Si la consulta retorna registros, regresaremos 
        //un arreglo de objetos por cada registro
        //Pero, si la consulta está vacía, retornamos nulo
        if ($query->num_rows() > 0) {
            //Retornamos el arreglo de la consulta
            return $query->result();
        }

        //Si no encontramos registros
        else {
            return NULL;
        }
    }

    /**
     * [lista_usuarios_arc description]
     * @return [type] [description]
     */
    function lista_usuarios_arc()
    {
        /*
        Mediante Active Record Class 
        Generamos una consulta:
        SELECT * FROM usuario
         */        
        $query = $this->db->get('usuario');

        if ($query->num_rows() > 0) {
            //Retornamos el arreglo de la consulta
            return $query->result();
        }

        //Si no encontramos registros
        else {
            return NULL;
        }
    }

    /**
     * [lista_clientes_sql description]
     * @return [type] [description]
     */
    function lista_clientes_sql()
    {
        $cmd = "SELECT * FROM usuario WHERE rol_id = '3'";

        //Ejecutamos la consulta
        $query = $this->db->query($cmd);

        //Si la consulta retorna registros, regresaremos 
        //un arreglo de objetos por cada registro
        //Pero, si la consulta está vacía, retornamos nulo
        //
        //var_dump_chidote($query);
        
        // Condicional ternario
        // (CONDICION) ? SI_CUMPLE : NO_CUMPLE
        return ($query->num_rows > 0) ? $query->result() : NULL;
    }

    function lista_clientes_arc()
    {
        /*
        Las consultas con ARC se arman al revés
        Premro agregamos los where
        despues los order
        y al final el get

        <SELECT * FROM usuario WHERE rol_id = 3>
        $this->db->where('campo', 'VALOR')
        $this->db->where('CONDICION')
         */         
        
        /*esto es lo mismo*/
        //$this->db->where('rol_id = 3 AND estatus = 1');
        //$this->db->where('rol_id', 3);
        //$this->db->where('estatus', 1);
        
        /*esto es lo mismo*/
        //$this->db->where('rol_id = 3 OR estatus = 1');
        //$this->db->where('rol_id', 3);
        //$this->db->or_where('estatus', 1);
        
        $this->db->where('rol_id', 3);
        $query = $this->db->get('usuario');

        return ($query->num_rows() > 0) ? $query->result() : NULL;

    }
}

/* End of file Usuario_model.php */
/* Location: ./application/models/Usuario_model.php */
