package hust.soict.hedspi.aims.media;

import java.util.ArrayList;
import java.util.List;

public class CompactDisc extends Disc implements Playable{

	private String artist;
	private List<Track> tracks = new ArrayList<Track>();

	
	public String getArtist() {
		return artist;
	}


	public void setArtist(String artist) {
		this.artist = artist;
	}


	public CompactDisc() {
		// TODO Auto-generated constructor stub
	}
	public CompactDisc(int id,String title, String category, String director, int length, float cost) {
		super();
		setId(id);
		setTitle(title);
		setCategory(category);
		setCost(cost);
		setDirector(director);
		setLength(length);
	}
	
	public void addTrack(Track track) {
		boolean flag = true;
		for (int i=0; i<tracks.size(); ++i) {
			if(tracks.get(i).equals(track)) {
				System.out.println("Track already exists");
				flag = false;
				break;
			}
		}
		if(flag)tracks.add(track);
		System.out.println("Track added");
	}
	public void removeTrack(Track track) {
		boolean flag = true;
		for (int i=0; i<tracks.size(); ++i) {
			if(tracks.get(i).equals(track)) {
				tracks.remove(i);
				System.out.println("Track removed");
				flag = false;
				break;
			}
		}
		if(flag)System.out.println("Track is not found");
	}
	
	// get sum of track length
	public int getLength() {
		int sum=0;
		for (int i=0; i<tracks.size(); ++i) {
			sum+=tracks.get(i).getLength();
		}
		return sum;
	}


	@Override
	public void play() {
		// TODO Auto-generated method stub
		System.out.println("Playing CD: " + this.getTitle());
		System.out.println("DVD length: " + this.getLength());
		for(int i=0; i<tracks.size(); ++i) {
			tracks.get(i).play();
		}
	}
	
	@Override
	public String toString() {
		return "CompactDisc | id: " + getId() +
				" title: " + getTitle() +
				" category: " + getCategory() +
				" director: " + getDirector() +
				" length: " + getLength() +
				" cost: " + getCost();
	}
}
