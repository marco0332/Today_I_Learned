import gc
import os
import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
from tqdm import tqdm
import seaborn as sns
import PIL
from PIL import ImageDraw
from sklearn.model_selection import train_test_split

# 다른 데이터 셋 추가(Pretrained Model Weights)로 인해 PATH가 변경된다면 PATH 변경
DATA_PATH = './competetion/'
os.listdir(DATA_PATH)
print(os.listdir(DATA_PATH))

# Data Description에 있는 파일 세부 정보
# train.csv - Train 셋의 이미지 파일명, 바운딩박스 좌표, 차종정보
# test.csv - Test 셋의 이미지 파일명, 바운딩박스 좌표, 차종정보
# submission.scv - Test 셋과 대응되는 제출 파일
# class.csv - 데이터 셋의 class 컬럼과 대응되는 차종의 레이블
# train - Train 이미지 파일
# test - Test 이미지 파일

# 이미지 폴더 경로
TRAIN_IMG_PATH = os.path.join(DATA_PATH, 'train')
TEST_IMG_PATH = os.path.join(DATA_PATH, 'test')

# CSV 파일 경로
df_train = pd.read_csv(os.path.join(DATA_PATH, 'train.csv'))
df_test = pd.read_csv(os.path.join(DATA_PATH, 'test.csv'))
df_class = pd.read_csv(os.path.join(DATA_PATH, 'class.csv'))

####################
# Data Exploration
# 데이터가 Description과 일치하는지, 데이터는 어떻게 구성되어있고
# 클래스 별로 어떤 분포를 가지고 있는지 등 데이터에 대한 분석 과정
#
# img_file - 데이터 셋의 각 로우와 연결되는 이미지 파일 이름
# bbox_x1, y1, x2, y2 - 좌상단 xy, 우하단 xy
# class - Target 차종
# id - 각 데이터 셋에 기입 되어 있는 클래스 id
# name - 클래스 id에 대응되는 실제 차종 레이블
####################

print(df_train.head())
print(df_test.head())

# Data 누락 체크
if set(list(df_train.img_file)) == set(os.listdir(TRAIN_IMG_PATH)):
    print("Train file 누락 없음!")
else:
    print("Train file 누락")

if set(list(df_test.img_file)) == set(os.listdir(TEST_IMG_PATH)):
    print("Test file 누락 없음!")
else:
    print("Test file 누락")

# Data 갯수
print("Number of Train Data : {}".format(df_train.shape[0]))
print("Number of Test Data : {}".format(df_test.shape[0]))

print(df_class.head())
print("타겟 클래스 총 갯수 : {}".format(df_class.shape[0]))
print("Train Data의 타겟 종류 갯수 : {}".format(df_train['class'].nunique()))


####################
# Class Distribution
# 분류 문제에서 가장 먼저 의심해봐야할 부분이 바로 Target Class의 분포.
# 학습에 사용해야 하는 Train Set의 타겟 분포를 확인해서 밸런스가 어느정도인지 체크
####################
plt.figure(figsize=(12, 6))
sns.countplot(df_train["class"], order=df_train["class"].value_counts(ascending=True).index)
plt.title("Number of data per each class")
plt.show()

cntEachClass = df_train["class"].value_counts(ascending=False)
print("Class with most count : {}".format(cntEachClass.index[0]))
print("Most Count : {}".format(cntEachClass.max()))
print("Class with fewest count : {}".format(cntEachClass.index[-1]))
print("Fewest Count : {}".format(cntEachClass.min()))
print("Mean : {}".format(cntEachClass.mean()))

print(cntEachClass.describe())

# 이미지 로드
tmp_imgs = df_train['img_file'][100:110]
plt.figure(figsize=(12,20))

for num, f_name in enumerate(tmp_imgs):
    img = PIL.Image.open(os.path.join(TRAIN_IMG_PATH, f_name))
    plt.subplot(5, 2, num+1)
    plt.title(f_name)
    plt.imshow(img)
    plt.axis('off')
plt.show()


