package story;

import story.Choice;
import java.util.List;

public abstract class DialogueEntry {
    // Marker for polymorphism – shared type
	public abstract String getCharacterPath();
}
