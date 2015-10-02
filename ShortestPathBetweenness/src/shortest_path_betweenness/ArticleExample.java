package shortest_path_betweenness;

public class ArticleExample {
	public static void main(String[] args) {
		Vertex[] v = new Vertex[7];
		Vertex root = null;
		for (int i = 0; i < v.length; i++) {
			Vertex tmp = new Vertex(i); 
			if(i == 0)
				root = tmp;
			v[i] = tmp; 
		}

		double[][] matrix = {
						 {0,1,1,0,0,0,0},
						 {1,0,0,1,0,0,0},
						 {1,0,0,1,1,0,0},
						 {0,1,1,0,0,1,0},
						 {0,0,1,0,0,1,1},
						 {0,0,0,1,1,0,0},
						 {0,0,0,0,1,0,0}};

		Graph g = new Graph(v, matrix);
		
		System.out.println("*** Initial Graph ***\nV id:(d,w) where: id= node id, d = distance node, w = weight node\n");
		g.breadthFirstSearch(root);
		g.resetVisited();
		System.out.println("\n*** Betweenness scores for the edges ***\n");
		g.calculateShortestPathBetweenness(root);
		g.resetVisited();
		System.out.println("\n*** Graph after computation ***\n");
		g.breadthFirstSearch(root);
		
	}

}
