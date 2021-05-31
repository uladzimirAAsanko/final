package graph.to.db.utility;

import graph.to.db.entity.PlotData;

import java.io.IOException;
import java.util.ResourceBundle;

public class Drawer {
    private static final String FILE_LOCATION_CONFIG = "config/locations";
    private static final String SCRIPT_LOC_CONFIG = "scriptLocation";
    private static final String FILES_LOC_CONFIG = "filesLocation";
    public static String scriptLocation;
    public static String fileLocations;

    static {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(FILE_LOCATION_CONFIG);
        scriptLocation = resourceBundle.getString(SCRIPT_LOC_CONFIG);
        fileLocations = resourceBundle.getString(FILES_LOC_CONFIG);
    }

    public static boolean drawPlot(PlotData data, String directoryName){
        StringBuilder command = new StringBuilder();
        command.append("python ").append(scriptLocation).append(" ")
                .append(data.getX()).append(" ").append(data.getY()).append(" ").append(data.getZ()).append(" ")
                .append(data.getH()).append(" ").append(directoryName); //составления запроса
        try {
            Process p = Runtime.getRuntime().exec(command.toString());
            return true;
        } catch (IOException e) {
            return false;
        }
    }

}
