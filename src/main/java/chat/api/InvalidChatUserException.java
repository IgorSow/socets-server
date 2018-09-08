package chat.api;

public class InvalidChatUserException extends RuntimeException {

    public InvalidChatUserException(String message) {
        super(message);
    }
}
