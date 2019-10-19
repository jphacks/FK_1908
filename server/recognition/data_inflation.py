import numpy as np
import cv2
from glob import glob

min_table = 50
max_table = 205
diff_table = max_table - min_table

h_rut = np.arange(256, dtype = 'uint8')
l_lut = np.arange(256, dtype = 'uint8')

for i in range(0, min_table):
    h_rut[i] = 0
for i in range(min_table, max_table):
    h_rut[i] = 255 * (i - min_table) / diff_table
for i in range(max_table, 255):
    h_rut[i] = 255

for i in range(256):
    l_lut[i] = min_table + i * (diff_table) / 255

image_list = glob('./dataset/data/fragments/no_breads/*')

# データ水増し
for image_url in image_list:
    src = cv2.imread(image_url, 1)
    filename = image_url.split('/')[-1]
    fileidef = filename.split('.')[0]

    high_dest = './dataset/data/pool/no_breads/{}_{}.jpg'.format(fileidef, 'high_cont')
    low_dest = './dataset/data/pool/no_breads/{}_{}.jpg'.format(fileidef, 'low_cont')
    hflip_dest = './dataset/data/pool/no_breads/{}_{}.jpg'.format(fileidef, 'hflip')
    vflip_dest = './dataset/data/pool/no_breads/{}_{}.jpg'.format(fileidef, 'vflip')

    # ハイコントラストデータ
    high_cont_img = cv2.LUT(src, h_rut)
    resized_img = cv2.resize(high_cont_img, (224, 224))
    cv2.imwrite(high_dest, resized_img)

    # ロウコントラストデータ
    low_cont_img = cv2.LUT(src, l_lut)
    resized_img = cv2.resize(low_cont_img, (224, 224))
    cv2.imwrite(low_dest, resized_img)

    # 垂直方向回転
    vfliped_img = cv2.flip(src, 1)
    resized_img = cv2.resize(vfliped_img, (224, 224))
    cv2.imwrite(vflip_dest, resized_img)

    # 水平方向回転
    hfliped_img = cv2.flip(src, 0)
    resized_img = cv2.resize(hfliped_img, (224, 224))
    cv2.imwrite(hflip_dest, resized_img)
