# GPX-JCONVERTER
Gpx-jconverter is a java project that can grab information from geocaching.com, store them in a local database or export them in a gpx format

It allows to generate gpx file even with a basic account but simplifies queries by storing all information in a local database

---

### Done

The **Downloader** is able to log-on based on the *Configuration* provided (call the ensure ensureLoggedIn method)
The **Downloader** can download read cache page HTML and use the search page

The **CachePageParser** is able to read a cache page HTML and convert it to a Java Object as I only have a basic account, the premium caches are considered a bit differently. Later this should be better tested with premium/basic accounts

The **Searcher** is able to perform a search from latitude and longitude for any cache in a certain amont of km. It parses each line and create Geocache objects with as much information as provided in the search result. On every cache parsed it notifies the registered CacheListeners (if any).

The **HibernateCacheListener** save each newCache in a database.

The **DefaultConfiguration** singleton will use a file named "config.json" in the current (execution) folder that contains the configuration properties like username, password, cookies, ...

### TODO

* Improve the exceptions to avoid bugs and give more information on what went wrong
* Parse the trackable informations available in the geocache page
* Create a GUI
* Improve the GPX format for multi cache
* Test with encoded logs
* Test with a premium/basic account
* ...
