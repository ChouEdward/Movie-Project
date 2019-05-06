# Movie Project


# Search Features

In this develop phase I, I have implemented the search features for my app. In this search feature, the users can input any numbers of key words knows as terms and then my system of app will sent this key words to my server. When my server receives these key words, it will perform the search function as quick as possible. After the server can find the Top K results of movies, it will send them back to my app and my app will show these movies to users. This process is very efficiency because I have implemented many optimizing algorithms on it. The user’s experience is also very good. The specific implement of algorithm has been showed below.

## Implementation Procedure 

__Deployment Instruction__
* Download the Java Server and Android App Client
* Open the Apache web server with port 8080, and run the java server on Intellij
* Open the Android App on Android Studio
* Connect your computer with an Android Simulator
* Click the Run button to compile the project and run it on the Android Simulator

__Implementation Instruction__
* Build Java Server with SSM framework and read the movies data from CSV files to Mysql Database.
* Tokenize the content of each movie.
* Tokenize the content of each movie.
* Perform stopword removal on the obtained tokens.
* Perform stemming on the obtained tokens.  
* Using the tokens, compute the TF-IDF vector for each movie. Use the following equation that we learned in the lectures to calculate the  term weights, in which t is a token and d is a movie, and TF-IDF vectors have been normalized. 
```
W(t,d)=(1+logTF(t,d))*(log(N/DF(t)))
```
* Given a query string, calculate the query vector and convert it to lower case. In calculating the query vector, don't consider IDF., use the following equation to calculate the term weights in the query vector, in which t is a token and q is the query, and the vector will also be normalized. 
```
W(t,q)=(1+logTF(t,q))
```
* Compute the cosine similarity between query and all of movies to find the movies that attains the highest Top-K cosine similarity score and send back to Android Client.

## Android App Client

![Search 1](https://lh6.googleusercontent.com/HCP578znIUvk8Qpkfr-hVNfg3QaA_yfI8AWIM2xbYQB2AHd0Gj09VjNWNshV5jcRxoTnbAaqj-svNZJ_iZH-PpZom71DjnUT4HZjs86QnfYXdukpUw=w271)
