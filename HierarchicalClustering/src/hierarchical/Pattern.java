package hierarchical;

public class Pattern {
	
	static private int indexGenerator = 0;
	
	private String name;
	private double[] values;
	private int index;

	public Pattern(String name,double... values){
		this.index = indexGenerator++;
		this.name = name;
		this.values = values;
	}
	
	public Pattern(int index,String name, double... values){
		this.index = index;
		this.name = name;
		this.values = values;
	}
	
	public String toString(){
		if(values.length == 0)
			return String.format("%s", name);
		String result = String.format("%s:(",name); 
		for(int i = 0; i < values.length; i++){
			String separator = (i == values.length - 1)? " )":", ";
			result += String.format("%5.2f%s", values[i],separator);
		}
		return result; 
	}
	
	public double[] getValues(){
		return values;
	}
	
	public int getIndex(){
		return index;
	}
	
	public int getPatternDimension(){
		return values.length;
	}
	
	public double getValue(int index){
		return values[index];
	}
	
	public static void main(String[] args) {
		Pattern p1 = new Pattern("P1", 2d,4.5d);
		Pattern p2 = new Pattern("P2", 2d,4.5d,5d,8d);
		System.out.println(p1);
		System.out.println(p2);
	}
	
}
