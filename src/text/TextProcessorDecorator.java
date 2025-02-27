package text;

public abstract class TextProcessorDecorator implements TextProcessor {
    protected TextProcessor wrappedProcessor;

    public TextProcessorDecorator(TextProcessor wrappedProcessor) {
        this.wrappedProcessor = wrappedProcessor;
    }

    @Override
    public String process(String str) {
        return wrappedProcessor.process(str);
    }
}
