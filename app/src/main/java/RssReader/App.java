/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package RssReader;

import RssReader.constant.ArgumentTypeEnum;
import RssReader.domain.Argument;
import RssReader.util.ConvertArgumentUtils;

import java.util.Map;

public class App {

    public static void main(String[] args) {
        System.out.println("Start RssReader");
        Argument argument = ConvertArgumentUtils.convertArgument(args);

        System.out.println("-i:" + argument.getInput());
        System.out.println("-c:" + argument.getConvertTypeList().get(0));
        System.out.println("-o:" + argument.getOutput());
    }
}
