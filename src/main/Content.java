package main;
// this class store the length & contents of each files
public class Content {
	private int docLength;
	private String content;
	private String fileName;

	public Content(int docLength, String content, String fileName) {
		super();
		this.docLength = docLength;
		this.content = content;
		this.fileName = fileName;
	}

	public int getDocLength() {
		return this.docLength;
	}
	public String getContent() {
		return this.content;
	}
	public String getFileName() {
		return this.fileName;
	}

}
