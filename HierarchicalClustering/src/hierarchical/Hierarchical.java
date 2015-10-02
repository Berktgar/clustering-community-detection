package hierarchical;

import java.util.ArrayList;
import java.util.List;

public class Hierarchical {

	private List<Cluster> clusters;
	private double[][] distanceMatrix;
	private LinkageCriterion criterion;
	private Linkage linkage;
	
	public Hierarchical(LinkageCriterion criterion) {
		this.clusters = new ArrayList<Cluster>();
		this.criterion = criterion;
		switch(criterion){
		case SINGLE: this.linkage = new SingleLinkage();
					 break;
		case COMPLETE: this.linkage = new CompleteLinkage();
					   break;
		case AVERAGE:  this.linkage = new AverageLinkage();
						break;
		}
	}
	
	public List<Cluster> partition(double[][] distanceMatrix){
		createClusterForEachPattern(distanceMatrix);
		
		createDistanceMatrix(distanceMatrix);
		String result = String.format("D \t K \t CLUSTERS");
		System.out.println(result);
		
		printClusters(0);
		
		clustering();
		
		return clusters;
	}
	
	private void createDistanceMatrix(double[][] distanceMatrix) {
		this.distanceMatrix = distanceMatrix;
	}

	private void createClusterForEachPattern(double[][] distanceMatrix) {
		char ch = 'A';
		
		for(int i = 0; i < distanceMatrix.length; i++){
			Pattern p = new Pattern(i,String.format("%c",ch++));
			Cluster c = new Cluster(String.format("C%d", i));
			c.addPattern(p);
			clusters.add(c);
		}
			
	}

	public List<Cluster> partition(Pattern...patterns){
		
		createClusterForEachPattern(patterns);
		
		createDistanceMatrix(patterns);
		
		String result = String.format("D \t K \t CLUSTERS");
		System.out.println(result);
		printClusters(0);
		
		clustering();
		
		return clusters;
	}


	private void clustering() {
		int d = 0;
		
		while(clusters.size() > 1){
			d++;
			for(int i = 0; i < clusters.size(); i++){
				List<Integer> clustersToMerge = new ArrayList<Integer>();
				for(int j = i + 1; j < clusters.size(); j++){
					if(toMerge(clusters.get(i),clusters.get(j),d))
						clustersToMerge.add(j);
				}
				merge(i,clustersToMerge);
			}
			printClusters(d);
		}
		
	}

	private void merge(int i, List<Integer> clustersToMerge) {
		if(clustersToMerge.size() == 0)	return;
		
		Cluster c = clusters.get(i);
		String result = String.format("--> Merged %s with ", clusters.get(i));
		
		
		for(int index: clustersToMerge){
			Cluster tmp = clusters.get(index);
			String separator = (clustersToMerge.get(clustersToMerge.size() - 1) == index)? "" : ", ";
			result += String.format("%s ", tmp, separator);
			for(Pattern p: tmp.getPatterns())
				c.addPattern(p);
		}
		
		
		System.out.println(result);
		System.out.println();
		
		for(int index: clustersToMerge){
			Cluster tmp = clusters.get(index);
			clusters.remove(tmp);
		}
	}

	private boolean toMerge(Cluster cluster, Cluster cluster2, int d) {
		return linkage.toMerge(distanceMatrix, cluster, cluster2, d);
	}

	private String printClusters(int d) {
		String result = String.format("%d \t %d \t", d, clusters.size());
		System.out.print(result);
		for(Cluster c: clusters)
			System.out.print(String.format("%s ",c));
		System.out.println("\n");
		
		return result;
	}


	//it's required that patterns are sorted by their index
	private void createDistanceMatrix(Pattern[] patterns) {
		//there are as many rows as the number of patterns
		//there are as many column as the number of patterns
		
		distanceMatrix = new double[patterns.length][patterns.length];
		
		for(int i = 0; i < distanceMatrix.length; i++){
			for(int j = i; j < distanceMatrix.length; j++){
				distanceMatrix[i][j] = distanceMatrix[j][i] = calculateDistance(patterns[i],patterns[j]);
			}
		}
		
		
	}

	private double calculateDistance(Pattern pattern, Pattern pattern2) {
		double distance = 0;
		
		for(int i = 0; i < pattern.getPatternDimension(); i++)
			distance += Math.pow(pattern.getValue(i) - pattern2.getValue(i), 2); 
		
		distance = Math.sqrt(distance);
		return distance;
	}

	private void createClusterForEachPattern(Pattern[] patterns) {
		for( int i = 0; i < patterns.length; i++){
			String name = String.format("C%d", i);
			Cluster c = new Cluster(name);
			c.addPattern(patterns[i]);
			clusters.add(c);
		}
	}

	public LinkageCriterion getCriterion() {
		return criterion;
	}
	

}
