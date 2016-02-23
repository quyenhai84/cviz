package cviz.timeline.commands;

import cviz.ITimeline;
import se.svt.caspar.amcp.AmcpLayer;

public class ClearCommand extends ICommand {
    public ClearCommand(int layerId) {
        super(layerId);
    }

    @Override
    public boolean execute(ITimeline timeline) {
        AmcpLayer layer = timeline.getLayer(getLayerId());

        try {
            layer.clear();
            // make sure to reset opacity and transforms, or we could fuck up the next timeline
            layer.sendCommand("MIXER", "OPACITY 1");
            layer.sendCommand("MIXER", "FILL 0 0 1 1");
            return true;
        } catch (Exception e){
            System.err.println("Failed to execute command: " + e.getMessage());
            return false;
        }
    }

    @Override
    public String toString() {
        return "ClearCommand: " + getLayerId();
    }
}
