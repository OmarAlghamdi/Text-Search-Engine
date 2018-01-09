package main;
// thrown when the selected file supposed to be Directory
public class NotDirectoryException extends Exception {

	public NotDirectoryException(String path) {
		super(path + " is not a directory");
	}

}
