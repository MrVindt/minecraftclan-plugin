## Перемещения плагина
Для перемещения плагина после сборки в папку сервера необходимо изменить конфигурацию Gradle

![alt text][changeSettingsGradle]

В поле `Tasks` добавить `moveToServerFolder`
И в поле аргументы добавить аргумент, путь до папки с сервером
```bash
-PpathToServer=/Volumes/LaCie/MineProject/Classic
```

![alt text][buildGradleSettings]


[buildGradleSettings]: https://gitlab.com/zendal/minecraftrpg-plugin/raw/master/wiki/img/buildGradleSettings.png "buildGradleSettings"
[changeSettingsGradle]: https://gitlab.com/zendal/minecraftrpg-plugin/raw/master/wiki/img/changeSettingsGradle.png "changeSettingsGradle"
