package hust.soict.hedspi.aims.media;

import java.util.ArrayList;
import java.util.List;

public class Book extends Media{
	private List<String> authors = new ArrayList<String>();
	//
	
	public Book() {
		// TODO Auto-generated constructor stub
		
	}
	public Book(int id,String title, String category, float cost) {
		super();
		setId(id);
		setTitle(title);
		setCategory(category);
		setCost(cost);
	}
	// add author
	public void addAuthor(String authorName) {
		boolean flag = true;
		for (int i=0; i<authors.size(); ++i) {
			if(authors.get(i).equals(authorName)) {
				System.out.println("Author already exists");
				flag = false;
				break;
			}
		}
		if(flag)authors.add(authorName);
		System.out.println("Author added");
	}
	public void removeAuthor(String authorName) {
		boolean flag = true;
		for (int i=0; i<authors.size(); ++i) {
			if(authors.get(i).equals(authorName)) {
				authors.remove(i);
				System.out.println("Author removed");
				flag = false;
				break;
			}
		}
		if(flag)System.out.println("Author is not found");
	}
	public void printAuthor() {
		for (int i=0; i<authors.size(); ++i) {
			System.out.print(authors.get(i) + ", ");
		}
	}
	
	@Override
	public String toString() {
		return "Book | id: " + getId() +
				" title: " + getTitle() +
				" category: " + getCategory() +
				" cost: " + getCost();
	}
}
