//3
db.tweets.aggregate([
    { $group: { _id: "$user.screen_name", total_tweets: { $sum: 1 } } },
    { $sort: { total_tweets: -1 } }
  ])

//4
db.tweets.aggregate([
    { $match: { "place.full_name": { $exists: true } } },
    { $group: { _id: "$place.full_name", total_tweets: { $sum: 1 } } },
    { $sort: { total_tweets: -1 } }
  ])

//5
db.tweets.aggregate([
    { $match: { "in_reply_to_screen_name": { $exists: true, $ne: null } } },
    { $group: { _id: "$in_reply_to_screen_name", total_replies: { $sum: 1 } } },
    { $sort: { total_replies: -1 } }
  ])

//6
db.tweets.aggregate([
    { $unwind: "$entities.hashtags" },
    { $group: { _id: "$user.screen_name", total_hashtags: { $sum: 1 } } },
    { $sort: { total_hashtags: -1 } }
  ])
  