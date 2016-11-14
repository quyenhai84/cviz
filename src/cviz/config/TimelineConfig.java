package cviz.config;

import java.io.Serializable;

public class TimelineConfig implements Serializable {
    private String id;
    private int channel;

    public TimelineConfig(String id, int channel){
        this.id = id;
        this.channel = channel;
    }

    public String getId(){
        if (id == null || id == "")
            return "default";

        return id;
    }

    public int getChannel(){
        if (channel <= 0)
            return 1;

        return channel;
    }
}
