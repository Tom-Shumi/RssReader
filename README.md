RssReader

Java version: openjdk version "11.0.2"

前提条件：
■ファイル取り込み時  
・取り込むファイルの文字コードはUTF-8のみとする。  
・取り込むファイルの絶対パスを引数で指定する。  
・取り込むファイルの形式は下記の繰り返しのみとする。  
　　1行目：「title」  
　　2行目：「body」  
　　3行目：空行  
・プレフィックスとコンテンツのデリミタは「:」のみとする。

■RSS取り込み時  
・取り込むタグは「title」「description」のみとする。

■ファイル出力時  
・出力ファイルの文字コードはUTF-8とする。  
・出力対象ファイルが既に存在した場合は追記ではなく、上書きとする。

TODO: クラス図の作成、READMEの修正、エラー処理、試験、JavaDocの付与

コンパイル：gradle shadowJar  
実行コマンド：java -jar app/build/libs/RssReader-all.jar -i http://test.com/ -c cut -o result.txt