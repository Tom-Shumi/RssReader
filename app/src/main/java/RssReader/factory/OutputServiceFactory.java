package RssReader.factory;

import RssReader.domain.Argument;
import RssReader.service.input.InputService;
import RssReader.service.input.impl.FileInputService;
import RssReader.service.input.impl.RssInputService;
import RssReader.service.output.OutputService;
import RssReader.service.output.impl.FileOutputService;
import RssReader.service.output.impl.StandardOutputService;

public class OutputServiceFactory {

    public static OutputService create(Argument argument) {
        if (argument.isOutputFile()) {
            return new FileOutputService();
        }

        return new StandardOutputService();
    }
}
