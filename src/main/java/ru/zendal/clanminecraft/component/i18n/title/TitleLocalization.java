package ru.zendal.clanminecraft.component.i18n.title;

/**
 * Interface of title localization
 */
public interface TitleLocalization {

    /**
     * Get message when player entry on clan territory
     *
     * @param nameClan name of clan
     * @return message
     */
    String getOnEntryClanTerritory(String nameClan);

    /**
     * Get message when player entry on clan territory, sub message
     *
     * @return message
     */
    String getSubOnEntryClanTerritory();

    /**
     * Get a message when a player leaves the clan territory
     *
     * @param nameClan name of clan
     * @return message
     */
    String getOnExitClanTerritory(String nameClan);

    /**
     * Get a message when a player leaves the clan territory, sub message
     *
     * @return message
     */
    String getSubExitEntryClanTerritory();
}
