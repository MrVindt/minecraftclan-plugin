package ru.zendal.clanminecraft.component.i18n.command;

/**
 * Localization of command
 */
public interface CommandLocalization {

    /**
     * Get message about successful created clan
     *
     * @return locale message
     */
    String getOnClanCreateSuccess(String nameClan);

    /**
     * Get message about incorrectly entered command
     *
     * @return local message
     */
    String getOnClanCreateError();

    /**
     * Get message about incorrectly entered сlan name
     *
     * @return local message
     */
    String getOnClanCreateNameClanError();

    /**
     * Get message about name clan is exist
     *
     * @param nameClan clan name
     * @return local message
     */
    String getOnClanCreateNameClanIsExist(String nameClan);

    /**
     * Get message about the area is occupied by another clan
     *
     * @return local message
     */
    String getOnClanCreateErrorChunkIsBusy();

    /**
     * Get message that you can't break blocks because you are not a member of a clan
     *
     * @return Local message
     */
    String getOnClanEventOnBreakBlockError();

    /**
     * Get message that you can't put blocks because you are not a member of a clan
     *
     * @return Local message
     */
    String getOnClanEventOnPutBlockError();
}
