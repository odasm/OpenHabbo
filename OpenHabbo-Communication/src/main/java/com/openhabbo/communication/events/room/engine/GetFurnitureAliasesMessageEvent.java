package com.openhabbo.communication.events.room.engine;

import com.openhabbo.communication.events.AbstractMessageEvent;
import com.openhabbo.communication.parsers.room.engine.GetFurnitureAliasesMessageParser;

import java.util.function.Consumer;

public class GetFurnitureAliasesMessageEvent extends AbstractMessageEvent<GetFurnitureAliasesMessageParser> {

    public GetFurnitureAliasesMessageEvent(Consumer<GetFurnitureAliasesMessageParser> callback) {
        super(callback, GetFurnitureAliasesMessageParser.class);
    }
}
