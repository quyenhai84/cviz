package cviz.timeline.commands;

import cviz.ITimeline;
import se.svt.caspar.amcp.AmcpLayer;

public class AmcpCommand extends ICommand {
    private String command;

    public AmcpCommand(int layerId, String command) {
        super(layerId);
        this.command = command;
    }

    @Override
    public boolean execute(ITimeline timeline) {
        String[] parts = command.split(" ", 2);

        return sendCommand(timeline, parts[0], parts.length > 1 ? parts[1] : "");
    }

    private boolean sendCommand(ITimeline timeline, String command, String parameters){
        AmcpLayer layer = timeline.getLayer(getLayerId());

        StringBuilder translatedParameters = new StringBuilder();
        for (String param: parameters.split(" ")) {
            param = timeline.getParameterValue(param);

            param = param.replace("\\\"", "\\\\\\\\\""); // \" => \\\\"
            param = param.replace("\"", "\\\""); // " => \"

            translatedParameters.append(param).append(" ");
        }

        try {
            layer.sendCommand(command, translatedParameters.toString());
            return true;
        } catch (Exception e){
            System.err.println("Failed to execute command: " + e.getMessage());
            return false;
        }
    }

    public String getCommandName(){
        return "AmcpCommand";
    }

    @Override
    public String toString() {
        return getCommandName() + ": " + getLayerId() + " " + command;
    }
}
