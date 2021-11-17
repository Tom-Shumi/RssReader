RssReader

Java version: openjdk version "11.0.2"

TODO: 試験、JavaDocの付与

コンパイル：gradle shadowJar  
実行コマンド：java -jar app/build/libs/RssReader-all.jar -i https://news.yahoo.co.jp/rss/topics/it.xml -c cut -o result.txt