package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import main.GameScreen;
import main.PlayerStats;
import main.TitleScreen;
import story.Scene;
import story.SceneManager;
import util.SaveManager;

public class SaveLoadScreen extends JFrame {
    private GameScreen gameScreen;
    private TitleScreen titleScreen;
    private SceneManager sceneManager;
    private boolean isSaving; // true = save mode, false = load mode
    private static final int SLOT_COUNT = 6;

    public SaveLoadScreen(GameScreen gameScreen, TitleScreen titleScreen, boolean isSaving) {
        this.gameScreen = gameScreen;
        this.titleScreen = titleScreen;
        this.isSaving = isSaving;
        sceneManager = new SceneManager(null,null);
        

        setTitle(isSaving ? "Save Game" : "Load Game");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel slotsPanel = new JPanel(new GridLayout(2, 3, 20, 20));
        slotsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        slotsPanel.setOpaque(false);

        for (int i = 1; i <= SLOT_COUNT; i++) {
            int slotNumber = i;
            JPanel slotContainer = new JPanel(new BorderLayout(5, 5));
            slotContainer.setOpaque(false);

            JButton slotButton = createThumbnailButton(slotNumber);
            slotButton.addActionListener(e -> handleSlot(slotNumber));

            JLabel dateLabel = new JLabel(getSaveFileDate("save" + slotNumber + ".dat"), SwingConstants.CENTER);
            dateLabel.setForeground(Color.WHITE);

            slotContainer.add(slotButton, BorderLayout.CENTER);
            slotContainer.add(dateLabel, BorderLayout.SOUTH);

            slotsPanel.add(slotContainer);
        }

        add(slotsPanel, BorderLayout.CENTER);

        // Back button
        JButton backButton = createStyledButton("⬅ Back");
        backButton.setPreferredSize(new Dimension(150, 40));
        backButton.addActionListener(e -> {
            if (gameScreen != null) {
                dispose();
            } else {
                new TitleScreen();
                dispose();
            }
        });

        JPanel backPanel = new JPanel();
        backPanel.setOpaque(false);
        backPanel.add(backButton);

        add(backPanel, BorderLayout.SOUTH);

        // Set background color
        getContentPane().setBackground(new Color(30, 30, 30));

        setVisible(true);
    }

    private void handleSlot(int slotIndex) {
        String filename = "save" + slotIndex + ".dat";
        System.out.println(filename);

        if (isSaving) {
            if (gameScreen != null) {
                SaveManager.saveGame(gameScreen.getPlayerStats(), filename);
                JOptionPane.showMessageDialog(this, "Game saved to " + filename + "!");
                dispose();
            }
        } else {
            PlayerStats stats = SaveManager.loadGame(filename);
            if (stats != null) {
                stats.dialogueIndex--;
                if (gameScreen != null) {
                    gameScreen.dispose();
                }
                new GameScreen(stats);
                dispose(); // Close load screen
            } else {
                JOptionPane.showMessageDialog(this, "No save file found!");
            }
        }
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                g2.setColor(new Color(70, 130, 180, 180)); // soft blue
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);

                super.paintComponent(g2);
                g2.dispose();
            }

            @Override
            protected void paintBorder(Graphics g) {
                // no border
            }
        };

        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("SansSerif", Font.BOLD, 16));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setPreferredSize(new Dimension(200, 60));
        return button;
    }

    private JButton createThumbnailButton(int slotNumber) {
        String filename = "save" + slotNumber + ".dat";
        PlayerStats stats = SaveManager.loadGame(filename);

        ImageIcon thumbnail = stats != null ? getSceneThumbnail(stats.sceneId) : getDefaultThumbnail();

        JButton button = new JButton((isSaving ? "Save" : "Load") + " Slot " + slotNumber, thumbnail);
        button.setHorizontalTextPosition(JButton.CENTER);
        button.setVerticalTextPosition(JButton.BOTTOM);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("SansSerif", Font.BOLD, 14));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setPreferredSize(new Dimension(200, 150));
        button.setBackground(new Color(70, 130, 180, 180));
        button.setFocusPainted(false);
        return button;
    }

    private ImageIcon getSceneThumbnail(int sceneId) {
        Scene scene = SceneManager.getScene(sceneId);
        if (scene != null && scene.backgroundPath != null) {
            String path = scene.backgroundPath;
            if (new File(path).exists()) {
                ImageIcon icon = new ImageIcon(path);
                Image scaledImage = icon.getImage().getScaledInstance(200, 100, Image.SCALE_SMOOTH);
                return new ImageIcon(scaledImage);
            }
        }
        return getDefaultThumbnail();
    }

    private ImageIcon getDefaultThumbnail() {
        // fallback image if no save or scene
        String path = "assets/default_thumbnail.png";
        if (new File(path).exists()) {
            ImageIcon icon = new ImageIcon(path);
            Image scaledImage = icon.getImage().getScaledInstance(200, 100, Image.SCALE_SMOOTH);
            return new ImageIcon(scaledImage);
        }
        return new ImageIcon(); // empty if even fallback missing
    }

    private String getSaveFileDate(String filename) {
        try {
            File file = new File(filename);
            if (file.exists()) {
                long lastModified = file.lastModified();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                return sdf.format(new Date(lastModified));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "No Data";
    }
}
