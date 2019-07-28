package com;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class InputReader {
	static Map<String, String> questionsAndAnswers = new HashMap<String, String>();
	static double value = 0.0;
	
	public static void readInputFile() throws IOException {
		InputStream is = new FileInputStream("./src/com/input.txt");
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		String s = br.readLine();
		
		while (s != null) {
			value = 0.0;
			readLine(s);
			s = br.readLine();
		}
		br.close();
		for (Map.Entry<String, String> entry : questionsAndAnswers.entrySet()) {
			System.out.println("- " + entry.getKey() + "\n " + entry.getValue());
		}
	}
	
	private static void readLine(String s) {
		List<String> romanValueMap = new LinkedList<>();
		String arr[] = s.split("((?<=:)|(?=:))|( )");
		
		for (int i = 0; i < arr.length; i++) {
			if (arr[i].equals(Constants.intergalactic_glob)) {
				romanValueMap.add(Constants.roman_numeral_I);
				
			} else if (arr[i].equals(Constants.intergalactic_prok)) {
				romanValueMap.add(Constants.roman_numeral_V);
				
			} else if (arr[i].equals(Constants.intergalactic_pish)) {
				romanValueMap.add(Constants.roman_numeral_X);
				
			} else if (arr[i].equals(Constants.intergalactic_tegj)) {
				romanValueMap.add(Constants.roman_numeral_L);
				
			} else if (arr[i].equals(Constants.intergalactic_silver)) {
				romanValueMap.add(Constants.intergalactic_string_value_silver);
				
			} else if (arr[i].equals(Constants.intergalactic_gold)) {
				romanValueMap.add(Constants.intergalactic_string_value_gold);
				
			} else if (arr[i].equals(Constants.intergalactic_iron)) {
				romanValueMap.add(Constants.intergalactic_string_value_iron);
				
			} else if (arr[i].equals(Constants.exclamation_point)) {
				if (romanValueMap.isEmpty()) {
					questionsAndAnswers.put(s, Constants.incorrect_input_answer);
				} else {
					questionsAndAnswers.put(s, "");
					analiseMap(romanValueMap, s);
				}
			}
		}
	}
	
	public static void analiseMap(List<String> romanValueMap, String s) {
		int v = 0;
		int l = 0;
		
		for (int size = 0; size < romanValueMap.size(); size++) {
			if (romanValueMap.get(size).equals(Constants.roman_numeral_V)) {
				v++;
			} else if (romanValueMap.get(size).equals(Constants.roman_numeral_L)) {
				l++;
			}
		}
		if (v > 1 | l > 1) {
			questionsAndAnswers.replace(s, "We can't calculate that!");
			return;
		}
		
		//Ifs everything okay, we start to calculate
		for (int size = 0; size < romanValueMap.size(); size++) {
			if (romanValueMap.get(size).equals(Constants.intergalactic_string_value_silver)) {
				value = value * Constants.intergalactic_value_silver;
				questionsAndAnswers.replace(s, String.valueOf(value));
				return;
				
			} else if (romanValueMap.get(size).equals(Constants.intergalactic_string_value_gold)) {
				value = value * Constants.intergalactic_value_gold;
				questionsAndAnswers.put(s, String.valueOf(value));
				return;
				
			} else if (romanValueMap.get(size).equals(Constants.intergalactic_string_value_iron)) {
				value = value * Constants.intergalactic_value_iron;
				questionsAndAnswers.replace(s, String.valueOf(value));
				return;
				
			} else if (romanValueMap.get(size).equals(Constants.roman_numeral_I)) {
				if (size + 1 < romanValueMap.size()) {
					if ((romanValueMap.get(size + 1) == Constants.roman_numeral_V) |
							(romanValueMap.get(size + 1) == Constants.roman_numeral_X)) {
						value = value - 1;
						
					} else if ((romanValueMap.get(size + 1) == Constants.roman_numeral_L) |
							(romanValueMap.get(size + 1) == Constants.roman_numeral_C)) {
						questionsAndAnswers.replace(s, "We can't calculate that!");
						return;
						
					} else {
						value = value + 1;
					}
				} else {
					value = value + 1;
					questionsAndAnswers.put(s, String.valueOf(value));
					return;
				}
			} else if (romanValueMap.get(size).equals(Constants.roman_numeral_V)) {
				if (size == romanValueMap.size()) {
					questionsAndAnswers.replace(s, String.valueOf(value));
					return;
				} else {
					value = value + 5;
				}
				
			} else if (romanValueMap.get(size).equals(Constants.roman_numeral_X)) {
				if (size == romanValueMap.size()) {
					questionsAndAnswers.replace(s, String.valueOf(value));
					return;
					
				} else if (size + 1 <= romanValueMap.size()) {
					if ((romanValueMap.get(size + 1).equals(Constants.roman_numeral_L)) |
							(romanValueMap.get(size + 1).equals(Constants.roman_numeral_C))) {
						value = value - 10;
						
					} else {
						value = value + 10;
					}
				} else {
					value = value + 10;
				}
			} else if (romanValueMap.get(size).equals(Constants.roman_numeral_L)) {
				if (size == romanValueMap.size()) {
					questionsAndAnswers.replace(s, String.valueOf(value));
					return;
					
				} else if (size + 1 <= romanValueMap.size()) {
					if ((romanValueMap.get(size + 1).equals(Constants.roman_numeral_L))) {
						questionsAndAnswers.replace(s, "We can't calculate that!");
						return;
						
					} else {
						value = value + 50;
					}
				} else {
					value = value + 50;
					questionsAndAnswers.replace(s, String.valueOf(value));
					return;
				}
			}
		}
	}
}
