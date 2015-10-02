package kmeans;

import java.util.ArrayList;

public class Exercise1 {
	public static void main(String[] args) {
		
		double[][] data = {{2d,10d},{2d,5d},{8d,4d},{5d,8d},{7d,5d}, {6d,4d}, {1d,2d}, {4d,9d}};
		double[][] clusterCenters = {{2d,10d},{5d,8d},{1d,2d}};
		int numClusters = 3;
        
        Kmeans kmeans = new Kmeans(data, numClusters, clusterCenters);
	
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
        System.out.println("Cluster 2 variance: " + kmeans.getClusterVars()[1]);
        System.out.println("Total variance:" + kmeans.getTotalVar());
        System.out.println("Cluster 0 center: " + kmeans.getClusterCenters()[0][0] + " " + kmeans.getClusterCenters()[0][1]);
        System.out.println("Cluster 1 center: " + kmeans.getClusterCenters()[1][0] + " " + kmeans.getClusterCenters()[1][1]);

		System.out.println("Cluster 2 center: " + kmeans.getClusterCenters()[2][0] + " " + kmeans.getClusterCenters()[2][1]);
	}
}
