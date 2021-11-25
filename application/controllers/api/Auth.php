<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Auth extends CI_Controller {

    public function __construct()
    {
        parent::__construct();
        $this->load->model('auth_model');
    }

    /**
     * [index description]
     * @return [type] [description]
     */
    function index()
    {
        die("<center>
            <br>
            <br>
            <h1>Auth API</h1>
            <hr>
            </center>");
    }

    /**
     * Servicio de inicio de sesión para 
     * nuestra app Navdemo196
     *
     * @example [POST(email, password)] https://zavaletazea.dev/labs/awos-dapps196/api/auth/login
     * @return JSON [description]
     */
    function login()
    {
        header('Content-Type: application/json; charset=utf-8');
        /**
         * Validamos que el servicio cuente con las variables necesarias
         */
        $this->form_validation->set_rules('usuario', 'usuario', 'trim|required');
        $this->form_validation->set_rules('password', 'password', 'trim|required|exact_length[32]');

        /*
        Si la validación de campos es correcta
         */
        if ($this->form_validation->run()) {
            $usuario = $this->input->post('usuario');
            $password = $this->input->post('password');

            $datos_usuario = $this->auth_model->login($usuario, 3);

            //Si el usuario existe            
            if (!is_null($datos_usuario)) {                                
                /*
                Revisamos si la contraseña es correcta
                 */
                if ($password === $datos_usuario->pass_auth) {                    
                    /*
                    Si el usuario tiene estatus 1
                     */                    
                    if ($datos_usuario->estatus == 1) {                        
                        //Regresamos los datos públicos del usuario
                        echo json_encode(
                            array(
                                'code'          => 200, 
                                'datos_usuario' => array(
                                    'usuario_id'     => $datos_usuario->usuario_id, 
                                    'email'          => $datos_usuario->email_auth, 
                                    'telefono'       => $datos_usuario->telefono_auth, 
                                    'fecha_registro' => $datos_usuario->fecha_registro
                                )
                            )
                        );
                    }

                    // si el usuario tiene estatus 2
                    // Cuenta deshabilitada
                    else if ($datos_usuario->estatus == 2) {
                        echo json_encode(
                            array(
                                'code' => 403
                            )
                        );
                    }

                    //Si la cuenta tiene estatus 3
                    else {
                        echo json_encode(
                            array(
                                'code' => 404
                            )
                        );
                    }
                }

                //Si la contraseña no coincide
                else {
                    echo json_encode(
                        array(
                            'code' => 404
                        )
                    );
                }
            }

            //Si el usuario NO existe
            else {
                echo json_encode(
                    array(
                        'code' => 404
                    )
                );
            }
        }
        else {
            //Limpiamos los caracteres del mensaje de error
            $this->form_validation->set_error_delimiters('','');

            //Si no es correcta indicamos los errores            
            echo json_encode(                
                array(
                    'code' => 400, 
                    'errors' => validation_errors()
                )
            );
        }
    }

}

/* End of file Auth.php */
/* Location: ./application/controllers/api/Auth.php */
