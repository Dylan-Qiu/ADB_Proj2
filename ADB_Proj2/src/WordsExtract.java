import java.util.HashSet;
import java.util.Iterator;
import java.util.TreeMap;
import java.util.TreeSet;


public class WordsExtract {
	//wordsExtract takes a set of URL strings and parse each of them
	//main unique word and its document frequency in TreeMap
	public static void wordsExtract(HashSet<String> hs, TreeMap<String, Integer> docFreq){
		if(hs == null || hs.size() == 0){
			return;
		}
		Iterator<String> it = hs.iterator();
		while(it.hasNext()){
			String url = it.next();
			System.out.println("Processing url: " + url);
			TreeSet<String> keywordsPerWeb = getWordsLynx.runLynx(url);
			Iterator<String> keysIt = keywordsPerWeb.iterator();
			while(keysIt.hasNext()){
				String word = keysIt.next();
				if(!docFreq.containsKey(word)){
					docFreq.put(word, 1);
				}else{
					docFreq.put(word, docFreq.get(word)+1);
				}
			}
			
		}
		return;
	}
}
