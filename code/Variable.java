/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic.parser;

/**
 * Evaluated in all combinations of false/true with other variables.
 * Modified by other statements such as Not, And, or Implies.
 * @author shatterwaltz
 */
public class Variable implements Statement{
    private boolean value=false;
    private String name="";
    public Variable(char name){
        this.name+=name;
    }
    public void switchVal(){
        if(value)
            value=false;
        else
            value=true;
    }
    public void setVal(boolean newVal){value=newVal;}
    
    public boolean evaluate(){return value;}
    public String toString(){
        return name;
    }
}
