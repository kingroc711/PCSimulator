/**
 * Created by kingroc on 16-8-22.
 */
public class PortDeviceInfo {
    private String customName;
    private String className;
    private String modular;
    private String port;
    private String values;
    private String onceOpenSecond;

    public PortDeviceInfo(String customName, String className, String modular, String values, String port, String onceOpenSecond) {
        this.onceOpenSecond = onceOpenSecond;
        this.customName = customName;
        this.className = className;
        this.modular = modular;
        this.values = values;
        this.port = port;
    }

    public String getCustomName() {
        return customName;
    }

    public void setCustomName(String customName) {
        this.customName = customName;
    }

    public String getClassName() {

        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getModular() {
        return modular;
    }

    public void setModular(String modular) {
        this.modular = modular;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getValues() {
        return values;
    }

    public void setValues(String values) {
        this.values = values;
    }

    public String getOnceOpenSecond() {
        return onceOpenSecond;
    }

    public void setOnceOpenSecond(String onceOpenSecond) {
        this.onceOpenSecond = onceOpenSecond;
    }
}
