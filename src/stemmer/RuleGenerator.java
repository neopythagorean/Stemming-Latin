package stemmer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class RuleGenerator {

	public static ArrayList<Rule> generateRulesList(String stemFile) {
		ArrayList<Rule> rules = new ArrayList<Rule>();

		File dir = new File(stemFile);

		String[] stemFilesToConcatenate = dir.list();

		Scanner rover = null;

		for (int i = 0; i < stemFilesToConcatenate.length; i++) {
			File file = new File(stemFile + '/' + stemFilesToConcatenate[i]);
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

					parsedStem.add(line.trim());
				}

//			String[] ruleToParse = line.split("[ ]");
//			for (String rule : ruleToParse) {
//					
//				if (rule.length() == 0) 
//					continue;
//				
//				boolean valid = true;

//				for(int i=0; i <ruleToParse.length; i++) {
//					if(ruleToParse[i].contains("{")) {
//						valid = false;
//						break;
//					}
//				}

//				if (valid) rules.add(new Rule(rule));
//				else break;
//			}
			}

			for (String s : parsedStem) {
				System.out.println(s);
			}
		}
		return null;
	}
}
