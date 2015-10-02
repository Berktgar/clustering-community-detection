package kmeans;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataClassReader {

	//FIELD
	private BufferedReader in;
	private File file;
	
	public DataClassReader(File file) throws FileNotFoundException {
		this.file = file;
		in = new BufferedReader(new FileReader(file));
	}
	
	
	public double[][] readDataCluster() throws NumberFormatException, IOException{
		
		List<Double[]> data = new ArrayList<Double[]>();
		
		String[] splittedString = null;
		
		String line;
		
		while( null != ( line = in.readLine())){
			splittedString = line.split(",");
			
			Double[] datum = new Double[splittedString.length];
			datum[0] = new Double(splittedString[0]);
			datum[1] = new Double(splittedString[1]);
			data.add(datum);
		}
		
		double[][] matrixOfData = new double[data.size()][splittedString.length];
		
		for (int i = 0; i < data.size(); i++) {
			for (int j = 0; j < splittedString.length; j++) {
				matrixOfData[i][j] = data.get(i)[j];
			}
		}

		return  matrixOfData;
	}


	public BufferedReader getIn() {
		return in;
	}


	public File getFile() {
		return file;
	}
}
