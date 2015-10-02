package kmeans;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args){
		double[][] data = {{1d,2d},{2d,4d},{1d,1d},{0d,0d},{2d,5d}, {2d,15d}, {4d,20d}};
		int numClusters = 2;

		Kmeans kmeans = new Kmeans(data, numClusters);
		//kmeans.setEpsilon(0.000001);
		kmeans.calculateClusters();

		ArrayList<double[]>[] clusters = kmeans.getClusters();
		for(ArrayList<double[]> cluster: clusters)
		{
			System.out.println("CLUSTER");
			for(Object dat:cluster)
			{
				for(int i=0;i<2;i++)
					System.out.print(((double[])dat)[i] + " ");
				System.out.println();
			}
		}

		System.out.println("Cluster 0 variance: " +  kmeans.getClusterVars()[0]);
		System.out.println("Cluster 1 variance: " + kmeans.getClusterVars()[1]);
		System.out.println("Total variance:" + kmeans.getTotalVar());
		System.out.println("Cluster 0 center: " + kmeans.getClusterCenters()[0][0] + " " + kmeans.getClusterCenters()[0][1]);
		System.out.println("Cluster 1 center: " + kmeans.getClusterCenters()[1][0] + " " + kmeans.getClusterCenters()[1][1]);

	}
}
