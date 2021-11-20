package RssReader.service.output;

import RssReader.domain.Argument;
import RssReader.domain.Article;
import RssReader.domain.OutputArticle;
import RssReader.factory.OutputServiceFactory;

import java.util.List;

/**
 * 出力処理用のサービスインターフェース
 */
public interface OutputService {

    /**
     * 出力処理
     * @param outputArticle 出力対象コンテンツ
     */
    void outputArticle(OutputArticle outputArticle);

    /**
     * 出力処理を実行する
     * @param articleList 出力対象コンテンツ
     * @param argument コマンドライン引数
     */
    static void output(List<Article> articleList, Argument argument) {
        OutputServiceFactory.create(argument)
                .outputArticle(OutputArticle.builder().articleList(articleList).output(argument.getOutput()).build());
    }
}
