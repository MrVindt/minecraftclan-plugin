package ru.zendal.clanminecraft.configuration;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Provides;
import com.google.inject.name.Named;
import org.bukkit.plugin.java.JavaPlugin;
import ru.zendal.clanminecraft.component.i18n.PluginLocalization;
import ru.zendal.clanminecraft.component.i18n.PluginLocalizationImpl;
import ru.zendal.clanminecraft.utils.PropertiesFile;

import java.io.IOException;

/**
 * Guice configuration about language (locale) plugin
 */
public class LanguageConfiguration extends AbstractModule {

    /**
     * Instance of java plugin
     */
    private final JavaPlugin javaPlugin;

    public LanguageConfiguration(JavaPlugin javaPlugin) {
        this.javaPlugin = javaPlugin;
    }

    @Override
    protected void configure() {
        bind(PluginLocalization.class).to(PluginLocalizationImpl.class);
    }

    @Provides
    @Inject
    @Named("languageFile")
    public PropertiesFile propertiesProvider(@Named("pluginLocale") String pluginLocale) throws IOException {
        return new PropertiesFile(javaPlugin.getResource("lang/" + pluginLocale + ".properties"));
    }


}
