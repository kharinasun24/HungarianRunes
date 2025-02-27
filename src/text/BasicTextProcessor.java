package text;

public class BasicTextProcessor implements TextProcessor {
    @Override
    public String process(String str) {
        return str; // Gibt den Text unverändert zurück
    }
}
