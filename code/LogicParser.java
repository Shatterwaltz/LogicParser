/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic.parser;
import java.util.Scanner;
import java.util.ArrayList;
/**
 *
 * @author shatterwaltz
 */
public class LogicParser {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println("& - and\n/ - or\n! - not\n> - implies\nInput must be fully disambiguated with parentheses.\nUse only one letter for variables.\n*******************\nEnter well formed formula:\n");
        Scanner s = new Scanner(System.in);
        Parser p = new Parser(s.nextLine());
        System.out.println("I interpreted: " + p.getWff());
        for(int i=0;i<p.getVarList().size();i++)
            System.out.println(p.getVarList().get(i));
    }
}
