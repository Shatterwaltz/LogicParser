/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicparser;

/**
 *
 * @author shatterwaltz
 */
public class Not implements Statement{
    Statement s;
    public Not(Statement s){
        this.s=s;
    }
    public boolean evaluate(){
        if(s.evaluate())
            return false;
        else
            return true;
    }
    public String toString(){
        return "Not("+s.toString()+")";
    }
}
