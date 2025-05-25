package story;

public class TextDialogueEntry extends DialogueEntry {
    private String speaker;
    private String text;
    private String characterPath;

    public TextDialogueEntry(String speaker, String text, String characterPath) {
        this.speaker = speaker;
        this.text = text;
        this.characterPath = characterPath;
    }

    public String getSpeaker() {
        return speaker;
    }

    public String getText() {
        return text;
    }

    public String getCharacterPath() {
        return characterPath;
    }
}
