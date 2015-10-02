package hierarchical;

import java.util.List;

public class CompleteLinkage extends Linkage {

	@Override
	public boolean toMerge(double[][] distanceMatrix, Cluster cluster,
			Cluster cluster2, int d) {
		List<Pattern> p = cluster.getPatterns();
		List<Pattern> p2 = cluster2.getPatterns();
		
		double distanceBetweenCluster = Double.MIN_VALUE;
		
		for(int i = 0; i < p.size(); i++){
			for(int j = 0; j < p2.size(); j++){
				if(distanceMatrix[p.get(i).getIndex()][p2.get(j).getIndex()] > distanceBetweenCluster){
					distanceBetweenCluster = distanceMatrix[p.get(i).getIndex()][p2.get(j).getIndex()]; 
				}
			}
		}
		return distanceBetweenCluster <= d;
	}

}
