import java.util.ArrayList;
import java.util.List;

/**
 * Created by kingroc on 16-8-23.
 */
public class ALLEvent extends ArrayList<ModuleDeviceInfo>{
    private String method;
    private String message;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
