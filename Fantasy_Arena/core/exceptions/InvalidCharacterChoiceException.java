package Fantasy_Arena.core.exceptions;

public class InvalidCharacterChoiceException extends Exception{
    public InvalidCharacterChoiceException() {
        super("Invalid choice of a character.");
    }

    public InvalidCharacterChoiceException(String message) {
        super(message);
    }
}
