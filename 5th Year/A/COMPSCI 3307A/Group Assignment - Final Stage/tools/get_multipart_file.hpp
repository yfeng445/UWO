/**
 * Copyright 2022 Antoine Morin-Paulhus
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */


// Modified by Huiliang Xia, for the group project of CS3307 at Western Universtiy

#include <iostream>
#include <string>
#include <sstream>
#include <fstream>

/*  
	Get 1 file from stdin from a multipart string
	outputs content-disposition
 */

void get_multi_file(std::string &inFileContent, std::string &outFilePath){
	std::string line;
	std::istringstream inFile(inFileContent); 	std::string boundary;
	std::string disp; /* content disposition string */
	std::string type; /* content type string */
	std::string file; /* actual file content */
	
	
	int i = 0;
	
	while (std::getline(inFile, line))
	{
		if(i == 0){
			// Get boundary
			boundary = line.substr(0,line.length() - 1);
		} else if(line.find("Content-Disposition") == 0){
			disp = line;
		} else if(line.find("Content-Type") == 0){
			type = line;
		} else if(line.find(boundary) == 0){
			// The end
			type = line;
			break;
		} else if(line.length() == 1){
			// Time to get raw data
			
			std::ofstream outfile(outFilePath);
			char c;
			int bl = boundary.length();
			bool endfile = false;
			
			if(outfile){
				char buffer[256];
				
				while(!endfile){
					int j = 0;
					
					int k;
					
					while(j < 256 && std::cin.get(c) && !endfile){
						buffer[j] = c;

						k = 0;

						// Verify if we are at the end
						while(boundary[bl - 1 - k] == buffer[j - k]){
							if(k >= bl - 1){
								// We are at the end of the file
								endfile = true;
								break;
							}
							k++;
						}
						j++;
					}
					
					outfile.write(buffer, j);
					
					j = 0;
				};
			}
			outfile.close();
			break;
		}
		i++;
	}
	
	std::cout << disp << "\n";
}