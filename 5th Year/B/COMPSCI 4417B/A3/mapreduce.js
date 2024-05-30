function myMapper() {
    if (this.entities && this.entities.hashtags) {
        this.entities.hashtags.forEach(function(hashtag) {
            emit(hashtag.text, 1);
        });
    }
}

function myReducer(key, values) {
    return Array.sum(values);
}

db.tweets.mapReduce(
    myMapper,
    myReducer,
    {
        query: {}, 
        out: "hashtagCounts" 
    }
);

db.hashtagCounts.aggregate([
    { $sort: { value: -1 } } 
]);

let results = db.hashtagCounts.find().sort({ value: -1 }).toArray();
printjson(results);

