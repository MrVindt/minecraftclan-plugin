package ru.zendal.clanminecraft.component.i18n;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import ru.zendal.clanminecraft.utils.PropertiesFile;

import java.util.List;

public class Test {

    @Inject
    public Test(@Named("languageFiles") List<PropertiesFile> as) {
    }
}
