# gpx-jconverter
A java project that can grab Geocaching.com information and format them in a GPX file or store all in a sqlite3 database

## Done and TODOs

Until now the GCDownloader is able to log-in and download the HTML of a cache page.

The GCCachePageParser is able to read a cache page HTML and convert it to a Java Object

The DefaultConfiguration singleton read/write a "config.json" file in the current (execution) folder that contains cookies (this use the remember-me opetion and prevent to login all the time)

What should be done is:

* Improve the exceptions
* Parse the trackable informations available in the geocache page
* The parser will probably crash on a premium geocache without a premium account (premium state should be a boolean)
* Add a parser able to perform a search and store GC codes
* Create a GUI
* ...
