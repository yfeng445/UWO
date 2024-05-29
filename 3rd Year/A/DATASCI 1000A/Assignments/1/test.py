import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
data = pd.read_csv("C:/Users/56111/OneDrive - The University of Western Ontario/PrevCourses/3rd Year/A/DATASCI 1000A/Assignments/1/tobacco_data.csv")
data = np.array(data)
#print(data)
plt.xlabel("Years")
plt.ylabel("Use of any tobacco")
plt.title("High School Tobacco Use")
plt.bar(data[:,6],data[:,0],)
plt.show()


