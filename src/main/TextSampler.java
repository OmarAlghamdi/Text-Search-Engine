package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
// this class return samples of txt containing search query
public class TextSampler {
	public static String getOneSampleOfQuery(String filePath, String query) {
		ArrayList<String> txtSample = new ArrayList<>();
		boolean wordIsFound = false;
		String nextWord, result = "";
		Scanner in = null;
		try {
			in = new Scanner(new File(filePath));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		while(in.hasNext() && txtSample.size() < 11) {
			nextWord = in.next();
			if(nextWord.equals(query))
				wordIsFound = true;
			if(!wordIsFound && txtSample.size() > 6)
				txtSample.remove(0);
			if(nextWord.equals(query))
				txtSample.add("<b>"+nextWord+"</b>");
			txtSample.add(nextWord);
		}
		in.close();

		for (Iterator<String> iterator = txtSample.iterator(); iterator.hasNext();) {
			String string = iterator.next();
			result = result + " " +string;
		}

		return result;
	}
}
