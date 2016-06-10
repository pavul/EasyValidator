/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easyvalidator;

import com.mexicode.CustomValidation;
import com.mexicode.EasyValidator;
import com.mexicode.ValidationParam;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author pavulzavala
 */
public class Validator {

    /**
     * @param args the command line arguments
     */
    public static void main( String[ ] args ) 
    {
    
    List< ValidationParam > valParams = new ArrayList<>();
    
    
    valParams.add( new ValidationParam( "nombre" , "alfonso" , EasyValidator.REQUIRED  ) );
    valParams.add( new ValidationParam( "edad" , "23" , "15", EasyValidator.MAXLENGTH ) );
    valParams.add( new ValidationParam( "carrera" , "10" , "20" , EasyValidator.MAXIMUN_INT ) );
    
//    int []pin = new int[ 10 ];
//                pin[ 0 ] = 10;
//                pin[ 1 ] = 15;
//                pin[ 2 ] = 20;
                
    valParams.add( new ValidationParam( 
            "salon" , 
            "contabilidad" ,
            "20" ,
            "No pertenece salon numero: ",
            new CustomValidation() {

        @Override
        public boolean customValidate(
                String fieldName, 
                String toValidate, 
                String validationValue, 
                String errorMsg) 
        {
         
                int []pin = new int[ 10 ];
                pin[ 0 ] = 10;
                pin[ 1 ] = 15;
                pin[ 2 ] = 20;
            
            for (int i = 0 ; i < pin.length ; i++) 
            {
             if( toValidate.equals( String.valueOf( pin[ i ] ) ) )
                 return true;   
            }
            
            return false;
        }//
       
    },
            EasyValidator.CUSTOM ) );
    
    EasyValidator ev = new EasyValidator( );
    ev.setParams( valParams );
    ev.validate( );
    
    System.err.println( " el error fue: " + ev.getErrorMsg() );
    
    
    }//main
    
    
    
    
    
    
    
    
}//class
