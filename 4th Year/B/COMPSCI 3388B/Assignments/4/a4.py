from PIL import *
from OpenGL.GL import *
import glfw
import numpy as np
import sys

"""
@Author: Yulun Feng
@ID: 251113989
@Date: March 13, 2023
"""

# class definition
class VertexData:
    def __init__(self):
        self.position = []
        self.normal = []
        self.color = []
        self.coordinate = []
class TriData:
    def __init__(self):
        self.data = []
class TexturedMesh:
    def __init__(self, PLYPath, bitMapPath):
        PLYFile = readPLYFile(PLYPath)
        self.elementVertex = PLYFile[0] # x, y, z, nx, ny, nz, u, v
        self.elementFace = PLYFile[1] # x, y, z
        self.bitMap = readBMPFile(bitMapPath)
        self.vertexPositionID = 0
        self.textureCoordinateID = 0
        self.faceVertexID = 0
        self.textureObjectID = 0 # store the bitmap image
        self.textureMeshID = 0 # used to render the texture mesh
        self.p_textureMeshID = 0 # for rendering the particular texture mesh


# File reading
def readBMPFile(filePath):
    list = []
    img = open(filePath, "rb")
    line = img.readline()
    while line:
        list.append(line)
        line = img.readline()
    return list
def readPLYFile(filePath):
    list = []
    file = open(filePath, "r")
    line = file.readline()
    while line:
        list.append(line.replace("\n", "").split(" "))
        line = file.readline()
    file.close()
    end_header = 0
    VDlist = []
    TDlist = []
    for i in range(len(list)):
        if not end_header:
            if list[i][0]=='end_header':
                end_header = 1
        else:
            #print("Data area: line", i)
            if len(list[i]) == 8: #VertexData Object
                tmp = VertexData()
                tmp.position = [float(list[i][0]), float(list[i][1]), float(list[i][2])] #x, y, z
                tmp.normal = [float(list[i][3]), float(list[i][4]), float(list[i][5])] #nx, ny,nz
                tmp.coordinate = [float(list[i][6]), float(list[i][7])] # u, v
                VDlist.append(tmp)
            if len(list[i]) == 4  and list[i][0] == '3': #TriData Object
                tmp = TriData()
                tmp.data = [int(list[i][1]), int(list[i][2]), int(list[i][3])]
                TDlist.append(tmp)
    t = (VDlist, TDlist)
    return t

# Rendering



# main function
TM = TexturedMesh(sys.argv)


