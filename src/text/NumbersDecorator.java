package text;

//import java.util.regex.Matcher;
//import java.util.regex.Pattern;

public class NumbersDecorator extends TextProcessorDecorator {
    public NumbersDecorator(TextProcessor wrappedProcessor) {
        super(wrappedProcessor);
    }

    @Override
    public String process(String str) {
    
    	/*//Eine Revertierung der Schreibrichtung der Zahlen innerhalb des String wäre hier möglich. 
    	str = wrappedProcessor.process(str); 

        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(str);
        
        StringBuffer result = new StringBuffer();
        
        while (matcher.find()) {
            String number = matcher.group(); 
            String reversedNumber = new StringBuilder(number).reverse().toString();
            matcher.appendReplacement(result, reversedNumber); 
        }
        matcher.appendTail(result); 
        
        return result.toString();
        */
        return str;
        
    }
}
