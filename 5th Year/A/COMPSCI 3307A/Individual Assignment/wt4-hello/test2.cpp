//
// Created by allen on 10/1/23.
//

#include "test2.h"
#include <iostream>
#include <random>

int main() {
    // Create a random number generator
    std::random_device rd;
    std::mt19937 generator(rd()); // Mersenne Twister pseudo-random number generator

    // Define a range for the random number (e.g., between 1 and 100)
    int min = 1;
    int max = 100;

    // Create a distribution to generate random numbers within the specified range
    std::uniform_int_distribution<int> distribution(min, max);

    // Generate a random number
    int randomNum = distribution(generator);

    // Print the random number
    std::cout << "Random number between " << min << " and " << max << ": " << randomNum << std::endl;

    return 0;
}
