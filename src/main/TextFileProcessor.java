package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.util.Scanner;


public class TextFileProcessor {
	private String folderPath;	// path of the folder contains txt files to be searched
	private File txtFilesDir;
	private String[] txtFilesNames; 	// names of each text file in the above folder
	private String[] stopwords;	// list of the stop words specified by user
	private Content[] contents;	// place holder of each file contents

	/*public TextFileProcessor2() {
		// for Unit testing only
	}*/

	public TextFileProcessor(String dirPath, String swPath) throws FileNotFoundException, NotDirectoryException {
		this.folderPath = dirPath;
		this.txtFilesDir = new File(this.folderPath);
		this.stopwords = getStopWords(swPath);
		this.txtFilesNames = getTxtFileNames(this.txtFilesDir);
		this.contents = readTextFiles(this.txtFilesNames, this.stopwords);
	}
	// returns the stop words in a text file
	public String[] getStopWords(String swPath) throws FileNotFoundException {
		int numOfSW = countLines(swPath);
		String[] stopWordList = new String[numOfSW];
		Scanner in = new Scanner(new File(swPath));
		for (int i = 0; i < stopWordList.length; i++)
			stopWordList[i] = in.nextLine();
		in.close();
		return stopWordList;
	}
	// returns the number of lines in a txt file
	public int countLines(String swPath) throws FileNotFoundException {
		int numOfLines = 0;
		Scanner in = new Scanner(new File(swPath));
		while(in.hasNextLine()) {
			numOfLines++;
			in.nextLine();
		}
		in.close();
		return numOfLines;
	}
	// retruns the name of each txt file in the specified folder
	public String[] getTxtFileNames(File dir) throws NotDirectoryException, FileNotFoundException{
		if(dir.isDirectory())
			// filtering files. returns only txt files
			return dir.list(new FilenameFilter() {
				@Override
				public boolean accept(File dir, String name) {
					return name.endsWith(".txt");
				}
			});
		else if(!dir.exists())
			throw new FileNotFoundException(dir.getPath());
		else
			throw new NotDirectoryException(dir.getPath());

	}
	// returns array of Content
	public Content[] readTextFiles(String[] filesPaths, String[] stopWords) throws FileNotFoundException {
		Content[] contents = new Content[filesPaths.length];
		Scanner fileIn = null;
		String currenFileContent;
		int currentFileLength;

		// iterating over all txt files
		for (int i = 0; i < contents.length; i++) {
			currenFileContent = "";
			currentFileLength = 0;

			fileIn = new Scanner(new File(this.folderPath + "/" + filesPaths[i]));
			fileIn.useDelimiter("[^\\p{L}\\p{Nd}]+"); // ignore non-word characters
			String nextWord;
			boolean skip;
			while(fileIn.hasNext()) {
				currentFileLength++;
				skip = false;
				nextWord = fileIn.next();

				// checks if the word is stop word to be ignored
				for (int j = 0; j < stopWords.length; j++)
					//			System.out.println("next word = " + nextWord + "\tstop word = " + stopWords[i]);
					if(stopWords[j].equalsIgnoreCase(nextWord)) {
						skip = true;
						break;
					}

				// adds the word if it is not a stop word
				if(!skip)
					currenFileContent = currenFileContent + nextWord + " ";
			}

			// saves the content of the file after stop words removal and its length
			contents[i] = new Content(currentFileLength, currenFileContent, filesPaths[i]);
		}

		return contents;
	}

	public static String[] getTxtFilesNamesInDir(File dir) throws FileNotFoundException, NotDirectoryException {
		if(dir.isDirectory())
			// filtering files. returns only txt files
			return dir.list(new FilenameFilter() {
				@Override
				public boolean accept(File dir, String name) {
					return name.endsWith(".txt");
				}
			});
		else if(!dir.exists())
			throw new FileNotFoundException(dir.getPath());
		else
			throw new NotDirectoryException(dir.getPath());
	}
	public Content[] getContents() {
		return this.contents;
	}

}
