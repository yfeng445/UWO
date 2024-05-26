from test2 import compute_tweets
while(True):
    tweets = input("Please enter tweets file name: ")
    keywords = input("Please enter keywords file name: ")
    results = compute_tweets(tweets,keywords)
    break
"""    if not results == ():
        print("")
        print("Happiness score in eastern is: ",results[0][0])
        print("Number of tweets: ",results[0][1])
        print("Number of keyword tweets: ",results[0][2])
        print("")
        print("Happiness score in central is: ",results[1][0])
        print("Number of tweets: ",results[1][1])
        print("Number of keyword tweets: ",results[1][2])
        print("")
        print("Happiness score in mountain is: ",results[2][0])
        print("Number of tweets: ",results[2][1])
        print("Number of keyword tweets: ",results[2][2])
        print("")
        print("Happiness score in pacific is: ",results[3][0])
        print("Number of tweets: ",results[3][1])
        print("Number of keyword tweets: ",results[3][2])
        print("")
        break
    else:
        print("The file does not exist")"""



