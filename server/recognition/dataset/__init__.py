import cv2
import numpy as np
from random import shuffle
from glob import glob
from sklearn.model_selection import train_test_split

classes = ['no_breads', 'breads']
no_breads_url_list = glob('./dataset/data/pool/no_breads/*')
breads_url_list = glob('./dataset/data/pool/breads/*')
baked_breads_url_list = glob('./dataset/data/pool/breads/*')

no_breads_imgs = []
breads_imgs = []
baked_breads_imgs = []

def _one_hot(i, size):
    return list(np.diag([1]*size)[i])

for img_url in no_breads_url_list:
    img = cv2.imread(img_url)
    img = cv2.resize(img, (224, 224))
    data = np.asarray(img)
    no_breads_imgs.append(data)

for img_url in breads_url_list:
    img = cv2.imread(img_url)
    img = cv2.resize(img, (224, 224))
    data = np.asarray(img)
    breads_imgs.append(data)

#for img_url in baked_breads_url_list:
#    img = cv2.imread(img_url)
#    img = cv2.resize(img, (224, 224))
#    data = np.asarray(img)
#    baked_breads_imgs.append(data)

x = np.array(no_breads_imgs + breads_imgs + baked_breads_imgs)
x = x.reshape(len(x), 224, 224, 3)
y = [_one_hot(0, len(classes))] * len(no_breads_imgs) + [_one_hot(1, len(classes))] * len(breads_imgs) #+ [_one_hot(2, len(classes))] * len(baked_breads_imgs)
y = np.array(y)

x_train, x_test, y_train, y_test = train_test_split(x, y, test_size=0.7, random_state=111)