def draw_rect(drawcontext, pos, outline=None, width=0):
    (x1, y1) = (pos[0], pos[1])
    (x2, y2) = (pos[2], pos[3])
    points = (x1, y1), (x2, y1), (x2, y2), (x1, y2), (x1, y1)
    drawcontext.line(points, fill=outline, width=width)

def make_boxing_img(img_name):
    if img_name.split('_')[0] == "train":
        PATH = TRAIN_IMG_PATH
        data = df_train
    elif img_name.split('_')[0] == "test":
        PATH = TEST_IMG_PATH
        data = df_test

    img = PIL.Image.open(os.path.join(PATH, img_name))
    pos = data.loc[data["img_file"] == img_name,
    ['bbox_x1', 'bbox_y1', 'bbox_x2', 'bbox_y2']].values.reshape(-1)
    draw = ImageDraw.Draw(img)
    draw_rect(draw, pos, outline='red', width=10)

    return img


f_name = "train_00102.jpg"
plt.figure(figsize=(20,10))
plt.subplot(1, 2, 1)

# Original Image
origin_img = PIL.Image.open(os.path.join(TRAIN_IMG_PATH, f_name))
plt.title("Original Image - {}".format(f_name))
plt.imshow(origin_img)
plt.axis('off')

# Image included bounding box
plt.subplot(1, 2, 2)
boxing = make_boxing_img(f_name)
plt.title("Boxing Image - {}".format(f_name))
plt.imshow(boxing)
plt.axis('off')

plt.show()


############################
# 모델 구현
# 보통은 ResNet500 Pretrained Model을 불러와서 사용
# 우리는 공부가 목적이므로 처음부터 트레이닝해보고
# 어느 정도 성능이 올라가면 pretrained Model을 사용해 보도록 할 생각
############################
# 우선은 Validation Set을 생성

df_train["class"] = df_train["class"].astype('str')

df_train = df_train[['img_file', 'class']]
df_test = df_test[['img_file']]

its = np.arange(df_train.shape[0])
train_idx, val_idx = train_test_split(its, train_size = 0.8, random_state=42)

X_train = df_train.iloc[train_idx, :]
X_val = df_train.iloc[val_idx, :]

print(X_train.shape)
print(X_val.shape)
print(df_test.shape)


##############################
# Generator
# 이미지 프로세싱에서 없어서는 안될 필수 과정
# 보통 환경에서는 메모리가 충분하지 않다. 특히 이미지처럼 하나의 용량이 매우 큰경우
# 한번에 모든 파일을 메모리에 적재하게 되면 큰 부담.
# 배치사이즈 단위 만큼 파일을 불러와 학습하고, 끝나면 다시 불러와서 학습하는 방법을
# 반복하기 때문에 메모리를 조금만 사용
#
# 케라스 ImageDataGenerator는 제너레이터의 기능은 물론 제너레이터를 정의하면서 동시에
# Data에 원하는 Noise까지 부여할 수 있음.

from keras.applications.resnet50 import ResNet50, preprocess_input
from keras.preprocessing.image import ImageDataGenerator

# Parameter
img_size = (224, 224)
nb_train_samples = len(X_train)
nb_validation_samples = len(X_val)
nb_test_samples = len(df_test)
epochs = 20
batch_size = 32

# Define Generator config
train_datagen = ImageDataGenerator(
    horizontal_flip = True,
    vertical_flip = False,
    zoom_range=0.10,
    preprocessing_function=preprocess_input)

val_datagen = ImageDataGenerator(preprocessing_function=preprocess_input)
test_datagen = ImageDataGenerator(preprocessing_function=preprocess_input)

# Make Generator
train_generator = train_datagen.flow_from_dataframe(
    dataframe=X_train,
    directory='./competetion/train/',
    x_col = 'img_file',
    y_col = 'class',
    target_size = img_size,
    color_mode = 'rgb',
    class_mode = 'categorical',
    batch_size = batch_size,
    seed = 42)

