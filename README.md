# [Scry](https://play.google.com/store/apps/details?id=com.taylorcase.hearthstonescry&hl=en)
[![Build Status](https://travis-ci.com/caseta/Scry.svg?branch=master)](https://travis-ci.com/caseta/Scry) [![codecov](https://codecov.io/gh/caseta/Scry/branch/master/graph/badge.svg)](https://codecov.io/gh/caseta/Scry)

Android app that lets users browse through Hearthstone cards, save cards, etc.

- Kotlin
- Repository Pattern with Room
- RxJava 2
- Dagger 2
- MVP - Model View Presenter

### TODO

Features
------
- [x] Add favorite hero
- [x] Add saved cards
- [x] Add card filter view
- [ ] Add sort cards feature 
- [ ] Add create deck feature

Maintenance
------
- [x] Add Crashlytics
- [x] Setup unit testing with Dagger 2
- [x] Setup error callbacks for all network RxJava 2 calls
- [x] Add leak canary
- [ ] Add new paging library
- [ ] Update packages to androidx

Known Issues
------
- [ ] A few Shared Element Transition issues -- overdrawing, etc. Also issues with Samsung devices because of their unique Activity transition animation
- [ ] Fix the proper color of Collapsing toolbar text when it is expanded. Sometimes black when should be white, etc.
