
/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

import acm.program.*;
import java.awt.event.*;
import javax.swing.*;

public class NameSurfer extends Program implements NameSurferConstants {
	private JTextField Names;
	private JButton Graph;
	private JButton Clear;
	private NameSurferGraph graph;
	private NameSurferDataBase base;
	private NameSurferEntry entry;

	/* Method: init() */
	/**
	 * This method has the responsibility for reading in the data base and
	 * initializing the interactors at the bottom of the window.
	 */
	public void init() {
		JLabel label = new JLabel("Name");
		add(label, SOUTH);
		Names = new JTextField(15);
		Names.addActionListener(this);
		add(Names, SOUTH);
		Graph = new JButton("Graph");
		add(Graph, SOUTH);
		Graph.addActionListener(this);
		Clear = new JButton("Clear");
		add(Clear, SOUTH);
		addActionListeners(this);
		base = new NameSurferDataBase(NAMES_DATA_FILE);
		graph = new NameSurferGraph();
		add(graph);

	}

	/* Method: actionPerformed(e) */
	/**
	 * This class is responsible for detecting when the buttons are clicked
	 */
	public void actionPerformed(ActionEvent e) {
		// You fill this in //
		if (e.getActionCommand().equals("Clear")) {
			graph.clear();
			graph.update();
		} else {
			entry = base.findEntry(Names.getText());
			if (entry != null) {
				graph.addEntry(entry);
				graph.update();
			}
		}

	}
}
