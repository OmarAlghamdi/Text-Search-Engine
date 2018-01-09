package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;

public class Search {
	// single query
	public static ArrayList<Frequncy> find(String query, InvertedIndex invIndex){
		ArrayList<Frequncy> result  = new ArrayList<>(invIndex.getData().get(query));
		Collections.sort(result);
		Collections.reverse(result);
		return result;
	}

	// NOT query
	public static ArrayList<String> findNOT(String query, InvertedIndex invIndex){
		//		ArrayList<Frequncy> result = new ArrayList<>();
		ArrayList<String> filesContainQuery = new ArrayList<>();
		ArrayList<String> filesDoNotContainQuery = new ArrayList<>();
		ArrayList<String> allFiles = null;

		try {
			allFiles = new ArrayList<>(Arrays.asList(TextFileProcessor.getTxtFilesNamesInDir(new File(Main.txtDir))));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (NotDirectoryException e) {
			e.printStackTrace();
		}

		ArrayList<Frequncy> frequnciesOfQuery = new ArrayList<>(invIndex.getData().get(query));
		// finds where the query exists
		for (Iterator<Frequncy> iterator = frequnciesOfQuery.iterator(); iterator.hasNext();) {
			Frequncy frequncy = iterator.next();
			filesContainQuery.add(frequncy.getFileName());
		}
		// finds where the query does not exist
		for (Iterator<String> iterator = allFiles.iterator(); iterator.hasNext();) {
			String string = iterator.next();
			if(!filesContainQuery.contains(string))
				filesDoNotContainQuery.add(string);
		}

		Collections.sort(filesDoNotContainQuery);	// sort the result according to the alphabetical order
		return filesDoNotContainQuery;
	}

	// AND query
	public static ArrayList<Frequncy> and(String query1, String query2, InvertedIndex invIndex){
		ArrayList<Frequncy> result = new ArrayList<>();

		ArrayList<Frequncy> list1 = new ArrayList<>(invIndex.getData().get(query1));
		// list1 sort
		list1.sort(new Comparator<Frequncy>() {		// sorting according to file names
			@Override
			public int compare(Frequncy f1, Frequncy f2) {
				String f1FileName = f1.getFileName();
				String f2FileName = f2.getFileName();
				return f1FileName.compareTo(f2FileName);
			}
		});

		ArrayList<Frequncy> list2 = new ArrayList<>(invIndex.getData().get(query2));
		// list2 sort
		list2.sort(new Comparator<Frequncy>() {		// sorting according to file names
			@Override
			public int compare(Frequncy f1, Frequncy f2) {
				String f1FileName = f1.getFileName();
				String f2FileName = f2.getFileName();
				return f1FileName.compareTo(f2FileName);
			}
		});

		// finds the common files and compute the frequency
		for (Iterator<Frequncy> iterator = list1.iterator(); iterator.hasNext();) {
			Frequncy frequncy1 = iterator.next();
			for (Iterator<Frequncy> iterator2 = list2.iterator(); iterator2.hasNext();) {
				Frequncy frequncy2 = iterator2.next();
				if(frequncy2.getFileName().equals(frequncy1.getFileName())){
					result.add(new Frequncy(frequncy1.getFileName(), frequncy1.getFrequncy() * frequncy2.getFrequncy()));
					break;
				}
			}
		}

		return result;
	}

	// or query
	public static ArrayList<Frequncy> or(String query1, String query2, InvertedIndex invIndex){
		ArrayList<Frequncy> result = new ArrayList<>();

		ArrayList<Frequncy> list1 = new ArrayList<>(invIndex.getData().get(query1));
		// list1 sort
		list1.sort(new Comparator<Frequncy>() {		// sort according to file names
			@Override
			public int compare(Frequncy f1, Frequncy f2) {
				String f1FileName = f1.getFileName();
				String f2FileName = f2.getFileName();
				return f1FileName.compareTo(f2FileName);
			}
		});

		ArrayList<Frequncy> list2 = new ArrayList<>(invIndex.getData().get(query2));
		// list2 sort
		list2.sort(new Comparator<Frequncy>() {		// sort according to file names
			@Override
			public int compare(Frequncy f1, Frequncy f2) {
				String f1FileName = f1.getFileName();
				String f2FileName = f2.getFileName();
				return f1FileName.compareTo(f2FileName);
			}
		});

		HashSet<String> files1 = new HashSet<>();	// stores all files contains query1
		HashSet<String> files2 = new HashSet<>();	// stores all files contains query2

		// finds files contains both words and calculate frequency
		for (Iterator<Frequncy> iterator = list1.iterator(); iterator.hasNext();) {
			Frequncy frequncy1 = iterator.next();
			for (Iterator<Frequncy> iterator2 = list2.iterator(); iterator2.hasNext();) {
				Frequncy frequncy2 = iterator2.next();
				if(frequncy2.getFileName().equals(frequncy1.getFileName())){
					result.add(new Frequncy(frequncy1.getFileName(), frequncy1.getFrequncy() + frequncy2.getFrequncy()));
					files1.add(frequncy1.getFileName());
					files2.add(frequncy2.getFileName());
					break;
				}
			}
		}
		// adds result of files contain only word1
		for (Iterator<Frequncy> iterator = list1.iterator(); iterator.hasNext();) {
			Frequncy frequncy = iterator.next();
			if(!files1.contains(frequncy.getFileName()))
				result.add(frequncy);
		}
		// adds result of files contain only word2
		for (Iterator<Frequncy> iterator = list2.iterator(); iterator.hasNext();) {
			Frequncy frequncy = iterator.next();
			if(!files2.contains(frequncy.getFileName()))
				result.add(frequncy);
		}

		return result;
	}
}
