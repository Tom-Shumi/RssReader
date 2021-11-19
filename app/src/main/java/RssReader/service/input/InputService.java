package RssReader.service.input;

import RssReader.domain.Argument;
import RssReader.domain.Article;
import RssReader.factory.InputServiceFactory;

import java.util.List;

/**
 * 取り込み処理用のサービスインターフェース
 */
public interface InputService {

    /**
     * 取り込み処理
     * @param input NotNull 取り込み対象
     * @return 取り込んだコンテンツリスト
     */
    List<Article> inputArticle(String input);

    /**
     * 取り込み処理を実行する
     * @param argument NotNull コマンドライン引数
     * @return 取り込んだコンテンツリスト
     */
    static List<Article> input(Argument argument) {
        return InputServiceFactory.create(argument).inputArticle(argument.getInput());
    }
}
