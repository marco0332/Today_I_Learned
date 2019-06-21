import tensorflow as tf
import os
import gc
import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
import seaborn as sns
import PIL

# PATH 설정
DATA_PATH = './competetion/'
os.listdir(DATA_PATH)

# 이미지 폴더 경로
TRAIN_IMG_PATH = os.path.join(DATA_PATH, 'train/')
TEST_IMG_PATH = os.path.join(DATA_PATH, 'test/')

# csv 파일 경로
df_train = pd.read_csv(os.path.join(DATA_PATH, 'train.csv'))
df_test = pd.read_csv(os.path.join(DATA_PATH, 'test.csv'))
df_class = pd.read_csv(os.path.join(DATA_PATH, 'class.csv'))

####################
# Data
# img_file - 데이터 셋의 각 로우와 연결되는 이미지 파일 이름
# bbox_x1, y1, x2, y2 - 좌상단 xy, 우하단 xy
# class - Target 차종
# id - 각 데이터 셋에 기입 되어 있는 클래스 id
# name - 클래스 id에 대응되는 실제 차종 레이블
####################

# preprocessing - crop from bounding box
def crop_by_boxing(img_name):
    PATH = TRAIN_IMG_PATH
    data = df_train

    img = PIL.Image.open(os.path.join(PATH, img_name))
    pos = data.loc[data["img_file"] == img_name,
    ['bbox_x1', 'bbox_y1', 'bbox_x2', 'bbox_y2']].values.reshape(-1)
    crop_img = img.crop((pos[0],pos[1],pos[2],pos[3]))

    return crop_img


# crop_by_boxing("train_00102.jpg")

# Parameter
img_size = (224, 224)
nb_train_samples = len(df_train)
nb_test_samples = len(df_test)
epochs = 5
batch_size = 32

# print(df_train['img_file'])

class Inputs(object):
    def __init__(self, image, x1, y1, x2, y2):
        self.image = image
        self.x1 = x1
        self.x2 = x2
        self.y1 = y1
        self.y2 = y2

class Conv_unit(object):
    def __init__(self, input, kernel_size, stride_size, cur_depth, next_depth):
        self.input = input
        self.kernel_size = kernel_size
        self.stride_size = stride_size
        self.cur_depth = cur_depth
        self.next_depth = next_depth

    def forward(self):
        self.W = tf.Variable(tf.truncated_normal([self.kernel_size, self.kernel_size, self.cur_depth, self.next_depth], stddev=0.1))
        self.Layer = tf.layers.conv2d(input, self.W, strides=[1,self.stride_size,self.stride_size,1],padding='SAME')
        self.Layer = tf.nn.relu(self.Layer)
        self.Layer = tf.layers.batch_normalization(self.Layer)

    def pooling(self, ksize, ssize):
        self.Layer = tf.nn.max_pool(self.Layer, ksize=[1,ksize,ksize,1], strides=[1,ssize,ssize,1])

class conv_split(object):
    def __init__(self, input, l1_size, l2_size, l3_size):
        self.input = input
        self.l1_size = l1_size
        self.l2_size = l2_size
        self.l3_size = l3_size
        self.layers = []

    def construct_layer(self):
        for i in range(32):
            self.layers.append(Conv_split_layer(self.input, self.l1_size, self.l2_size, self.l3_size).construct_layer())

    def forward(self):
        for i in range(32):
            self.layers[i].forward()

        self.concat = tf.stack(self.layers, axis=3)
        self.output = Conv_unit(self.concat, 1, 1, self.l2_size, self.l3_size).forward()
        self.output = tf.add(self.output, self.input)

        return self.output

class Conv_split_layer(object):
    def __init__(self, input, l1_size, l2_size, l3_size):
        self.input = input
        self.l1_size = l1_size
        self.l2_size = l2_size
        self.l3_size = l3_size

    def construct_layer(self):
        self.layer1 = Conv_unit(self.input, 1, 1, self.l1_size, self.l2_size)
        self.layer2 = Conv_unit(self.layer1, 3, 1, self.l2_size, self.l2_size)

    def forward(self):
        self.layer1.forward()
        self.layer2.forward()

class Model(object):
    def __init__(self, inputs: Inputs, learning_rate):
        self.inputs = inputs
        self.learning_rate = learning_rate
        self.predictions = self.predict(inputs)
        self.loss = self.calculate_loss(inputs, self.predictions)
        self.opt_step = tf.train.AdamOptimizer(learning_rate=self.learning_rate).minimize(self.loss)

    # TODO 위에 만든 layer 이용해서 model 구현, prediction, -> 학습 def 정의.
    # TODO 데이터 불러오는 것 깔끔하게 완성
    # TODO 클래스와 함수들 및 기타 메인 등 코드 파일 분할에서 깔끔하게 보관

