/*
The software is licensed under a Creative Commons Attribution 3.0 Unported License.
Copyright (C) 2012 Timo Rantalainen
*/
/*CSV file reader.
Reads first row as column headings into Vector columnHeadings
Reads remaining data as columns into columns Vector Vector where the outermost
vector is column and innermost is rows from the respective column
*/

import java.io.*;
import java.util.*;
public class CSVReader{
	public Vector<String> columnHeadings;
	public Vector<Vector<Double>> columns;
	public CSVReader(String fileName,String separator){
		try {
			BufferedReader br = new BufferedReader( new FileReader(fileName));
			String strLine = "";
			StringTokenizer st = null;
			int tokenNumber;
			/*Read column headings from first row*/
			strLine = br.readLine(); /*Read the headings row*/
			st = new StringTokenizer(strLine, separator);
			columnHeadings = new Vector<String>();
			columns = new Vector<Vector<Double>>();
			while(st.hasMoreTokens()){
				columnHeadings.add(st.nextToken().trim());
				columns.add(new Vector<Double>());
			}
			/*Read data row by row*/
			while( (strLine = br.readLine()) != null){
				st = new StringTokenizer(strLine, separator);
				tokenNumber = 0;
				while(st.hasMoreTokens()){
					columns.get(tokenNumber).add(Double.valueOf(st.nextToken()));
					++tokenNumber;
				}
			}
			br.close();
		} catch (Exception err){System.err.println("Error: "+err.getMessage());}
	}
}