/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic.parser;
import java.util.ArrayList;
/**
 * Changes string into logical expression probably
 * @author shatterwaltz
 */
public class Parser {
    private Statement wff;
    static ArrayList<Variable> varlist = new ArrayList(0);
    public Parser(String data){
        wff=parse(data);
    }
    
    public Statement getWff(){return wff;}
    private Statement parse(String input){
    int pos=0;
    int parencount=0;
    char cur;
    cur=input.charAt(pos);
        do{
        if(cur=='('){
            String temp1=parenParser(input, pos);
            if(pos<input.length()){
                pos++;
                do{
                    cur=input.charAt(pos);
                    if(cur=='(')
                        parencount++;
                    else if(cur==')')
                        parencount--;
                    pos++;
                    
                }while(parencount>0);
                pos++;
                cur=input.charAt(pos);
                char operator = cur;
                String temp2 = parenParser(input, pos);
                System.out.println(temp1+","+temp2);
                if(operator=='&'){
                    return new And(parse(temp1), parse(temp2));
                }
                else if(operator=='/'){
                    return new Or(parse(temp1), parse(temp2));
                }
                else if(operator=='>'){
                    return new Implies(parse(temp1), parse(temp2));
                }
            }
            
        }
        if(cur=='!'){
            return new Not(parse(parenParser(input, pos)));
        }
        else{
            //Check if variable already exists in varlist. If not, add to list.
            //boolean varExists=false;
            Variable v=new Variable(cur);
            
            return v;
        }
        }while(pos<input.length());
    }
        
    //Parses through substrings encased in () or whatever
    //Looks like it can handle things like ((~~~)~~) too which is cool
    private String parenParser(String input, int pos){
        int parenCount=0;
        char cur=input.charAt(pos);
        String temp="";
            pos++;
            do{
                cur=input.charAt(pos);
                if(cur=='('){
                    parenCount++;
                    if(parenCount!=1)
                        temp+=cur;
                }
                else if(cur==')'){
                    parenCount--;
                    if(parenCount!=0)
                        temp+=cur;
                }
                else
                    temp+=cur;
                pos++;
            }while(parenCount>0);
            
        return temp;
    }
    public ArrayList<Variable> getVarList(){return varlist;}
}
