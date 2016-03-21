from skimage import io
import matplotlib.pyplot as plt


filename = 'Image 1.png'


def main():
    rgb = io.imread(filename)
    r = []
    g = []
    b = []
    total_r = 0
    total_g = 0
    total_b = 0
    for line in rgb:
        for pixel in line:
            total_r += pixel[0]
            r.append(pixel[0])
            total_g += pixel[1]
            g.append(pixel[1])
            total_b += pixel[2]
            b.append(pixel[2])
    total_r /= rgb.shape[0] * rgb.shape[1]
    total_g /= rgb.shape[0] * rgb.shape[1]
    total_b /= rgb.shape[0] * rgb.shape[1]

    print('Average RGB : {0}, {1}, {2}'.format(total_r, total_g, total_b))

    plt.subplot('311')
    plt.hist(r, bins=range(257), color='red')
    plt.xlim((0, 256))
    plt.subplot('312')
    plt.hist(g, bins=range(257), color='green')
    plt.xlim((0, 256))
    plt.subplot('313')
    plt.hist(b, bins=range(257), color='blue')
    plt.xlim((0, 256))
    plt.show()


if __name__ == '__main__':
    main()
