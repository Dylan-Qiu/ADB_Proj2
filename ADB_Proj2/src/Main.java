import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import org.json.JSONException;

public class Main {

	public static void main(String[] args) throws IOException, JSONException {
		
		//get input
		System.out.println("Please type in required info like below, account key has been encoded:");
		System.out.println("<t_es> <t_ec> <host>");
		System.out.println("e.g. 0.6 100 health.com");
		Scanner console = new Scanner(System.in);
		if(!console.hasNext()){
			System.out.println("No Input");
		}
		double t_es = console.nextDouble();
		double t_ec = console.nextDouble();
		String host = console.next();
		
		System.out.println("Specificity threshold is " + t_es);
		System.out.println("Coverage threshold is " + t_ec);
		System.out.println("Host website is "+ host);
		
		String accountKey = Constant.accountKey;
		//URL Set
		HashSet<String> rootSet = new HashSet<String>();
		HashSet<String> branchSet = new HashSet<String>();
		//classify host and update URL set
		String catigory = Math.classify(accountKey, t_es, t_ec, host, rootSet, branchSet);
		System.out.println(host+": "+ catigory);
		
		//root doc and branch doc
		TreeMap<String, Integer> rootWords = new TreeMap<String, Integer>();
		TreeMap<String, Integer> branchWords = new TreeMap<String, Integer>();
		WordsExtract.wordsExtract(rootSet, rootWords);
		WordsExtract.wordsExtract(branchSet, branchWords);
		
		//Write to doc
		String fileName = "Root-" + host + ".txt";
		PrintWriter writer = new PrintWriter(fileName,"UTF-8");
		
		for(Map.Entry<String,Integer> entry: rootWords.entrySet()){
			String word = entry.getKey();
			Integer docFreq = entry.getValue();
			System.out.println(word + "->" + docFreq);
			writer.println(word+"#"+docFreq);
		}
		writer.close();
		if(branchWords.size() > 0){
			String fileNameBranch = Math.level1Node + "-" + host + ".txt";
			PrintWriter writerBranch = new PrintWriter(fileNameBranch,"UTF-8");
			for(Map.Entry<String,Integer> entry : branchWords.entrySet()){
				String word = entry.getKey();
				Integer docFreq = entry.getValue();
				writerBranch.println(word+"#"+docFreq);
			}
			writerBranch.close();
		}
		
	}
}