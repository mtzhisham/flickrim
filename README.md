# FlickrIM

A demonstration of some of almost latest Android technologies to interface Flickr API  

## Build

Add an API key to `gradle.properties`  

```gradle
api_key = "key_here"
```

## Summary

- [x] Used Kotlin
- [x] Used MVVM as architecture design pattern and utilized most of Android Jetpack components
- [x] Used Room for local storage and Rerofit for API Interfacing
- [x] Data is loaded through a datasource that provides a paged list for the view model to populate the photos list
- [x] Used Flickr search API to get photos with most interestingness from date of post request back to one yearm couldn't find an API for trending of popular for reference [what is flickr interestingnes](hhttp://www.steves-digicams.com/knowledge-center/how-tos/online-sharing-social-networking/what-is-flickr-interestingness.html)
- [x] used Picasso with default caching options and a third party library for pinch to zoom option
- [x] used a handler to run an update task each minute    


