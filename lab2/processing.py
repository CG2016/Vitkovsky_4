from skimage import io, color
import numpy as np


def convert(filename, source_color, radius, target_color, savefile):
    rgb = io.imread(filename)
    lab = color.rgb2lab(rgb)
    tslab = np.empty((1, 1, 3), dtype=np.uint8)
    tslab[0, 0] = np.array(source_color, dtype=np.uint8)
    source_lab = color.rgb2lab(tslab)[0, 0]
    ttlab = np.empty((1, 1, 3), dtype=np.uint8)
    ttlab[0, 0] = np.array(target_color, dtype=np.uint8)
    target_lab = color.rgb2lab(ttlab)[0, 0]
    for i in range(lab.shape[0]):
        for j in range(lab.shape[1]):
            diff = np.sqrt((lab[i, j, 1] - source_lab[1]) ** 2 + (lab[i, j, 2] - source_lab[2]) ** 2 + ((lab[i, j, 0] - source_lab[0]) / 4) ** 2)
            if diff < radius:
                # lab[i, j, 0] = (lab[i, j, 0] + target_lab[0]) / 2
                lab[i, j, 0] = target_lab[0] + (lab[i, j, 0] - source_lab[0]) * 1.5
                lab[i, j, 1] = target_lab[1] + (lab[i, j, 1] - source_lab[1]) / 3
                lab[i, j, 2] = target_lab[2] + (lab[i, j, 2] - source_lab[2]) / 3
    data = color.lab2rgb(lab)
    io.imsave(savefile, data)

