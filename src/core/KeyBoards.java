package core;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.im.InputContext;
import java.util.Locale;

import javax.swing.JTextArea;

public class KeyBoards {
	
	/**
	 * Die Methode <code>simulateQwertzTyping</code> kann tastatureingaben auf einer deutschen tastatur simulieren.
	 * @param text
	 */
	static void simulateQwertzTyping(String text) {
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
}
