package ru.zendal.clanminecraft.configuration;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Provides;
import com.google.inject.name.Named;
import ru.zendal.clanminecraft.command.handler.ClanAddChunkCommand;
import ru.zendal.clanminecraft.command.handler.ClanCreateCommandHandler;
import ru.zendal.clanminecraft.command.handler.CommandHandler;

import java.util.List;

public class CommandHandlerConfiguration extends AbstractModule {


    public CommandHandlerConfiguration() {

    }

    @Provides
    @Named("ClanHandlers")
    @Inject
    public List<CommandHandler> providerCommandHandler(Injector injector) {
        return List.of(
                injector.getInstance(ClanCreateCommandHandler.class),
                injector.getInstance(ClanAddChunkCommand.class)
                );
    }
}
