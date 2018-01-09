package main;

public class Occurrence{
	private String word, fileName;
	private int count, docLength;
	public String getFileName() {
		return this.fileName;
	}

	public Occurrence(String word, String fileName, int docLength) {
		this.word = word;
		this.fileName = fileName;
		this.docLength = docLength;
		this.count = 1;
	}

	public int getCount() {
		return this.count;
	}
	public void increament() {
		this.count++;
	}
	public String getWord() {
		return this.word;
	}
	public int getDocLength() {
		return this.docLength;
	}

	@Override
	public boolean equals(Object obj) {
		if(super.equals(obj) == true)
			return true;
		else if(obj == null)
			return false;
		else if(obj instanceof Occurrence )
			if(this.word.equals(((Occurrence) obj).word) && this.count ==((Occurrence) obj).count)
				return true;
			else return false;
		else return false;
	}

	@Override
	public int hashCode() {
		return this.word.hashCode();
	}

}
