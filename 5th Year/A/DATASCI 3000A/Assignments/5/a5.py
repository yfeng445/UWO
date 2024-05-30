import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
import seaborn
import seaborn as sns
from sklearn.model_selection import train_test_split,StratifiedKFold
from sklearn.linear_model import LogisticRegression
from sklearn.pipeline import Pipeline
from sklearn.preprocessing import StandardScaler
from sklearn.metrics import roc_curve, auc, roc_auc_score

pd.set_option('display.max_columns', 500)
#%matplotlib inline
plt.style.use('ggplot')



data = pd.read_csv('loan_Data.csv',on_bad_lines='skip')
df = data['loanAmnt;annualInc;application_type;int_rate;revol_bal;revol_util;dti;emp_length;grade;homeOwnership;installment;job;loanDefault;mortAcc;pub_rec_bankruptcies;purpose;term;Year'].str.split(';', expand=True)
df.columns = ['loanAmnt', 'annualInc', 'application_type', 'int_rate', 'revol_bal', 'revol_util', 'dti', 'emp_length', 'grade', 'homeOwnership', 'installment', 'job', 'loanDefault', 'mortAcc', 'pub_rec_bankruptcies', 'purpose', 'term', 'Year']
df['loanDefault'] = np.where(df['loanDefault'] == 'Charged Off', 1, 0)

df['annualInc'] = np.log(df['annualInc'].astype(float))
df['loanAmnt'] = df['loanAmnt'].astype(float)
df['int_rate'] = df['int_rate'].astype(float)
df['revol_bal'] = df['revol_bal'].astype(float)
df['revol_util'] = df['revol_util'].astype(float)
df['dti'] = df['dti'].astype(float)
df['emp_length'] = df['emp_length'].astype(int)
df['installment'] = df['installment'].astype(float)
df['mortAcc'] = df['mortAcc'].astype(int)
df['term'] = df['term'].astype(int)





X = df[['annualInc']]
y = df['loanDefault']
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=0)
pipe = Pipeline([
    ('scaler', StandardScaler()),
    ('logreg', LogisticRegression(solver='lbfgs', max_iter=10000, random_state=0))])
pipe.fit(X_train, y_train)
prob = pipe.predict_proba(X_test)

#print(np.shape(y_test)) # 197,
#print(prob.T[1]) # 197,2


fpr, tpr, thresholds = roc_curve(y_test, prob.T[1])
auc_value = roc_auc_score(y_test, prob.T[1])

def AUC_calculation(model, X, y, index_train, index_test):
    Xtrain, ytrain = X.iloc[index_train], y.iloc[index_train]
    Xtest, ytest = X.iloc[index_test], y.iloc[index_test]

    # Fit the model
    model.fit(Xtrain, ytrain)

    # Predict the probability of the positive class
    proba_positive_class = model.predict_proba(Xtest)[:, 1]

    # Calculate the auc score
    score_auc = roc_auc_score(ytest, proba_positive_class)

    return score_auc


def AUC_cross_validation(model, X, y, n_fold):
    skf = StratifiedKFold(n_splits=n_fold, shuffle=True, random_state=42)

    list_auc = []
    for train_index, test_index in skf.split(X, y):
        auc = AUC_calculation(model, X, y, train_index, test_index)
        list_auc.append(auc)

    return list_auc



data = pd.read_csv('loan_Data.csv',on_bad_lines='skip')
df = data['loanAmnt;annualInc;application_type;int_rate;revol_bal;revol_util;dti;emp_length;grade;homeOwnership;installment;job;loanDefault;mortAcc;pub_rec_bankruptcies;purpose;term;Year'].str.split(';', expand=True)
df.columns = ['loanAmnt', 'annualInc', 'application_type', 'int_rate', 'revol_bal', 'revol_util', 'dti', 'emp_length', 'grade', 'homeOwnership', 'installment', 'job', 'loanDefault', 'mortAcc', 'pub_rec_bankruptcies', 'purpose', 'term', 'Year']
df['loanDefault'] = np.where(df['loanDefault'] == 'Charged Off', 1, 0)
df['annualInc'] = np.log(df['annualInc'].astype(float))
df['loanAmnt'] = df['loanAmnt'].astype(float)
df['int_rate'] = df['int_rate'].astype(float)
df['revol_bal'] = df['revol_bal'].astype(float)
df['revol_util'] = df['revol_util'].astype(float)
df['dti'] = df['dti'].astype(float)
df['emp_length'] = df['emp_length'].astype(int)
df['installment'] = df['installment'].astype(float)
df['mortAcc'] = df['mortAcc'].astype(int)
df['term'] = df['term'].astype(int)



