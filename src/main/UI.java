package main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
// this is the UI user uses to search
public class UI extends JFrame implements ActionListener{

	private JPanel searchP = new JPanel(new GridLayout(2, 1));
	private JPanel p1 = new JPanel(new FlowLayout()), p2 = new JPanel(new FlowLayout());//, p3 = new JPanel(new FlowLayout());
	private JLabel instru = new JLabel("Enter your search query here");
	private JTextField searchBox = new JTextField(20), andSearchBox = new JTextField(20);//, orSearchBox = new JTextField(20);
	private JButton search = new JButton("Search");
	private JButton clear = new JButton("Clear");
	private JEditorPane resultBox = new JEditorPane("text/html", "");
	private JScrollPane resultPane = new JScrollPane(this.resultBox);
	private JCheckBox not = new JCheckBox("NOT"), and = new JCheckBox("AND"), or = new JCheckBox("OR");

	// set frame dimension and location
	private int width  = 650, height = 500;
	private Dimension userScreenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private int xLocation = (this.userScreenSize.width-this.width)/2;
	private int yLocation = (this.userScreenSize.height-this.height)/2;

	public UI() {
		// setup the frame
		setTitle("txt Search Engine");
		this.setSize(this.width, this.height);
		setLocation(this.xLocation, this.yLocation);
		setLayout(new BorderLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// add panels
		this.add(this.searchP, BorderLayout.NORTH);
		this.add(this.resultPane, BorderLayout.CENTER);
		// add sub panels
		this.searchP.add(this.p1);
		this.searchP.add(this.p2);
		// add components
		this.p1.add(this.instru);
		this.p1.add(this.not);
		this.p1.add(this.searchBox);
		this.p1.add(this.search);
		this.p1.add(this.clear);
		// add more components
		this.p2.add(this.and);
		this.p2.add(this.or);
		this.p2.add(this.andSearchBox);

		// add listeners
		this.search.addActionListener(this);
		this.clear.addActionListener(this);

		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.clear) {	// clear all fields
			this.searchBox.setText("");
			this.resultBox.setText("");
		} else										// search
			if(!this.not.isSelected() && !this.and.isSelected() && !this.or.isSelected())	// one word query
				this.resultBox.setText(ResultFormater.find(this.searchBox.getText()));
			else if(this.not.isSelected() && !this.and.isSelected() && !this.or.isSelected())	// one word NOT query
				this.resultBox.setText(ResultFormater.not(this.searchBox.getText()));
			else if(!this.not.isSelected() && this.and.isSelected() && !this.or.isSelected())	// two words AND query
				this.resultBox.setText(ResultFormater.and(this.searchBox.getText(), this.andSearchBox.getText()));
			else if(!this.not.isSelected() && !this.and.isSelected() && this.or.isSelected())	// two words OR query
				this.resultBox.setText(ResultFormater.or(this.searchBox.getText(), this.andSearchBox.getText()));
			else 		// any other combination
				this.resultBox.setText("Sorry, this combination is not supported");

	}

}
