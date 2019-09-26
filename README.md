# E - Cell Android Application
This is the Official Repository of Entrepreneurship Cell, NIT Raipur's Android Application.

It is an android application for a complete guide to The Entrepreneurship Cell, NIT Raipur. This app contains complete detail about events, sponsors, moto, and vision of the committee. This app also contains an option for communicating with the E-Cell committee and most importantly an online quiz about Entrepreneurship.

## Table of Contents
* [Libraries and Dependencies](#librariesanddependencies)
* [Setup](#setup)
* [Walkthrough](#walkthrough)
* [Contributors](#contributors)

## Setup
### Steps to setup the Repository
1. Install the latest version of [Android Studio](https://developer.android.com/studio)
2. Clone the Repository
3. On launch of Android Studio, click on "Open an existing Android Studio project"
4. Go to the location of the cloned repository, select the folder with name ECellApp and icon of Android Studio and click on OK.
5. Let the Gradle build. It requires fairly good internet connection and about 15-20 minutes on average.

## Libraries and Dependencies
### Specifications :
* **Java version :** 1.8
* **Android Dependecies :** AndroidX
* **SDK :**
  * Minimum - 19
  * Maximum - 28

### Libraries Used :
* [Retrofit 2.5.0](https://square.github.io/retrofit/)
* [OkHttp 3.14.2](https://github.com/square/okhttp)
* [Glide 4.9.0](https://bumptech.github.io/glide/)
* [Glide Transformations 3.3.0](https://github.com/wasabeef/glide-transformations)
* [RxAndroid 2.2.10](https://github.com/ReactiveX/RxAndroid)
* [RxJava 2.1.1](https://github.com/ReactiveX/RxJava)
* [Lottie 3.0.7](https://github.com/airbnb/lottie-android)

## Walkthrough
1. ### Login Screen : 
    Extended ConstraintView with Vertical Scrolling Animation.
    
    <img src="https://user-images.githubusercontent.com/44755140/65644144-24eba680-e011-11e9-99cb-6ea791ae4186.gif" width="300">
    
    
2. ### Home Screen : 
    RecyclerView inside a ConstraintView for smooth scrolling of items with OnClick actions triggering a new activity.
    Menu button that triggers CustomAlertDialog
    
    <img src="https://user-images.githubusercontent.com/44755140/65649781-a0575300-e025-11e9-9622-55aeee8dcfbd.gif" width="300">
    
    
3. ### E-Summit '19 :
    NestedScrollView inside a ConstraintView for scrolling in section of the screen. Displaying list of speakers of E-Summit '19 called using APIs.
    
    <img src="https://user-images.githubusercontent.com/44755140/65633262-548eb480-dff9-11e9-9ad0-6dc05650fbff.png" width="300">
    <img src="https://user-images.githubusercontent.com/44755140/65633265-56587800-dff9-11e9-88dd-9771a5815c67.png" width="300">
    

4. ### Events :
    GridView of Events of E-Summit '19. For each event, user gets the description of the event along with 
    
    <img src="https://user-images.githubusercontent.com/44755140/65633285-5eb0b300-dff9-11e9-8bb8-cb7ffd36384f.png" width="300">
    <img src="https://user-images.githubusercontent.com/44755140/65681940-8f373200-e077-11e9-9fa3-c7e1be9fbb7a.gif" width="300">


5. ### B-Quiz :
    The Entrepreneurship Quiz. Real-Time made using Web Sockets and Rest APIs. LeaderBoard displaying rankers according to scores earned.
    
    <img src="https://user-images.githubusercontent.com/44755140/65633279-5b1d2c00-dff9-11e9-8f38-18023dfc28a0.png" width="300">
    <img src="https://user-images.githubusercontent.com/44755140/65633282-5ce6ef80-dff9-11e9-8fab-48889248ff10.png" width="300">
    
 
## Contributors
* [Viren Manoj Khatri](https://github.com/werainkhatri)
* [Prateek Kocher](https://github.com/prateekk2001)
* [Rishabh Vishwakarma](https://github.com/thepseudoartist)
* [Bhushan Thakre](https://github.com/bhushan-7)
