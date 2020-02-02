package ru.zendal.clanminecraft.component.i18n;

import ru.zendal.clanminecraft.component.i18n.command.CommandLocalization;

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
}
