/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mexicode;

/**
 *
 * @author pavulzavala
 * esta interface es para efectuar aqui el proceso de 
 * validacion de reglas personalizadas
 */
public interface CustomValidation 
{

   /**
    * funcion que procesa las reglas de validacion personalizadas,
     * es decir, aquellas que son creadas por el programador
    * @param fieldName
    * @param toValidate
    * @param validationValue
    * @param errorMsg
    * @return 
    */
    public boolean customValidate( 
            String fieldName,
            String toValidate, 
            String validationValue,
            String errorMsg
            );
}
