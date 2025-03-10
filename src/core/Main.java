package core;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.im.InputContext;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import i18n.I18n;
import io.Reader;
import log.MyLogger;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * The class <code>Main</code> generates a JFrame with two TextAreas. In the
 * TextArea below, Latin characters can be written. In the TextArea above, the
 * translation into Old Hungarian runes will appear.
 * 
 * @author agz
 * @version 1.0
 */
public class Main { 

	private static final MyLogger LOGGER  = new MyLogger(Main.class);
	
	private static final I18n I18N = I18n.getInstance(Locale.getDefault());

	/**
	 * Die Methode <code>main</code>
	 */
	public static void main(String[] args) {

		StringBuilder sb = new StringBuilder();
		StringBuilder sbLatin = new StringBuilder();

		// JFrame erstellen
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(660, 400);
		frame.setMinimumSize(new Dimension(650, 400));
		frame.setResizable(false);
		frame.setLayout(new BorderLayout());

		// Lade das Icon
		ImageIcon icon = new ImageIcon(System.getProperty("user.dir") + "\\src\\lib\\ico.png");
		frame.setIconImage(icon.getImage());

		// Obere TextArea (nicht bearbeitbar)
		JTextArea topTextArea = new JTextArea();
		topTextArea.setEditable(false);
		topTextArea.setBackground(Color.LIGHT_GRAY);
        
		// Untere TextArea (bearbeitbar)
		JTextArea bottomTextArea = new JTextArea();

		// Schriftart laden
		try {

			Font font = Font
					.createFont(Font.TRUETYPE_FONT,
							new java.io.File(
									System.getProperty("user.dir") + "\\src\\ttfs\\NotoSansOldHungarian-Regular.ttf"))
					.deriveFont(24f);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(font);

			topTextArea.setFont(font);
			bottomTextArea.setFont(font);

			// Event Listener für die untere TextArea
			bottomTextArea.getDocument().addDocumentListener(new DocumentListener() {
			    @Override
			    public void insertUpdate(DocumentEvent e) {
			        translateAndSetText();
			    }

			    @Override
			    public void removeUpdate(DocumentEvent e) {
			        translateAndSetText();
			    }

			    @Override
			    public void changedUpdate(DocumentEvent e) {
			        translateAndSetText();
			    }

			    private void translateAndSetText() {
			    	
			        String inputText = bottomTextArea.getText();
			       
					 LOGGER.info("Checking keyboard...");
						
					 Locale locale = InputContext.getInstance().getLocale();
			         String keyboard = checkKeyBoard(topTextArea, locale);
			        
			        if("QWERTZ".equals(keyboard) && inputText.length() > 1) {
			            KeyBoards.simulateQwertzTyping(inputText);	
			        }
			        
			        //TODO: Simulate for other layouts, too!
			        
			    }

				private String checkKeyBoard(JTextArea topTextArea, Locale locale) {
					List<String> qwertzLocales = Arrays.asList("de", "de_AT", "de_CH", "hu", "sl", "sk");
			         List<String> qwertyLocales = Arrays.asList("en", "en_US", "en_GB", "en_AU", "en_CA");

			         if (locale == null || (!qwertzLocales.contains(locale.getLanguage()) && !qwertyLocales.contains(locale.getLanguage()))) {
			             topTextArea.append("⚠ Achtung: Unterstütztes Tastaturlayout nicht erkannt! Das Programm könnte nicht korrekt funktionieren.\n");
			             return "";
			         }

			         if (qwertzLocales.contains(locale.getLanguage())) {
			        	 
			             return "QWERTZ";
			         
			         } else if (qwertyLocales.contains(locale.getLanguage())) {
			         
			        	 return "QWERTY";
			         
			         }
			         
			         topTextArea.append("⚠ Achtung: Unterstütztes Tastaturlayout nicht erkannt! Das Programm könnte nicht korrekt funktionieren.\n");
		             return "";
				}
			});
			
		} catch (Exception e) {

			e.printStackTrace();

		}

		
		// Scrollpane für beide TextAreas
		JScrollPane topScrollPane = new JScrollPane(topTextArea);
		JScrollPane bottomScrollPane = new JScrollPane(bottomTextArea);

		// Event Listener für die untere TextArea
		bottomTextArea.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				 
				Translator.translatorOldHungarianRepeatedLetters(sb, sbLatin, topTextArea, bottomTextArea);

				bottomTextArea.setText("");

			}

		});     

		// "DEL"-Button
		JButton deleteButton = new JButton("Del");
		deleteButton.setMinimumSize(new Dimension(100, 30));
		deleteButton.addActionListener(e -> {
			topTextArea.setText("");
			sb.setLength(0);
			bottomTextArea.setText("");
		});

		// "Delete First"-Button
		JButton deleteFirstButton = new JButton("<");
		deleteFirstButton.setMinimumSize(new Dimension(100, 30));
		deleteFirstButton.addActionListener(e -> {
			if (sb.length() > 0) {
				int runeLength = 2;
				int endIndex = sb.length();
				int startIndex = endIndex - runeLength;

				if (startIndex >= 0) {
					sb.delete(startIndex, endIndex);
					topTextArea.setText(sb.toString());
				}
			}
		});

		JButton saveButton = new JButton(I18N.getValue("Save"));  
		saveButton.setMinimumSize(new Dimension(100, 30));
		saveButton.addActionListener(e -> {
			fileChoser(sb, frame, topTextArea);
		});

		// "Delete First"-Button
		JButton readButtonRunes = new JButton(I18N.getValue("ReadRunes"));
		readButtonRunes.setMinimumSize(new Dimension(110, 30));
		readButtonRunes.addActionListener(e -> {

			Reader roh = new Reader(Alphabets.OLDHUNGARIAN);
			roh.read();
		});

		// "Delete First"-Button
		JButton readButtonLatin = new JButton(I18N.getValue("ReadLatin"));
		readButtonLatin.setMinimumSize(new Dimension(110, 30));
		readButtonLatin.addActionListener(e -> {

			Reader roh = new Reader(Alphabets.LATIN);
			roh.read();

		});

		// JButton erstellen
		JButton copyButton = new JButton(I18N.getValue("Copy"));
		copyButton.setMinimumSize(new Dimension(100, 30));

		// ActionListener für den Button
		copyButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String text = topTextArea.getText();
				if (!text.isEmpty()) {
					Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
					StringSelection selection = new StringSelection(text);
					clipboard.setContents(selection, null);
					JOptionPane.showMessageDialog(frame,  I18N.getValue("TextCopy")); 
				}
			}
		});

		// JButton erstellen
		JButton qButton = new JButton(I18N.getValue("Paste")); 
		qButton.setMinimumSize(new Dimension(100, 30));

		// ActionListener für den Button
		qButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				  openPanel();
			}

		private void openPanel() {

			
        // Neues Panel erstellen
        JPanel panel = new JPanel(new BorderLayout());
        panel.setPreferredSize(new Dimension(300, 200));

        // TextArea in die Mitte
        JTextArea textArea = new JTextArea(5, 20);
        panel.add(new JScrollPane(textArea), BorderLayout.CENTER);

		
		// Schriftart laden
		try {

			Font font = Font
					.createFont(Font.TRUETYPE_FONT,
							new java.io.File(
									System.getProperty("user.dir") + "\\src\\ttfs\\NotoSansOldHungarian-Regular.ttf"))
					.deriveFont(24f);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(font);

			textArea.setFont(font);

					
		} catch (Exception e) {

			e.printStackTrace();

		}
        
        // Button-Panel unten links
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        JButton btn1 = new JButton("Del");
        btn1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				textArea.setText("");
			}
		});
		
        JButton btn2 = new JButton(I18N.getValue("Translate"));
        btn2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String content = textArea.getText();
				textArea.setText("");
				if (content == null)
					return;

				content = Translator.translateToLatinEntireText(new StringBuilder(content)).toString();
				
				textArea.setText(content);
			}
        	
        	
        });
        
        buttonPanel.add(btn1);
        buttonPanel.add(btn2);

        panel.add(buttonPanel, BorderLayout.SOUTH);

        // Neues Fenster/Dialog öffnen
        JDialog dialog = new JDialog();
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.add(panel);
        dialog.pack();
        dialog.setVisible(true);
    }

		});

		
		// Panels für bessere Anordnung
		JPanel centerPanel = new JPanel(new GridLayout(2, 1));
		centerPanel.add(topScrollPane);
		centerPanel.add(bottomScrollPane);

		JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		bottomPanel.add(deleteButton);
		bottomPanel.add(deleteFirstButton);
		bottomPanel.add(saveButton);
		bottomPanel.add(readButtonRunes);
		bottomPanel.add(readButtonLatin);
		bottomPanel.add(copyButton);
		bottomPanel.add(qButton);

		// Komponenten zum Frame hinzufügen
		frame.add(centerPanel, BorderLayout.CENTER);
		frame.add(bottomPanel, BorderLayout.SOUTH);

		// Frame sichtbar machen
		frame.setVisible(true);


		
	}

	
	private static void fileChoser(StringBuilder sb, JFrame frame, JTextArea topTextArea) {

		JFileChooser fileChooser = new JFileChooser();
		// Setze das aktuelle Verzeichnis auf den Desktop

		fileChooser.setCurrentDirectory(new File(System.getProperty("user.home"), "Desktop"));

		// Nice to have.
		// ImageIcon icon = new ImageIcon(System.getProperty("user.dir") +
		// "\\src\\lib\\ico.png");
		// JLabel label = new JLabel(icon);
		// fileChooser.setAccessory(label);

		FileNameExtensionFilter filter = new FileNameExtensionFilter("Textdateien (*.txt)", "txt");
		fileChooser.setFileFilter(filter);
		int result = fileChooser.showSaveDialog(frame);

		if (result == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			// Überprüfen, ob die Datei die richtige Endung hat
			if (!selectedFile.getAbsolutePath().endsWith(".txt")) {
				selectedFile = new File(selectedFile + ".txt");
			}
			try (BufferedWriter writer = new BufferedWriter(new FileWriter(selectedFile))) {
				writer.write(topTextArea.getText());
				topTextArea.setText("");
				sb.setLength(0);
			} catch (IOException ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(frame, "Fehler beim Speichern der Datei.", "Fehler",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
