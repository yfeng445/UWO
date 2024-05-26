import sa
import compute_tweets

infile = input("Please enter tweets file name: ")
keywords = input("Please enter keywords file name: ")
filename = '"'+infile+'"'
keywordsname = '"'+keywords+'"'
infile = open(filename,"r")
keywords = open(keywordsname,"r")
#input words
