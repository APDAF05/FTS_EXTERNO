/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ve.org.bcv.fts.exception;

import org.eclipse.persistence.exceptions.ExceptionHandler;

/**
 *
 * @author furibe
 */
public class JpaExceptionHandler extends Exception implements ExceptionHandler{

    @Override
    public Object handleException(RuntimeException re) {
        throw new UnsupportedOperationException(re); //To change body of generated methods, choose Tools | Templates.
    }
    
}
