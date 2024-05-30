from geolite2 import geolite2
import pandas as pd
import numpy as np

df = pd.read_csv("customer_data.csv")
df = df.drop(df[df['age'] > 80].index).drop(df[df['age'] < 18].index)
df = df.dropna()

