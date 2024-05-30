# Packages for this assignment
import numpy as np
import matplotlib.pyplot as plt
import pandas as pd
import scipy.optimize as so
from sklearn.linear_model import LinearRegression
from sklearn.metrics import mean_squared_error
from IPython.display import display

def exponentialNegLogLikelihood(lamb, y):
    result = 0
    if (type(lamb) == type(0)):
        for i in range(len(y)):
            result += np.log(lamb) - lamb * y[i]
        return 0-result
    elif (len(lamb) == len(y)):
        for i in range(len(y)):
            result += np.log(lamb[i]) - lamb[i] * y[i]
        return 0-result
    else:
        return None

def exponentialRegressionNegLogLikelihood(b,X,y):
    return exponentialNegLogLikelihood(np.exp(np.dot(X,b)), y)

def Model_predict(b, X):
    return np.exp(np.dot(X, b))

def Model_fit(X,y):
    # Instantiate a guess for the betas, beta_start, so that the optimizer has somewhere to start
    # Keep in mind what shape the beta_start should be. It shoud have the same number of elements as X as columns
    X_design = np.hstack([np.ones((X.shape[0], 1)), X])
    beta_start = np.zeros(X_design.shape[1])
    # Minimize the appropriate likelihood function
    mle = so.minimize(exponentialRegressionNegLogLikelihood, beta_start, args=(X_design, y))
    # Extract the maximum likelihood estimates from the optimizer.
    betas = mle.x
    return betas


data = pd.read_csv('exponential_regression.csv')
# 1 pts

# Get the x and y. Create the design matrix X
xlist = []
ylist = []

for index, row in data.iterrows():
    rowData = row[0].split(";")
    xlist.append(float(rowData[0]))
    ylist.append(float(rowData[1]))

x = np.array([xlist]).T
y = np.array(ylist)


betas = Model_fit(x,y)

y_pred = np.exp(np.dot(np.hstack([np.ones((x.shape[0], 1)), x]), betas))
mse = mean_squared_error(y, y_pred)
print("Mean Squared Error:", mse)

