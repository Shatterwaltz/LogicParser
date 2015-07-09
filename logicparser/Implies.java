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
public class Implies implements Statement{
    Statement s1;
    Statement s2;
    public Implies(Statement s1, Statement s2){
        this.s1=s1;
        this.s2=s2;
    }
    public boolean evaluate(){
        if(s1.evaluate()==false||s2.evaluate())
            return true;
        else
            return false;
    }
    public String toString(){
        return "(" + s1.toString() + ") Implies (" + s2.toString() + ")";
    }
}
