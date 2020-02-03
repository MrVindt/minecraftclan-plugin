package ru.zendal.clanminecraft.component.i18n;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import ru.zendal.clanminecraft.component.i18n.command.CommandLocalization;
import ru.zendal.clanminecraft.utils.PropertiesFile;

/**
 * Simple implements of plugin localization
 */
public class PluginLocalizationImpl implements PluginLocalization {

    /**
     * Source of localization
     */
    private final PropertiesFile languagePropertiesFile;

    /**
     * Command localization instance
     */
    private final CommandLocalization commandLocalization;

    @Inject
    public PluginLocalizationImpl(@Named("languageFile") PropertiesFile languagePropertiesFile) {
        this.languagePropertiesFile = languagePropertiesFile;
        this.commandLocalization = this.initializeLocalization();
    }

    /**
     * Create command localization
     *
     * @return instance of command localization
     */
    private CommandLocalization initializeLocalization() {
        return new CommandLocalization() {
            @Override
            public String getOnClanCreateSuccess(String nameClan) {
                return languagePropertiesFile.get("command.clan.create.successful")
                        .replaceAll("\\{CLAN_NAME}", nameClan);
            }
            @Override
            public String getOnClanCreateError(){
                return languagePropertiesFile.get("command.clan.create.error");
            }
            @Override
            public String getOnClanCreateNameClanError(){
                return languagePropertiesFile.get("command.clan.create.error.nameClan");
            }
            @Override
            public String getOnClanCreateNameClanIsExist(String nameClan){
                return languagePropertiesFile.get("command.clan.create.error.nameClanIsExist")
                        .replaceAll("\\{CLAN_NAME", nameClan);
            }
        };
    }

    @Override
    public CommandLocalization getCommandLocale() {
        return this.commandLocalization;
    }
}
