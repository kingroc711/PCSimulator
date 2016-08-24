import java.util.ArrayList;
import java.util.List;

/**
 * Created by kingroc on 16-8-23.
 */
public class ModuleDeviceInfo extends  ArrayList<PortDeviceInfo>{
    private String customName;
    private String SerialName;

    public ModuleDeviceInfo(String SerialName){
        this.SerialName = SerialName;
    }

    public String getCustomName() {
        return customName;
    }

    public void setCustomName(String customName) {
        this.customName = customName;
    }

    public String getSerialName() {
        return SerialName;
    }

    public void setSerialName(String serialName) {
        SerialName = serialName;
    }
}
