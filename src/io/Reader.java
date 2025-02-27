package io;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Locale;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import core.Alphabets;
import core.Translator;
import i18n.I18n;

/**
 * Ein Objekt der Klasse <code>ReaderOldHungarian</code>
 */
public class Reader {

	private static final I18n I18N = I18n.getInstance(Locale.getDefault());
	
	private Alphabets alphabet;
	private Font loadedFont;

	/**
	 * Konstruktor; lädt die Schriftart.
	 * 
	 * @param alphabet
	 */
	public Reader(Alphabets alpht) {
		alphabet = alpht;
		loadFont();
	}

	/**
	 * Setzt die Schriftart, diese wird für die Hin- und für die Herübersetzung
	 * benötigt.
	 */
	private void loadFont() {
		try {
			Path fontPath = Paths.get(System.getProperty("user.dir"), "src", "ttfs",
					"NotoSansOldHungarian-Regular.ttf");
			loadedFont = Font.createFont(Font.TRUETYPE_FONT, fontPath.toFile()).deriveFont(24f);
			GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(loadedFont);
		} catch (Exception e) {
			showErrorDialog("Fehler beim Laden des Fonts.");
			e.printStackTrace();
		}
	}

	/**
	 * Liest die Datei ein und übersetzt diese.
	 */
	public void read() {
		SwingUtilities.invokeLater(() -> {
			Path filePath = chooseFile();
			if (filePath == null)
				return;

			String content = readFileContent(filePath);
			if (content == null)
				return;

			if (alphabet == Alphabets.OLDHUNGARIAN) {
				content = Translator.translateToLatinEntireText(new StringBuilder(content)).toString();
			}

			else if (alphabet == Alphabets.LATIN) {
				content = Translator.translateToOldHungarianEntireText(new StringBuilder(content)).toString();
			}

			showTextWindow(content); 
		});
	}

	/**
	 * Zeigt den übersetzten Text
	 * 
	 * @param text
	 */
	private void showTextWindow(String text) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(600, 400);
		frame.setResizable(false);

		// Lade das Icon
		ImageIcon icon = new ImageIcon(System.getProperty("user.dir") + "\\src\\lib\\ico.png");
		frame.setIconImage(icon.getImage());

		JTextArea textArea = new JTextArea(text);
		textArea.setFont(loadedFont);
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		textArea.setEditable(false);

		JButton saveButton = new JButton(I18N.getValue("Save"));
		saveButton.setMinimumSize(new Dimension(100, 30));
		saveButton.addActionListener(e -> saveFile(frame, textArea));

		JButton clearButton = new JButton("Del");
		clearButton.setMinimumSize(new Dimension(100, 30));
		clearButton.addActionListener(e -> textArea.setText(""));

		// JButton erstellen
		JButton copyButton = new JButton(I18N.getValue("Copy"));
		copyButton.setMinimumSize(new Dimension(100, 30));
		frame.add(copyButton, BorderLayout.SOUTH);

		// ActionListener für den Button
		copyButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String text = textArea.getText();
				if (!text.isEmpty()) {
					Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
					StringSelection selection = new StringSelection(text);
					clipboard.setContents(selection, null);
					JOptionPane.showMessageDialog(frame, "Text wurde in die Zwischenablage kopiert!");
				}
			}
		});

		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		buttonPanel.add(saveButton);
		buttonPanel.add(clearButton);
		buttonPanel.add(copyButton);

		frame.add(new JScrollPane(textArea), BorderLayout.CENTER);
		frame.add(buttonPanel, BorderLayout.SOUTH);
		frame.setVisible(true);
	}

	private Path chooseFile() {
		JFileChooser fileChooser = new JFileChooser(new File(System.getProperty("user.home"), "Desktop"));

		// Icon setzen geht nicht.
		// ImageIcon icon = new ImageIcon(System.getProperty("user.dir") +
		// "\\src\\lib\\ico.png");
		// JLabel label = new JLabel(icon);
		// fileChooser.setAccessory(label);

		fileChooser.setDialogTitle("Choose file...");
		fileChooser.setFileFilter(new FileNameExtensionFilter("Textdateien (*.txt)", "txt"));

		if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			Path filePath = fileChooser.getSelectedFile().toPath();
			if (!filePath.toString().endsWith(".txt")) {
				showErrorDialog("Die ausgewählte Datei ist keine Textdatei (.txt).");
				return null;
			}
			return filePath;
		}
		return null;
	}

	private String readFileContent(Path filePath) {
		try (BufferedReader reader = new BufferedReader(
				new InputStreamReader(new FileInputStream(filePath.toFile()), StandardCharsets.UTF_8))) {
			StringBuilder content = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null) {
				content.append(line).append("\n");
			}
			return content.toString();
		} catch (IOException e) {
			showErrorDialog("Fehler beim Lesen der Datei.");
			e.printStackTrace();
			return null;
		}
	}

	private void saveFile(JFrame frame, JTextArea textArea) {
		JFileChooser fileChooser = new JFileChooser(new File(System.getProperty("user.home"), "Desktop"));
		fileChooser.setFileFilter(new FileNameExtensionFilter("Textdateien (*.txt)", "txt"));

		if (fileChooser.showSaveDialog(frame) == JFileChooser.APPROVE_OPTION) {
			Path filePath = fileChooser.getSelectedFile().toPath();
			if (!filePath.toString().endsWith(".txt")) {
				filePath = Paths.get(filePath + ".txt");
			}

			try {
				Files.writeString(filePath, textArea.getText(), StandardCharsets.UTF_8);
				textArea.setText("");
			} catch (IOException e) {
				showErrorDialog("Fehler beim Speichern der Datei.");
				e.printStackTrace();
			}
		}
	}

	private void showErrorDialog(String message) {
		JOptionPane.showMessageDialog(null, message, "Fehler", JOptionPane.ERROR_MESSAGE);
	}

}
