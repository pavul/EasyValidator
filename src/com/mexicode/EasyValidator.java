/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mexicode;

import java.util.List;
import java.util.regex.Pattern;



/**
 * clase que puede hacer diferentes tipos de validaciones sobre los campos
 * de los formularios que se usen en android
 * Created by pavulzavala on 3/27/16.
 */
public class EasyValidator
{

    //constantes para la i18n
    public static final String EN_LOC = "en"; //
    public static final String ES_LOC = "es"; //


    public static final int EMAIL = 100; //checa que sea un email valido
    public static final int NUMERIC = 101; // checa que el valor sea un numero
    public static final int ALPHA = 102; //checa que el valor sea una cadena alfanumerica
    public static final int REQUIRED = 103;//el valor no tiene que ser vacio, es requerido
    public static final int IPADDRESS = 104; // el valor tiene que que ser una ip de formato ipv4
    public static final int URL = 105; //el valor tiene que ser una URL valida


    public static final int SAMEAS_STR = 106; //el valor tiene que ser igual a algun otro valor de parametros
    public static final int SAMEAS_INT = 107; //el valor tiene que ser igual a algun otro valor de parametros
    public static final int SAMEAS_FLT = 108; //el valor tiene que ser igual a algun otro valor de parametros
    public static final int SAMEAS_DBL = 109; //el valor tiene que ser igual a algun otro valor de parametros


    public static final int MINIMUN_INT = 110; //el valor que a validar debe de ser mayor que el validationValue establecido
    public static final int MINIMUN_FLT = 111; //el valor que a validar debe de ser mayor que el validationValue establecido
    public static final int MINIMUN_DBL = 112; //el valor que a validar debe de ser mayor que el validationValue establecido


    public static final int MAXIMUN_INT = 113; // el valor a validar debe de ser menor que el validationValue establecido
    public static final int MAXIMUN_FLT = 114; // el valor a validar debe de ser menor que el validationValue establecido
    public static final int MAXIMUN_DBL = 115; // el valor a validar debe de ser menor que el validationValue establecido


    public static final int LESSTHAN_INT = 116; //el valor a validar debe de ser menor que el validationValue establecido
    public static final int LESSTHAN_FLT = 117; //el valor a validar debe de ser menor que el validationValue establecido
    public static final int LESSTHAN_DBL = 118; //el valor a validar debe de ser menor que el validationValue establecido

    public static final int MAYORTHAN_INT = 119; //el valor a validar debe de ser mayor el validationValue establecido
    public static final int MAYORTHAN_FLT = 120; //el valor a validar debe de ser mayor el validationValue establecido
    public static final int MAYORTHAN_DBL = 121; //el valor a validar debe de ser mayor el validationValue establecido

    public static final int ALPHA_WITHOUT_SPECIAL_CHARACTERS = 122; //este valor debe de ser cualquier string
    public static final int MAXLENGTH = 123; //longitud maxima de caracteres de un string
    public static final int MINLENGTH = 124; //longitud minima de caracteres de un string

    public static final int NUMERIC_INT = 125; // checa que el valor sea un numero entero
    public static final int NUMERIC_FLT = 126; // checa que el valor sea un numero flotante
    public static final int NUMERIC_DBL = 127; // checa que el valor sea un numero double
    
    public static final int CUSTOM = 1000; // verifica valores personalizados, el usuario se encarga de manejar estas validaciones desde la interfaz


    //los parametros que se tendran que validar
    private List< ValidationParam > params;
    private String errorMsg; //error de mensaje que se regresara
    private String locale;

    /**
     * constructor que no crea el listado de parametros
     * ni el mensaje de error
     */
    public EasyValidator()
    {
        errorMsg = "";
        locale = EN_LOC; //por default se pone el locale en ingles
    }//

    /**
     * constructor 2 en el cual el validador acepta una lsta de parametros a validar
     * @param params
     */
    public EasyValidator( List<ValidationParam> params )
    {
        this();
        this.params = params;

    }//




