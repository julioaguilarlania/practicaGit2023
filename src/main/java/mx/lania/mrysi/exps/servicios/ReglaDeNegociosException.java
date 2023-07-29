/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.lania.mrysi.exps.servicios;

/**
 *
 * @author jaguilar
 */
public class ReglaDeNegociosException extends RuntimeException {

    public ReglaDeNegociosException(String no_se_puede_eliminar_un_expediente_no_CER) {
        super(no_se_puede_eliminar_un_expediente_no_CER);
    }
    
}
