package stemmer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class RuleGenerator {

	public static ArrayList<String> generateCleanedSource(String sourceDir) {
		ArrayList<String> cleanedSource = new ArrayList<String>();

		File dir = new File(sourceDir);

		String[] stemFilesToConcatenate = dir.list();

		Scanner rover = null;

		for (int i = 0; i < stemFilesToConcatenate.length; i++) {
			File file = new File(sourceDir + '/' + stemFilesToConcatenate[i]);
			try {
				rover = new Scanner(file);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			ArrayList<String> parsedStem = new ArrayList<String>();

			while (rover.hasNextLine()) {
				String line = rover.nextLine();

				if (line.isEmpty()) {
					continue;
				} else if (line.charAt(0) == '#') {
					continue;
				} else {
					int commentLocation = line.indexOf('#');
					if (commentLocation != -1) {
						line = line.substring(0, commentLocation);
					}
					line = line.replaceAll("\t", " ");
					line = line.replaceAll(" +", " ");

					cleanedSource.add(line.trim());
				}

			}

			for (String s : parsedStem) {
				System.out.println(s);
			}
		}

		return cleanedSource;
	}

	public static HashMap<String, RuleBin> gernerateRuleBin(ArrayList<String> source) {

		HashMap<String, RuleBin> ruleDeclaration = new HashMap<String, RuleBin>();

		for (String rover : source) {
			if (rover.matches("^[A-Za-z0-9_]+ (\\([A-Za-z0-9_]+\\) )?\\{$")) {
				RuleBin rule = new RuleBin(rover);
				String[] strippedRuleDeclaration = rover.split("[ ]");
				ruleDeclaration.put(strippedRuleDeclaration[0], rule);
//				System.out.println(strippedRuleDeclaration[0]);
			}

		}

		return ruleDeclaration;
	}

	public static HashMap<String, Rule> generateRule(ArrayList<String> source) {

		HashMap<String, Rule> rule = new HashMap<String, Rule>();

		for (String rover : source) {

			String binName = "";
//			this is saved for the key of the hash
			if (rover.matches("^[A-Za-z0-9_]+ (\\([A-Za-z0-9_]+\\) )?\\{$")) {
				binName = rover + "_BIN";
			}

//			Regex that checks to see if the string is a rule
			String expression = "\\(\\u0026 \\(m>0\\) \\(\\u007C (\\(\\*[A-Z]+\\) \\(\\*[A-Z]+\\)| \\(\\*[A-Z]+\\))\\)\\)";
			if (rover.matches("^(\\(m>0\\) )?([A-Z]+ -> \\u03bb)$")
					|| rover.matches(expression + " ([A-Z]+ -> [A-Z]+) (\\([A-Za-z0-9_]+\\))")) {
//				TODO Figure out how to split the rule
				System.out.println(rover);
			}

		}

		return null;
	}

	public static ArrayList<Rule> generateRulesList(String sourceDir) {
		ArrayList<String> source = generateCleanedSource(sourceDir);
		ArrayList<Rule> rules = new ArrayList<Rule>();

		HashMap<String, RuleBin> bins = gernerateRuleBin(source);

		HashMap<String, Rule> rulesInBins = generateRule(source);

		// Some logic here

//		String[] ruleToParse = line.split("[ ]");
//		for (String rule : ruleToParse) {
//				
//			if (rule.length() == 0) 
//				continue;
//			
//			boolean valid = true;

//			for(int i=0; i <ruleToParse.length; i++) {
//				if(ruleToParse[i].contains("{")) {
//					valid = false;
//					break;
//				}
//			}

//			if (valid) rules.add(new Rule(rule));
//			else break;
//		}

		return rules;
	}

}
