package RssReader.factory;

import RssReader.domain.Argument;
import RssReader.service.input.InputService;
import RssReader.service.input.impl.FileInputService;
import RssReader.service.input.impl.RssInputService;

/**
 * InputServiceインスタンス生成クラス
 */
public class InputServiceFactory {

    /**
     * コマンドライン引数の入力オプションを元にInputServiceを生成する
     * @param argument Notnull コマンドライン引数
     * @return コマンドライン引数を元に生成したInputService
     */
    public static InputService create(Argument argument) {
        if (argument.isInputRss()) {
            return new RssInputService();
        }

        return new FileInputService();
    }
}
