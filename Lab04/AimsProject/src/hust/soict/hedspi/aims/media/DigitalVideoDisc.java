package hust.soict.hedspi.aims.media;
public class DigitalVideoDisc extends Disc implements Playable{
	
	public DigitalVideoDisc() {
		//
	}
	public DigitalVideoDisc(int id,String title, String category, String director, int length, float cost) {
		super();
		setId(id);
		setTitle(title);
		setCategory(category);
		setCost(cost);
		setDirector(director);
		setLength(length);
	}
	
	// Comparison
	public boolean isMatchID(int id) {
		if(getId() == id) return true;
		return false;
	}
	
	public boolean isMatchTitle(String title) {
		if (getTitle().toLowerCase().contains(title.toLowerCase()))
			return true;
		return false;
	}
	@Override
	public void play() {
		// TODO Auto-generated method stub
		System.out.println("Playing DVD: " + this.getTitle());
		System.out.println("DVD length: " + this.getLength());
	}
	
	@Override
	public String toString() {
		return "DigitalVideoDisc | id: " + getId() +
				" title: " + getTitle() +
				" category: " + getCategory() +
				" director: " + getDirector() +
				" length: " + getLength() +
				" cost: " + getCost();
	}
}
 
 
 
 