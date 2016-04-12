import os
from time import time
from PIL import Image, ExifTags
from wand import image


folder = 'input'


def work(filename):
    print('\n' + filename)
    img = Image.open(filename)
    print(img.format)
    with image.Image(filename=filename.encode()) as iimage:
        print(iimage.compression)
    print('size', img.size)
    for tag, value in img.info.items():
        print('{0} {1}'.format(tag, value))
    try:
        exif_data = img._getexif()
        if exif_data:
            exif = {
                ExifTags.TAGS[k]: v
                for k, v in exif_data.items()
                if k in ExifTags.TAGS
            }
            for tag, value in exif.items():
                print('Tag: {0} \t\t\t Value: {1}'.format(tag, value))
        else:
            print('No EXIF data')
    except AttributeError:
        print('No EXIF data')


def main():
    # work(folder + '/1305х864х183RLE.pcx')
    for filename in os.listdir(folder):
        work(folder + '/' + filename)


if __name__ == '__main__':
    t0 = time()
    main()
    t1 = time()
    print('function main takes {0}'.format(t1 - t0))
