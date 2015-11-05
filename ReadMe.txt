COMS E 6111 Advanced database project 2
a)     Your name and your partner's name and Columbia UNI
yq2183, YiYang Qiu
yn2287, YaZhuo Nan

b)     A list of all the files that you are submitting
Constant.java
getWordsLynx.java
Main.java
Math.java
Url.java
WordsExtract.java
commons-codec-1.7.jar
java-json.jar
README.txt


c)     A clear description of how to run your program (note that your project must compile/run under Linux in your CS account)
cd into project1 document
$ javac -cp \* *.java
$ java -cp \*: Main
Then insert into search words to start the whole process
for example,to classify health.com, we can set t_es = 0.6, t_ec = 100, 
0.6 100 health.com

d)     A clear description of the internal design of your project
Part 1 :
For part 1, the aim is to get the classification of an URL typed by the user. Input should also cover the threashold t_es[0, 1], t_ec[1, ...]. A low value for the specificity threshold τs will result in a coverage-based classification of the databases. Similarly, a low value for the coverage threshold τc will result in a specificity-based classification of the databases. We first ontain list of websites contain the site and also the keywordList contained all the classifier keywords as queries(Url.java, Parser.java). Constant.java is used to record all the keyword list and coverLevel's name. In the Math.java, we get the calculation of coverage number of the results in one category, then specialty number is obtained by  coverNum/D, D is the total number of websites in the database contained those classifications in a typical level(e.g. Computers, Health, Sports). After comparing the specLevel1 number with t_es, coverLevel1 number with t_ec, we can decide the first level's classification of the website searching. 
After that, we get deeper inside this classification(e.g. Computers) to see if the website is belonged to Hardware or Programming by doing the same calculation, and then return the result, print it in main.java. (e.g.Root/Computers/Hardware)

Part 2 :
For part 2, we build simple content summaries of Web databases, two txt files are outputed that include list of words together with their document frequencies. The Root-websiteName.txt contains summary of both sample root document frequency of each words in first level and the document frequency of each words in the second level. In Math.java, everytime we want to find out the classification of a certain website name, we need to first get its coverage number by merge the given lists of classifier words with the website name in the totalCount method. Before that, it is necessary to make the merge contents as a new query to gain Bing search result in Url.java. The coverage number is the total number of urls we gain through searching the combined query(website name + classifier words). We then get the top four results from the String of Urls to a List of Urls inside Parser.java's urlsPerQuery method. The top four urls are stored inside the rootSet in first level searching. The branchSet record the top four urls in the second level searching(same procedure). Everytime we get a new subset of classifier, we add this branchSet's url into the rootSet for part2's further calculation. All the set we build are aim to avoid url duplication while searching. 

After getting rootSet and branchSet of urls, we need to gain document frequency of each word appear in the urls. We build two hashMaps to keep map's key word and its document frequency. The Words.Extract file is used to extract each word inside a typical URL and obtain their document frequency. For each URL in the set, we do "getWordsLynx.runLynx(url)" to get a TreeSet of words in the url address. After iteration each word inside each url, we could obtain the frequency of documents provided. Finally we write all the results in the rootWords map and branchWords map to the external files. 

e)  A detailed description of your query-modification method (this is the core component of the project; see below)
In this assignment, we used the Qprober algorithm to handle automatic Hidden-Web Database Classification. The classifiers with their key words are given in the assignment, such as "String [] Computer = {"cpu", "java", "module", "multimedia", "perl", "vb", "agp card", "application windows", "applet code", "array code", "audio file", "avi file", "bios", "buffer code", "bytes code", "shareware", "card drivers", "card graphics", "card pc", "pc windows"}". Everytime we search a typical website, we combine the website information with the keywords list above to build a URL. By searching this URL in the Bing API, we gain lots of urls contain those information. The coverage number is the number of URL contain a given level's classifier(e.g. Computer). The specialty fraction is coverage/D, D is the total number of URLs in a given level's all classifiers (Computer, Health, Sports). Both of the two number we gain should be compared to the two threasholds(t_ec, t_es) we decide before searching. If both of them are larger than two thresholds, we could say the website we search belongs to one of the classifier provided(such as Computer). We then do the same thing for the second level classification.   

f) Your Bing Search Account Key (so we can test your project)
LYF/LYL2Y/GALrJN0iNTIlSuqb0I+kgnWqDi9GDvIDA


