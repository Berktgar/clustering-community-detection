package nearest_neightbor_clustering;

import java.util.ArrayList;
import java.util.List;

public class Cluster {

	private String name;
	private List<Pattern> patterns;

	public Cluster(String name){
		this.name = name;
		this.patterns = new ArrayList<Pattern>();
	}
	
	public void addPattern(Pattern p){
		patterns.add(p);
	}
	
	public String toString(){
		String result = String.format("%s:{ ", name);
		for(int i = 0; i < patterns.size(); i++){
			String separator = (i == patterns.size() - 1)? " }":", ";
			result += String.format("%s%s", patterns.get(i),separator);
		}
		return result;
	}
	
	public List<Pattern> getPatterns(){
		return patterns;
	}
	
	public static void main(String[] args) {
		Pattern p1 = new Pattern("P1", 2d,4.5d);
		Pattern p2 = new Pattern("P2", 5d,8d);
		
		Cluster c = new Cluster("C1");
		c.addPattern(p1);
		c.addPattern(p2);
		
		System.out.println(c);
	}
	
	public boolean equals(Object o){
		if(o instanceof Cluster)
			return equals((Cluster)o);
		else
			return false;
	}
	
	public boolean equals(Cluster c){
		return name.equals(c.getName());
	}

	public String getName() {
		return name;
	}

}
