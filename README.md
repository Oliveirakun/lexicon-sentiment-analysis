# Student's feedback polarity classifier
This repository contains the code used to reproduce the experiment for my article
about sentiment analysis. 

## Dependencies
* Java >= 8

## How to run
* Install java on your machine(if not installed)
* Clone this repository
* Execute the command:
```
$ ./gradlew run
```

## How it works
This project uses a sentiment analysis algorithm to classify student's feedbacks
in one of these three categories: positive, negative or neutral.
It implements the algorithm proposed by the original authors and a different version with some
modifications, according to the proposal of my article.

When you run this project, it will process all feedbacks from the file `feedbacks.txt`, compare the results with
the polarities in the other file `feedbacks-polarity.txt`  and will show a comparative of the results obtained from each algorithm
, for each feedback, like in this example:

```shell script
Feedback: Nothing to complain
Sentiment Score: -1
Fliped polarity: 0
Desired: neutral
```
**Feedback**: Is the feedback wrote by a student

**Sentiment Score**: Is the sentiment score calculated by the original implementation

**Fliped polarity**: Is the sentiment score calculated by the proposed change using the Vote & Flip algorithm

**Desired**: Is the correct polarity of the feedback

The result scores translates to polarities according to the following rule:

Score   |   Polarity
------- | -----------
 `> 0`  |   positive
 `< 0`  |   negative
 `= 0`  |   neutral
 
In the last line, will be printed the percentage of correct polarities classified by each implementation:
```
Percentage correct original: 76.47058823529412
Porcentage correct flipped: 80.3921568627451
```
Additionaly, will be displayed a word cloud image with all positive(green) and negative(red) words found in the feedbacks:

![Word cloud](/.images/word-cloud-article.png)

 