    /**
     * metodo que valida todos los parametros segun sus tipos
     * de validacion a realizar
     * @return 
     */
    public  boolean validate()
    {

        if( null == this.params )
        {
            errorMsg = "You Need To set the validationParams";
            return false;
        }
        
        //se van a sacar todos los parametros que tienen
        //que ser validados
        for( ValidationParam vp : this.params )
        {

            //se validara el campo de este parametro
            //tantas veces como tipos de validaciones se tengan
            for( int valType : vp.getValidationType() )
            {

                
                if( valType == CUSTOM )
                {
                
                //primero se valida si viene un tipo custom,
                //asi ya no se verifican las demas
                               
                errorMsg = vp.getFieldName()+" : "+ vp.getErrorMsg();
                return vp.getCustomValidation()
                        .customValidate( 
                                vp.getFieldName(),
                                vp.getToValidate(),
                                vp.getValidationValue(),
                                vp.getErrorMsg()
                                );
                
                }
                /**
                 * se valida el parametro a validar, la validacion que
                 * se va a hacer y el valor de validacion sobre el cual
                 * validar en contra...
                 * ejemplo: 5, menor_int, 5
                 * en este caso regresa notificacion que 5 no es menor que 5
                 */
                else if( !validating( vp.getToValidate(), valType, vp.getValidationValue() ) )
                {

                    if( null == vp.getErrorMsg() )
                    {
                     errorMsg = createErrorMsg(valType, vp.getFieldName(), vp.getValidationValue());
                    }
                    else
                    {
                     errorMsg = vp.getErrorMsg();
                    }

                    return false;
                }

            }//


        }


        return true;
    }//validate


    /**
     * funcion que tiene la logica para valdar los campos segun los tipos de validacion que tengan
     * aveces este metodo toma la variable validationValue, para hacer algunas validaciones
     * @param fieldToValidate
     * @param validationType
     * @return
     */
    private boolean validating( String fieldToValidate, int validationType, String validationValue )
    {

        boolean response = true;

        switch( validationType )
        {
            case EMAIL:
                Pattern rfc2822 = Pattern.compile(
                        "^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$"
                );

                if ( !rfc2822.matcher( fieldToValidate ).matches() )
                {
                 response = false;
                }
                break;

            case NUMERIC:

                try
                {
                 Integer.parseInt( fieldToValidate );
                }
                catch( NumberFormatException | NullPointerException ex )
                {
                response = false;
                }

                break;
            case ALPHA:
                if( fieldToValidate instanceof String )
                {}else{ response = false; }
                break;
            case REQUIRED:

                if( fieldToValidate.isEmpty() )
                {
                    response = false;
                }

                break;
            case IPADDRESS:
                break;
            case URL:
                break;
            case SAMEAS_STR:
                response = fieldToValidate.equals( validationValue );
                break;
            case SAMEAS_INT:

                int i1 = Integer.valueOf( fieldToValidate );
                int i2 = Integer.valueOf( validationValue );
                response = ( i1 == i2 );

            break;
            case SAMEAS_FLT:
                float f1 = Float.valueOf( fieldToValidate );
                float f2 = Float.valueOf( validationValue );
                response = ( f1 == f2 );
                break;
            case SAMEAS_DBL:
                float d1 = Float.valueOf( fieldToValidate );
                float d2 = Float.valueOf( validationValue );
                response = ( d1 == d2 );
                break;
            case MINIMUN_INT:
                int ii1 = Integer.valueOf( fieldToValidate );
                int ii2 = Integer.valueOf( validationValue );
                response = ( ii1 >= ii2 );
                break;
            case MINIMUN_FLT:
                float ff1 = Float.valueOf( fieldToValidate );
                float ff2 = Float.valueOf( validationValue );
                response = ( ff1 >= ff2 );
                break;
            case MINIMUN_DBL:
                double dd1 = Float.valueOf( fieldToValidate );
                double dd2 = Float.valueOf( validationValue );
                response = ( dd1 >= dd2 );
                break;
            case MAXIMUN_INT:
                int iii1 = Integer.valueOf( fieldToValidate );
                int iii2 = Integer.valueOf( validationValue );
                response = ( iii1 <= iii2 );
                break;
            case MAXIMUN_FLT:
                float fff1 = Float.valueOf( fieldToValidate );
                float fff2 = Float.valueOf( validationValue );
                response = ( fff1 <= fff2 );
                break;
            case MAXIMUN_DBL:
                double ddd1 = Float.valueOf( fieldToValidate );
                double ddd2 = Float.valueOf( validationValue );
                response = ( ddd1 <= ddd2 );
                break;
            case LESSTHAN_INT:
                int iiii1 = Integer.valueOf( fieldToValidate );
                int iiii2 = Integer.valueOf( validationValue );
                response = ( iiii1 < iiii2 );
                break;
            case LESSTHAN_FLT:
                float ffff1 = Float.valueOf( fieldToValidate );
                float ffff2 = Float.valueOf( validationValue );
                response = ( ffff1 < ffff2 );
                break;
            case LESSTHAN_DBL:
                double dddd1 = Float.valueOf( fieldToValidate );
                double dddd2 = Float.valueOf( validationValue );
                response = ( dddd1 < dddd2 );
                break;
            case MAYORTHAN_INT:
                int iiiii1 = Integer.valueOf( fieldToValidate );
                int iiiii2 = Integer.valueOf( validationValue );
                response = ( iiiii1 > iiiii2 );
                break;
            case MAYORTHAN_FLT:
                float fffff1 = Float.valueOf( fieldToValidate );
                float fffff2 = Float.valueOf( validationValue );
                response = ( fffff1 > fffff2 );
                break;
            case MAYORTHAN_DBL:
                double ddddd1 = Float.valueOf( fieldToValidate );
                double ddddd2 = Float.valueOf( validationValue );
                response = ( ddddd1 > ddddd2 );
                break;
            case ALPHA_WITHOUT_SPECIAL_CHARACTERS:
                break;
            case MAXLENGTH:
                int lenmax = Integer.parseInt( validationValue );
                if( fieldToValidate.length() > lenmax )
                    response = false;
                break;
            case MINLENGTH:
                int lenmin = Integer.parseInt( validationValue );
                if( fieldToValidate.length() < lenmin )
                    response = false;
                break;
            case NUMERIC_INT:
                try
                {
                    Integer.parseInt( fieldToValidate );
                }
                catch( NumberFormatException | NullPointerException ex )
                {
                    response = false;
                }
                break;
            case NUMERIC_FLT:
                try
                {
                    Float.parseFloat( fieldToValidate );
                }
                catch( NumberFormatException | NullPointerException ex )
                {
                    response = false;
                }
                break;
            case NUMERIC_DBL:
                try
                {
                   Double.parseDouble( fieldToValidate );
                }
                catch( NumberFormatException | NullPointerException ex )
                {
                    response = false;
                }
                break;
        }//suich

        return response;
    }//


