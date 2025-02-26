import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Robot;
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
import java.util.Locale;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * The class <code>Main</code> generates a JFrame with two TextAreas. In the
 * TextArea below, Latin characters can be written. In the TextArea above, the
 * translation into Old Hungarian runes will appear.
 * 
 * @author agz
 * @version 1.0
 */
public class Main { 

	
	
	/**
	 * Die Methode <code>main</code>
	 */
	public static void main(String[] args) {

		StringBuilder sb = new StringBuilder();
		StringBuilder sbLatin = new StringBuilder();

		// JFrame erstellen
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 300);
		frame.setMinimumSize(new Dimension(500, 300));
		frame.setResizable(false);
		frame.setLayout(new BorderLayout());

		// Lade das Icon
		ImageIcon icon = new ImageIcon(System.getProperty("user.dir") + "\\src\\lib\\ico.png");
		frame.setIconImage(icon.getImage());

		// Obere TextArea (nicht bearbeitbar)
		JTextArea topTextArea = new JTextArea();
		topTextArea.setEditable(false);
		topTextArea.setBackground(Color.LIGHT_GRAY);

        if(!checkKeyBoard(topTextArea)) {
        	return;
        }
		
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

			///////////////////////////////////////////////////////////////////////////////////////////////////////////////

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
			       
