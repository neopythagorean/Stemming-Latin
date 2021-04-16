package stemmer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RuleGenerator {

	public static final String RULEBIN_REGEX = "^([A-Za-z0-9_]+) (\\([A-Za-z0-9_]+\\) )?\\{$";
	public static final Pattern RULEBIN_PATTERN = Pattern.compile(RULEBIN_REGEX);
	
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
			if (rover.matches(RULEBIN_REGEX)) {
				Matcher m = RULEBIN_PATTERN.matcher(rover);
				m.matches();
				RuleBin rule = new RuleBin(m.group(1));
				ruleDeclaration.put(m.group(1), rule);
			}

		}

		return ruleDeclaration;
	}

	public static void generateRules(ArrayList<String> source, HashMap<String, RuleBin> ruleBins) {

		HashMap<String, Rule> rule = new HashMap<String, Rule>();
		
		RuleBin currentBin = null;
		
		for (String line : source) {
			if (line.equals("}")) {
				currentBin = null;
			} else if (line.matches(RULEBIN_REGEX)) {
				Matcher m = RULEBIN_PATTERN.matcher(line);
				m.matches();
				currentBin = ruleBins.get(m.group(1));
				// Check if there's a pointer to a bin in parenthesis
				if (m.group(2) != null) {
					String transition = m.group(2).substring(1, m.group(2).length()-2);
					currentBin.defaultTransition = ruleBins.get(transition);
				}
			} else if (line.contains("->")) {
				Rule r = new Rule(line, ruleBins, currentBin);
				currentBin.rules.add(r);
			} else {
				System.out.println("Bad Source Line");
			}
		}
	}

	public static HashMap<String, RuleBin> generateRulesList(String sourceDir) {
		ArrayList<String> source = generateCleanedSource(sourceDir);
		ArrayList<Rule> rules = new ArrayList<Rule>();

		HashMap<String, RuleBin> bins = gernerateRuleBin(source);
		
		generateRules(source, bins);
		
		return bins;
	}

}
