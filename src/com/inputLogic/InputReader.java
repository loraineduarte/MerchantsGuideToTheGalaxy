package com.inputLogic;

import com.util.Constants;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class InputReader {
	static Map<String, String> questionsAndAnswers = new HashMap<String, String>();
	
	public static void readInputFile() throws IOException {
		InputStream is = new FileInputStream("./src/com/util/input.txt");
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		String s = br.readLine();
		
		while (s != null) {
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
					InputProcessor.analiseMap(romanValueMap, s);
				}
			}
		}
	}
}
