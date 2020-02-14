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
     * Get message that player is the admin of another clan
     *
     * @return local message
     */
    String getOnClanCreateErrorPlayerIsAdminAnotherClan(String nameClan);

    /**
     * Get message that you can't break blocks because you are not a member of a clan
     *
     * @return local message
     */
    String getOnClanEventOnBreakBlockError();

    /**
     * Get message that you can't put blocks because you are not a member of a clan
     *
     * @return local message
     */
    String getOnClanEventOnPutBlockError();

    /**
     * Get message about chunk added to the clan
     *
     * @return local message
     */
    String getOnClanAddChunkSuccess();

    /**
     * Get message about incorrectly entered command
     *
     * @return local message
     */
    String getOnClanAddChunkError();

    /**
     * Get message about nearby there are no chunks with the clan
     *
     * @return local message
     */
    String getOnClanAddChunkErrorNoChunkNearby();

    /**
     * Get message about player a not member of clan
     *
     * @return local message
     */
    String getOnClanAddChunkErrorPlayerNotMemberClan();

    /**
     * Get message about chunk has already been added to the clan
     *
     * @return local message
     */
    String getOnClanAddChunkErrorChunkToClan();

    /**
     * Get message about chunk can't add to clan because allowed area of the clan has been exceeded!
     *
     * @return local message
     */
    String getOnClanAddChunkErrorValidAreaClan();
}
