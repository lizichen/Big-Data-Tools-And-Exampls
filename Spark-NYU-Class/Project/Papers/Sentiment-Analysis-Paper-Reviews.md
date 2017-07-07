
# Papers for the Project: Stock Price Prediction by Financial News Sentiment Analysis

There are two theories in the work of 'Stock Prediction':
1. An **“informational”** theory: Information published online publication medias is new, in the sense that it has not yet been incorporated into stock prices. That information can thus be expected to influence the value one could rationally expect for the future cash flows of an asset.
2. A **“sentimental”** theory: The price of an asset deviates from its fundamental value depending on waves of optimism or pessimism, and online news media enables the measurement of investor sentiment.

### 1. Sentiment analysis algorithms and applications: A survey - Walaa Medhat

This paper presents a full analysis on the various approaches from **54 published papers** in the last year. This survey can be useful for new comer researchers in this field as it covers the most famous SA techniques and applications in one research paper. It discusses also new related fields in SA which have attracted the researchers lately and their corresponding articles. These fields include Emotion Detection (ED), Building Resources (BR) and Transfer Learning (TL). Emotion detection aims to extract and analyze emotions, while the emotions could be explicit or implicit in the sentences.   

The **supervised learning** approach has four sub-methods: 1) Decision Tree, 2) Linear Classifier, 3)Rule-Based Classifier, 4)Probablistic Classifier, where Linear Classifier includes SVM and Neural Network, and Probablistic Classifier includes Navie Bayes, Bayesian Network, and Maximum Entropy. 

The **Corpus-based** approach includes statistical method and semantic analysis method. 

**Neural Network** consists of many neurons where the neuron is its basic unit. Multilayer neural networks are used for non-linear boundaries. These multiple layers are used to induce multiple piece-wise linear boundaries, which are used to approximate enclosed regions belonging to a particular class. The outputs of the neurons in the earlier layers feed into the neurons in the later layers. The training process is more complex because the errors need to be back-propagated over different layers. 

There is an empirical comparison between SVM and Artificial neural networks ANNs presented by Moraes and Valiati regarding document-level sentiment analysis. They made this comparison because SVM has been widely and successfully used in SA while ANNs have attracted little attention as an approach for sentiment learning. They have discussed the requirements, resulting models and contexts in which both approaches achieve better levels of classification accuracy. They have also adopted a standard evaluation context with popular supervised methods for feature selection and weighting in a traditional BOWs model. Their experiments indicated that ANN produced superior results to SVM except for some unbalanced data contexts. They have tested three benchmark data sets on Movie, GPS, Camera and Books Reviews from amazon.com. They proved that the experiments on movie reviews ANN outperformed SVM by a statistically significant difference. They confirmed some potential limitations of both models, which have been rarely discussed in the SA literature, like the computational cost of SVM at the running time and ANN at the training time. They proved that using Information gain (a computationally cheap feature selection Method) can reduce the computational effort of both ANN and SVM without significantly affecting the resulting classification accuracy.

SVM and NN can be used also for the classification of personal relationships in biographical texts as presented by van de Camp and van den Bosch [47]. They marked relations between two persons (one being the topic of a biography, the other being mentioned in this biography) as positive, neutral, or unknown. Their case study was based on historical biographical information describing people in a particular domain, region and time frame. They showed that their classifiers were able to label these relations above a majority class baseline score. They found that a training set containing relations, surrounding multiple persons, produces more desirable results than a set that focuses on one specific entity. They proved that SVM and one layer NN (1-NN) algorithm achieve the highest scores.

### 2. Techniques and Applications for Sentiment Analysis - Ronen Feldman

Sentiment Analysis and Opinion Mining is the computational study of people’s opinions, attitudes and emotions toward an entity. The entity can represent individuals, events or topics. These topics are most likely to be covered by reviews. Opinion Mining extracts and analyzes people’s opinion about an entity while Sentiment Analysis identifies the sentiment expressed in a text then analyzes it. Sentiment Analysis can be considered a classification process. 

### 3. Sentiment Analysis in Financial News - Pablo Daniel Azar

This paper studies the relation between the **numerical information** found in financial markets and the **verbal information** reported in *Financial news*. It explores whether we can use market data to teach a computer to distinguish between positive news and negative news. It touches on the possibility of determining polarity in financial texts and applying it to other domain areas such as movie review, etc. It also analyzes the approach of using knowledge about polarity in texts (financial news) to predict market returns.

This paper compares and combines the existing methods to sentiment analysis in Financial news, evaluating them using the above questions. 

The paper concludes that methods from machine learning are very effective at distinguishing between positive and negative news. However, algorithms trained on Financial news are not useful for predicting sentiment in **movie reviews**. It also concludes that the methods from machine learning outperform the methods proposed by the Finance community when predicting future stock returns. **These predictions suggest strategies that generate positive returns when trading with public information, but these returns vanish after accounting for trading costs.**