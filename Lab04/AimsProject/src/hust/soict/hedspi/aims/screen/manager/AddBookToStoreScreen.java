package hust.soict.hedspi.aims.screen.manager;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import hust.soict.hedspi.aims.media.Book;
import hust.soict.hedspi.aims.store.Store;

public class AddBookToStoreScreen extends AddItemToStoreScreen{

	public AddBookToStoreScreen(Store store) {
		super(store);
		
	}
	@Override
	public JPanel addItemPanel() {
	    JPanel panel = new JPanel();
	    panel.setLayout(new GridLayout(6, 2, 5, 5)); // increased rows to 6

	    // Form labels and fields
	    JLabel idLabel = new JLabel("ID:");
	    JTextField idField = new JTextField();

	    JLabel titleLabel = new JLabel("Title:");
	    JTextField titleField = new JTextField();

	    JLabel categoryLabel = new JLabel("Category:");
	    JTextField categoryField = new JTextField();

	    JLabel costLabel = new JLabel("Cost:");
	    JTextField costField = new JTextField();

	    JLabel authorsLabel = new JLabel("Authors (comma-separated):");
	    JTextField authorsField = new JTextField();

	    JButton addButton = new JButton("Add Book");

	    addButton.addActionListener(e -> {
	        try {
	            int id = Integer.parseInt(idField.getText());
	            String title = titleField.getText();
	            String category = categoryField.getText();
	            float cost = Float.parseFloat(costField.getText());

	            Book newBook = new Book(id, title, category, cost);

	            String authorsInput = authorsField.getText();
	            if (!authorsInput.isBlank()) {
	                String[] authors = authorsInput.split(",");
	                for (String author : authors) {
	                    newBook.addAuthor(author.trim());
	                }
	            }

	            store.addMedia(newBook);

	            JOptionPane.showMessageDialog(panel, "Book added successfully!");

	            // Clear fields
	            idField.setText("");
	            titleField.setText("");
	            categoryField.setText("");
	            costField.setText("");
	            authorsField.setText("");

	        } catch (NumberFormatException ex) {
	            JOptionPane.showMessageDialog(panel, "Please enter valid numeric values for ID and Cost.", "Input Error", JOptionPane.ERROR_MESSAGE);
	        }
	    });

	    panel.add(idLabel); panel.add(idField);
	    panel.add(titleLabel); panel.add(titleField);
	    panel.add(categoryLabel); panel.add(categoryField);
	    panel.add(costLabel); panel.add(costField);
	    panel.add(authorsLabel); panel.add(authorsField);
	    panel.add(new JLabel()); panel.add(addButton);

	    return panel;
	}


}
