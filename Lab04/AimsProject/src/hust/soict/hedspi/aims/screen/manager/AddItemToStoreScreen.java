package hust.soict.hedspi.aims.screen.manager;

import java.awt.BorderLayout;
import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.JPanel;

import hust.soict.hedspi.aims.store.Store;

public abstract class AddItemToStoreScreen extends JFrame{
	protected Store store;
	private StoreManagerScreen smScreen;
	public AddItemToStoreScreen(Store store) {
		this.store = store;
		smScreen = new StoreManagerScreen(store);
		
	    Container cp = getContentPane();
	    cp.setLayout(new BorderLayout());
	    cp.add(smScreen.createNorth(), BorderLayout.NORTH);
	    cp.add(addItemPanel(), BorderLayout.CENTER);
	    //cp.add(createCenter(), BorderLayout.CENTER);

	    setTitle("Store");
	    setSize(1024, 768);
	    setLocationRelativeTo(null);
	    setVisible(true);
	}
	public abstract JPanel addItemPanel();
}
