package shortest_path_betweenness;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Graph {
	//FIELDS
	private Map<Integer,Vertex> vertices;
	private double[][] adjacencyMatrix;

	//CONSTRUCTOR
	public Graph(Vertex[] vertices, double[][] adjacencyMatrix){
		this.vertices = new HashMap<Integer, Vertex>();
		for(Vertex v: vertices)
			this.vertices.put(v.getId(),v);

		this.adjacencyMatrix = adjacencyMatrix;
	}

	//METHODS
	public void breadthFirstSearch(Vertex root){
		//FIFO
		LinkedList<Vertex> queue = new LinkedList<Vertex>();

		queue.addLast(root);

		while(queue.size() > 0){
			Vertex v = queue.removeFirst();
			v.setVisited(true);
			System.out.println(v);
			//search adjacency vertices 
			for(int i = 0; i < adjacencyMatrix[0].length; i++){
				if(adjacencyMatrix[v.getId()][i] > 0){ //adjacency vertices
					Vertex adjacencyVertex = vertices.get(i);
					if(!adjacencyVertex.isVisited()){
						queue.addLast(adjacencyVertex);
						adjacencyVertex.setVisited(true);
					}
				} 				
			}
		}
		
	}

	public void calculateShortestPathBetweenness(Vertex root){
		//FIFO
		LinkedList<Vertex> queue = new LinkedList<Vertex>();
		queue.addLast(root);
		
		root.setDistance(0);
		root.setWeight(1);
		
		int cont = 0;
		
		while(queue.size() > 0){
			Vertex v = queue.removeFirst();
			v.setVisited(true);
			
			for(int i = 0; i < adjacencyMatrix[0].length; i++){
				if(adjacencyMatrix[v.getId()][i] > 0){ 
					Vertex t = vertices.get(i); 

					if(cont == 0){ 
						t.setDistance(v.getDistance() + 1);
						t.setWeight(1);
					}else if( t.getDistance() == -1){
						t.setDistance(v.getDistance() + 1);
						t.setWeight(v.getWeight());
					}else if( t.getDistance() == v.getDistance() + 1){
						t.setWeight(t.getWeight() + v.getWeight());
					}
					
					if(!t.isVisited()){
						queue.addLast(t);
						t.setVisited(true);
						v.addChildren(t);
						t.addFather(v);
					}else if(v.getDistance() < t.getDistance()){
						v.addChildren(t);
						t.addFather(v);
					}else if(v.getDistance() == t.getDistance()){
						if(!(v.getFathers().contains(t) && t.getChildren().contains(v))){
							v.addChildren(t);
							t.addFather(v);
						}
					}
					
					
					
				}
			}
		cont++;
		}
		
//		System.out.println("**** father & children ***");
//		for(Vertex v: vertices.values()){
//			System.out.println(v);
//			System.out.print("father: ");
//			for(Vertex f: v.getFathers())
//				System.out.print(f.getId() +" ");
//			System.out.print("children: ");
//			for(Vertex f: v.getChildren())
//				System.out.print(f.getId() +" ");
//			System.out.println();
//		}
//		System.out.println("**** father & children ***");
		
		calculateEdgeBetweenness();
		
		
	}


	private void calculateEdgeBetweenness() {
		LinkedList<Vertex> leaves = findLeaves();
		
		
		for(Vertex v: leaves){
			for(int i = 0; i < adjacencyMatrix[0].length; i++){
				if(adjacencyMatrix[v.getId()][i] > 0){
					adjacencyMatrix[v.getId()][i] = adjacencyMatrix[i][v.getId()]  = vertices.get(i).getWeight() / v.getWeight();
					System.out.println(String.format("Betweenness score for edge <%d,%d>:\n\t  %5.2f / %5.2f = %5.2f",
														v.getId(), vertices.get(i).getId(),vertices.get(i).getWeight(),v.getWeight(),adjacencyMatrix[v.getId()][i]));
					
				}
			}
		}
		
		LinkedList<Vertex> orderedVertices = verticesOrderByDistances(leaves);
		
		
		for(Vertex v: orderedVertices){
			//calculate score
			for(Vertex f: v.getFathers()){
				System.out.println(String.format("Betweenness score for edge <%d,%d>: ", v.getId(), f.getId()));
				double score = 1;
				System.out.print(String.format("\t ( 1"));
				score += calculateScoreChildren(v);
				score *= f.getWeight() / v.getWeight() ;
				adjacencyMatrix[v.getId()][f.getId()] = adjacencyMatrix[f.getId()][v.getId()] = score;
				System.out.println(String.format(" ) * %5.2f / %5.2f = %5.2f", f.getWeight() , v.getWeight(),score));
			}
			
		}
		
		
		
	}

	private double calculateScoreChildren(Vertex v) {
		double score = 0;
		
		for(Vertex c: v.getChildren()){
			System.out.print(String.format(" + %5.2f", adjacencyMatrix[c.getId()][v.getId()]));
			score += adjacencyMatrix[c.getId()][v.getId()];
		}
		
		return score;
	}

	private LinkedList<Vertex> verticesOrderByDistances(LinkedList<Vertex> leaves) {
		LinkedList<Vertex> orderedVertices = new LinkedList<Vertex>();

		
		for(Vertex v: vertices.values()){
			
			if(leaves.contains(v))
				continue;

			boolean inserted = false;

			for(int i = 0; i < orderedVertices.size() && !inserted; i++){
				if(orderedVertices.get(i).getDistance() < v.getDistance()){
					orderedVertices.add(i, v);
					inserted = true;
				}
			}
			
			if(!inserted)
				orderedVertices.addLast(v);
			
		}
		
		
//		for(Vertex v: vertices.values()){
//			if(!leaves.contains(v)){
//				int i = 0;
//				
//				while(orderedVertices.size() != 0 &&  v.getDistance() < orderedVertices.get(i).getDistance()){
//					i++;
//				}
//				orderedVertices.add(i, v);
//			}
//		}
		
		
		return orderedVertices;
	}
	
	public static void quickSortInDescendingOrder (LinkedList<Vertex> vertices, int low, int high)
    {
 
        int i=low;
        int j=high;
        Vertex temp;
        Vertex temp1;
        int middle = vertices.get((low+high)/2).getDistance();
 
        while (i<j)
        {
            while (vertices.get(i).getDistance() > middle)
            {
                i++;
            }
            while (vertices.get(j).getDistance() < middle)
            {
                j--;
            }
            if (j>=i)
            {
                temp = vertices.get(i);
                temp1 = vertices.get(j);
                vertices.remove(i);
                vertices.remove(j);
                vertices.add(i, temp1);
                vertices.add(j, temp);
                i++;
                j--;
            }
        }
 
 
        if (low<j)
        {
            quickSortInDescendingOrder(vertices, low, j);
        }
        if (i<high)
        {
            quickSortInDescendingOrder(vertices, i, high);
        }
    }
	
	

	private LinkedList<Vertex> findLeaves() {
		LinkedList<Vertex> leaves = new LinkedList<>();
		for(Vertex v: vertices.values()){
			if(v.getFathers().size() == 1 && v.getChildren().size() == 0)
				leaves.add(v);
		}
		
		return leaves;
	}

	public void printAdjacencyMatrix() {
		for(int i = 0; i < adjacencyMatrix.length; i++){
			for(int j = 0; j < adjacencyMatrix[0].length; j++){
				System.out.print(String.format("%5.2f ", adjacencyMatrix[i][j]));
			}
			System.out.println();
		}
	}

	public void resetVisited() {
		for(Vertex v: vertices.values())
			v.setVisited(false);
	}


}
