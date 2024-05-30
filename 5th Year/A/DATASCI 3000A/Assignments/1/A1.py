import matplotlib.pyplot as plt
import numpy as np
import pandas as pd
import scipy.stats as ss
import scipy.optimize as so
# %matplotlib inline

def linearModelPredict(b,X):
    yp = np.dot(b,X.T)
    return yp
def linearModelLossRSS(b, X, y):
    predY = linearModelPredict(b, X)
    residual_sum_of_squares = 0
    delta = np.zeros(len(y))
    for i in range(len(delta)):
        delta[i] = (predY[i] - y[i]) ** 2
        residual_sum_of_squares += round((predY[i] - y[i]) ** 2, 2)
        # print(linearModelPredict(b,X)[i], y[i])
        # print(residual_sum_of_squares)
    gradient = np.gradient(delta)

    return (residual_sum_of_squares, gradient)

def linearModelFit(X,y,lossfcn = linearModelLossRSS):
    bstart = [0,0,0]
    RESULT = so.minimize(lossfcn,bstart, args = (X.T,y))
    return RESULT;
    #return (estimated_betas,R2)


X = np.array([[1,0],[1,-1],[1,2]])
y = np.array([0,0.4,2])
b = np.array([0.1,0.3])

print(linearModelFit(X,y))