package com.openhabbo.communication.parsers.sound;

import com.openhabbo.api.communication.data.IncomingMessageWrapper;
import com.openhabbo.communication.parsers.AbstractMessageParser;

public class SetSoundSettingsMessageParser extends AbstractMessageParser {
 
    @Override
    public boolean parse(IncomingMessageWrapper data) {
        return true;
    }

    @Override
    public boolean flush() {
        return true;
    }
}
