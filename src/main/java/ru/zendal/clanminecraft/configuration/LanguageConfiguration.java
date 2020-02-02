package ru.zendal.clanminecraft.configuration;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Named;
import org.bukkit.plugin.java.JavaPlugin;
import ru.zendal.clanminecraft.component.i18n.PluginLocalization;
import ru.zendal.clanminecraft.component.i18n.PluginLocalizationImpl;
import ru.zendal.clanminecraft.utils.PropertiesFile;

import java.io.IOException;

/**
 * Guice configuration about language (locale) plugin
 * TODO Add support config.yml
 */
public class LanguageConfiguration extends AbstractModule {

    /**
     * Instance of java plugin
     */
    private final JavaPlugin javaPlugin;

    /**
     * Available locales
     */
    private final String[] availableLocales;

    public LanguageConfiguration(JavaPlugin javaPlugin, String... locales) {
        this.javaPlugin = javaPlugin;
        this.availableLocales = locales;
    }

    @Override
    protected void configure() {
        bind(PluginLocalization.class).to(PluginLocalizationImpl.class);
    }

    @Provides
    @Named("languageFile")
    public PropertiesFile propertiesProvider() throws IOException {
        return new PropertiesFile(javaPlugin.getResource("lang/" + "en_us" + ".properties"));
    }


}
