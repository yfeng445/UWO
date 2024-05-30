def fractional(n):
    result = n
    for i in range(n-1, 1, -1):
        print(i)
        result = result * i
    return result


print(fractional(6))