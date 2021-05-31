package graph.to.db.controller.command;

public class CommandProvider {
    private static final CommandProvider instance = new CommandProvider();

    private CommandProvider() {
    }

    public static CommandProvider getInstance() {
        return instance;
    }

    public Command defineCommand(String commandName) {
        if (commandName == null || commandName.isEmpty()) {
            return CommandType.WRONG_COMMAND.getCommand();
        }
        Command command;
        try {
            String commandOfString = commandName.toUpperCase();
            CommandType[] values = CommandType.values();
            command = CommandType.valueOf(commandOfString).getCommand();
        } catch (IllegalArgumentException e) {
            return CommandType.WRONG_COMMAND.getCommand();
        }
        return command;
    }
}