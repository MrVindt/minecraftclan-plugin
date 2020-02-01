package ru.zendal.clanminecraft.configuration;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Named;
import org.bukkit.plugin.java.JavaPlugin;
import ru.zendal.clanminecraft.utils.PropertiesFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LanguageConfiguration extends AbstractModule {

    private final JavaPlugin javaPlugin;

    private final String[] availableLocales;

    public LanguageConfiguration(JavaPlugin javaPlugin, String... locales) {
        this.javaPlugin = javaPlugin;
        this.availableLocales = locales;
    }


    @Provides
    @Named("languageFiles")
    public List<PropertiesFile> propertiesProvider() throws IOException {
        var result = new ArrayList<PropertiesFile>();
        for (String locale : availableLocales) {
            result.add(new PropertiesFile(javaPlugin.getResource("lang/" + locale + ".properties")));
        }
        return result;
    }


}