			        if(inputText.length() > 1) {
			            simulateTyping(inputText);	
			        }
			        
			    }

				///////////////////////////////////////////////////////////////////////////////////////////////////////////////
			    


			    private static void simulateTyping(String text) {
			        try {
			            Robot robot = new Robot();
			            
			            for (char ch : text.toCharArray()) {
			                robot.delay(100);

			                switch (ch) {

			                case '?': 
			                    robot.keyPress(KeyEvent.VK_SHIFT);
			                    robot.keyPress(KeyEvent.VK_2);
			                    robot.keyRelease(KeyEvent.VK_2);
			                    robot.keyRelease(KeyEvent.VK_SHIFT);
			                    break;

			         
		                    // Ô, ô
		                    case 'Ô': case 'Ő':
		                        robot.keyPress(KeyEvent.VK_SHIFT);
		                        robot.keyPress(KeyEvent.VK_DEAD_CIRCUMFLEX);
		                        robot.keyRelease(KeyEvent.VK_DEAD_CIRCUMFLEX);
		                        robot.keyPress(KeyEvent.VK_O);
		                        robot.keyRelease(KeyEvent.VK_O);
		                        robot.keyRelease(KeyEvent.VK_SHIFT);
		                        break;
		                    case 'ô': case 'ő':
		                        robot.keyPress(KeyEvent.VK_DEAD_CIRCUMFLEX);
		                        robot.keyRelease(KeyEvent.VK_DEAD_CIRCUMFLEX);
		                        robot.keyPress(KeyEvent.VK_O);
		                        robot.keyRelease(KeyEvent.VK_O);
		                        break;

		                        // Ö, ö
		                           
		                    case 'Ö': case 'ö':
		                        robot.keyPress(KeyEvent.VK_SHIFT);
		                        robot.keyPress(KeyEvent.VK_ALT); // Alt-Taste drücken
		                        robot.keyPress(KeyEvent.VK_NUMPAD0);
		                        robot.keyPress(KeyEvent.VK_NUMPAD2);
		                        robot.keyPress(KeyEvent.VK_NUMPAD1);
		                        robot.keyPress(KeyEvent.VK_NUMPAD4); // Alt+0214 für Ö
		                        robot.keyRelease(KeyEvent.VK_ALT); // Alt-Taste loslassen
		                        robot.keyRelease(KeyEvent.VK_SHIFT);
		                        break;
		                 
		                       case 'Ü': case 'ü':
		                        robot.keyPress(KeyEvent.VK_SHIFT);
		                        robot.keyPress(KeyEvent.VK_ALT); // Alt-Taste drücken
		                        robot.keyPress(KeyEvent.VK_NUMPAD0);
		                        robot.keyPress(KeyEvent.VK_NUMPAD2);
		                        robot.keyPress(KeyEvent.VK_NUMPAD5);
		                        robot.keyPress(KeyEvent.VK_NUMPAD2); // Alt+0252 für ü
		                        robot.keyRelease(KeyEvent.VK_ALT); // Alt-Taste loslassen
		                        robot.keyRelease(KeyEvent.VK_SHIFT);
		                        break;
		                        
		                      /*
		                    case 'Ü':
		                        robot.keyPress(KeyEvent.VK_SHIFT);
		                        robot.keyPress(KeyEvent.VK_OPEN_BRACKET); // Auf deutscher Tastatur ist Ü dort
		                        robot.keyRelease(KeyEvent.VK_OPEN_BRACKET);
		                        robot.keyRelease(KeyEvent.VK_SHIFT);
		                        break;
		                        
		                    // ö, ü
		      
		                        
		                    case 'ü':
		                        robot.keyPress(KeyEvent.VK_OPEN_BRACKET);
		                        robot.keyRelease(KeyEvent.VK_OPEN_BRACKET);
		                        break;
		                        */
		                        
			                    // Û, û
			                    case 'Û': case 'Ű':
			                        robot.keyPress(KeyEvent.VK_SHIFT);
			                        robot.keyPress(KeyEvent.VK_DEAD_CIRCUMFLEX);
			                        robot.keyRelease(KeyEvent.VK_DEAD_CIRCUMFLEX);
			                        robot.keyPress(KeyEvent.VK_U);
			                        robot.keyRelease(KeyEvent.VK_U);
			                        robot.keyRelease(KeyEvent.VK_SHIFT);
			                        break;
			                    case 'û': case 'ű':
			                        robot.keyPress(KeyEvent.VK_DEAD_CIRCUMFLEX);
			                        robot.keyRelease(KeyEvent.VK_DEAD_CIRCUMFLEX);
			                        robot.keyPress(KeyEvent.VK_U);
			                        robot.keyRelease(KeyEvent.VK_U);
			                        break;
									
			                    // á, é, í, ó, ú
			                    case 'á':
			                        robot.keyPress(KeyEvent.VK_DEAD_ACUTE);
			                        robot.keyRelease(KeyEvent.VK_DEAD_ACUTE);
			                        robot.keyPress(KeyEvent.VK_A);
			                        robot.keyRelease(KeyEvent.VK_A);
			                        break;
			                    case 'é':
			                        robot.keyPress(KeyEvent.VK_DEAD_ACUTE);
			                        robot.keyRelease(KeyEvent.VK_DEAD_ACUTE);
			                        robot.keyPress(KeyEvent.VK_E);
			                        robot.keyRelease(KeyEvent.VK_E);
			                        break;
			                    case 'í':
			                        robot.keyPress(KeyEvent.VK_DEAD_ACUTE);
			                        robot.keyRelease(KeyEvent.VK_DEAD_ACUTE);
			                        robot.keyPress(KeyEvent.VK_I);
			                        robot.keyRelease(KeyEvent.VK_I);
			                        break;
			                    case 'ó':
			                        robot.keyPress(KeyEvent.VK_DEAD_ACUTE);
			                        robot.keyRelease(KeyEvent.VK_DEAD_ACUTE);
			                        robot.keyPress(KeyEvent.VK_O);
			                        robot.keyRelease(KeyEvent.VK_O);
			                        break;
			                    case 'ú':
			                        robot.keyPress(KeyEvent.VK_DEAD_ACUTE);
			                        robot.keyRelease(KeyEvent.VK_DEAD_ACUTE);
			                        robot.keyPress(KeyEvent.VK_U);
			                        robot.keyRelease(KeyEvent.VK_U);
			                        break;

			                    // Á, É, Í, Ó, Ú
			                    case 'Á':
			                        robot.keyPress(KeyEvent.VK_SHIFT);
			                        robot.keyPress(KeyEvent.VK_DEAD_ACUTE);
			                        robot.keyRelease(KeyEvent.VK_DEAD_ACUTE);
			                        robot.keyPress(KeyEvent.VK_A);
			                        robot.keyRelease(KeyEvent.VK_A);
			                        robot.keyRelease(KeyEvent.VK_SHIFT);
			                        break;
			                    case 'É':
			                        robot.keyPress(KeyEvent.VK_SHIFT);
			                        robot.keyPress(KeyEvent.VK_DEAD_ACUTE);
			                        robot.keyRelease(KeyEvent.VK_DEAD_ACUTE);
			                        robot.keyPress(KeyEvent.VK_E);
			                        robot.keyRelease(KeyEvent.VK_E);
			                        robot.keyRelease(KeyEvent.VK_SHIFT);
			                        break;
			                    case 'Í':
			                        robot.keyPress(KeyEvent.VK_SHIFT);
			                        robot.keyPress(KeyEvent.VK_DEAD_ACUTE);
			                        robot.keyRelease(KeyEvent.VK_DEAD_ACUTE);
			                        robot.keyPress(KeyEvent.VK_I);
			                        robot.keyRelease(KeyEvent.VK_I);
			                        robot.keyRelease(KeyEvent.VK_SHIFT);
			                        break;
			                    case 'Ó':
			                        robot.keyPress(KeyEvent.VK_SHIFT);
			                        robot.keyPress(KeyEvent.VK_DEAD_ACUTE);
			                        robot.keyRelease(KeyEvent.VK_DEAD_ACUTE);
			                        robot.keyPress(KeyEvent.VK_O);
			                        robot.keyRelease(KeyEvent.VK_O);
			                        robot.keyRelease(KeyEvent.VK_SHIFT);
			                        break;
			                    case 'Ú':
			                        robot.keyPress(KeyEvent.VK_SHIFT);
			                        robot.keyPress(KeyEvent.VK_DEAD_ACUTE);
			                        robot.keyRelease(KeyEvent.VK_DEAD_ACUTE);
			                        robot.keyPress(KeyEvent.VK_U);
			                        robot.keyRelease(KeyEvent.VK_U);
			                        robot.keyRelease(KeyEvent.VK_SHIFT);
			                        break;

			                    default: try { int keyCode = KeyEvent.getExtendedKeyCodeForChar(ch); if (keyCode != KeyEvent.VK_UNDEFINED) { robot.keyPress(keyCode); robot.keyRelease(keyCode); } else { System.out.println("Zeichen ignoriert: " + ch); } } catch (IllegalArgumentException e) { System.out.println("Ungültiges Zeichen übersprungen: " + ch); } break; 
			                        
			                }
			            }
			        } catch (AWTException e) {
			            e.printStackTrace();
			        }
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

		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

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

		JButton saveButton = new JButton("Speichern");
		saveButton.setMinimumSize(new Dimension(100, 30));
		saveButton.addActionListener(e -> {
			fileChoser(sb, frame, topTextArea);
		});

		// "Delete First"-Button
		JButton readButtonRunes = new JButton("Read Runes");
		readButtonRunes.setMinimumSize(new Dimension(110, 30));
		readButtonRunes.addActionListener(e -> {

			Reader roh = new Reader(Alphabets.OLDHUNGARIAN);
			roh.read();
		});

		// "Delete First"-Button
		JButton readButtonLatin = new JButton("Read Latin");
		readButtonLatin.setMinimumSize(new Dimension(110, 30));
		readButtonLatin.addActionListener(e -> {

			Reader roh = new Reader(Alphabets.LATIN);
			roh.read();

		});

		// JButton erstellen
		JButton copyButton = new JButton("Kopieren");
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
					JOptionPane.showMessageDialog(frame, "Text wurde in die Zwischenablage kopiert!");
				}
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

	private static boolean checkKeyBoard(JTextArea topTextArea) {
		Locale locale = InputContext.getInstance().getLocale();
        if (locale == null || !locale.getLanguage().equals("de")) {
        	topTextArea.append("⚠ Achtung: QWERTZ-Tastatur nicht erkannt! Das Programm könnte nicht korrekt funktionieren.\n");
           return false;
        }
        return true;
	}

}
