
/*
 * File: FacePamphlet.java
 * -----------------------
 * When it is finished, this program will implement a basic social network
 * management system.
 */

import java.awt.event.ActionEvent;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import acm.graphics.GImage;
import acm.program.Program;
import acm.util.ErrorException;

public class FacePamphlet extends Program implements FacePamphletConstants {
	JLabel namefield;
	JTextField name;
	JTextField status;
	JTextField picture;
	JTextField friend;
	JButton adding;
	JButton delete;
	JButton lookup;
	JButton changeStatus;
	JButton changePicture;
	JButton addFriend;
	private FacePamphletDatabase base = new FacePamphletDatabase();
	private FacePamphletCanvas canvas = new FacePamphletCanvas();

	// keeps track of the current profile
	private FacePamphletProfile currentProfile = null;

	/**
	 * This method has the responsibility for initializing the interactors in the
	 * application, and taking care of any other initialization that needs to be
	 * performed.
	 */
	@Override
	public void init() {
		firstLines();
		picture();
		status();
		friend();

	}

//creates the buttons add delete and look up
	private void firstLines() {
		namefield = new JLabel("Name: ");
		add(namefield, NORTH);
		name = new JTextField(15);
		add(name, NORTH);
		name.addActionListener(this);
		adding = new JButton("Add");
		add(adding, NORTH);
		delete = new JButton("Delete");
		add(delete, NORTH);
		lookup = new JButton("LookUp");
		add(lookup, NORTH);
	}

//creates button for adding picture
	private void picture() {
		status = new JTextField(TEXT_FIELD_SIZE);
		add(status, WEST);
		status.addActionListener(this);
		changeStatus = new JButton("Change Status");
		add(changeStatus, WEST);
		add(new JLabel(EMPTY_LABEL_TEXT), WEST);
		picture = new JTextField(TEXT_FIELD_SIZE);
		picture.addActionListener(this);
		add(picture, WEST);

	}

//creates button for status
	private void status() {
		changePicture = new JButton("Change Picture");
		add(changePicture, WEST);
		add(new JLabel(EMPTY_LABEL_TEXT), WEST);
		friend = new JTextField(TEXT_FIELD_SIZE);
		add(friend, WEST);
	}

//creates burron for adding friend
	private void friend() {
		addFriend = new JButton("Add Friend");
		add(addFriend, WEST);
		addActionListeners();
		add(canvas);
	}

