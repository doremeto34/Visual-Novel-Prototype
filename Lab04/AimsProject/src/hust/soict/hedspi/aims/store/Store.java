package hust.soict.hedspi.aims.store;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import hust.soict.hedspi.aims.media.DigitalVideoDisc;
import hust.soict.hedspi.aims.media.Media;

public class Store {

	// Max number of disc
	public static final int MAX_NUMBERS_STORED = 36;
	private List<Media> itemsOrdered = new ArrayList<Media>();

	// add media
	public void addMedia(Media media) {
		if(itemsOrdered.contains(media)) {
			System.out.println("Media already exists");
		}else {
			itemsOrdered.add(media);
			System.out.println("Media is added");
		}
	}
	// remove media
	public void removeMedia(Media media) {
		if(itemsOrdered.contains(media)) {
			itemsOrdered.remove(media);
			System.out.println("Media is removed");
		}else {
			System.out.println("No media was found");
		}
	}
	// find title
	public Media findTitle(String title) {
		for(Media media: itemsOrdered) {
			if(media.getTitle().equals(title)) {
				return media;
			}
		}
		return null;
	}
	// play media
	public void playMedia(Media media) {
		
	}
	
	public void sortByTitle() {
		Collections.sort(itemsOrdered,Media.COMPARE_BY_TITLE_COST);
	}
	public void sortByCost() {
		Collections.sort(itemsOrdered,Media.COMPARE_BY_COST_TITLE);
	}
	
	public void displayStore() {
		for(Media media: itemsOrdered) {
			System.out.println(media.toString());
		}
	}
	// for exercise
	public List<Media> getItemsInStore() {
		return itemsOrdered;
	}
}
