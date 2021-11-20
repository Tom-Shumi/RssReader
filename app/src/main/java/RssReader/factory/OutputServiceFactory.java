package RssReader.factory;

import RssReader.domain.Argument;
import RssReader.service.output.OutputService;
import RssReader.service.output.impl.FileOutputService;
import RssReader.service.output.impl.StandardOutputService;

/**
 * OutputServiceインスタンス生成クラス
 */
public class OutputServiceFactory {

    /**
     * コマンドライン引数の出力オプションを元にOutputServiceを生成する
     * @param argument コマンドライン引数
     * @return コマンドライン引数を元に生成したOutputService
     */
    public static OutputService create(Argument argument) {
        if (argument.isOutputFile()) {
            return new FileOutputService();
        }

        return new StandardOutputService();
    }
}
