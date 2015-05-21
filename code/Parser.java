/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicparser;
import java.util.ArrayList;
/**
 * Changes string into logical expression probably
 * @author shatterwaltz
 */
public class Parser {
    private Statement wff;
    private int position=0;
    private ArrayList<Variable> varlist = new ArrayList(0);
    public Parser(String data){
       wff=parse(data);
    }
    
    public Statement getWff(){return wff;}
    
    private Statement parse(String input){
        position = 0;
        char cur;
        cur=input.charAt(position);
        if(cur=='('){
            position++;
            String temp1=parenParser(input.substring(position));
            if(position>=input.length()){
                return parse(temp1);
            }
            else{
                char operator = input.charAt(position);
                position+=2;
                String temp2=parenParser(input.substring(position));
                switch(operator){
                    case '&':
                        return new And(parse(temp1), parse(temp2));
                    case '/':
                        return new Or(parse(temp1), parse(temp2));
                    case '>':
                        return new Or(new Not(parse(temp1)), parse(temp2));
                }
                        
                        
            }
        }else if(cur=='!'){
            position+=2;
            return new Not(parse(parenParser(input.substring(position))));
        }else{
            boolean exists=false;
            String name=""+cur;
            for(int i=0;i<varlist.size();i++){
                if(name.equals(varlist.get(i).getName()))
                    return varlist.get(i);
            }
            varlist.add(new Variable(cur));
            return varlist.get(varlist.size()-1);
        }
        
        
        return null;
    }
    private String parenParser(String input){
        int parencount=1;
        //tracks parenparser's progress through the substring
        int parenpos=0;
        char cur;
        do{
            cur=input.charAt(parenpos);
            if(cur=='(')
                parencount++;
            else if(cur==')')
                parencount--;
            
            parenpos++;
        }while(parencount>0);
        //shifts the parser's position marker over by how much was processed by parenparser
       
       
        position+=parenpos;
        return input.substring(0, parenpos-1);
    }
    public ArrayList<Variable> getVarList(){return varlist;}
}
