package pl.jsystems.qa.qagui.config;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class GuiConfig {
    private static final Config CONFIG = ConfigFactory.load("qaguiconfig.conf");
    private static final String ENVIROMENT = CONFIG.getString("enviorement");
    private static final Config ENV = CONFIG.getConfig("envioroments").getConfig(ENVIROMENT);

    public static final String BASE_URL = ENV.getString("baseUrl");
    public static final String LOGIN = ENV.getString("login");
    public static final String PASSWORD = ENV.getString("password");
}
