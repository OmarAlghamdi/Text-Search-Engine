package main;

import java.util.ArrayList;
import java.util.Iterator;

public class ResultFormater {
	// used to format the result of single word query
	public static String find(String query) {
		ArrayList<Frequncy> result = Search.find(query, Main.invInd);	// gets the result
		// format the result
		String formatedResult = "<html>These are the files that contain <b>" + query +"</b><br>";
		for (Iterator<Frequncy> iterator = result.iterator(); iterator.hasNext();) {
			Frequncy frequncy = iterator.next();
			formatedResult = formatedResult + "<b>" + frequncy.getFileName() + "</b>" + " \t with frequncy of <b>" + frequncy.getFrequncy() + "</b><br>";
			formatedResult = formatedResult + "..." + TextSampler.getOneSampleOfQuery(Main.txtDir + "/" + frequncy.getFileName(), query) + "...<br>";
		}
		formatedResult = formatedResult + "</html>";
		return formatedResult;
	}
	// used to format the result of single NOT query
	public static String not(String query) {
		ArrayList<String> result = Search.findNOT(query, Main.invInd);	// gets the result
		// format the result
		String formatedResult = "<html>These are the files that do NOT contain <b>" + query +"</b><br>";
		for (Iterator<String> iterator = result.iterator(); iterator.hasNext();) {
			String string = iterator.next();
			formatedResult = formatedResult + "<b>" + string + "</b><br>";
		}
		formatedResult = formatedResult + "</html>";
		return formatedResult;
	}
	// used to format the result of two words AND query
	public static String and(String query1, String query2) {
		ArrayList<Frequncy> result = Search.and(query1, query2, Main.invInd);	// gets the result
		// format the result
		String formatedResult = "<html>These are the files that contain both <b>" + query1 + "</b> and <b>" + query2 +"</b><br>";
		for (Iterator<Frequncy> iterator = result.iterator(); iterator.hasNext();) {
			Frequncy frequncy = iterator.next();
			formatedResult = formatedResult + "<b>" + frequncy.getFileName() + "</b>" + " \t with frequncy of <b>" + frequncy.getFrequncy() + "</b><br>";
			//		formatedResult = formatedResult + "..." + TextSampler.getOneSampleOfQuery(Main.txtDir + "/" + frequncy.getFileName(), query1) + "...\n";
			//		formatedResult = formatedResult + "..." + TextSampler.getOneSampleOfQuery(Main.txtDir + "/" + frequncy.getFileName(), query2) + "...\n";
		}
		formatedResult = formatedResult + "</html>";
		return formatedResult;
	}
	// used to format the result of two words OR query
	public static String or(String query1, String query2) {
		ArrayList<Frequncy> result = Search.or(query1, query2, Main.invInd);	// gets the result
		// format the result
		String formatedResult = "<html>These are the files that contain either <b>" + query1 + "</b> or <b>" + query2 +"</b><br>";
		for (Iterator<Frequncy> iterator = result.iterator(); iterator.hasNext();) {
			Frequncy frequncy = iterator.next();
			formatedResult = formatedResult + "<b>" + frequncy.getFileName() + "</b>" + " \t with frequncy of <b>" + frequncy.getFrequncy() + "</b><br>";
			//		formatedResult = formatedResult + "..." + TextSampler.getOneSampleOfQuery(Main.txtDir + "/" + frequncy.getFileName(), query1) + "...\n";
			//		formatedResult = formatedResult + "..." + TextSampler.getOneSampleOfQuery(Main.txtDir + "/" + frequncy.getFileName(), query2) + "...\n";
		}
		formatedResult = formatedResult + "</html>";
		return formatedResult;
	}
}
