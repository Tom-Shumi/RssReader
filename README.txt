RssReader

Java version: openjdk version "11.0.2"
コンパイルコマンド：gradle shadowJar  
実行コマンド：java -jar RssReader-all.jar -i https://news.yahoo.co.jp/rss/topics/it.xml -c cut -o result.txt

前提条件：
    ■ファイル取り込み時
        ・取り込むファイルの文字コードはUTF-8のみとする。
        ・取り込むファイルの絶対パスを引数で指定する。
        ・取り込むファイルの形式は下記の繰り返しのみとする。
            1行目：「title」
            2行目：「body」
            3行目：空行
        ・項目名とコンテンツのデリミタは「:」のみとする。

    ■RSS取り込み時
        ・取り込むタグは「title」「description」のみとする。

    ■ファイル出力時
        ・出力ファイルの文字コードはUTF-8とする。
        ・出力対象ファイルが既に存在した場合は追記ではなく、上書きとする。
        ・下記の形式の繰り返しで取り込み内容をファイルに出力する。
            1行目：「title」
            2行目：「body」
            3行目：空行