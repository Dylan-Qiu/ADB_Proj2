import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Parser {
	/*
	 * coverageNumber function parse the result content
	 * input is the string content
	 * output is total number of web search results
	*/
	public static int coverageNum(String content) throws JSONException{
		final JSONObject json = new JSONObject(content);
		JSONArray results = json.getJSONObject("d").getJSONArray("results");
    int nums = Integer.valueOf((String) results.getJSONObject(0).get("WebTotal"));
    return nums; 
	}
	
	/*
	 * urlsPerQuery parse result content
	 * return list of all the URLs in the content
	 */
	public static List<String> urlsPerQuery(String content) throws JSONException{
		List<String> urlList = new ArrayList<String>();
		final JSONObject json = new JSONObject(content);
		JSONArray results = json.getJSONObject("d").getJSONArray("results");
		JSONArray webs =  (JSONArray) results.getJSONObject(0).get("Web");
		for(int i = 0; i<4 && i<webs.length(); i++){
			urlList.add((String) webs.getJSONObject(i).get("Url"));
		}
    return urlList;
	}
}
