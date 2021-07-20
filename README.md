# multiKeySumValue

This is a simple open script that helps you quickly match two keys in an excel table and calculate the sum of the corresponding columns.

The code uses two JAR packages, including poi-3.15.jar and commons-collections-3.2.jar. You can find both packages in the lib directory.

The above two JARs need to be loaded correctly when importing the local IDE.

For IDEA: file -> Project Structure -> Modules

For eclipse: Right click -> build path -> configue build path -> Libraries -> Add JARs

For command line: 

cd fullpath/src 
java -cp .:fullpath/lib/commons-collections-3.2.jar:fullpath/lib/poi-3.15.jar RunData fullpath/data/testRun.xls 0 2 5 10

You need to replace fullpath with the path to the multikeySumValue file.

For Windows users, it is important to convert ":" to ";".
