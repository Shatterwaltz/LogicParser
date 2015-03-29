/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic.parser;

/**
 *
 * @author shatterwaltz
 */
public class Or implements Statement{
    Statement s1;
    Statement s2;
    public Or(Statement s1, Statement s2){
        this.s1=s1;
        this.s2=s2;
    }
    public boolean evaluate(){
        if(s1.evaluate()||s2.evaluate())
            return true;
        else
            return false;
    }
    public String toString(){
        return "(" + s1.toString() + ") Or (" + s2.toString() + ")";
    }
}
