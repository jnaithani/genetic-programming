package test;

import static org.junit.Assert.*;

import java.util.Enumeration;
import java.util.Properties;

import org.junit.Test;

import utilities.Settings;

public class SettingsTest {

    @Test
    public void testPrintMaxNodesSetting() {
        System.out.println("***testPrintMaxNodeSetting***");
        
        try {
            Properties settings = Settings.getSettings();
            
            System.out.println(Settings.PROP_MAX_NODES + "=" + settings.getProperty(Settings.PROP_MAX_NODES));
        } catch (Exception e) {
            e.printStackTrace();
            fail("Could not load print '" + Settings.PROP_MAX_NODES + "'");
        }
    }

    @Test
    public void testPrintAllSettings() {
        System.out.println("***testPrintAllSettings***");
        
        try {
            Properties settings = Settings.getSettings();
            
            Enumeration e = settings.propertyNames();

            while (e.hasMoreElements()) {
              String key = (String) e.nextElement();
              System.out.println(key + "=" + settings.getProperty(key));
            }
        } catch (Exception e) {
            e.printStackTrace();
            fail("Could not load GPS settings");
        }
    }
}
