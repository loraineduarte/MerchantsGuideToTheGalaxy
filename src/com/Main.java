package com;

import sun.misc.IOUtils;

import java.io.*;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Main {
    
    public static void main(String[] args) throws IOException {
        
        Map<Character, Integer> romanToNumericMap = new HashMap<Character, Integer> () {
            {
                put('I', 1);
                put('V', 5);
                put('X', 10);
                put('L', 50);
                put('C', 100);
                put('D', 500);
                put('M', 1000);
            }
        };
        
        Map<String, Character> intergalaticToRomanMap = new HashMap<String, Character> () {
            {
                put("glob", 'I');
                put("prok", 'V');
                put("pish", 'X');
                put("tegj", 'L');
            }
        };
        
        Map<String, Double> intergalaticMetalsValuesMap = new HashMap<String, Double> () {
            {
                put("Silver", 17.0);
                put("Gold" , 14450.0);
                put("Iron" , 195.5);
            }
        };
    
        int linha=0;
        //file reader retirado de : https://www.caelum.com.br/apostila-java-orientacao-objetos/pacote-java-io/#15-6-uma-maneira-mais-facil-scanner-e-printstream
        InputStream is = new FileInputStream("./src/com/input.txt");
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
    
        String s = br.readLine(); // primeira linha
    
        while (s != null) {
            System.out.println(s);
            linha++;
            s = br.readLine();
        }
    
        br.close();
    }
    
}