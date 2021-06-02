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
#path_to_directory+name+file.name
def plot_2(to, mu,x,y,file_name):
    num_of_sh = to * mu
    t = np.linspace(0, to, int(num_of_sh), True)
    V = [math.sin(2*math.pi*mu*item + math.atan2(x,y) * 180 / math.pi)  for item in t]

    fig, ax = plt.subplots()
    ax.plot(t, V,  label = 'График V_x')
    ax.legend()
    ax.grid()

    #  Добавляем подписи к осям:
    ax.set_xlabel('t')
    ax.set_ylabel('V_x')
    ax.set_title('График V_x')
    fig.set_figheight(5)
    fig.set_figwidth(8)
    # показываем график
    fig.savefig(file_name + "/2.png")

def plot_3(to, mu, ro,x,y,file_name):
    r = pow(x**2 + y**2, 1/2)
    num_of_sh = to * mu
    t = np.linspace(0, to, int(num_of_sh), True)
    V = [math.sin(2*math.pi*mu*item + math.atan2(x,y) * 180 / math.pi)*r/ro  for item in t]

    fig, ax = plt.subplots()
    ax.plot(t, V,  label = 'График V_y')
    ax.legend()
    ax.grid()

    #  Добавляем подписи к осям:
    ax.set_xlabel('t')
    ax.set_ylabel('V_y')
    ax.set_title('График V_y')
    fig.set_figheight(5)
    fig.set_figwidth(8)
    # показываем график
    fig.savefig(file_name + "/3.png")

def plot_4(to, mu, h,ro,x,y,file_name):
    num_of_sh = to * mu
    t = np.linspace(0, to, int(num_of_sh), True)
    V = [math.sin(2*math.pi*mu*item + math.atan2(x,y) * 180 / math.pi)*h/ro  for item in t]

    fig, ax = plt.subplots()
    ax.plot(t, V,  label = 'График V_z')
    ax.legend()
    ax.grid()

    #  Добавляем подписи к осям:
    ax.set_xlabel('t')
    ax.set_ylabel('V_z')
    ax.set_title('График V_z')
    fig.set_figheight(5)
    fig.set_figwidth(8)
    # показываем график
    fig.savefig(file_name + "/4.png")

script, first, second, third, fourth, fifth,sixth, seventh, eith = argv
x_in = float(first)
y_in = float(second)
z_in = float(third)
h_in = float(fourth)
t_in = float(fifth)
mu_in = float(sixth)
z_0_in = float(seventh)
plot_2(t_in,mu_in,x_in, y_in,eith)
plot_3(t_in,mu_in,z_0_in, x_in, y_in,eith)
plot_4(t_in,mu_in,h_in,z_0_in,x_in, y_in,eith)
drawPlot(x_in,y_in,z_in,h_in, eith)