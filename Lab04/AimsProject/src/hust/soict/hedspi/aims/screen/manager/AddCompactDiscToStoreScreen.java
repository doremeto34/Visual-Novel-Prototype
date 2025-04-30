package hust.soict.hedspi.aims.screen.manager;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import hust.soict.hedspi.aims.media.DigitalVideoDisc;
import hust.soict.hedspi.aims.store.Store;

public class AddCompactDiscToStoreScreen extends AddItemToStoreScreen{

	public AddCompactDiscToStoreScreen(Store store) {
		super(store);
		// TODO Auto-generated constructor stub
	}
	@Override
	public JPanel addItemPanel() {
	    JPanel panel = new JPanel();
	    panel.setLayout(new GridLayout(7, 2, 5, 5));

	    JTextField idField = new JTextField();
	    JTextField titleField = new JTextField();
	    JTextField categoryField = new JTextField();
	    JTextField directorField = new JTextField();
	    JTextField lengthField = new JTextField();
	    JTextField costField = new JTextField();

	    JButton addButton = new JButton("Add DVD");

	    addButton.addActionListener(e -> {
	        try {
	            int id = Integer.parseInt(idField.getText());
	            String title = titleField.getText();
	            String category = categoryField.getText();
	            String director = directorField.getText();
	            int length = Integer.parseInt(lengthField.getText());
	            float cost = Float.parseFloat(costField.getText());

	            DigitalVideoDisc dvd = new DigitalVideoDisc(id, title, category, director, length, cost);
	            store.addMedia(dvd);

	            JOptionPane.showMessageDialog(panel, "DVD added successfully!");
	            idField.setText(""); titleField.setText(""); categoryField.setText("");
	            directorField.setText(""); lengthField.setText(""); costField.setText("");

	        } catch (NumberFormatException ex) {
	            JOptionPane.showMessageDialog(panel, "Invalid input! Check ID, Length, and Cost.", "Error", JOptionPane.ERROR_MESSAGE);
	        }
	    });

	    panel.add(new JLabel("ID:")); panel.add(idField);
	    panel.add(new JLabel("Title:")); panel.add(titleField);
	    panel.add(new JLabel("Category:")); panel.add(categoryField);
	    panel.add(new JLabel("Director:")); panel.add(directorField);
	    panel.add(new JLabel("Length (min):")); panel.add(lengthField);
	    panel.add(new JLabel("Cost:")); panel.add(costField);
	    panel.add(new JLabel()); panel.add(addButton);

	    return panel;
	}

}
