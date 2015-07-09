/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicparser;
import java.util.ArrayList;
/**
 *Handles creating a truth table
 * @author shatterwaltz
 */
public class TruthGenerator {
    private Statement wff;
    private ArrayList<Variable> varlist;
    private String truthTable="";
    public TruthGenerator(Parser p){
        wff=p.getWff();
        varlist=p.getVarList();
        for(int i=0;i<varlist.size();i++){
            truthTable+=varlist.get(i).getName()+"    |";
            
        }
        truthTable+=wff.toString()+"\n";
        tableGen();
    }
    
    private void tableGen(){
        boolean complete=false;
        while(!complete){
            for(int i=0;i<varlist.size();i++){
                if(varlist.get(i).evaluate()==false){
                    addTruthLine();
                    varlist.get(i).switchVal();
                    break;
                }else if(i<varlist.size()&&varlist.get(i).evaluate()==true){
                    addTruthLine();
                    while(i<varlist.size()&&varlist.get(i).evaluate()==true){
                        varlist.get(i).switchVal();
                        i++;
                    }
                    //Loop through varlist until hit false. switch everything along way, and the false
                    if(i<varlist.size())
                        varlist.get(i).switchVal();
                    else
                        complete=true;
                    break;
                }else{
                    addTruthLine();
                    complete=true;
                    break;
                }
            }
        }
    }
    private void addTruthLine(){
        for(int i=0;i<varlist.size();i++){
            if(varlist.get(i).evaluate())
                truthTable+="true |";
            else
                truthTable+="false|";
        }    
        truthTable+=wff.evaluate()+"\n";
    }
    
    public String getTable(){return truthTable;}
}
