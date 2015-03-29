/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic.parser;

/**
 * Changes string into logical expression probably
 * @author shatterwaltz
 */
public class Parser {
    private String input;
    int pos=0;
    char cur;
    
    public Parser(String data){
        input=data;
        cur=input.charAt(pos);
        System.out.println(data);
    }
    public Statement parse(){
        do{
        if(cur=='('){
            int start=pos;
            //probably have to decrement pos because I increment it to do something with parens
            //just trust me it maybe works
            pos--;
            String temp1=parenParser(input);
            //System.out.println(temp1);
            if(pos<input.length()){
                cur=input.charAt(pos);
                char operator = cur;
                String temp2 = parenParser(input);
                //System.out.println(temp2);
                System.out.println("op="+operator);
                if(operator=='&'){
                    System.out.println("anded it!");
                    return new And(new Parser(temp1).parse(), new Parser(temp2).parse());
                }
                else if(operator=='/'){
                    return new Or(new Parser(temp1).parse(), new Parser(temp2).parse());
                }
                else if(operator=='>'){
                    return new Implies(new Parser(temp1).parse(), new Parser(temp2).parse());
                }
            }
            
        }
        if(cur=='!'){
            return new Not(new Parser(parenParser(input)).parse());
        }
        else{
            return new Variable();
        }
        }while(pos<input.length());
    }
    //Parses through substrings encased in () or whatever
    //Looks like it can handle things like ((~~~)~~) too which is cool
    private String parenParser(String input){
        int parenCount=0;
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
    
}
