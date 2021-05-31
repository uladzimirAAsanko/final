package graph.to.db.controller.command;

import graph.to.db.controller.command.impl.DrawPlot;
import graph.to.db.controller.command.impl.GetPlotByName;
import graph.to.db.controller.command.impl.WrongAction;

public enum CommandType {
    WRONG_COMMAND(new WrongAction()),
    GET_PLOT(new GetPlotByName()),
    DRAW(new DrawPlot());

    private Command command;
    CommandType(Command command) {
        this.command = command;
    }
    public Command getCommand() {
        return command;
    }
}
