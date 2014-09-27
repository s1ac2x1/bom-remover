After several unsuccessful attempts to find functional and free tool for removing BOM from files,
I have decided to write my own.

Usage is as simple as it source code:

run.bat $sourceFolder $mask 

example: run.bat C:\test *.xml

Tool will find recursively all files with specified mask and remove any BOM types from them (utf-8, utf-16, utf-32)

You should have Java 1.7+ and Maven installed in your Windows :)
