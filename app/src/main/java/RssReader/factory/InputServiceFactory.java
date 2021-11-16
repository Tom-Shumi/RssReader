package RssReader.factory;

import RssReader.domain.Argument;
import RssReader.service.input.InputService;
import RssReader.service.input.impl.FileInputService;
import RssReader.service.input.impl.RssInputService;

public class InputServiceFactory {

    public static InputService create(Argument argument) {
        if (argument.isInputRss()) {
            return new RssInputService();
        }

        return new FileInputService();
    }
}
