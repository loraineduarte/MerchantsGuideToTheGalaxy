package com;

import sun.misc.IOUtils;

import java.io.*;
import java.net.URL;
import java.util.*;

public class Main {
    static Map<Character, Integer> romanToNumericMap = new HashMap<Character, Integer>() {
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
    
    Map<String, Character> intergalaticToRomanMap = new HashMap<String, Character>() {
        {
            put("glob", 'I');
            put("prok", 'V');
            put("pish", 'X');
            put("tegj", 'L');
        }
    };
    
    Map<String, Double> intergalaticMetalsValuesMap = new HashMap<String, Double>() {
        {
            put("Silver", 17.0);
            put("Gold", 14450.0);
            put("Iron", 195.5);
        }
    };
    
    static Map<String, String> questionsAndAnswers = new HashMap<String, String>();
    static double value = 0.0;
    
    public static void main(String[] args) throws IOException {
        
        List<String> romanValueMap = new LinkedList<>();
        List<String> numericValueMap = new LinkedList<>();
      
        
        InputStream is = new FileInputStream("./src/com/input.txt");
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        String s = br.readLine(); // primeira linha
        
        while (s != null) {
            romanValueMap.clear();
            value = 0.0;
            System.out.println(s);
            String arr[] = s.split("((?<=:)|(?=:))|( )");
            
            for (int i = 0; i < arr.length; i++) {
                if (arr[i].equals("glob")) {
                    romanValueMap.add("I");
                    
                } else if (arr[i].equals("prok")) {
                    romanValueMap.add("V");
                    
                } else if (arr[i].equals("pish")) {
                    romanValueMap.add("X");
                    
                } else if (arr[i].equals("tegj")) {
                    romanValueMap.add("L");
                    
                } else if (arr[i].equals("silver") | arr[i].equals("Silver")) {
                    romanValueMap.add("17.0");
                    
                } else if (arr[i].equals("gold") | arr[i].equals("Gold")) {
                    romanValueMap.add("14450");
                    
                } else if (arr[i].equals("iron") | arr[i].equals("Iron")) {
                    romanValueMap.add("195.5");
                    
                } else if (arr[i].equals("?")) {
                    if (romanValueMap.isEmpty()) {
                        questionsAndAnswers.put(s, "What?!?! I don't understand what are you saying, man!");
                        System.out.println("Entrou");
                    } else {
                        //pede pra analisar o mapa
                        questionsAndAnswers.put(s, "");
                        analiseMap(romanValueMap, s);
                    }
                }
            }
            s = br.readLine();
        }
        br.close();
        for(int i=0; i<questionsAndAnswers.size(); i++){
            System.out.println(i);
        }
    }
    
    public static void analiseMap(List<String> romanValueMap, String s) {
        int v = 0;
        int l = 0;
        
        for (int size = 0; size < romanValueMap.size(); size++) {
            if (romanValueMap.get(size).equals("V")) {
                v++;
            } else if (romanValueMap.get(size).equals("L")) {
                l++;
            }
        }
        if (v > 1 | l > 1) {
            questionsAndAnswers.replace(s, "We can't calculate that!");
            return;
        }
        //System.out.println("The Line is okay");
        
        
        //Ifs everything okay, we start to calculate
       
        for (int size = 0; size < romanValueMap.size(); size++) {
            if (romanValueMap.get(size).equals("17.0")) {
                value = value * 17;
                questionsAndAnswers.replace(s, String.valueOf(value));
                return;
                
            } else if (romanValueMap.get(size).equals("14450.0")) {
                value = value * 14450.0;
                questionsAndAnswers.replace(s, String.valueOf(value));
                return;
                
            } else if (romanValueMap.get(size).equals("195.5")) {
                value = value * 195.5;
                questionsAndAnswers.replace(s, String.valueOf(value));
                return;
            } else if (romanValueMap.get(size).equals("I")) {
                if (size == romanValueMap.size()) { //we can sum
                    questionsAndAnswers.replace(s, String.valueOf(value));
                    return;
                } else if (size+1 < romanValueMap.size()){
                    if ((romanValueMap.get(size+1) == "V") | (romanValueMap.get(size+1) == "X") ) { //we can sub
                        value = value - 1;
                    } else if ((romanValueMap.get(size+1) == "L") | (romanValueMap.get(size+1) == "C") ) { //we can sub
                        questionsAndAnswers.replace(s, "We can't calculate that!");
                        return;
                    } else {
                        value = value + 1;
                    }
                } else {
                    value = value + 1;
                }
            }
            else if (romanValueMap.get(size).equals("V")) {
                if (size == romanValueMap.size()) { //we can sum
                    questionsAndAnswers.replace(s, String.valueOf(value));
                    return;
                } else {
                    value = value + 5;
                }
                
            } else if (romanValueMap.get(size).equals("X")) {
                if (size == romanValueMap.size()) { //we can sum
                    questionsAndAnswers.replace(s, String.valueOf(value));
                    return;
                } else if (size+1 <= romanValueMap.size()){
                    if ( (romanValueMap.get(size + 1) == "L") | (romanValueMap.get(size+1) == "C")) { //we can sum
                        value = value - 10;
                    } else {
                        value = value + 10;
                    }
                } else {
                    value = value + 10;
                }
            } else if (romanValueMap.get(size).equals("L")) {
                if (size == romanValueMap.size()) { //we can sum
                    questionsAndAnswers.replace(s, String.valueOf(value));
                    return;
                } else if (size+1 <= romanValueMap.size()){
                    if ( (romanValueMap.get(size + 1) == "L")) { //we can sum
                        questionsAndAnswers.replace(s, "We can't calculate that!");
                        return;
                    } else {
                        value = value + 50;
                    }
                }  else {
                    value = value + 50;
                }
            }
           
        }
    }
}
