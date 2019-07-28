package com.inputLogic;

import com.util.Constants;

import java.util.List;
import java.util.Map;

public class InputProcessor {
	
	
	public static Map<String, String> analiseMap(List<String> romanValueMap, String s, Map<String, String> questionsAndAnswers) {
		int v = 0;
		int l = 0;
		double value = 0.0;
		
		for (int size = 0; size < romanValueMap.size(); size++) {
			if (romanValueMap.get(size).equals(Constants.roman_numeral_V)) {
				v++;
			} else if (romanValueMap.get(size).equals(Constants.roman_numeral_L)) {
				l++;
			}
		}
		if (v > 1 | l > 1) {
			InputReader.questionsAndAnswers.replace(s, "We can't calculate that!");
			return questionsAndAnswers;
		}
		
		//Ifs everything okay, we start to calculate
		for (int size = 0; size < romanValueMap.size(); size++) {
			if (romanValueMap.get(size).equals(Constants.intergalactic_string_value_silver)) {
				value = value * Constants.intergalactic_value_silver;
				InputReader.questionsAndAnswers.replace(s, String.valueOf(value));
				return questionsAndAnswers;
				
			} else if (romanValueMap.get(size).equals(Constants.intergalactic_string_value_gold)) {
				value = value * Constants.intergalactic_value_gold;
				InputReader.questionsAndAnswers.put(s, String.valueOf(value));
				return questionsAndAnswers;
				
			} else if (romanValueMap.get(size).equals(Constants.intergalactic_string_value_iron)) {
				value = value * Constants.intergalactic_value_iron;
				InputReader.questionsAndAnswers.replace(s, String.valueOf(value));
				return questionsAndAnswers;
				
			} else if (romanValueMap.get(size).equals(Constants.roman_numeral_I)) {
				if (size + 1 < romanValueMap.size()) {
					if ((romanValueMap.get(size + 1) == Constants.roman_numeral_V) |
							(romanValueMap.get(size + 1) == Constants.roman_numeral_X)) {
						value = value - 1;
						
					} else if ((romanValueMap.get(size + 1) == Constants.roman_numeral_L) |
							(romanValueMap.get(size + 1) == Constants.roman_numeral_C)) {
						InputReader.questionsAndAnswers.replace(s, "We can't calculate that!");
						return questionsAndAnswers;
						
					} else {
						value = value + 1;
					}
				} else {
					value = value + 1;
					InputReader.questionsAndAnswers.put(s, String.valueOf(value));
					return questionsAndAnswers;
				}
			} else if (romanValueMap.get(size).equals(Constants.roman_numeral_V)) {
				if (size == romanValueMap.size()) {
					InputReader.questionsAndAnswers.replace(s, String.valueOf(value));
					return questionsAndAnswers;
				} else {
					value = value + 5;
				}
				
			} else if (romanValueMap.get(size).equals(Constants.roman_numeral_X)) {
				if (size == romanValueMap.size()) {
					InputReader.questionsAndAnswers.replace(s, String.valueOf(value));
					return questionsAndAnswers;
					
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
					InputReader.questionsAndAnswers.replace(s, String.valueOf(value));
					return questionsAndAnswers;
					
				} else if (size + 1 <= romanValueMap.size()) {
					if ((romanValueMap.get(size + 1).equals(Constants.roman_numeral_L))) {
						InputReader.questionsAndAnswers.replace(s, "We can't calculate that!");
						return questionsAndAnswers;
						
					} else {
						value = value + 50;
					}
				} else {
					value = value + 50;
					InputReader.questionsAndAnswers.replace(s, String.valueOf(value));
					return questionsAndAnswers;
				}
			}
		}
		return questionsAndAnswers;
	}

}
