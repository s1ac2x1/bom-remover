After several unsuccessful attempts to find functional and free tool for removing BOM from files,
So I've decided to write my own.

Usage is simple:

java -jar target/bom-remover-1.0.jar $sourceFolder $mask $type $deep

$sourceFolder - root folder to scan files in
$mask - files mask
$type - processing type. Currently there is just one type: default
$deep - whether to scan subfolders of root folder (y/n)

example: java -jar target/bom-remover-1.0.jar C:\test *.xml default y

Processing type was added for convenience in future (maybe sometimes I will improve algorithm with more fast and least buggy processing)
Tool will find all files with specified mask and remove any BOM types from them (utf-8, utf-16, utf-32)

You should have installed Java 1.7+



