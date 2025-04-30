package hust.soict.hedspi.aims.media;

import java.util.Comparator;

public class MediaComparatorByCostTitle implements 	Comparator<Media>{

	public MediaComparatorByCostTitle() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int compare(Media o1, Media o2) {
		// TODO Auto-generated method stub
		if(o1.getCost() == o2.getCost()) {
			if(o1.getTitle().equals(o2.getTitle())) return 0;
			return o1.getTitle().compareTo(o2.getTitle());
			
		}else
		if(o1.getCost() < o2.getCost()) return -1; else
		return 1;
		
	}
	
}
