# 털과 날개가 있는지 없는지에 따라 포유류인지 조류인지 분류하는 신경망 모델
# 신경망의 레이어를 여러개로 구성
import tensorflow as tf
import numpy as np

# [털, 날개]
x_data = np.array([
        [0,0],
        [1,0],
        [1,1],
        [0,0],
        [0,0],
        [0,1]
    ])

# [기타, 포유류, 조류]
# 다음과 같은 형식을 one-hot-encoding 데이터라고 함
y_data = np.array([
    [1, 0, 0], # 기타
    [0, 1, 0], # 포유류
    [0, 0, 1], # 조류
    [1, 0, 0],
    [1, 0, 0],
    [0, 0, 1]])


########
# 신경망 모델 구성
########
X = tf.placeholder(tf.float32)
Y = tf.placeholder(tf.float32)

# 첫번째 가중치의 차원은 [특성, 히든 레이어의 뉴런 갯수] - > [2, 10]으로
W1 = tf.Variable(tf.random_uniform([2, 10], -1., 1.))

# 두번째 가중치의 차원을 [첫번째 히든 레이어의 뉴런 갯수 ,분류 갯수] - > [10, 3]으로
W2 = tf.Variable(tf.random_uniform([10, 3], -1., 1.))

# 편향을 각각 레이어의 아웃풋 갯수로 설정
# b1 은 히든 레이어의 뉴런 갯수로, b2는 최종 결과값, 즉 분류 갯수인 3으로 설정
b1 = tf.Variable(tf.zeros([10]))
b2 = tf.Variable(tf.zeros([3]))

# 신경망의 히든 레이어에 가중치 W1과 편향 b1을 적용
L1 = tf.add(tf.matmul(X, W1), b1)
L1 = tf.nn.relu(L1)

# 최정적인 아웃풋을 계산합니다.
# 히든레이어에 두번째 가중치 W2와 편향 b2를 적용하여 3개의 출력값 생성
model = tf.add(tf.matmul(L1, W2), b2)

# 텐서플로우에서 기본적으로 제공되는 크로스 엔트로피 함수를 이용
# 복잡한 수식을 사용하지 않고도 최적화를 위한 비용 함수를 다음처럼 간단하게 적용할 수 있음
cost = tf.reduce_mean(
    tf.nn.softmax_cross_entropy_with_logits_v2(labels=Y, logits=model))

optimizer = tf.train.AdamOptimizer(learning_rate=0.01)
train_op = optimizer.minimize(cost)

