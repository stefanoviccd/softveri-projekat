package rs.ac.bg.fon.ps.biblioteka.communication;


import java.io.Serializable;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Dragana Stefanovic
 */
public class Request implements Serializable{
    private int operation; //tip operacije
    private Object argument;

    public Request() {
    }

    public Request(int operacija, Object argument) {
        this.operation = operacija;
        this.argument = argument;
    }

    public Object getArgument() {
        return argument;
    }

    public int getOperation() {
        return operation;
    }

    public void setArgument(Object argument) {
        this.argument = argument;
    }

    public void setOperation(int operation) {
        this.operation = operation;
    }
    
    
    
    
}
