import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.json.JSONException;

public class Math {
	static String level1Node = ""; 
	//totalCount calculates the total number of results of one category
	public static double totalCount(String accountKey, String host, String [] keywords, HashSet<String> hs) throws IOException, JSONException{
		double count = 0;
		for(int i = 0; i<keywords.length; i++){
			String content = Url.getContent(accountKey, host, keywords[i]);
			//Test
			List<String> urls = Parser.urlsPerQuery(content);
			for(int j = 0; j<urls.size(); j++){
				hs.add(urls.get(j));
			}
			int num = Parser.coverageNum(content);
			count += (double)num;
		}
		return count;
	}
	
	//classify function returns the category of a host
	//rootSet and branchSet are updated with urls
	public static String classify(String accountKey, double t_es, double t_ec, String host, HashSet<String> rootSet, HashSet<String> branchSet) throws IOException, JSONException{
		double [] coverLevel1 = new double[3];
		
		coverLevel1[0] = Math.totalCount(accountKey, host, Constant.Computer, rootSet);
		coverLevel1[1] = Math.totalCount(accountKey, host, Constant.Health, rootSet);
		coverLevel1[2] = Math.totalCount(accountKey, host, Constant.Sports, rootSet);
		
		double D = coverLevel1[0] + coverLevel1[1] + coverLevel1[2];
		double [] specLevel1 = new double[3];
		for(int i = 0; i<coverLevel1.length; i++){
			specLevel1[i] = coverLevel1[i]/D;
		}

		//Result
		String res = "Root";
		List<Integer> interRes = new ArrayList<Integer>();
		for(int i = 0; i<coverLevel1.length; i++){
			if(coverLevel1[i] >= t_ec && specLevel1[i] >= t_es){
				interRes.add(i);
			}
		}
		if(interRes.size() == 1){
			res = res + "/" + Constant.level1[interRes.get(0)];
			double [] coverLevel2 = new double[2];
			int level1Choice = interRes.get(0);
			level1Node = Constant.level1[level1Choice]; 
			if(level1Choice == 0){
				coverLevel2[0] = Math.totalCount(accountKey, host, Constant.Hardware, branchSet);
				coverLevel2[1] = Math.totalCount(accountKey, host, Constant.Programming, branchSet);		
			}else if(level1Choice == 1){
				coverLevel2[0] = Math.totalCount(accountKey, host, Constant.Fitness, branchSet);
				coverLevel2[1] = Math.totalCount(accountKey, host, Constant.Diseases, branchSet);	
			}else if(level1Choice == 2){
				coverLevel2[0] = Math.totalCount(accountKey, host, Constant.Basketball, branchSet);
				coverLevel2[1] = Math.totalCount(accountKey, host, Constant.Soccer, branchSet);
			}
			System.out.println(Arrays.toString(coverLevel2));
			double [] specLevel2 = new double[2];
			double D2 = coverLevel2[0] + coverLevel2[1];
			for(int i = 0; i<coverLevel2.length; i++){
				specLevel2[i] = coverLevel2[i]/D2;
			}
			System.out.println(Arrays.toString(specLevel2));
			List<Integer> interRes2 = new ArrayList<Integer>();
			for(int i = 0; i<coverLevel2.length; i++){
				if(coverLevel2[i] >= t_ec && specLevel2[i] >= t_es){
					interRes2.add(2*level1Choice + i);
				}
			}
			if(interRes2.size() == 1){
				res = res + "/" + Constant.level2[interRes2.get(0)];
			}
			System.out.println("before adding: rootSet size is "+rootSet.size() + ", branchSet size is "+branchSet.size());
			rootSet.addAll(branchSet);
			System.out.println("After adding: rootSet size is "+rootSet.size() + ", branchSet size is "+branchSet.size());
		}
		return res;
	}
	
}
