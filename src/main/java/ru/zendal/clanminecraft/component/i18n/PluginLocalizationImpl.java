package ru.zendal.clanminecraft.component.i18n;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import ru.zendal.clanminecraft.component.i18n.command.CommandLocalization;
import ru.zendal.clanminecraft.component.i18n.title.TitleLocalization;
import ru.zendal.clanminecraft.utils.PropertiesFile;

/**
 * Simple implements of plugin localization
 */
public final class PluginLocalizationImpl implements PluginLocalization {

    /**
     * Source of localization
     */
    private final PropertiesFile languagePropertiesFile;

    /**
     * Command localization instance
     */
    private final CommandLocalization commandLocalization;

    /**
     * Title localization instance
     */
    private final TitleLocalization titleLocalization;


    @Inject
    public PluginLocalizationImpl(@Named("languageFile") PropertiesFile languagePropertiesFile) {
        this.languagePropertiesFile = languagePropertiesFile;
        this.commandLocalization = this.initializeLocalization();
        this.titleLocalization = this.initializeTitleLocalization();
    }

    private TitleLocalization initializeTitleLocalization() {
        return new TitleLocalization() {
            @Override
            public String getOnEntryClanTerritory(String nameClan) {
                return languagePropertiesFile.get("title.clan.entry")
                        .replaceAll("\\{CLAN_NAME}", nameClan);
            }

            @Override
            public String getSubOnEntryClanTerritory() {
                return languagePropertiesFile.get("title.clan.entry.sub");
            }

            @Override
            public String getOnExitClanTerritory(String nameClan) {
                return languagePropertiesFile.get("title.clan.exit")
                        .replaceAll("\\{CLAN_NAME}", nameClan);
            }

            @Override
            public String getSubExitEntryClanTerritory() {
                return languagePropertiesFile.get("title.clan.exit.sub");
            }
        };
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
            public String getOnClanCreateError() {
                return languagePropertiesFile.get("command.clan.create.error");
            }

            @Override
            public String getOnClanCreateNameClanError() {
                return languagePropertiesFile.get("command.clan.create.error.nameClan");
            }

            @Override
            public String getOnClanCreateNameClanIsExist(String nameClan) {
                return languagePropertiesFile.get("command.clan.create.error.nameClanIsExist")
                        .replaceAll("\\{CLAN_NAME}", nameClan);
            }

            @Override
            public String getOnClanCreateErrorChunkIsBusy() {
                return languagePropertiesFile.get("command.clan.create.error.chunkIsBusy");
            }

            @Override
            public String getOnClanCreateErrorPlayerIsAdminAnotherClan(String nameClan) {
                return languagePropertiesFile.get("command.clan.create.error.playerIsAdminAnotherClan")
                        .replaceAll("\\{CLAN_NAME}", nameClan);
            }

            @Override
            public String getOnClanEventOnBreakBlockError() {
                return languagePropertiesFile.get("clan.event.onBreakBlock.error");
            }

            @Override
            public String getOnClanEventOnPutBlockError() {
                return languagePropertiesFile.get("clan.event.onPutBlock.error");
            }

            @Override
            public String getOnClanAddChunkSuccess() {
                return languagePropertiesFile.get("command.clan.add.chunk.successful");
            }

            @Override
            public String getOnClanAddChunkError() {
                return languagePropertiesFile.get("command.clan.add.chunk.error");
            }

            @Override
            public String getOnClanAddChunkErrorNoChunkNearby() {
                return languagePropertiesFile.get("command.clan.add.chunk.error.noChunkNearby");
            }

            @Override
            public String getOnClanAddChunkErrorPlayerNotMemberClan() {
                return languagePropertiesFile.get("command.clan.add.chunk.error.playerNotMemberClan");
            }

            @Override
            public String getOnClanAddChunkErrorChunkToClan() {
                return languagePropertiesFile.get("command.clan.add.chunk.error.chunkToClan");
            }

            @Override
            public String getOnClanAddChunkErrorValidAreaClan() {
                return languagePropertiesFile.get("command.clan.add.chunk.error.validAreaClan");
            }
        };
    }

    @Override
    public CommandLocalization getCommandLocale() {
        return this.commandLocalization;
    }

    @Override
    public TitleLocalization getTitleLocalization() {
        return this.titleLocalization;
    }
}
