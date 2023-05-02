
/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes or the window is resized.
 */

import acm.graphics.*;
import acm.util.RandomGenerator;

import java.awt.event.*;
import java.util.*;
import java.awt.*;

public class NameSurferGraph extends GCanvas implements NameSurferConstants, ComponentListener {
	private ArrayList<NameSurferEntry> entryNames;
	private RandomGenerator rgen = RandomGenerator.getInstance();

	/**
	 * Creates a new NameSurferGraph object that displays the data.
	 */
	public NameSurferGraph() {
		addComponentListener(this);
		entryNames = new ArrayList<NameSurferEntry>();
	}
//draws the first main graph of decades . horizontal and vertical lines
	private void drawGraph() {
		int x = getWidth() / NDECADES;
		int y = getHeight();
		for (int i = 0; i < NDECADES - 1; i++) {
			GLine line = new GLine(x, 0, x, y);
			add(line);
			x += getWidth() / NDECADES;
		}
		GLine line2 = new GLine(0, GRAPH_MARGIN_SIZE, getWidth(), GRAPH_MARGIN_SIZE);
		add(line2);
		GLine line3 = new GLine(0, y - GRAPH_MARGIN_SIZE, getWidth(), y - GRAPH_MARGIN_SIZE);
		add(line3);
		int z = 0;
		int decades = START_DECADE;
		for (int i = 0; i < NDECADES; i++) {
			String change = Integer.toString(decades);
			GLabel label = new GLabel(change, z, y - GRAPH_MARGIN_SIZE / 3);
			add(label);
			decades += 10;
			z += getWidth() / NDECADES;
		}
	}

//Draws the graph of popularity on suitable coordinates in proper decades
	private void graphPop(NameSurferEntry entry) {
		// draws the graph line
		int x = getWidth() / NDECADES;
		int start = 0;
		int y = getHeight() - GRAPH_MARGIN_SIZE;
		for (int i = 0; i < NDECADES - 1; i++) {
			if (entry.getRank(i) == 0 && entry.getRank(i + 1) == 0) {
				GLine pop = new GLine(start, y, start + x, y);
				pop.setColor(rgen.nextColor());
				add(pop);

			} else if (entry.getRank(i) == 0 && entry.getRank(i + 1) != 0) {
				GLine pop1 = new GLine(start, y, start + x,
						GRAPH_MARGIN_SIZE + ((y - GRAPH_MARGIN_SIZE) * entry.getRank(i + 1) / MAX_RANK));
				pop1.setColor(rgen.nextColor());
				add(pop1);

			} else if (entry.getRank(i) != 0 && entry.getRank(i + 1) != 0) {
				GLine pop2 = new GLine(start,
						GRAPH_MARGIN_SIZE + ((y - GRAPH_MARGIN_SIZE) * entry.getRank(i) / MAX_RANK), start + x,
						GRAPH_MARGIN_SIZE + ((y - GRAPH_MARGIN_SIZE) * entry.getRank(i + 1) / MAX_RANK));
				pop2.setColor(rgen.nextColor());
				add(pop2);
			} else if (entry.getRank(i) != 0 && entry.getRank(i + 1) == 0) {
				GLine pop3 = new GLine(start,
						GRAPH_MARGIN_SIZE + ((y - GRAPH_MARGIN_SIZE) * entry.getRank(i) / MAX_RANK), start + x, y);
				pop3.setColor(rgen.nextColor());
				add(pop3);
			}
			start += x;
		}

	}

//draws the name on proper coordinates.
	private void writeName(NameSurferEntry entry) {
		int x = getWidth() / NDECADES;
		int start = 0;
		int y = getHeight() - GRAPH_MARGIN_SIZE;
		for (int i = 0; i < NDECADES; i++) {
			String name1 = entry.getName() + Integer.toString(entry.getRank(i));
			if (entry.getRank(i) == 0) {
				GLabel name = new GLabel(entry.getName() + "*", start, y);
				name.setColor(rgen.nextColor());
				add(name);
			} else {
				GLabel name = new GLabel(name1, start,
						GRAPH_MARGIN_SIZE + ((y - GRAPH_MARGIN_SIZE) * entry.getRank(i) / MAX_RANK));
				name.setColor(rgen.nextColor());
				add(name);
			}
			start += x;
		}
	}

	/**
	 * Clears the list of name surfer entries stored inside this class.
	 */
	public void clear() {
		// You fill this in //
		entryNames.clear();
	}

	/* Method: addEntry(entry) */
	/**
	 * Adds a new NameSurferEntry to the list of entries on the display. Note that
	 * this method does not actually draw the graph, but simply stores the entry;
	 * the graph is drawn by calling update.
	 */
	public void addEntry(NameSurferEntry entry) {
		entryNames.add(entry);
	}

	/**
	 * Updates the display image by deleting all the graphical objects from the
	 * canvas and then reassembling the display according to the list of entries.
	 * update is also called whenever the size of the canvas changes.
	 */
	public void update() {
		// You fill this in //
		removeAll();
		drawGraph();
		if (entryNames.size() >= 0) {
			for (int i = 0; i < entryNames.size(); i++) {
				NameSurferEntry entry = entryNames.get(i);
				graphPop(entry);
				writeName(entry);
			}
		}
	}

	/* Implementation of the ComponentListener interface */
	public void componentHidden(ComponentEvent e) {
	}

	public void componentMoved(ComponentEvent e) {
	}

	public void componentResized(ComponentEvent e) {
		update();
	}

	public void componentShown(ComponentEvent e) {
	}
}
