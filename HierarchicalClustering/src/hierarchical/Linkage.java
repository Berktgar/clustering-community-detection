package hierarchical;

public abstract class Linkage {

	public abstract boolean toMerge(double[][] distanceMatrix, Cluster cluster, Cluster cluster2, int d);
	
}
