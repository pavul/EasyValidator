/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mexicode;

/**
 * clase que contiene los parametros de validacion
 * que utilizara el validador
 * Created by pavulzavala on 3/27/16.
 */
public class ValidationParam
{

    private String toValidate; //valor  a validar, por lo general un string
    private int [] validationType;
    private String fieldName; //nombre del campo que ve el usuario final
    private CustomValidation customValidation;
    
    
    //mensage de error personalizable que tendra cada parametro de validacion
    //por ejemplo hay un error que se regresa por default, pero en el caso de
    //validar los selectores, se tiene que especificar este mensaje de manera
    //diferente
    private String errorMsg;

    /**
     * este valor por lo general se debe de utilizar cuando se quiere validar
     * ciertos campos que necesitan de un valor establecido, por ejemplo
     * para validar un valor minimo, se tiene que establecer cual es el valor minimo,
     * en este caso en esta propiedad se validaria ese valor
     */
    private String validationValue;

    /**
     * este constructor se usa cuando las variables del objeto se establecen
     * por medio del setter
     */
    public ValidationParam()
    {}

    /**
     * este constructor establece los parametros del objeto al crearlo
     * @param fieldName
     * @param toValidate
     * @param validationType
     */
    public ValidationParam( 
            String fieldName,
            String toValidate,
            int... validationType)
    {
        this.toValidate = toValidate;
        this.validationType = validationType;
        this.validationValue = "";
        this.fieldName = fieldName;
    }

    /**
     * este constructor se usa para establecer las validaciones que requieran de
     * un valor prestablecido por el usuario, por ejemplo
     * para validar un valor minimo, se tiene que establecer cual es el valor minimo,
     * en este caso validationValue sera el valor sobre el cual se validara el valor minimo
     * por ejemplo:
     * new ValidationParam( etHectare.getHint().toString(),
     * etHectare.getText().toString(),
     * "0",
     *  Validator.REQUIRED )
     *  @param fieldName el nombre del campo que se valida
     * @param toValidate el valor que se va a validar
     * @param validationType tipo de validacion que se efectua ( required,mayor than, etc )
     * @param validationValue valor predefinido sobre el cual efectuar cierta validacion
     *                        por ejemplo cuando el valor a validar es 5 y el maximo
     *                        permitido es 10, entonces esta correcto
     */
    public ValidationParam( 
            String fieldName, 
            String toValidate,  
            String validationValue, 
            int... validationType )
    {
        this( fieldName, toValidate, validationType );
        this.validationValue = validationValue;
    }

    /**
     * este constructor sirve para agregar aparte un parametro de validacion
     * con un mensaje de error creado por el usuario, es decir, un mensaje que
     * no esta predefinido, si no que lo define el usuario
     * @param fieldName
     * @param toValidate
     * @param validationValue
     * @param errorMsg
     * @param validationType
     */
    public ValidationParam( String fieldName,
                            String toValidate,
                            String validationValue,
                            String errorMsg ,
                            int... validationType )
    {
        this( fieldName, toValidate, validationValue, validationType );
        this.errorMsg = errorMsg;
    }

    /**
     * este constructor sirve para validar reglas personalizadas que son
     * creadas/agregadas por el desarrollador
     * @param fieldName
     * @param toValidate
     * @param validationValue
     * @param errorMsg
     * @param customValidation
     * @param validationType 
     */
     public ValidationParam( String fieldName,
                            String toValidate,
                            String validationValue,
                            String errorMsg ,
                            CustomValidation customValidation,
                            int... validationType )
    {
        this( fieldName, toValidate, validationValue, errorMsg, validationType );
        this.customValidation = customValidation;
    }
    
    

    public String getToValidate() {
        return toValidate;
    }

    public void setToValidate(String toValidate) {
        this.toValidate = toValidate;
    }

    public int[] getValidationType() {
        return validationType;
    }

    public void setValidationType(int[] validationType) {
        this.validationType = validationType;
    }

    public String getValidationValue() {
        return validationValue;
    }

    public void setValidationValue(String validationValue) {
        this.validationValue = validationValue;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public CustomValidation getCustomValidation() {
        return customValidation;
    }

    public void setCustomValidation(CustomValidation customValidation) {
        this.customValidation = customValidation;
    }
    
    
    
}//

