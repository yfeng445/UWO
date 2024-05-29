import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
data = pd.read_csv("C:/Users/56111/OneDrive - The University of Western Ontario/PrevCourses/3rd Year/A/DATASCI 1000A/Assignments/1/ex01-25carcolor.csv")
data=  np.array(data)
#print(data)

plt.pie(data[:,1],colors='azure',labels=data[:,0],autopct="%.1f%%",)
plt.title("Percent of colors")
plt.show()
