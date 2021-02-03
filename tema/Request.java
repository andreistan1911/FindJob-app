package tema;

import java.text.DecimalFormat;

public class Request<K, V> implements Comparable<Request<K,V>>{
	private K key;
	private V value1, value2;
	private Double score;
	private static DecimalFormat df2 = new DecimalFormat("#.##");
	
	public Request(K key, V value1, V value2, Double score) {
		this.key = key;
		this.value1 = value1;
		this.value2 = value2;
		this.score = score;
	}
	
	public K getKey() {
		return key;
	}
	
	public V getValue1() {
		return value1;
	}
	
	public V getValue2() {
		return value2;
	}
	
	public Double getScore() {
		return score;
	}
	
	public String toString() {
		return "Key: " + key + " ; Value1: " + value1 + " ; Value2: " + value2 +
				 " ; Score: " + score;
	}
	
	public String myToString() {
		//Un toString pe care il utilizez in afisarea cererilor in cadrul ManagerPage-ului
		return 	"User: " + ((User)value1).toString() +
				" | Job: " + ((Job)key).name +
				" | Recruiter: " + ((Recruiter)value2).toString() +
				" | Score: " + df2.format(score);
	}
	
	public int compareTo(Request<K, V> req) {
		if(score - req.getScore() > 0)
			return -1;
		if(score - req.getScore() < 0)
			return 1;
		return 0;
	}
}
