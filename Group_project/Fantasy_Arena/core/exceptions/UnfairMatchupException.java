package Fantasy_Arena.core.exceptions;

public class UnfairMatchupException extends Exception{
    public UnfairMatchupException() {
        super("The number of team members should be equal.");
    }

    public UnfairMatchupException(String message) {
        super(message);
    }
}
