package main;

import java.io.FileNotFoundException;
// the main class that control app launch setting
public class Main implements Runnable{
	public static String txtDir = "", swPath = "";	// where to search + what to ignore
	public static InvertedIndex invInd;		// the data storage
	public static boolean startBuildInvInd;	// signal

	public Main() {
		// show user UI
		new SetupUI();

		// inverted index building thread
		new Thread(this).start();
	}

	@Override
	public void run() {
		while(!startBuildInvInd)		// wait until user choose where and what

			try {
				Thread.sleep(500);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}

		try {
			//	long s = System.currentTimeMillis()/1000, ms = System.currentTimeMillis(), ns = System.nanoTime();	// time testing

			invInd = new InvertedIndex(new TextFileProcessor(txtDir, swPath).getContents());

			/*System.out.printf("time in s = %d\n",  System.currentTimeMillis()/1000-s );		// time testing
			System.out.printf("time in ms = %d\n", System.currentTimeMillis()-ms);
			System.out.printf("time in ns = %d\n", System.nanoTime()-ns);*/

		} catch (FileNotFoundException | NotDirectoryException e) {
			e.printStackTrace();
		}

	}
	public static void main(String[] args) {

		new Main();

	}



}
