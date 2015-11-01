//Tree Node 
public class Node {
	private String tag;
	private double spce = 0.0;
	private double cov = 0.0;
	
	public Node(String s){
		this.tag = s;
	}
	
	public String getTag(){
		return this.tag;
	}
	public double getSpec(){
		return this.spce;
	}
	public double getCov(){
		return this.cov;
	}
	
	public void setSpec(double x){
		this.spce = x;
	}
	public void setCov(double x){
		this.cov = x;
	}

}
