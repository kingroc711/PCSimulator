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

    private static ModuleDeviceInfo find(String module){
        ModuleDeviceInfo find_m = null;
        for(ModuleDeviceInfo m : all){
            if(m.getSerialName().equals(module)){
                find_m = m;
                break;
            }
        }

        if(find_m == null) {
            System.out.print("can not find module");
            return null;
        }

        return find_m;
    }

    private static PortDeviceInfo find(String module, String port){
        ModuleDeviceInfo find_m = find(module);
        if(find_m == null) {
            System.out.print("can not find module");
            return null;
        }

        PortDeviceInfo find_p = null;
        for (PortDeviceInfo p : find_m) {
            if (p.getPort().equals(port)) {
                find_p = p;
                break;
            }
        }

        if(find_p == null){
            System.out.println("can not find port");
            return null;
        }

        return find_p;
    }

    public static String set(ModuleDeviceInfo m){
        ModuleDeviceInfo find = find(m.getSerialName());
        if(find == null){
            return null;
        }

        find.setCustomName(m.getSerialName());

        Gson gson = new Gson();
        gson.toJson(find);

        return gson.toString();
    }

    public static String set(PortDeviceInfo p){
        PortDeviceInfo find = find(p.getSerialName(), p.getPort());
        if(find == null){
            return null;
        }

        Gson gson = new Gson();
        gson.toJson(find);

        return gson.toString();
    }

    public static String get(String module){
        ModuleDeviceInfo m = find(module);
        Gson gson = new Gson();
        String json = gson.toJson(m);
        System.out.println("module : " + json);
        return json;
    }

    public static String get(String module, String port){
        if (all == null) {
            createSimulateData();
        }

        PortDeviceInfo find = find(module, port);

        if(find == null){
            System.out.println("can not find port");
            return null;
        }

        Gson gson = new Gson();
        String json = gson.toJson(find);
        System.out.println("get module : " + module + ", port : " +  port +  " , json : " + json);
        return json;
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
