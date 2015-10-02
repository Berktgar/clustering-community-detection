package main;

import hierarchical.Hierarchical;
import hierarchical.LinkageCriterion;
import hierarchical.Pattern;

public class Exercise4 {

	public static void main(String[] args) {
		Hierarchical h = new Hierarchical(LinkageCriterion.SINGLE);
		Pattern[] patterns = new Pattern[8];

		patterns[0] = new Pattern(0,"A1", 2,10);
		patterns[1] = new Pattern(1,"A2", 2,5);
		patterns[2] = new Pattern(2,"A3", 8,4);
		patterns[3] = new Pattern(3,"A4", 5,8);
		patterns[4] = new Pattern(4,"A5", 7,5);
		patterns[5] = new Pattern(5,"A6", 6,4);
		patterns[6] = new Pattern(6,"A7", 1,2);
		patterns[7] = new Pattern(7,"A8", 4,9);


		System.out.println("*** SINGLE LINKAGE ***\n");
		h.partition(patterns);

		System.out.println("\n*** COMPLETE LINKAGE ***\n");
		h = new Hierarchical(LinkageCriterion.COMPLETE);
		h.partition(patterns);

		System.out.println("\n*** AVERAGE LINKAGE ***\n");
		h = new Hierarchical(LinkageCriterion.AVERAGE);
		h.partition(patterns);	
	}

}
