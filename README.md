# Paging Library Architecture Component Demo

[![Build Status](https://travis-ci.org/jshvarts/PagingRoom.svg?branch=master)](https://travis-ci.org/jshvarts/PagingRoom) [![Android Weekly](https://img.shields.io/badge/Android%20Weekly-%23329-2CA3E6.svg?style=flat)](http://androidweekly.net/issues/issue-329)

Companion article: [Exploring Paging Library from Jetpack](https://proandroiddev.com/exploring-paging-library-from-jetpack-c661c7399662)
 
This project contains a sample of using the [Paging Library Architecture Component](https://developer.android.com/topic/libraries/architecture/paging/) by way of a simple Notes app--no domain knowledge needed to understand the purpose of this app.

## Libraries
* [Paging Library](https://developer.android.com/topic/libraries/architecture/paging/)
* [Room Persistence Library](https://developer.android.com/topic/libraries/architecture/room)
* [Navigation Architecture Component](https://developer.android.com/topic/libraries/architecture/navigation/)
* [Lifecycle Architecture component](https://developer.android.com/topic/libraries/architecture/lifecycle)
* [Dagger Android 2.17](https://google.github.io/dagger/android.html) or [Koin](https://insert-koin.io/)
* [RxJava](https://github.com/ReactiveX/RxJava)

## App Design
The app follows **Clean Architecture** Principles to make it cleaner, more readable and maintainable.
* Separate layers: Data, Domain, Presentation and UI
* MVVM architecture for clean separation between UI and Presentation and below
* Repository pattern
* Mapping model between data and domain (`NoteEntity` and `Note`)   

## Branches
1) [master](https://github.com/jshvarts/PagingRoom) -- common setup with no paging used
2) [room-livedata](https://github.com/jshvarts/PagingRoom/tree/room-livedata) -- paging using `LiveData` and default `DataSource` 
3) [room-rxjava](https://github.com/jshvarts/PagingRoom/tree/room-rxjava) -- paging using `RxJava` and default `DataSource` 
4) [room-livedata-custom-datasource](https://github.com/jshvarts/PagingRoom/tree/room-livedata-custom-datasource) -- paging using `LiveData` and custom `ItemKeyedDataSource`
5) [room-livedata-custom-datasource-koin](https://github.com/jshvarts/PagingRoom/tree/room-livedata-custom-datasource-koin) -- paging using `LiveData` and custom `ItemKeyedDataSource` with [Koin](https://insert-koin.io/)

## License

    Copyright 2018 James Shvarts

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

