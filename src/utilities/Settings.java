package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

/**
 * Displays settings of the GP program including but not limited to {Probabilities ..}
 * @author bash1664
 *
 */
public class Settings extends Properties {
    
    public static String PROP_MUTATOR_PROB = "mutatorprobability";
    public static String PROP_POPULATION_SIZE = "populationsize";
    public static String PROP_CROSSOVER_PROB = "crossoverprobability";
    public static String PROP_MAX_NODES = "maxnodes";
    
    public static double DEFAULT_MUTATOR_PROB = 0.5;
    public static int DEFAULT_POPULATION_SIZE = 1000;
    public static double DEFAULT_CROSSOVER_PROB = 0.4;
    public static int DEFAULT_MAX_NODES = 5;
    
    private static final long serialVersionUID = 1L;
    private final static String SETTINGS_FILE_NAME = "settings.properties";
    
    private static Properties settings = null;
    
    public static final Properties getSettings() throws Exception {
        if (settings == null) {
            return loadSettings(getDefaultSettingsFilename());
        } else {
            return settings;
        }
    }
        
    public static final Properties loadSettings(String fileName) throws Exception {
            settings = new Settings(fileName);            
        return settings;
    }
    
    private static String getDefaultSettingsFilename() throws Exception {
        return getCurrentWorkingDir() + File.separator + SETTINGS_FILE_NAME;
    }
    
    private static String getCurrentWorkingDir() throws Exception {
        return System.getProperty("user.dir");
    }
    
    private Settings(String fileName) throws Exception {
        load(new FileInputStream(fileName));
    }
}
