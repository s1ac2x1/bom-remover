###### The history
After several unsuccessful attempts to find functional and free tool for removing BOM from files,
So I've decided to write my own.

###### Usage
java -jar target/bom-remover-1.0.jar -f $folder [-m mask1] [-m mask2] [-m maskN] [-r]

1. $folder -- root folder to search in
2. mask1...maskN -- file masks, you can provide 0+ masks; "*" will be used if no mask provided.
3. -r -- whether to scan subfolders of root folder, switched off by default

###### Examples
* "java -jar target/bom-remover-1.0.jar -f /Users/me" will process all files in /Users/me folder
* "java -jar target/bom-remover-1.0.jar -f C:\test -r" will process all files in C:\test folder and all subfolders
* "java -jar target/bom-remover-1.0.jar -f /usr/docs -m *.txt -m *.java -m ?Class*.hs -r" will process all files based upon given masks in /usr/doc folder and it's subfolders

Processing type was added for convenience in future (maybe sometimes I will improve algorithm with more fast and least buggy processing)
Tool will find all files (if any) and remove any BOM types from them: utf-8, utf-16 or utf-32.

###### Requirements
Java 1.7+

###### Using as external dependency

```xml
<repository>
  <id>kishlaly</id>
  <url>http://maven.kishlaly.com</url>
</repository>
```

```xml
<dependency>
  <groupId>com.kishlaly.utils</groupId>
  <artifactId>bom-remover</artifactId>
  <version>1.0</version>
</dependency>
```
###### Maven plugin

Another simple usage of that tool is to set [this maven plugin](http://github.com/s1ac2x1/bom-remover-maven)
