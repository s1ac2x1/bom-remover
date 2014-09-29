After several unsuccessful attempts to find functional and free tool for removing BOM from files,
I have decided to write my own.

Usage is as simple as it source code:

run.bat $sourceFolder $mask $type $deep

$sourceFolder - root folder to scan files in
$mask - files mask
$type - processing type. Currently there is just one type: default
$deep - whether to scan subfolders of root folder (y/n)

example: run.bat C:\test *.xml default y

Processing type was added for convenience in future (maybe sometimes I will improve algorithm with more fast and lest buggy processing)

Tool will find all files with specified mask and remove any BOM types from them (utf-8, utf-16, utf-32)

You should have Java 1.7+ and Maven installed in your Windows :)
