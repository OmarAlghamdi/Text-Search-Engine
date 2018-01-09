package main;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Paths;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
// this class give user the ability to specify where to search and what to ignore
public class SetupUI extends JFrame implements ActionListener{
	private JButton chooseDir, chooseFile, search;
	private JFileChooser jfc;

	// set frame dimension and location
	private int width  = 300, height = 500;
	private Dimension userScreenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private int xLocation = (this.userScreenSize.width-this.width)/2;
	private int yLocation = (this.userScreenSize.height-this.height)/2;
	public SetupUI() {
		// setup the frame
		setLayout(new GridLayout(3, 1));
		this.setSize(this.width, this.height);
		setLocation(this.xLocation, this.yLocation);
		// add components
		this.chooseDir = new JButton("Choose txt files Folder");
		this.chooseDir.addActionListener(this);
		this.add(this.chooseDir);
		this.chooseFile = new JButton("Choose stop words File");
		this.chooseFile.addActionListener(this);
		this.add(this.chooseFile);
		this.search = new JButton("search");
		this.search.addActionListener(this);
		this.add(this.search);
		// setup the working directory
		this.jfc = new JFileChooser(Paths.get("").toAbsolutePath().toString());

		setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.chooseDir) {	// choose where to search
			this.jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			int retVal = this.jfc.showOpenDialog(this);
			if(retVal == JFileChooser.APPROVE_OPTION)
				Main.txtDir = this.jfc.getSelectedFile().getAbsolutePath();
		}
		else if(e.getSource() == this.chooseFile) {	// choose what to ignore
			this.jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
			int retVal = this.jfc.showOpenDialog(this);
			if(retVal == JFileChooser.APPROVE_OPTION)
				Main.swPath = this.jfc.getSelectedFile().getAbsolutePath();
		}
		else if(e.getSource() == this.search)
			if(Main.txtDir.equals("") || Main.swPath.equals(""))
				// show show a message
				JOptionPane.showMessageDialog(this, "You need to chooes a directory and stop words list", "Action is required", JOptionPane.ERROR_MESSAGE);
			else {
				Main.startBuildInvInd = true;	// give the signal to Main class to start building the inverted index
				new UI();
				dispose();
			}
	}

}
