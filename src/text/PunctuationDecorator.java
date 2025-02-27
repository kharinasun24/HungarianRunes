package text;

import core.Main;
import log.MyLogger;

public class PunctuationDecorator extends TextProcessorDecorator {
	
	private static final MyLogger LOGGER  = new MyLogger(Main.class);
	
    public PunctuationDecorator(TextProcessor wrappedProcessor) {
        super(wrappedProcessor);
    }

    @Override
    public String process(String str) {
    	
    	LOGGER.info("If there is a punctuation mark at the right hand side, ithas to be moved to the left.");
    	
        str = wrappedProcessor.process(str); // Vorherige Verarbeitung
        if (str.endsWith("?") || str.endsWith(".") || str.endsWith("!")) {
            if (str.length() <= 1) {
                return str;
            }
            char lastChar = str.charAt(str.length() - 1);
            return lastChar + str.substring(0, str.length() - 1);
        }
        return str;
    }
}
