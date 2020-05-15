package ru.avalon.j120.order_accounting_system.configuration;

import java.util.Properties;
import java.io.IOException;
import java.io.FileInputStream;

public class Configuration {

    private static Configuration initialisation;
    private final static String DEFAULT_CONFIG = "src/ru/avalon/j120/order_accounting_system/configurationsFiles/defaultConfig.properties";
    private final static String USER_CONFIG = "src/ru/avalon/j120/order_accounting_system/configurationsFiles/userConfig.properties";
    private Properties properties;

    /**
     * Создаю приватный конструктор для инкапсудяции
     * @throws IOException
     */
    private Configuration() throws IOException {
        Properties defaultProperties = new Properties();
        defaultProperties.load(new FileInputStream(DEFAULT_CONFIG));
        properties = new Properties(defaultProperties);
        defaultProperties.load(new FileInputStream(USER_CONFIG));
    }

    /**
     * Метод позволяет инициализировать объект типа Configuration
     * @return
     * @throws IOException
     */
    public static Configuration getInitialisation() throws IOException {
        if (initialisation == null)
            initialisation = new Configuration();
        return initialisation;
    }

    public Properties getProperties(){
        return properties;
    }
}