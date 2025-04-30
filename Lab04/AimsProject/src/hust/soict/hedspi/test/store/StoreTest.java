package hust.soict.hedspi.test.store;

import hust.soict.hedspi.aims.media.DigitalVideoDisc;
import hust.soict.hedspi.aims.store.*;

public class StoreTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Create fuck new cart
		Store store = new Store();
		
		// create dvd to add to fucking cart
		DigitalVideoDisc dvd1 =  new DigitalVideoDisc(1,"The Lion King","Animation","Roger Allers",87,19.95f);
		store.addMedia(dvd1);
		
		DigitalVideoDisc dvd2 =  new DigitalVideoDisc(2,"SW","SF","George Lucas",87,24.95f);
		store.addMedia(dvd2);
		
		DigitalVideoDisc dvd3 =  new DigitalVideoDisc(3,"Snow White","Animation","Disney",36,18.98f);
		store.addMedia(dvd3);
		
		store.removeMedia(dvd2);
	}

}
