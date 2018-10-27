# HearthstoneScry

Showcase Android app that lets users browse through Hearthstone cards, create decks, save cards, etc.

- Kotlin
- Room
- Repository Pattern - caching, disk, network
- RxJava 2
- Dagger 2
- MVP - Model View Presenter

### TODO

Features
------
- [x] Add favorite hero
- [x] Add saved cards
- [ ] Add card filter view
- [ ] Add create deck feature

Maintenance
------
- [ ] Add Crashlytics
- [x] Setup unit testing with Dagger 2
- [x] Setup error callbacks for all network RxJava 2 calls
- [x] Add leak canary
- [ ] Add new paging library
- [ ] Update packages to use Jetpack

Known Issues
------
- [ ] Weird Shared Element Transition issue. Click on a card on the grid, click back. Sometimes, just before that card settles in it's original place, it flashes and swaps places with another card above it in the grid
- [ ] Tweak drawing over parent for top app bar when a card does a shared element transition
- [ ] Fix the proper color of Collapsing toolbar text when it is expanded. Sometimes black when should be white, etc.
