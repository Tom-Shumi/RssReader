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

TODO: コメント、JavaDocの付与、試験

コンパイル：gradle shadowJar  
実行コマンド：java -jar app/build/libs/RssReader-all.jar -i http://test.com/ -c cut -o result.txt