package hust.soict.hedspi.aims.cart;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import hust.soict.hedspi.aims.media.DigitalVideoDisc;
import hust.soict.hedspi.aims.media.Media;
import hust.soict.hedspi.aims.media.Track;

public class Cart {
	// Max number of disc
	public static final int MAX_NUMBERS_ORDERED = 20;
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
	// get cost
	public float totalCost() {
		float sum=0;
		for(int i=0;i<itemsOrdered.size();++i)
			sum+=itemsOrdered.get(i).getCost();
		return sum;
	}
	
	// print cart
	public void displayCart() {
		if(itemsOrdered.isEmpty())
			System.out.print("The cart is empty");
		else {
			System.out.println("***********************CART***********************");
			for(Media media: itemsOrdered) {
				System.out.println(media.toString());
			}
			System.out.println("Total cost: " + totalCost());
			System.out.println("**************************************************");
			
		}
	}
	
	// search by ID
	public void seachByID(int id) {
		if(itemsOrdered.isEmpty())
			System.out.print("The cart is empty");
		else {
			int mediaCnt = 0;
			for(Media media: itemsOrdered) {
				String mediaStr = Integer.toString(media.getId());
		        String idStr = Integer.toString(id);
		        if(mediaStr.contains(idStr)) {
					System.out.println(media.toString());
					++mediaCnt;
		        }
			}
			if(mediaCnt == 0)System.out.println("No Media with alike ID found");
		}
	}
	
	// search by title
	public void seachByTitle(String title) {
		if(itemsOrdered.isEmpty())
			System.out.print("The cart is empty");
		else {
			int mediaCnt = 0;
			for(Media media: itemsOrdered) {
				if(media.getTitle().toLowerCase().contains(title.toLowerCase())) {
					System.out.println(media.toString());
					++mediaCnt;
				}
			}
			if(mediaCnt == 0)System.out.println("No Media with alike title found");
		}
	}
	
	public void sortByTitle() {
		Collections.sort(itemsOrdered,Media.COMPARE_BY_TITLE_COST);
	}
	public void sortByCost() {
		Collections.sort(itemsOrdered,Media.COMPARE_BY_COST_TITLE);
	}
	public void emptyCart() {
		itemsOrdered.removeAll(itemsOrdered);
	}
}
 
 