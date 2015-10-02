package nearest_neightbor_clustering;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NearestNeightborClustering {

	private int threshold;
	private Map<String, Cluster> clusters;
	
	public NearestNeightborClustering(int threshold) {
		this.threshold = threshold;
		clusters = new HashMap<String, Cluster>();
	}
	
	public List<Cluster> partition(Pattern[] patterns){
		//the first pattern is put in a cluster by itself
		Cluster c = new Cluster("C0");
		c.addPattern(patterns[0]);
		clusters.put(patterns[0].getName(), c);
		
		for(int i = 1; i < patterns.length; i++){
			double minDistance = Double.MAX_VALUE;
			String name = null;
			int k = -1;
			for(int j = 0; j < i; j++){
				double distance = distance(patterns[i],patterns[j]);
				if(distance < minDistance){
					minDistance = distance;
					name = patterns[j].getName();
					k = i;
				}
			}
			if(minDistance <= threshold){
				addPatternToCluster(name, patterns[k]);
			}else{
				c = new Cluster("C"+i);
				c.addPattern(patterns[i]);
				clusters.put(patterns[i].getName(), c);
			}
			System.out.println("***");
			for(Cluster c1: clusters.values())
				System.out.println(c1);
			System.out.println("***");
		}
		
		return  new ArrayList<Cluster>(clusters.values());
	}

	private void addPatternToCluster(String name, Pattern p) {
		Cluster cluster = clusters.get(name);
		if(cluster != null)
			cluster.addPattern(p);
		else{
			for(Cluster c: clusters.values()){
				for(Pattern pattern: c.getPatterns())
					if(pattern.getName().equals(name)){
						c.addPattern(p);
						return;
					}
			}
		}
	}

	private double distance(Pattern pattern, Pattern pattern2) {
		double distance = 0;
		
		for(int i = 0; i < pattern.getPatternDimension(); i++)
			distance += Math.pow(pattern.getValue(i) - pattern2.getValue(i), 2); 
		
		distance = Math.sqrt(distance);
		return distance;
	}
	
	public static void main(String[] args) {
		
		NearestNeightborClustering nearest = new NearestNeightborClustering(4);
		
		Pattern[] patterns = new Pattern[8];

		patterns[0] = new Pattern(0,"A1", 2,10);
		patterns[1] = new Pattern(1,"A2", 2,5);
		patterns[2] = new Pattern(2,"A3", 8,4);
		patterns[3] = new Pattern(3,"A4", 5,8);
		patterns[4] = new Pattern(4,"A5", 7,5);
		patterns[5] = new Pattern(5,"A6", 6,4);
		patterns[6] = new Pattern(6,"A7", 1,2);
		patterns[7] = new Pattern(7,"A8", 4,9);
		
		List<Cluster> partition = nearest.partition(patterns);
		
	}
	
}
