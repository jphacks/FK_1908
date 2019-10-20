import flask
import os
import cv2
import numpy as np
import tensorflow as tf
from flask import Flask, request
from keras.models import load_model

from config import *

global model, graph
model = load_model(os.path.dirname(os.path.abspath(__file__)) + '/' + conf.model_name)
graph = tf.compat.v1.get_default_graph()

app = flask.Flask(__name__)

@app.route('/', methods = ['POST'])
def handle_request():
    stream = request.files['image'].stream
    img_array = np.asarray(bytearray(stream.read()), dtype=np.uint8)
    img = cv2.imdecode(img_array, 1)
    img = np.asarray(img)
    img = img.reshape((1, conf.image_size, conf.image_size, 3))
    with graph.as_default():
        result = model.predict(img)
    print(parse2label(result)[0])
    return parse2label(result)[0]

@app.route('/hello', methods = ['GET'])
def handle_hello():
    return 'hello'

def parse2label(predicted_arr):
    return [conf.classes[np.argmax(result)] for result in predicted_arr]

if __name__ == "__main__":
    app.run(host="0.0.0.0", port=5000, debug=True)
