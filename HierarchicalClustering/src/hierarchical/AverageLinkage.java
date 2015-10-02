package hierarchical;

import java.util.List;

public class AverageLinkage extends Linkage {

	@Override
	public boolean toMerge(double[][] distanceMatrix, Cluster cluster,
			Cluster cluster2, int d) {
		List<Pattern> p = cluster.getPatterns();
		List<Pattern> p2 = cluster2.getPatterns();
		
		double distanceBetweenCluster = 0;
		int cont = 0;
		
		for(int i = 0; i < p.size(); i++){
			for(int j = 0; j < p2.size(); j++){
					distanceBetweenCluster += distanceMatrix[p.get(i).getIndex()][p2.get(j).getIndex()]; 
					cont++;
			}
		}
		
		distanceBetweenCluster = distanceBetweenCluster / cont;
		
		return distanceBetweenCluster <= d;
	}

}
