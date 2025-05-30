package story;

import story.Choice;
import java.util.List;

public class ChoiceDialogueEntry extends DialogueEntry {
	private String text;
    private List<Choice> choices;

    public ChoiceDialogueEntry(String characterPath,List<Choice> choices) {
        this.choices = choices;
        this.characterPath = characterPath;
    }

    public String getText() {
    	return text;
    }
    public String getCharacterPath() {
    	return characterPath;
    }
    public List<Choice> getChoices() {
        return choices;
    }
}
