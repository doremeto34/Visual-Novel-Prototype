package hust.soict.hedspi.test.cart;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.media.Book;
import hust.soict.hedspi.aims.media.CompactDisc;
import hust.soict.hedspi.aims.media.DigitalVideoDisc;
import hust.soict.hedspi.aims.media.Media;

public class CartTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Create fuck new cart
		Cart cart = new Cart();
		
		// create dvd to add to fucking cart
		DigitalVideoDisc dvd1 =  new DigitalVideoDisc(1,"The Lion King","Animation","Roger Allers",87,19.95f);
		cart.addMedia(dvd1);
		
		DigitalVideoDisc dvd2 =  new DigitalVideoDisc(2,"SW","SF","George Lucas",87,24.95f);
		cart.addMedia(dvd2);
		
		DigitalVideoDisc dvd3 =  new DigitalVideoDisc(3,"Snow White","Animation","Disney",36,18.98f);
		cart.addMedia(dvd3);
		
		cart.removeMedia(dvd2);
		cart.displayCart();
		
		List<Media> mediae = new ArrayList<Media>();
		CompactDisc cd1 = new CompactDisc(1,"xamvn.test","Pop","manhthuong",0,36);
		DigitalVideoDisc dvd4 = new DigitalVideoDisc(2,"dien dan xamvn","Country","xuanha",0,96);
		Book book1 = new Book(3,"1984","George Orwell",69);
		//cd1.setTitle(null);
		mediae.add(cd1);
		mediae.add(dvd4);
		mediae.add(book1);
		Collections.sort(mediae,Media.COMPARE_BY_COST_TITLE);
		for(Media media: mediae) {
			System.out.println(media.toString());
		}
	}
	
}
