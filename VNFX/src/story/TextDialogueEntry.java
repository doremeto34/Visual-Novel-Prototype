package story;

public class TextDialogueEntry extends DialogueEntry {
    private String speaker;
    private String text;

    public TextDialogueEntry(String speaker, String text, String characterPath, String characterAnimation) {
        this.speaker = speaker;
        this.text = text;
        this.characterPath = characterPath;
        this.characterAnimation = characterAnimation;
    }

    public String getSpeaker() {
        return speaker;
    }

    public String getText() {
        return text;
    }

}
