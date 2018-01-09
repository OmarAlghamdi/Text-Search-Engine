package main;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.util.TreeMap;

import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;

public class InvertedIndex {
	private ArrayListValuedHashMap<String, Frequncy> data = new ArrayListValuedHashMap<>();

	public InvertedIndex(Content[] contents) {
		this.data = build(contents);

	}

	public ArrayListValuedHashMap<String, Frequncy> build(Content[] contents) {
		ArrayListValuedHashMap<String, Frequncy>  frequncies = new ArrayListValuedHashMap<>();
		LinkedList<TreeMap<String, Occurrence>>  occurrences;

		occurrences = calculateOccurrence(contents);
		frequncies = calculateFrequncy(occurrences);

		return frequncies;
	}

	// calculates how many times a word occurred per file
	public LinkedList<TreeMap<String, Occurrence>> calculateOccurrence(Content[] contents) {
		LinkedList<TreeMap<String, Occurrence>>  existentWords = new LinkedList<>();
		StringTokenizer strTok;

		// iterate over all files
		for (int i = 0; i < contents.length; i++) {
			TreeMap<String, Occurrence> wordsInFile = new TreeMap<>();
			strTok = new StringTokenizer(contents[i].getContent());
			String nextToken;

			// iterate over a file content
			while(strTok.hasMoreTokens()) {
				nextToken = strTok.nextToken().toLowerCase();

				// checks if the next Token already exist in the hash map
				if(wordsInFile.containsKey(nextToken))
					wordsInFile.get(nextToken).increament();
				else
					wordsInFile.put(nextToken, new Occurrence(nextToken, contents[i].getFileName(), contents[i].getDocLength()));
			}
			// adds words in a file to all words of all files
			existentWords.add(wordsInFile);
		}

		return existentWords;
	}

	// calculates words frequencies
	public ArrayListValuedHashMap<String, Frequncy> calculateFrequncy(
			LinkedList<TreeMap<String, Occurrence>> occurrences) {
		ArrayListValuedHashMap<String, Frequncy>  wordsFrequncies = new ArrayListValuedHashMap<>();
		// iterates over all files
		for (Iterator<TreeMap<String, Occurrence>> iterator = occurrences.iterator(); iterator.hasNext();) {
			TreeMap<String, Occurrence> treeMap = iterator.next();
			// iterates over file occurrences
			int mapSize = treeMap.size();
			for (int i = 0; i < mapSize; i++) {
				Occurrence top = treeMap.pollFirstEntry().getValue();
				wordsFrequncies.put(top.getWord()	, new Frequncy(top.getFileName(), top.getDocLength(), top.getCount()));
			}
		}

		return wordsFrequncies;
	}

	public ArrayListValuedHashMap<String, Frequncy> getData() {
		return this.data;
	}

}