feau_num = ['int32', 'int64', 'float64']
data_num = pd.DataFrame(df.select_dtypes(include=feau_num))
data_num['loanAmnt'] = np.log(data_num['loanAmnt'])
data_num = data_num.drop(columns=['Year', 'installment'], errors='ignore')

AUC_models = pd.DataFrame()


# Run cross-validation for each feature
for feature in data_num.columns:
    auc_scores = AUC_cross_validation(pipe, data_num[[feature]], y, n_fold=10)  # Using each feature for prediction
    AUC_models[f'simple-{feature}'] = auc_scores
#print(AUC_models)


# Melt the dataframe for plotting
melted_AUC_models = AUC_models.melt(var_name='Features', value_name='AUC Score')
"""
# Create the boxplot
plt.figure(figsize=(15, 8))
sns.boxplot(x='Features', y='AUC Score', data=melted_AUC_models, showfliers=False)  # showfliers=False to hide outliers
plt.xticks(rotation=45)  # Rotate feature names for better visualization
plt.title('AUC scores for each feature')
plt.tight_layout()
plt.show()
"""

# 1. Extract X and y
X_all = data_num
y = data['default']  # Assuming 'default' is your target

# 2. Calculate the auc scores using cross validation
auc_scores_all_numeric = AUC_cross_validation(pipe, X_all, y, n_fold=10)

# 3. Include the auc scores in the AUC_models DataFrame in the column 'All_numeric'
AUC_models['All_numeric'] = auc_scores_all_numeric

# Print the new data frame
print(AUC_models)

# 4. Visualize using sns.boxplot

# Melt the dataframe for plotting
melted_AUC_models = AUC_models.melt(var_name='Features', value_name='AUC Score')

# Create the boxplot
plt.figure(figsize=(15, 8))
sns.boxplot(x='Features', y='AUC Score', data=melted_AUC_models, showfliers=False)  # showfliers=False to hide outliers
plt.xticks(rotation=45)  # Rotate feature names for better visualization
plt.title('Distribution of AUC scores for each feature including All Numeric Features')
plt.tight_layout()
plt.show()

from sklearn.model_selection import train_test_split
from sklearn.metrics import roc_auc_score

# 1. Train test split
X_train, X_test, y_train, y_test = train_test_split(X_all_with_grade, y, test_size=0.2, random_state=42)

# 2. Fit the model
pipe.fit(X_train, y_train)

# 3. Calculate the predictions on the Test data
y_prob = pipe.predict_proba(X_test)[:, 1] # Probability of class 1 (default)

# Create the AUC
auc = roc_auc_score(y_test, y_prob)
print(f'Original AUC: {auc}')

# 4. Bootstrap
bootstrap_aucs = []
n_bootstrap = 1000

for _ in range(n_bootstrap):
    # Sample with replacement from y_test and y_prob
    indices = np.random.choice(len(y_test), len(y_test), replace=True)
    y_test_sampled = y_test.iloc[indices].values
    y_prob_sampled = y_prob[indices]
    bootstrap_aucs.append(roc_auc_score(y_test_sampled, y_prob_sampled))

# 5. Plot
plt.figure(figsize=(10, 6))
sns.histplot(bootstrap_aucs, kde=True, bins=30)
plt.title('Bootstrap Distribution of AUC Scores')
plt.xlabel('AUC')
plt.ylabel('Frequency')
plt.show()

# Find the confidence interval
alpha = 0.05
ci_min = np.percentile(bootstrap_aucs, 100 * alpha / 2.)
ci_max = np.percentile(bootstrap_aucs, 100 * (1 - alpha / 2.))

print(f'The CI for the AUC of the model is: {(ci_min, ci_max)}')






