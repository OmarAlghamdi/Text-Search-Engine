package main;

public class Frequncy implements Comparable<Frequncy>{
	private String fileName;
	private double frequncy;
	public Frequncy( String fileName, int totalWords, int occurrences) {

		this.fileName = fileName;
		this.frequncy = 1.0*occurrences/totalWords;	// calculates frequency
	}

	public Frequncy(String fileName, double frequncy) {
		super();
		this.fileName = fileName;
		this.frequncy = frequncy;
	}

	public String getFileName() {
		return this.fileName;
	}
	public double getFrequncy() {
		return this.frequncy;
	}

	@Override
	public int compareTo(Frequncy o) {
		if(o == null)
			throw new NullPointerException();
		else if(!(o instanceof Frequncy))
			throw new ClassCastException();
		else if(this.frequncy > o.frequncy)
			return 1;
		else if(this.frequncy < o.frequncy)
			return -1;
		else
			return 0;
	}

}
