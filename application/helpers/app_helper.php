<?php
/*
Indicamos que la zona horaria d enuestra aplicación
será siempre Mexico_City
 */
date_default_timezone_set('America/Mexico_City');

/**
 * Función var_dump con formato de salida
 * @param  [type] $var [description]
 * @return [type]      [description]
 */
function var_dump_chidote($var) {
    echo "<pre>";
    var_dump($var);
    echo "</pre>";
}
