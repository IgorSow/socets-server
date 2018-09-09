package chat.domain.model;

public enum ChatCommand {

    LIST_USERS("users"),

    SEND_MASSAGE("send"),

    MASSAGES("massages"),
    NEW_MASSAGES("new_massages");

    private String commandName;

    ChatCommand(String commandName) {
        this.commandName = commandName;
    }

    public static ChatCommand getByCommandName(String commandName) {
        switch (commandName) {
            case "users":
                return ChatCommand.LIST_USERS;

            case "send":
                return ChatCommand.SEND_MASSAGE;

            case "massages":
                return ChatCommand.MASSAGES;

            case "new_massages":
                return ChatCommand.NEW_MASSAGES;


            default:
                return null;

        }
    }
}