    /**
     * funcion que genera el string de error que
     * se muestra al usuario final
     * @param validationType
     * @return el mensaje de error segun el locale que se tenga establecido
     */
    private String createErrorMsg( int validationType, String fieldName , String validationValue)
    {
        String msg = fieldName+": ";

        if( null == validationValue)
            validationValue = "";

        /**
         * mensajes en ingles
         */
//        if( locale.equals( EN_LOC ) )
        
        switch( validationType )
            {
                case EMAIL:
                    
                    switch( locale )
                    {
                        case EN_LOC:
                            msg += "email format is invalid.";
                            break;
                        case ES_LOC:
                            msg += "El Formato de Email es invalido.";
                            break;
                    }//suich
                    
                    
                case NUMERIC:
                    switch( locale )
                    {
                        case EN_LOC:
                            msg+="The field must be numeric/integer.";
                            break;
                        case ES_LOC:
                            msg+="El Campo debe de ser numerico.";
                            break;
                    }//suich
                    
                    break;
                case ALPHA:
                    
                    switch( locale )
                    {
                        case EN_LOC:
                            msg+="The field must be alphanumeric string.";
                            break;
                        case ES_LOC:
                            msg += "El Campo debe de ser alfanumerico.";
                            break;
                    }//suich
                    
                    
                    break;
                case REQUIRED:
                    
                    switch( locale )
                    {
                        case EN_LOC:
                            msg+="The field is required.";
                            break;
                        case ES_LOC:
                            msg+="El Campo es requerido.";
                            break;
                    }//suich
                    
                    
                    break;
                case IPADDRESS:
                    switch( locale )
                    {
                        case EN_LOC:
                            msg+="( IPV4 ) ip format is not valid, recived value: "+validationValue;
                            break;
                        case ES_LOC:
                            msg+="El Formato de IP address (IPV4) no es valido, valor recibido: "+validationValue;
                            break;
                    }//suich
                    
                    break;
                case URL:
                    
                    switch( locale )
                    {
                        case EN_LOC:
                            msg+="URL format is not valid, recived value: "+validationValue;
                            break;
                        case ES_LOC:
                            msg+="El Formato de URL no es valido, valor recibido: "+validationValue;
                            break;
                    }//suich
                    
                    break;
                case SAMEAS_STR:
                case SAMEAS_INT:
                case SAMEAS_FLT:
                case SAMEAS_DBL:
                    
                    switch( locale )
                    {
                        case EN_LOC:
                            msg+="not the same value, recived value: "+validationValue;
                            break;
                        case ES_LOC:
                            msg+="No es el mismo valor, valor recibido "+validationValue;
                            break;
                    }//suich
                    
                    break;
                case MINIMUN_INT:
                case MINIMUN_FLT:
                case MINIMUN_DBL:
                    switch( locale )
                    {
                        case EN_LOC:
                            msg+="Minimal value permited is: "+validationValue;
                            break;
                        case ES_LOC:
                            msg+="Valor minimo permitido es: "+validationValue;
                            break;
                    }//suich
                    break;
                case MAXIMUN_INT:
                case MAXIMUN_FLT:
                case MAXIMUN_DBL:
                    switch( locale )
                    {
                        case EN_LOC:
                            msg+="Maximal value permited is: "+validationValue;
                            break;
                        case ES_LOC:
                            msg+="Valor maximo permitido es: "+validationValue;
                            break;
                    }//suich
                    break;
                case LESSTHAN_INT:
                case LESSTHAN_FLT:
                case LESSTHAN_DBL:
                     switch( locale )
                    {
                        case EN_LOC:
                            msg+="Value must be less than: "+validationValue;
                            break;
                        case ES_LOC:
                            msg+="El valor tiene que ser menor a: "+validationValue;
                            break;
                    }//suich
                    break;
                case MAYORTHAN_INT:
                case MAYORTHAN_FLT:
                case MAYORTHAN_DBL:
                    switch( locale )
                    {
                        case EN_LOC:
                            msg+="Value must be greater than: "+validationValue;
                            break;
                        case ES_LOC:
                            msg+="El valor tiene que ser mayor a: "+validationValue;
                            break;
                    }//suich
                    break;
                case ALPHA_WITHOUT_SPECIAL_CHARACTERS:
                    
                    switch( locale )
                    {
                        case EN_LOC:
                            msg+="special characters not permited: "+validationValue;
                            break;
                        case ES_LOC:
                            msg+="No se permiten caracteres especiales: "+validationValue;
                            break;
                    }//suich
                    
                    break;
                case MAXLENGTH:
                    switch( locale )
                    {
                        case EN_LOC:
                            msg+="Max length permited is: "+validationValue;
                            break;
                        case ES_LOC:
                            msg+="La longitud maxima permitida es: "+validationValue;
                            break;
                    }//suich
                    
                    break;
                case MINLENGTH:
                    switch( locale )
                    {
                        case EN_LOC:
                            msg+="Min length permited is: "+validationValue;
                            break;
                        case ES_LOC:
                            msg+="La longitud minima permitida es: "+validationValue;
                            break;
                    }//suich
                    break;
                case NUMERIC_INT:
                case NUMERIC_FLT:
                case NUMERIC_DBL:
                    switch( locale )
                    {
                        case EN_LOC:
                            msg+=validationValue+" is not numeric.";
                            break;
                        case ES_LOC:
                            msg+=validationValue+" no es numerico.";
                            break;
                    }//suich
                    
                    break;

            }//suich
        
        
        
        

        return msg;
    }//



    /**
     * getters and setters
     * @return
     */

    public List<ValidationParam> getParams() {
        return params;
    }

    public void setParams(List<ValidationParam> params) {
        this.params = params;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }
}//class

