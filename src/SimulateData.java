import com.google.gson.Gson;

/**
 * Created by kingroc on 16-8-24.
 */
public class SimulateData {
    private SimulateData(){}
    static ALLEvent all = null;

    private static ModuleDeviceInfo createModule_0(){
        ModuleDeviceInfo m = new ModuleDeviceInfo("module1");
        for(int i = 0; i < 6; i++) {
            PortDeviceInfo p = new PortDeviceInfo("", "water_level", m.getSerialName(), String.valueOf(i), "0", "0");
            m.add(p);
        }
        return m;
    }

    private static ModuleDeviceInfo createModule_1(){
        ModuleDeviceInfo m = new ModuleDeviceInfo("module1");

        for(int i = 0; i < 6; i++) {
            PortDeviceInfo p = new PortDeviceInfo("", "12V_out", m.getSerialName(), String.valueOf(i), "0", "0");
            m.add(p);
        }

        return m;
    }

    private static ModuleDeviceInfo createModule_2(){
        int i = 0;
        ModuleDeviceInfo m = new ModuleDeviceInfo("module2");
        for(; i < 2; i++){
            PortDeviceInfo p = new PortDeviceInfo("", "temperature_probe", m.getSerialName(), String.valueOf(i), "0", "0");
            m.add(p);
        }

        for(; i < 4; i++){
            PortDeviceInfo p = new PortDeviceInfo("", "PH_probe", m.getSerialName(), String.valueOf(i), "0", "0");
            m.add(p);
        }

        for(; i < 6; i++){
            PortDeviceInfo p = new PortDeviceInfo("", "salinity_probe", m.getSerialName(), String.valueOf(i), "0", "0");
            m.add(p);
        }

        return m;
    }

    private static ModuleDeviceInfo createModule_3(){
        ModuleDeviceInfo m = new ModuleDeviceInfo("module3");

        for(int i = 0; i < 6; i++) {
            PortDeviceInfo p = new PortDeviceInfo("", "pwm_dump", m.getSerialName(), String.valueOf(i), "0", "0");
            m.add(p);
        }

        return m;
    }

    private static ModuleDeviceInfo createModule_4(){
        ModuleDeviceInfo m = new ModuleDeviceInfo("module4");

        for(int i = 0; i < 6; i++) {
            PortDeviceInfo p = new PortDeviceInfo("", "pwm_led", m.getSerialName(), String.valueOf(i), "0", "0");
            m.add(p);
        }

        return m;
    }

    private static void createSimulateData(){
        all = new ALLEvent();
        all.add(createModule_0());
        all.add(createModule_1());
        all.add(createModule_2());
        all.add(createModule_3());
        all.add(createModule_4());
    }

    public static String getAll() {
        if (all == null) {
            createSimulateData();
        }
        Gson gson = new Gson();
        String json = gson.toJson(all);
        System.out.println("get All : " + json );
        return json;
    }
}
