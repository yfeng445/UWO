from sentence_transformers import SentenceTransformer
from numpy import dot
from math import sqrt
import torch
import json


if torch.cuda.is_available():
    torch_device = 'cuda'
else: 
    torch_device = "cpu"

# Load MiniLM model
minilm_model = SentenceTransformer('sentence-transformers/all-MiniLM-L6-v2', device=torch_device)
# Load GLoVe model
glove_model = SentenceTransformer('sentence-transformers/average_word_embeddings_glove.840B.300d', device=torch_device)

def get_tweets():
    file_path = "./tweets-utf-8.json"
    data = []
    with open(file_path, 'r', encoding='utf-8') as file:
        for line in file:
            data.append(json.loads(line))
    text = []
    for d in data:
        text.append(d['text'])
    return text

def cosine_similarity(a, b):
    dot_product = dot(a, b)
    norm_a = sqrt(dot(a, a))
    norm_b = sqrt(dot(b, b))
    if (norm_a == 0 or norm_b == 0): 
        return 0
    else: 
        return dot_product / (norm_a * norm_b)

def sort_by_sim(query_embedding,document_embeddings,documents):
    sim = []
    for i in range(len(document_embeddings)):
        sim.append((cosine_similarity(query_embedding,document_embeddings[i]),documents[i]))
    sim.sort(reverse=True)
    return sim

def glove_top25(query,documents):
    query_embedding = glove_model.encode(query)
    document_embeddings = glove_model.encode(documents)
    return sort_by_sim(query_embedding,document_embeddings,documents)[:25]

def minilm_top25(query,documents):
    query_embedding = minilm_model.encode(query)
    document_embeddings = minilm_model.encode(documents)
    return sort_by_sim(query_embedding,document_embeddings,documents)[:25]
        
## Test Code

tweets = get_tweets()

print("**************GLOVE*****************")
for p in glove_top25("I am looking for a job.",tweets): print(p)

print("**************MINILM*****************")
for p in minilm_top25("I am looking for a job.",tweets): print(p)