	/**
	 * This class is responsible for detecting when the buttons are clicked or
	 * interactors are used, so you will have to add code to respond to these
	 * actions.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Add")) {
			addingProf();
		} else if (e.getActionCommand().equals("Delete")) {
			deleteProf();
		} else if (e.getActionCommand().equals("LookUp")) {
			lookUpProf();
		} else if (e.getSource() == changeStatus || e.getSource() == status) {
			changeStatusProf();
		} else if (e.getSource() == changePicture || e.getSource() == picture) {
			changeProfProf();
		} else if (e.getSource() == addFriend || e.getSource() == friend) {
			addFriendProf();
		}

	}

//adds new profile
	private void addingProf() {
		if (name.getText().isEmpty()) {
			canvas.showMessage("name field is empty");
		} else {
			if (correctWord()) {
				if (base.containsProfile(name.getText()) == true) {
					canvas.displayProfile(base.getProfile(name.getText()));
					canvas.showMessage("A profile with name " + name.getText() + " already exists.");
					currentProfile = base.getProfile(name.getText());
				} else {
					FacePamphletProfile account = new FacePamphletProfile(name.getText());
					base.addProfile(account);
					canvas.displayProfile(account);
					canvas.showMessage("Account Created");
					currentProfile = account;
				}
			} else {
				canvas.showMessage("Dont use Space in the beginning");
			}
		}

	}

//deletes the profile shown on canvas
	private void deleteProf() {
		if (currentProfile != null) {
			if (correctWord()) {
				if (base.containsProfile(name.getText())) {
					canvas.removeAll();
					currentProfile = null;
					base.deleteProfile(name.getText());
					canvas.showMessage("Account Deleted");
				} else if (!base.containsProfile(name.getText())) {
					canvas.showMessage("Account of " + name.getText() + " doesn't exist");
				}
			} else {
				canvas.showMessage("Dont use Space in the beginning");
			}
		} else {
			canvas.showMessage("Create profile to delete");
		}

	}

//checks for already created profile
	private void lookUpProf() {
		if (currentProfile != null) {
			if (correctWord()) {
				if (base.containsProfile(name.getText()) == true) {
					canvas.removeAll();
					canvas.displayProfile(base.getProfile(name.getText()));
					canvas.showMessage("Displaying " + name.getText());
				} else {
					canvas.removeAll();
					canvas.showMessage("A profile with name " + name.getText() + " does not exist.");
					currentProfile = null;
				}
			} else {
				canvas.showMessage("Dont use Space in the beginning");
			}
		} else {
			canvas.showMessage("create profila to look up");
		}
	}

//changes the status of an account
	private void changeStatusProf() {
		if (currentProfile != null) {
			if (correctWord()) {
				if (currentProfile == null) {
					canvas.showMessage("Please select an account to change status");
				} else {

					base.getProfile(currentProfile.getName())
							.setStatus(base.getProfile(currentProfile.getName()).getName() + " is " + status.getText());
					canvas.displayProfile(base.getProfile(currentProfile.getName()));
					canvas.showMessage("Status updated to " + status.getText());
				}
			} else {
				canvas.showMessage("Dont use Space in the beginning");
			}
		} else {
			canvas.showMessage("create profile to change status");
		}
	}

//changes the profile picture of an account
	private void changeProfProf() {
		if (currentProfile != null) {
			if (correctWord()) {
				if (currentProfile != null) {
					FacePamphletProfile profile = base.getProfile(currentProfile.getName());
					GImage image = null;
					try {
						image = new GImage(picture.getText());
						profile.setImage(image);
					} catch (ErrorException ex) {
						image = null;
					}
					canvas.displayProfile(profile);
					if (image == null) {
						canvas.showMessage("Unable to open file " + picture.getText());
					} else {
						canvas.showMessage("Picture uploaded");
					}
				} else {
					println("Please select a profile to change picture");
				}
			} else {
				canvas.showMessage("Dont use Space in the beginning");
			}
		} else {
			canvas.showMessage("Create profile to cahneg prof pic");

		}
	}

//adds the friend in the account friend list
	private void addFriendProf() {
		if (currentProfile != null) {

			if (correctWord()) {
				FacePamphletProfile profile = base.getProfile(currentProfile.getName());
				if (base.containsProfile(friend.getText())) {
					if (containsFriend(friend.getText())) {
						canvas.showMessage("Friend already Exists");
					} else {
						currentProfile.addFriend(friend.getText());
						base.getProfile(friend.getText()).addFriend(name.getText());
					}
				} else {
					canvas.showMessage("This profile doesn't exist");
				}
				canvas.displayProfile(profile);
			} else {
				canvas.showMessage("Dont use Space in the beginning");
			}
		} else {
			canvas.showMessage("Create profile to add friends");
		}
	}

//checks if an account already contains a friend
	private boolean containsFriend(String friendName) {
		Iterator<String> it = base.getProfile(currentProfile.getName()).getFriends();
		while (it.hasNext()) {
			if (friendName.equals(it.next())) {
				return true;
			}
		}
		return false;
	}

//checks if the words contains any spaces in the beggining
	private boolean correctWord() {
		if (name.getText().startsWith(" ")) {
			return false;
		} else if (status.getText().startsWith(" ")) {
			return false;
		} else if (picture.getText().startsWith(" ")) {
			return false;
		} else if (friend.getText().startsWith(" ")) {
			return false;
		}
		return true;
	}
}
