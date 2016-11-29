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
public class RestException extends Exception {

    private Status statusCode;
    
    public RestException(String string, Status statusCode){
        super(string);
        this.statusCode = statusCode;
    }
    
    public Status getStatusCode(){
        return statusCode;
    }
    
    public void setStatusCode(Status statusCode){
        this.statusCode = statusCode;
    }
    /**
     * Creates a new instance of <code>RestException</code> without detail
     * message.
     */
    public RestException() {
    }

    /**
     * Constructs an instance of <code>RestException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public RestException(String msg) {
        super(msg);
    }
}
