/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ve.org.bcv.fts.exception;

import java.io.Serializable;
import java.sql.SQLException;
import javax.persistence.NoResultException;
import org.eclipse.persistence.exceptions.DatabaseException;
import ve.org.bcv.fts.util.AlmacenPropiedades;


/**
 *
 * @author furibe
 */
public class ExceptionTreatment implements Serializable {

    public void treat(Throwable throwable) throws ServicesException {
        System.out.println("throwable = " + throwable);
        
        if (throwable instanceof javax.persistence.PersistenceException) {
            final javax.persistence.PersistenceException de = (javax.persistence.PersistenceException) throwable;
            if (de.getCause() instanceof DatabaseException) {
                final DatabaseException databaseException = (DatabaseException) de.getCause();
                if (databaseException.getCause() instanceof SQLException) {
                    SQLException sQLException = (SQLException) databaseException.getCause();
                    System.out.println("pSQLException = " + sQLException.getErrorCode());
//                    throw new ServicesException(AlmacenPropiedades.getPropiedad(String.valueOf(sQLException.getErrorCode())));
                    if (AlmacenPropiedades.getPropiedad(String.valueOf(sQLException.getErrorCode())) != null && AlmacenPropiedades.getPropiedad(String.valueOf(sQLException.getErrorCode())).length() > 0){
                        throw new ServicesException(AlmacenPropiedades.getPropiedad(String.valueOf(sQLException.getErrorCode())));
                    } else {
                        throw new ServicesException(AlmacenPropiedades.getPropiedad("6550"));
                    }
                }
            } else if (de.getCause() instanceof NoResultException) {
                javax.persistence.NoResultException noResultException = (javax.persistence.NoResultException) throwable;
                String message = noResultException.getMessage();
                throw new ServicesException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
            }
            String message = de.getMessage();
            throw new ServicesException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
        } else if (throwable instanceof javax.persistence.NoResultException) {
            javax.persistence.NoResultException noResultException = (javax.persistence.NoResultException) throwable;
            String message = noResultException.getMessage();
            throw new ServicesException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
        } else if (throwable instanceof java.lang.UnsupportedOperationException) {
            java.lang.UnsupportedOperationException operationException = (java.lang.UnsupportedOperationException) throwable;
            System.out.println("de = " + operationException.getMessage());
            String message = operationException.getMessage();
            throw new ServicesException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
        } else if (throwable instanceof java.sql.SQLException) {
            java.sql.SQLException sQLException = (java.sql.SQLException) throwable;
            System.out.println("de = " + sQLException.getMessage());
            String message = sQLException.getMessage();
            throw new ServicesException(message);
        }else if (throwable instanceof io.jsonwebtoken.JwtException){
            if (throwable instanceof io.jsonwebtoken.ExpiredJwtException){
                io.jsonwebtoken.ExpiredJwtException expiredJwtException = (io.jsonwebtoken.ExpiredJwtException) throwable;
                System.out.println("ExpiredJwtException = " + expiredJwtException.getMessage());
                throw new ServicesException(AlmacenPropiedades.getPropiedad("ExpiredToken"));   
            } else if (throwable instanceof io.jsonwebtoken.SignatureException){
                io.jsonwebtoken.SignatureException signatureException = (io.jsonwebtoken.SignatureException) throwable;
                System.out.println("SignatureException = " + signatureException.getMessage());
                throw new ServicesException(AlmacenPropiedades.getPropiedad("SignatureException"));   
            }         
        }else if (throwable instanceof ServicesException){
             final ServicesException de = (ServicesException) throwable;
             throw new ServicesException(de.getMessage());
        }else if (throwable instanceof InterruptedException){          
            InterruptedException  interruptedException= (InterruptedException) throwable;
            System.out.println("de = " + interruptedException.getMessage());
            throw new ServicesException(AlmacenPropiedades.getPropiedad("InterruptedException"));
        } else if (throwable instanceof java.text.ParseException) {
            java.text.ParseException parseException = (java.text.ParseException) throwable;
            System.out.println("de = " + parseException.getMessage());
            String message = parseException.getMessage();
            throw new ServicesException(message);
        }
    }

}
