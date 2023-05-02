
/*
 * File: FacePamphletCanvas.java
 * -----------------------------
 * This class represents the canvas on which the profiles in the social
 * network are displayed.  NOTE: This class does NOT need to update the
 * display when the window is resized.
 */

import acm.graphics.*;
import java.awt.*;
import java.util.*;

public class FacePamphletCanvas extends GCanvas implements FacePamphletConstants {
	GLabel message;

	/**
	 * Constructor This method takes care of any initialization needed for the
	 * display
	 */
	public FacePamphletCanvas() {
		// You fill this in
	}

	/**
	 * This method displays a message string near the bottom of the canvas. Every
	 * time this method is called, the previously displayed message (if any) is
	 * replaced by the new message text passed in.
	 */
	public void showMessage(String msg) {
		if (message != null) {
			remove(message);
		}
		message = new GLabel(msg);
		double x = getWidth() / 2 - message.getWidth() / 2;
		double y = getHeight() - BOTTOM_MESSAGE_MARGIN;
		message.setFont(MESSAGE_FONT);
		add(message, x, y);
	}

	/**
	 * This method displays the given profile on the canvas. The canvas is first
	 * cleared of all existing items (including messages displayed near the bottom
	 * of the screen) and then the given profile is displayed. The profile display
	 * includes the name of the user from the profile, the corresponding image (or
	 * an indication that an image does not exist), the status of the user, and a
	 * list of the user's friends in the social network.
	 */
	public void displayProfile(FacePamphletProfile profile) {
		removeAll();
		displayName(profile.getName());
		displayImage(profile.getImage());
		displayFriend(profile.getFriends());
		displayPost(profile.getStatus());

	}
//shows the name on its suitable place
	private void displayName(String name) {
		GLabel accName = new GLabel(name);
		accName.setFont(PROFILE_NAME_FONT);
		accName.setColor(Color.BLUE);
		double x = LEFT_MARGIN;
		double y = TOP_MARGIN / 2 + accName.getHeight() / 2;
		;
		add(accName, x, y);
	}

//shows the image on its  suitable place
	private void displayImage(GImage image) {
		double x = LEFT_MARGIN;
		double y = TOP_MARGIN + IMAGE_MARGIN;
		if (image != null) {
			image.setBounds(x, y, IMAGE_WIDTH, IMAGE_HEIGHT);
			add(image);
		} else {
			GRect rect = new GRect(x, y, IMAGE_WIDTH, IMAGE_HEIGHT);
			add(rect);
			GLabel noImage = new GLabel("No Image");
			noImage.setFont(PROFILE_IMAGE_FONT);
			add(noImage, x + IMAGE_WIDTH / 2 - noImage.getWidth() / 2, y + IMAGE_HEIGHT / 2);
		}
	}

//shows the post on its suitable place
	private void displayPost(String status) {
		GLabel post = new GLabel(status);
		post.setFont(PROFILE_STATUS_FONT);
		double x = LEFT_MARGIN;
		double y = TOP_MARGIN + IMAGE_MARGIN + IMAGE_HEIGHT + STATUS_MARGIN + post.getHeight();
		if (getElementAt(x, y) != null) {
			remove(getElementAt(x, y));
		}
		add(post, x, y);
	}

//shows friend list on  suitable place
	private void displayFriend(Iterator<String> friends) {
		GLabel friendNames = new GLabel("Friends:");
		friendNames.setFont(PROFILE_FRIEND_LABEL_FONT);
		add(friendNames, getWidth() / 2, TOP_MARGIN + IMAGE_MARGIN);
		Iterator<String> it = friends;
		for (int i = 1; it.hasNext(); i++) {
			GLabel friendName = new GLabel(it.next());
			friendName.setFont(PROFILE_FRIEND_FONT);
			double listHeight = TOP_MARGIN + IMAGE_MARGIN + friendNames.getHeight() * i;
			add(friendName, getWidth() / 2, listHeight);
		}
	}
}
