package story;

import java.util.function.BooleanSupplier; // <- important

public class Choice {
    public String text;
    public Runnable action;
    public BooleanSupplier condition; // <- NEW!

    public Choice(String text, Runnable action) {
        this(text, action, () -> true); // Default: always show
    }

    public Choice(String text, Runnable action, BooleanSupplier condition) {
        this.text = text;
        this.action = action;
        this.condition = condition;
    }
}
