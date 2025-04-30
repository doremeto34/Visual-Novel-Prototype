package hust.soict.hedspi.aims.disc;
public class DigitalVideoDisc {
	private int id;
	private String title;
	private String category;
	private String director;
	private int length;
	private float cost;
	public void setTitle(String title) {
        this.title = title;
    }
	public String getCategory() {
		return category;
	}
	public float getCost() {
		return cost;
	}
	public String getTitle() {
		return title;
	}
	public String getDirector() {
		return director;
	}
	public int getLength() {
		return length;
	}
	public int getID() {
		return id;
	}
	public DigitalVideoDisc(String title) {
		super();
		this.title = title;
	}
	public DigitalVideoDisc(String title, String category, float cost) {
		super();
		this.title = title;
		this.category = category;
		this.cost = cost;
	}
	public DigitalVideoDisc(String title, String category, String director, float cost) {
		super();
		this.title = title;
		this.category = category;
		this.director = director;
		this.cost = cost;
	}
	public DigitalVideoDisc(String title, String category, String director, int length, float cost) {
		super();
		this.title = title;
		this.category = category;
		this.director = director;
		this.length = length;
		this.cost = cost;
	}
	public DigitalVideoDisc(int id,String title, String category, String director, int length, float cost) {
		super();
		this.id= id;
		this.title = title;
		this.category = category;
		this.director = director;
		this.length = length;
		this.cost = cost;
	}
	
	// Comparison
	public boolean isMatchID(int id) {
		if(this.id == id) return true;
		return false;
	}
	
	public boolean isMatchTitle(String title) {
		if (this.title.toLowerCase().contains(title.toLowerCase()))
			return true;
		return false;
	}
}
 
 
 
 