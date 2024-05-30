from sklearn.linear_model import Ridge
from sklearn import linear_model as skl
from sklearn.datasets import make_regression
from sklearn.model_selection import train_test_split

# Generate a sample dataset with 100 samples, 3 features, and added noise
X, y = make_regression(n_samples=100, n_features=3, noise=0.1, random_state=42)

# Split the data into training and test sets
seed = 2023
X_train, X_test, y_train, y_test = train_test_split(X, y, random_state=seed, test_size=0.3)
ridge_reg = skl.Ridge(alpha=4.0, fit_intercept=True)
ridge_reg.fit(X_train, y_train)
score = ridge_reg.score(X_test, y_test)

print(f"R^2 Score for Ridge Regression on Test Data: {score:.4f}")
