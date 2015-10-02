package main;

import hierarchical.Hierarchical;
import hierarchical.LinkageCriterion;

public class Exercise3 {
	public static void main(String[] args) {

		double[][] distanceMatrix = new double[4][4];
		distanceMatrix[0][0] = distanceMatrix[1][1] = distanceMatrix[2][2] = distanceMatrix[3][3] = 0;
		distanceMatrix[0][1] = distanceMatrix[1][0] = 1;
		distanceMatrix[0][2] = distanceMatrix[2][0] = 4;
		distanceMatrix[0][3] = distanceMatrix[3][0] = 5;
		distanceMatrix[1][2] = distanceMatrix[2][1] = 2;
		distanceMatrix[1][3] = distanceMatrix[3][1] = 6;
		distanceMatrix[2][3] = distanceMatrix[3][2] = 3;

		System.out.println("*** SINGLE LINKAGE ***\n");
		Hierarchical h = new Hierarchical(LinkageCriterion.SINGLE);
		h.partition(distanceMatrix);
		
		System.out.println("\n*** COMPLETE LINKAGE ***\n");
		h = new Hierarchical(LinkageCriterion.COMPLETE);
		h.partition(distanceMatrix);
		
		System.out.println("\n*** AVERAGE LINKAGE ***\n");
		h = new Hierarchical(LinkageCriterion.AVERAGE);
		h.partition(distanceMatrix);
		
	}

}
