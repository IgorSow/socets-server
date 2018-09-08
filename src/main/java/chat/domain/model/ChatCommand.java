package chat.domain.model;

public enum ChatCommand {
    AUTHENTICATE("hello"),
    LIST_USERS("users"),

    SEND_MASSAGE("send");

    private String commandName;

    ChatCommand(String commandName) {
        this.commandName = commandName;
    }

    public static ChatCommand getByCommandName(String commandName) {
        switch (commandName) {
            case "hello":
                return ChatCommand.AUTHENTICATE;

            case "users":
                return ChatCommand.LIST_USERS;

            case "send":
                return ChatCommand.SEND_MASSAGE;

            default:
                return null;

        }
    }
}
