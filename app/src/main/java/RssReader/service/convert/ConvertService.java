package RssReader.service.convert;

import RssReader.domain.Argument;
import RssReader.domain.Article;
import RssReader.factory.ConvertServiceFactory;

import java.util.List;

/**
 * 変換処理用のサービスインターフェース
 */
public interface ConvertService {

    /**
     * 変換処理
     * @param articleList 取り込んだコンテンツのリスト
     * @return 変換処理後のリスト
     */
    List<Article> convertArticle(List<Article> articleList);

    /**
     * 変換処理を実行する
     * @param articleList 取り込んだコンテンツのリスト
     * @param argument コマンドライン引数
     * @return 変換処理後のリスト
     */
    static List<Article> convert(List<Article> articleList, Argument argument) {
        List<ConvertService> convertServiceList = ConvertServiceFactory.create(argument);

        for (ConvertService convertService: convertServiceList) {
            articleList = convertService.convertArticle(articleList);
        }

        return articleList;
    }
}
