package ru.zendal.clanminecraft.component.i18n;

import ru.zendal.clanminecraft.component.i18n.command.CommandLocalization;
import ru.zendal.clanminecraft.component.i18n.title.TitleLocalization;

/**
 * Localization of plugin
 */
public interface PluginLocalization {

    /**
     * Localization about command
     *
     * @return get command locale
     */
    CommandLocalization getCommandLocale();

    /**
     * Localization for title's
     *
     * @return localization
     * @see net.md_5.bungee.api.chat.BaseComponent
     */
    TitleLocalization getTitleLocalization();
}