validation_generator = val_datagen.flow_from_dataframe(
    dataframe=X_val,
    directory='./competetion/train/',
    x_col = 'img_file',
    y_col = 'class',
    target_size = img_size,
    color_mode='rgb',
    class_mode='categorical',
    batch_size=batch_size,
    shuffle=False)

test_generator = test_datagen.flow_from_dataframe(
    dataframe=df_test,
    directory='./competetion/test/',
    x_col='img_file',
    y_col=None,
    target_size= img_size,
    color_mode='rgb',
    class_mode=None,
    batch_size=batch_size,
    shuffle=False)

resNet_model = ResNet50(include_top=False, input_shape=(224,224,3))
# resNet_model.summary()

from keras.models import Sequential, Model
from keras.layers import Dense, Dropout, Flatten, Activation, Conv2D, GlobalAveragePooling2D

# for layer in resNet_model.layers
#     layer.trainable = False
#     print(layer, layer.trainable)

model = Sequential()
model.add(resNet_model)
model.add(GlobalAveragePooling2D())
model.add(Dense(196, activation='softmax', kernel_initializer='he_normal'))
model.summary()


##########################
# Model Compile
# 학습 방법 정하기.
##########################
from sklearn.metrics import f1_score

def micro_f1(y_true, y_pred):
    return f1_score(y_true, y_pred, average='micro')

model.compile(optimizer='adam', loss='categorical_crossentropy', metrics=['acc'])

'''
model.compile(loss='categorical_crossentropy', 
              optimizer=Adam(lr=0.001, beta_1=0.9, beta_2=0.999), 
              metrics=['accuracy'])
'''


###########################
# training
###########################
def get_steps(num_samples, batch_size):
    if (num_samples % batch_size) > 0:
        return (num_samples // batch_size) + 1
    else:
        return num_samples // batch_size

from keras.callbacks import ModelCheckpoint, EarlyStopping

filepath = "my_resnet_model_{val_acc:.2f}_{val_loss:.4f}.h5"

#ckpt = ModelCheckpoint(filepath, monitor='val_acc', verbose=1, save_best_only=True)
es = EarlyStopping(monitor='val_acc', min_delta=0, patience=3, verbose=1, mode='auto')

callbackList = [es]

history = model.fit_generator(
    train_generator,
    steps_per_epoch = get_steps(nb_train_samples, batch_size),
    epochs = epochs,
    validation_data = validation_generator,
    validation_steps = get_steps(nb_validation_samples, batch_size),
    callbacks = callbackList
)
gc.collect()


#########################3
# Training History Visualization
##########################

# Plot training & validation accuracy values
plt.plot(history.history['acc'])
plt.plot(history.history['val_acc'])
plt.title('Model accuracy')
plt.ylabel('Accuracy')
plt.xlabel('Epoch')
plt.legend(['Train', 'Test'], loc='upper left')
plt.show()

# Plot training & validation loss values
plt.plot(history.history['loss'])
plt.plot(history.history['val_loss'])
plt.title('Model loss')
plt.ylabel('Loss')
plt.xlabel('Epoch')
plt.legend(['Train', 'Test'], loc='upper left')
plt.show()


#################################
# Predict & Make submission
#################################
test_generator.reset()
prediction = model.predict_generator(
    generator = test_generator,
    steps = get_steps(nb_test_samples, batch_size),
    verbose = 1
)

# Inference가 끝난 결과를 sample_submission 파일에 매핑해야 함
# 케라스 제너레이터를 사용하는 경우에는
# 타겟(클래스)의 카테고리컬 매핑이 제너레이터 임의로 결정됨.
# 따라서 제너레이터가 가지고 있는 class index 딕셔너리를 불러와
# 새롭게 매핑해야 함.
predicted_class_indices = np.argmax(prediction, axis=1)

# Generator class dictionary mapping
labels = (train_generator.class_indices)
labels = dict((v,k) for k,v in labels.items())
predictions = [labels[k] for k in predicted_class_indices]

submission = pd.read_csv(os.path.join(DATA_PATH, 'sample_submission.csv'))
submission["class"] = predictions
submission.to_csv("submission.csv", index=False)
submission.head()
