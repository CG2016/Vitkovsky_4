import matplotlib.pyplot as plt
import numpy as np


size = 20
# method = 'step'
# method = 'dda'
# method = 'bres'
# method = 'circle'


def dda(points, field):
    x, y, x2, y2 = *points[0], *points[1]
    x, y, x2, y2 = round(x), round(y), round(x2), round(y2)
    Dx, Dy = x2 - x, y2 - y
    # field = np.zeros((size, size), dtype=np.int8)
    field[round(y), round(x)] = 2
    if abs(Dx) > abs(Dy):
        if x2 < x:
            x, y, x2, y2 = x2, y2, x, y
        while x < x2:
            x += 1.
            y += Dy / Dx
            field[round(y), round(x)] = 2
    else:
        if y2 < y:
            x, y, x2, y2 = x2, y2, x, y,
        while y < y2:
            y += 1.
            x += Dx / Dy
            field[round(y), round(x)] = 2
    return field


def step_method(points, field):
    x1, y1, x2, y2 = *points[0], *points[1]
    x1, y1, x2, y2 = round(x1), round(y1), round(x2), round(y2)
    k = (y2 - y1) / (x2 - x1)
    b = y1 - k * x1
    # field = np.zeros((size, size), dtype=np.int8)
    if abs(y2 - y1) < abs(x2 - x1):
        if x1 > x2:
            x1, x2 = x2, x1
        for i in range(int(round(x1)), int(round(x2)) + 1):
            field[k * i + b][i] = 1
    else:
        if y1 > y2:
            y1, y2 = y2, y1
        for i in range(int(round(y1)), int(round(y2)) + 1):
            field[i][round((i - b) // k)] = 1
    return field


def bresenham(points, field):
    x1, y1, x2, y2 = *points[0], *points[1]
    x1, y1, x2, y2 = round(x1), round(y1), round(x2), round(y2)
    steep = abs(y2 - y1) > abs(x2 - x1)
    if steep:
        x1, y1, x2, y2 = y1, x1, y2, x2
    if x1 > x2:
        x1, y1, x2, y2 = x2, y2, x1, y1
    x, y = x1, y1
    Dx, Dy = x2 - x, abs(y2 - y)
    e = Dx / 2
    y_step = 1 if y1 < y2 else -1
    # field = np.zeros((size, size), dtype=np.int8)
    while x <= x2:
        field[round(x) if steep else round(y), round(y) if steep else round(x)] = 3
        e -= Dy
        if e < 0:
            y += y_step
            e += Dx
        x += 1
    return field


def circle(points):
    x1, y1, x2, y2 = *points[0], *points[1]
    x1, y1, x2, y2 = round(x1), round(y1), round(x2), round(y2)
    x1, y1 = round(x1), round(y1)
    x, y = round(x2), round(y2)
    # x, y = x2, y2
    field = np.zeros((size, size), dtype=np.int8)
    R_2 = (x2 - x1) ** 2 + (y2 - y1) ** 2
    if R_2 == 1.:
        field[y1 + 1, x1 + 1] = 1
        field[y1 + 1, x1] = 1
        field[y1 + 1, x1 - 1] = 1
        field[y1, x1 + 1] = 1
        field[y1, x1 - 1] = 1
        field[y1 - 1, x1 + 1] = 1
        field[y1 - 1, x1] = 1
        field[y1 - 1, x1 - 1] = 1
        return field
    while True:
        if x >= x1 and y >= y1:
            Dg = abs((x + 1 - x1) ** 2 + (y - y1) ** 2 - R_2)
            Dd = abs((x + 1 - x1) ** 2 + (y - 1 - y1) ** 2 - R_2)
            Dv = abs((x - x1) ** 2 + (y - 1 - y1) ** 2 - R_2)
            if Dg == min(Dg, Dd, Dv):
                x += 1
            elif Dd == min(Dg, Dd, Dv):
                x += 1
                y -= 1
            elif Dv == min(Dg, Dd, Dv):
                y -= 1
        elif x >= x1 and y <= y1:
            Dg = abs((x - 1 - x1) ** 2 + (y - y1) ** 2 - R_2)
            Dd = abs((x - 1 - x1) ** 2 + (y - 1 - y1) ** 2 - R_2)
            Dv = abs((x - x1) ** 2 + (y - 1 - y1) ** 2 - R_2)
            if Dg == min(Dg, Dd, Dv):
                x -= 1
            elif Dd == min(Dg, Dd, Dv):
                x -= 1
                y -= 1
            elif Dv == min(Dg, Dd, Dv):
                y -= 1
        elif x <= x1 and y <= y1:
            Dg = abs((x - 1 - x1) ** 2 + (y - y1) ** 2 - R_2)
            Dd = abs((x - 1 - x1) ** 2 + (y + 1 - y1) ** 2 - R_2)
            Dv = abs((x - x1) ** 2 + (y + 1 - y1) ** 2 - R_2)
            if Dg == min(Dg, Dd, Dv):
                x -= 1
            elif Dd == min(Dg, Dd, Dv):
                x -= 1
                y += 1
            elif Dv == min(Dg, Dd, Dv):
                y += 1
        elif x <= x1 and y >= y1:
            Dg = abs((x + 1 - x1) ** 2 + (y - y1) ** 2 - R_2)
            Dd = abs((x + 1 - x1) ** 2 + (y + 1 - y1) ** 2 - R_2)
            Dv = abs((x - x1) ** 2 + (y + 1 - y1) ** 2 - R_2)
            if Dg == min(Dg, Dd, Dv):
                x += 1
            elif Dd == min(Dg, Dd, Dv):
                x += 1
                y += 1
            elif Dv == min(Dg, Dd, Dv):
                y += 1
        if 0 <= x < size and 0 <= y < size:
            if field[y][x] == 1:
                break
            field[y][x] = 1
    return field


def draw_line(points, field):
    # if method == 'step':
    #     field = step_method(points, field)
    # elif method == 'dda':
    #     field = dda(points, field)
    # elif method == 'bres':
    #     field = bresenham(points, field)
    # else:
    #     field = circle(points)
    # field = step_method(points, field)
    # field = dda(points, field)
    # field = bresenham(points, field)
    field = circle(points)
    return field


def main():
    field = np.zeros((size, size), dtype=np.int8)
    plt.ion()
    plt.imshow(field, interpolation='none')
    plt.grid(which='both')
    plt.pause(0.001)
    while True:
        points = plt.ginput(n=2, timeout=0)
        field = np.zeros((size, size), dtype=np.int8)
        field = draw_line(points, field)
        plt.imshow(field, interpolation='none')
        plt.draw()
    input()


if __name__ == '__main__':
    main()
