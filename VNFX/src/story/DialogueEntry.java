package story;

import story.Choice;
import java.util.List;

public abstract class DialogueEntry {
    // Marker for polymorphism – shared type
	String characterPath;
	String characterAnimation;
	public String getCharacterPath() {
		return characterPath;
	};
	public String getCharacterAnimation() {
		return characterAnimation;
	}
}
