/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

import javax.ws.rs.core.Response.Status;

/**
 *
 * @author josephawwal
 */
public class FlightException extends Exception {

    
    private Status statusCode;
    private int errorCode;
    
    /**
     * Creates a new instance of <code>FlightException</code> without detail
     * message.
     */
    public FlightException(String string, Status statusCode, int errorCode){
    
        super(string);
        this.statusCode = statusCode;
        this.errorCode = errorCode;
    
    }
    
    public Status getStatusCode(){
        return statusCode;
    }
    
    public void setStatusCode(Status statusCode){
        this.statusCode = statusCode;
    }
    
    public void setErrorCode(int code){
        this.errorCode = code;
    }
    
    public int getErrorCode(){
        return this.errorCode;
    }

    /**
     * Constructs an instance of <code>FlightException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
   
}
