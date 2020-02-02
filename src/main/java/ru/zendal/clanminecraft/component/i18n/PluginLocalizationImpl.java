package ru.zendal.clanminecraft.component.i18n;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import ru.zendal.clanminecraft.component.i18n.command.CommandLocalization;
import ru.zendal.clanminecraft.utils.PropertiesFile;


public class PluginLocalizationImpl implements PluginLocalization {

    private final PropertiesFile languagePropertiesFile;

    private final CommandLocalization commandLocalization;

    @Inject
    public PluginLocalizationImpl(@Named("languageFile") PropertiesFile languagePropertiesFile) {
        this.languagePropertiesFile = languagePropertiesFile;
        this.commandLocalization = this.initializeLocalization();
    }


    private CommandLocalization initializeLocalization() {
        return new CommandLocalization() {
            @Override
            public String getOnClanCreateSuccess() {
                return languagePropertiesFile.get("command.clan.create.successful");
            }
        };
    }

    @Override
    public CommandLocalization getCommandLocale() {
        return this.commandLocalization;
    }
}
