package app;

import java.util.Scanner;

import model.Validator;

public class TranslatorApp {
	public static void main(String[] args) {
		System.out.println("Welcome to the PigLatin Translator!");
		Scanner sc = new Scanner(System.in);
		String[] words = null;
		String choice = "";
		do {
			words = getWordArray(sc);
			parser(words);
			choice = Validator.getChoice(sc,
					"Translate another sentence?(y/n) :", "y", "n");
		} while (choice.equalsIgnoreCase("y"));
		sc.close();
	}

	private static String[] getWordArray(Scanner sc) {
		String[] words = getInput(sc, "Enter a line to be translated: ").split(
				" ");
		return words;
	}

	private static void parser(String[] words) {
		String[] parsedWords = new String[words.length];

		for (int i = 0; i < words.length; i++) {
			words[i].toLowerCase();
			if (startsWithVowel(words[i])) {
				parsedWords[i] = words[i] + "way";
			} else {
				parsedWords[i] = moveLetters(words[i]) + "ay";
			}
		}
		display(parsedWords);

	}

	private static boolean startsWithVowel(String s) {
		boolean vowelStart;
		switch (s.charAt(0)) {
		case 'a':
		case 'e':
		case 'i':
		case 'o':
		case 'u':
			vowelStart = true;
			break;
		default:
			vowelStart = false;
			break;
		}
		return vowelStart;
	}

	private static String moveLetters(String word) {
		String newWord = word;
		do {
			newWord = newWord.substring(1, newWord.length())
					+ newWord.charAt(0);
		} while (!startsWithVowel(newWord));

		return newWord;
	}

	private static void display(String[] parsedWords) {
		for (String word : parsedWords) {
			System.out.print(word + " ");
		}

	}

	private static String getInput(Scanner sc, String prompt) {
		boolean isValid = false;
		String sentence = "";
		while (isValid == false) {
			System.out.println(prompt);
			sentence = sc.nextLine().toLowerCase();
			if (!sentence.equals("")) {
				isValid = true;
			} else {
				System.out.println("Error no  sentence found! ");
			}
		}
		return sentence;

	}

}
