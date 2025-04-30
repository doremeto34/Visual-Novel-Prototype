package hust.soict.hedspi.aims;
import hust.soict.hedspi.aims.media.Book;
import hust.soict.hedspi.aims.media.CompactDisc;
import hust.soict.hedspi.aims.media.DigitalVideoDisc;
import hust.soict.hedspi.aims.media.Media;
import hust.soict.hedspi.aims.store.Store;

import java.util.Collections;
import java.util.Scanner;

import hust.soict.hedspi.aims.cart.*;

public class Aims {
 
	public static Scanner scanner = new Scanner(System.in);
	static Store store = new Store();
	static Cart cart = new Cart();
	
	public static void main(String[] args) {
		CompactDisc cd1 = new CompactDisc(1,"xamvn.test","Pop","manhthuong",0,36);
		DigitalVideoDisc dvd4 = new DigitalVideoDisc(2,"dien dan xamvn","Country","xuanha",0,96);
		Book book1 = new Book(3,"1984","George Orwell",69);
		//cd1.setTitle(null);
		store.addMedia(book1);
		store.addMedia(dvd4);
		store.addMedia(cd1);
		cart.addMedia(book1);
		cart.addMedia(dvd4);
		cart.addMedia(cd1);
		
		showMenu();
	}
	// show menu
	public static void showMenu() {
		System.out.println("AIMS: ");
		System.out.println("--------------------------------");
		System.out.println("1. View store");
		System.out.println("2. Update store");
		System.out.println("3. See current cart");
		System.out.println("0. Exit");
		System.out.println("--------------------------------");
		System.out.println("Please choose a number: 0-1-2-3");
		//option
		String option = scanner.nextLine();
		if(option.equals("1")) {
			storeMenu();
		} else
		if(option.equals("2")) {
			//updateStore();
		} else
		if(option.equals("3")) {
			cartMenu();
		}
	}
	// show store menu
	public static void storeMenu() {
		System.out.println("Options: ");
		System.out.println("--------------------------------");
		System.out.println("1. See a media’s details");
		System.out.println("2. Add a media to cart");
		System.out.println("3. Play a media");
		System.out.println("4. See current cart");
		System.out.println("0. Back");
		System.out.println("--------------------------------");
		System.out.println("Please choose a number: 0-1-2-3-4");
		//option
		String option = scanner.nextLine();
		if(option.equals("1")) {
			findMediaDetails();
		}else
		if(option.equals("3")) {
			System.out.println("Enter media's title");
			String title = scanner.nextLine();
			Media media = store.findTitle(title);
			if(media == null) {
				System.out.println(title + " was not found");
				storeMenu();
			}else {
				System.out.println(media.toString());
				storeMenu();
			}
		} else
		if(option.equals("0")) {
			showMenu();
		}
	}
	// show media detail menu
	public static void mediaDetailsMenu(Media media) {
		System.out.println("Options: ");
		System.out.println("--------------------------------");
		System.out.println("1. Add to cart");
		System.out.println("2. Play");
		System.out.println("0. Back");
		System.out.println("--------------------------------");
		System.out.println("Please choose a number: 0-1-2");
		// option
		String option = scanner.nextLine();
		if(option.equals("1")) {
			cart.addMedia(media);
			System.out.println("Added media to cart");
			storeMenu();
		} else
		if(option.equals("2")) {
			if(media instanceof Book) {
				System.out.println("Can't play book");
			} else {
				media.play();
			}
		} else
		if(option.equals("0")) {
			storeMenu();
		}
	}
	// show cart menu
	public static void cartMenu() {
		System.out.println("Options: ");
		System.out.println("--------------------------------");
		System.out.println("1. Filter media in cart");
		System.out.println("2. Sort media in cart");
		System.out.println("3. Remove media from cart");
		System.out.println("4. Play a media");
		System.out.println("5. Place order");
		System.out.println("0. Back");
		System.out.println("--------------------------------");
		System.out.println("Please choose a number: 0-1-2-3-4-5");
		// option
		String option = scanner.nextLine();
		if(option.equals("1")) {
			displayCartFilter();
		} else
		if(option.equals("2")) {
			displayCartSort();
		} else
		if(option.equals("5")) {
			System.out.println("Order has been created");
			cart.emptyCart();
			cartMenu();
		} else
		if(option.equals("0")) {
			showMenu();
		}
	}
	
	public static void findMediaDetails(){
		System.out.println("Enter media's title");
		String title = scanner.nextLine();
		Media media = store.findTitle(title);
		if(media == null) {
			System.out.println(title + " was not found");
			storeMenu();
		}else {
			System.out.println(media.toString());
			mediaDetailsMenu(media);
		}	
	}
	
	public static void displayCartSort() {
		System.out.println("Option:");
		System.out.println("--------------------------------");
		System.out.println("1. Sort by title");
		System.out.println("2. Sort by cost");
		System.out.println("0. Back");
		System.out.println("--------------------------------");
		String option = scanner.nextLine();
		if(option.equals("0")) {
			cartMenu();
		}
		if(option.equals("1")) {
			cart.sortByTitle();
			cart.displayCart();
		}else
		if(option.equals("2")) {
			cart.sortByCost();
			cart.displayCart();
		}
		System.out.println("Enter any to return");
		option = scanner.nextLine();
		cartMenu();
	}
	
	public static void displayCartFilter() {
		System.out.println("Option:");
		System.out.println("--------------------------------");
		System.out.println("1. Filt by ID");
		System.out.println("2. Filt by title");
		System.out.println("0. Back");
		System.out.println("--------------------------------");
		String option = scanner.nextLine();
		if(option.equals("0")) {
			cartMenu();
		}
		if(option.equals("1")) {
			System.out.println("Enter the ID");
			int id = scanner.nextInt();
			cart.seachByID(id);
		}else
		if(option.equals("2")) {
			System.out.println("Enter the title");
			String title = scanner.nextLine();
			cart.seachByTitle(title);
		}
		System.out.println("Enter any to return");
		option = scanner.nextLine();
		cartMenu();
	}
}
 
 