package shortest_path_betweenness;

import java.util.LinkedList;

public class Vertex {

	//FIELDS
	private int id;
	private boolean visited;
	private int distance;
	private double weight;
	private LinkedList<Vertex> fathers;
	private LinkedList<Vertex> children;
	
	
	//CONSTRUCTOR
	public Vertex(int id) {
		this.id = id;
		this.visited = false;
		this.distance = -1;
		this.weight = -1;
		fathers = new LinkedList<Vertex>();
		children = new LinkedList<Vertex>();
	}

	//METHODS
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public LinkedList<Vertex> getFathers() {
		return fathers;
	}

	public void setFathers(LinkedList<Vertex> fathers) {
		this.fathers = fathers;
	}

	public LinkedList<Vertex> getChildren() {
		return children;
	}

	public void setChildren(LinkedList<Vertex> children) {
		this.children = children;
	}
	
	public void addChildren(Vertex v){
		children.addLast(v);
	}
		
	public void addFather(Vertex v){
		fathers.addLast(v);
	}

	public String toString(){
		String vertex = "";
		vertex += String.format("V %d:( %d,%5.2f)", id, distance, weight );
		return vertex;
	}
	
	
}
