# Currency Exchange Application Using Kotlin
In this repository I made currency converter app in Kotlin Language with using MVVM architecture. I fetch data from api which is [here][1]. The api contains more country codes, but I chose to reduce it.

Libraries Used
--------------
* [Architecture][10] - A collection of libraries that help you design robust, testable, and maintainable apps.
    * [Lifecycles][11] - Create a UI that automatically responds to lifecycle events.
    * [ViewModel][12] - Easily schedule asynchronous tasks for optimal execution.
    * [LiveData][13] - Build data objects that notify views when the underlying database changes.

* Third party and miscellaneous libraries
    * [Retrofit][30] for turns your HTTP API into a Java interface
    * [Gson][31] for convert Java Objects into their JSON representation
    * [RxJava][32] for composing asynchronous and event-based programs by using observable sequences.


Architecture
--------------
The app uses [MVVM architecture][10] to have a unidirectional live of data, separation of concern, testability, and a lot more.




[1]: https://apilayer.com/marketplace/exchangerates_data-api
[10]: https://developer.android.com/topic/architecture
[11]: https://developer.android.com/jetpack/androidx/releases/lifecycle
[12]: https://developer.android.com/topic/libraries/architecture/viewmodel
[13]: https://developer.android.com/topic/libraries/architecture/livedata
[30]: https://square.github.io/retrofit/
[31]: https://github.com/google/gson
[32]: https://github.com/ReactiveX/RxJava