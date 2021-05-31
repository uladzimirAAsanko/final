import matplotlib.pyplot as plt
import numpy as np
from sys import argv
import matplotlib.pyplot as plt
import numpy as np
import math
import pylab
from scipy.special import hankel1


def drawPlot(x_in,y_in,z_in,h_in, file_name):
    x = x_in
    y = y_in
    r = pow(x**2 + y**2, 1/2)
    zo = 0
    z = np.linspace(0, z_in, 1000, True)
    h=h_in
    def p(r, z):
        result = 0
        for n in range(-1000, 1000):
            tmp = pow(-1, n)
            val = pow(r**2+pow(z-zo-2*n*h,2),1/2)
            val1 = pow(r**2+pow(z+zo-2*n*h,2),1/2)
            tmp = (pow(hankel1(0,r),val)/val)
            result += tmp.real
        return result

    pf = [p(r,item) for item in z]
    fig, ax = plt.subplots()
    ax.plot(z, pf,  label = 'Plot')
    ax.legend()
    ax.grid()

    #  Добавляем подписи к осям:
    ax.set_xlabel('z')
    ax.set_ylabel('p')
    ax.set_title('График')
    fig.set_figheight(5)
    fig.set_figwidth(8)
    # показываем график
    fig.savefig(file_name + "/1.png")
    plt.show()
#path_to_directory+name+file.name
script, first, second, third, fourth, fifth = argv
x_in = float(first)
y_in = float(second)
z_in = float(third)
h_in = float(fourth)
drawPlot(x_in,y_in,z_in,h_in, fifth  )