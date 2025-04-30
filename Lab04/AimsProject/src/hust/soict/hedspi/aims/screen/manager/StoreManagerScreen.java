package hust.soict.hedspi.aims.screen.manager;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import hust.soict.hedspi.aims.media.*;
import hust.soict.hedspi.aims.store.Store;

public class StoreManagerScreen extends JFrame{
	
	private Store store;
	int curIndex = 0;
	
	public static void main(String[] args) {
    	Store store = new Store();
    	Book book1 = new Book(1, "Effective Java", "Programming", 45.99f);
        Book book2 = new Book(2, "Clean Code", "Software Engineering", 39.99f);
        Book book3 = new Book(3, "The Pragmatic Programmer", "Development", 42.50f);

        // DVDs
        DigitalVideoDisc dvd1 = new DigitalVideoDisc(4, "Inception", "Science Fiction", "Christopher Nolan", 148, 19.99f);
        DigitalVideoDisc dvd2 = new DigitalVideoDisc(5, "The Matrix", "Action", "Lana Wachowski", 136, 17.50f);
        DigitalVideoDisc dvd3 = new DigitalVideoDisc(6, "The Social Network", "Biography", "David Fincher", 120, 16.99f);

        // CDs
        CompactDisc cd1 = new CompactDisc(7, "Abbey Road", "Music", "George Martin", 47, 14.99f);
        CompactDisc cd2 = new CompactDisc(8, "Thriller", "Pop", "Quincy Jones", 42, 13.50f);
        CompactDisc cd3 = new CompactDisc(9, "The Dark Side of the Moon", "Rock", "Pink Floyd", 43, 15.00f);
        CompactDisc cd4 = new CompactDisc(10, "50 shades of cyan", "Rock", "Pink Floyd", 43, 15.00f);

        // Add all media to store
        store.addMedia(book1);
        store.addMedia(book2);
        store.addMedia(book3);

        store.addMedia(dvd1);
        store.addMedia(dvd2);
        store.addMedia(dvd3);

        store.addMedia(cd1);
        store.addMedia(cd2);
        store.addMedia(cd3);
        store.addMedia(cd4);
        
    	new StoreManagerScreen(store);

    }
	// main
	public StoreManagerScreen(Store store) {
	    this.store = store;
	    
	    Container cp = getContentPane();
	    cp.setLayout(new BorderLayout());
	    cp.add(createNorth(), BorderLayout.NORTH);
	    cp.add(createCenter(), BorderLayout.CENTER);
	    setTitle("Store");
	    setSize(1024, 768);
	    setLocationRelativeTo(null);
	    setVisible(true);
	}
	// create north panel
	JPanel createNorth() {
		JPanel north = new JPanel();
		north.setLayout(new BoxLayout(north, BoxLayout.Y_AXIS));
		north.add(createMenuBar());
		north.add(createHeader());
		return north;
	}
	// create menu bar
	JMenuBar createMenuBar() {
	    JMenu menu = new JMenu("Options");
	    JMenu smUpdateStore = new JMenu("Update Store");
	    
	    JMenuItem viewStoreMenu = new JMenuItem("View store");
	    viewStoreMenu.addActionListener(e -> {
	    	this.dispose();
	    	new StoreManagerScreen(store);
	    });
	    
	    JMenuItem addBookMenu = new JMenuItem("Add Book");
	    addBookMenu.addActionListener(e -> {
	    	this.dispose();
	        new AddBookToStoreScreen(store);
	    });

	    JMenuItem addCDMenu = new JMenuItem("Add CD");
	    addCDMenu.addActionListener(e -> {
	        this.dispose();
	        new AddCompactDiscToStoreScreen(store);  // assuming this class exists
	    });

	    JMenuItem addDVDMenu = new JMenuItem("Add DVD");
	    addDVDMenu.addActionListener(e -> {
	        this.dispose();
	        new AddDigitalVideoDiscToStoreScreen(store);  // assuming this class exists
	    });
	    menu.add(viewStoreMenu);
	    smUpdateStore.add(addBookMenu);
	    smUpdateStore.add(addCDMenu);
	    smUpdateStore.add(addDVDMenu);
	    menu.add(smUpdateStore);
	    //menu.add(addBookMenu);
	    
	    JMenuBar menuBar = new JMenuBar();
	    menuBar.setLayout(new FlowLayout(FlowLayout.LEFT));
	    menuBar.add(menu);
	    
	    return menuBar;
	}
	// create header
	JPanel createHeader() {
	    JPanel header = new JPanel();
	    header.setLayout(new BoxLayout(header, BoxLayout.X_AXIS));

	    JLabel title = new JLabel("AIMS");
	    title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 50));
	    title.setForeground(Color.CYAN);

	    header.add(Box.createRigidArea(new Dimension(10, 10)));
	    header.add(title);
	    header.add(Box.createHorizontalGlue());
	    header.add(Box.createRigidArea(new Dimension(10, 10)));

	    return header;
	}
	
	// create center
	JPanel createCenter() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
	    JPanel center = new JPanel();
	    JPanel control = new JPanel();
	    center.setLayout(new GridLayout(3, 3, 2, 2));
	    control.setLayout(new GridLayout(1,2,2,2));
	    List<Media> mediaInStore = store.getItemsInStore();
	    for (int i = curIndex; i < curIndex+9; i++) {
    		if(i < mediaInStore.size()) {
    			MediaStore cell = new MediaStore(mediaInStore.get(i));
		        center.add(cell);
    		}
	    }
	    
	    JButton prevButton = new JButton("Previous page");
	    JButton nextButton = new JButton("Next page");
	    
	    if(curIndex+9>mediaInStore.size())nextButton.setEnabled(false);
	    
	    prevButton.addActionListener(e -> {
	    	if(curIndex>0) {
	    		curIndex-=9;
	    		center.removeAll();
	    		for (int i = curIndex; i < curIndex+9; i++) {
		    		if(i < mediaInStore.size()) {
		    			MediaStore cell = new MediaStore(mediaInStore.get(i));
				        center.add(cell);
		    		}else
		    			center.add(new JPanel());
			    }
		    	panel.repaint();
		    	panel.revalidate();
	    	}
	    });
	    nextButton.addActionListener(e -> {
	    	if(curIndex+9<mediaInStore.size()) {
	    		curIndex+=9;
	    		center.removeAll();
		    	for (int i = curIndex; i < curIndex+9; i++) {
		    		if(i < mediaInStore.size()) {
		    			MediaStore cell = new MediaStore(mediaInStore.get(i));
				        center.add(cell);
		    		}else
		    			center.add(new JPanel());
			    }
		    	panel.repaint();
		    	panel.revalidate();
	    	}
	    });
	    
	    control.add(prevButton);
	    control.add(nextButton);
	    prevButton.setPreferredSize(new Dimension(200,100));
	    panel.add(center,BorderLayout.CENTER);
	    panel.add(control,BorderLayout.SOUTH);
	    return panel;
	}
	
}
