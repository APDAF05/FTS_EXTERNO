/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ve.org.bcv.fts.exception;

/**
 *
 * @author jyanez
 */
public class SimpleExceptionBean {
    
    private String message;
    private String code;
 
    public SimpleExceptionBean() {
    }

    public SimpleExceptionBean(String message, String code) {
        this.message = message;
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    
    public SimpleExceptionBean(String message) {
        this.message = message;
    }
 
    public String getMessage() {
        return message;
    }
    
}
