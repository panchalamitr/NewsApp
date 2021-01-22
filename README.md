# News App

I have made News App using MVVM architecture.

There are three screens
-
1) Login Screen (Google, Facebook Login) 
2) Top 5 news headlines
3) Detail Screen
-------------------------
I have used following tools


 - Timber ⏺ (For Log purpose)
 - RxJava 🚀 ( For Async Operation)
 - DaggerHilt 💉 ( Dependancy Injection)
 - Retrofit 📲 (Network Call)
 - LoggingInterceptor ⏺︎ (Api Logs)

-------------------------
**Video Link**
[![Everything Is AWESOME](https://github.com/panchalamitr/NewsApp/blob/main/screenshot/Youtube.png)](https://youtu.be/vU4uO8UXnKM "Everything Is AWESOME")

-------------------------

**Screenshots**
![News App Screenshot](https://github.com/panchalamitr/NewsApp/blob/main/screenshot/Banner.jpg)

-------------------------

**Description**

1) Login Screen (Google, Facebook Login) 
I have done login operation in SocialLoginActivity 

2) NewsHeadLinesActivity
I have fetch data from the (The Guardian Open Platform) API and displayed those records. 
I have followed MVVM Architecture in this screen 
I have created NewsHeadLinesActivityas UI, NewsHeadLinesViewModel as ViewModel and NewsHeadLinesRepository as Repository

3) DetailNewsActivity
Here I just need to display 5 to 10 lines of detail description, but I could not found detail description so I have load that webpage using Webview.

