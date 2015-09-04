###### The history
After several unsuccessful attempts to find functional and free tool for removing BOM from files,
So I've decided to write my own.

###### Usage
java -jar target/bom-remover-1.0.jar $sourceFolder $mask $type $deep

1. $sourceFolder - root folder to scan files in
2. $mask - files mask
3. $type - processing type. Currently there is just one type: default
4. $deep - whether to scan subfolders of root folder (y/n)

###### Example
java -jar target/bom-remover-1.0.jar C:\test *.xml default y

Processing type was added for convenience in future (maybe sometimes I will improve algorithm with more fast and least buggy processing)
Tool will find all files with specified mask and remove any BOM types from them (utf-8, utf-16, utf-32)

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
