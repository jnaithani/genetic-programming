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
    public static String PROP_SURVIVAL_PROB = "survivalprobability";
    public static String PROP_FITNESS_THRESHOLD = "fitnessThreshold";
    public static String PROP_MAX_NODES = "maxnodes";
    public static String PROP_MAX_DEPTH = "maxdepth";
    public static String PROP_DEBUG = "debug";
    public static String PROP_TRACE = "trace";
    
    public static double DEFAULT_MUTATOR_PROB = 0.5;
    public static int DEFAULT_POPULATION_SIZE = 1000;
    public static double DEFAULT_CROSSOVER_PROB = 0.4;
    public static int DEFAULT_MAX_NODES = 5;
    
    private static final long serialVersionUID = 1L;
    private final static String SETTINGS_FILE_NAME = "settings.properties";
    
    private static Properties settings = null;
    private static boolean debug = false;
    
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
    
    public static boolean debug() {
        try {
            if (settings == null) {
                Settings.loadSettings(getDefaultSettingsFilename());
            }

            if (settings.getProperty(PROP_DEBUG) != null)
                return Boolean.valueOf(settings.getProperty(PROP_DEBUG));
            else
                return false;
        } catch (Exception e) {
            return false;
        }   
    }
    
    public static boolean trace() {
        try {
            if (settings == null) {
                Settings.loadSettings(getDefaultSettingsFilename());
            }

            if (settings.getProperty(PROP_TRACE) != null)
                return Boolean.valueOf(settings.getProperty(PROP_TRACE));
            else
                return false;
        } catch (Exception e) {
            return false;
        }   
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

    public static int getMaxDepth() throws Exception {
        Properties settings = getSettings();
        
        String prop = settings.getProperty(PROP_MAX_DEPTH);
        
        return Integer.parseInt(prop);
    }
    
    public static double getSurvivalProbability() throws Exception {
        Properties settings = getSettings();
        
        String prop = settings.getProperty(PROP_SURVIVAL_PROB);
        
        return Double.parseDouble(prop);
    }
    
    public static int getPopulationSize() throws Exception {
        Properties settings = getSettings();
        
        String prop = settings.getProperty(PROP_POPULATION_SIZE);
        
        return Integer.parseInt(prop);
    }
    
    public static double getFitnessThreshold() throws Exception {
        Properties settings = getSettings();
        
        String prop = settings.getProperty(PROP_POPULATION_SIZE);
        
        return Double.parseDouble(prop);
    }
    
    public static long maxExecutionTime() throws Exception {
        Properties settings = getSettings();
        
        String prop = settings.getProperty(PROP_POPULATION_SIZE);
        
        return Long.parseLong(prop);
    }
}
