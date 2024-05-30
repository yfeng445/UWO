//1
db.tweets.find({"in_reply_to_screen_name": "globeandmail"})

//2
db.tweets.find({"user.screen_name": "MLHealthUnit"})
