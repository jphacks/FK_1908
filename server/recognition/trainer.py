import numpy as np
from glob import glob
from dataset import *
from keras import models, optimizers
from keras.applications.vgg16 import VGG16
from keras.layers import Dense, Dropout, Activation, Flatten

batch_size = 20
epochs = 20

vgg = VGG16(include_top=True, weights='imagenet', input_shape=(224, 224, 3))
for layer in vgg.layers[:-4]:
    layer.trainable = False

model = models.Sequential()
model.add(vgg)
model.add(Dense(1024, activation='relu'))
model.add(Dropout(0.5))
model.add(Dense(2, activation='softmax'))

model.compile(loss='categorical_crossentropy',
              optimizer=optimizers.SGD(lr=1e-4, momentum=0.9),
              metrics=['accuracy'])


history = model.fit(x_train, y_train,
                    batch_size=batch_size,
                    epochs=epochs,
                    validation_data=(x_test, y_test),
                    shuffle=True)

# save model
model_path = './vgg16.h5'
model.save(model_path)
print('Saved trained model at %s ' % model_path)

# Score trained model
scores = model.evaluate(x_test, y_test, verbose=1)
print('Test loss:', scores[0])
print('Test accuracy:', scores[1])
