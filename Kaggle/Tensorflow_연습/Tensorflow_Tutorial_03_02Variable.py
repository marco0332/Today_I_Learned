# 플레이스홀더와 변수
import tensorflow as tf

# tf.placeholder: 계산을 실행할 때 입력값을 받는 변수로 사용
# None 은 크기가 정해지지 않았음을 의미.
X = tf.placeholder(tf.float32, [None, 3])
print(X)

# X 플레이스홀더에 넣을 값
# 플레이스홀더에서 설정한 것 처럼, 두번째 차원의 요소의 갯수는 3으로 맞춤
x_data = [[1,2,3], [4,5,6]]

# tf.Variable: 그래프를 계산하면서 최적화할 변수.
# 이 값이 신경망을 좌우하는 값들
# tf.random_noraml: 각 변수들의 초기값을 정규분포 랜덤 값으로 초기화.
W = tf.Variable(tf.random_normal([3, 2]))
b = tf.Variable(tf.random_normal([2, 1]))

# 입력값과 변수들을 계산할 수식 작성
# tf.matmul 처럼 mat*로 되어있는 함수로 행렬 계산
expr = tf.matmul(X, W) + b

sess = tf.Session()
# 위에서 설정한 Variable 들의 값들을 초기화하기 위해
# 처음에 tf.global_variables_initializer 를 한 번 실행해야 함.
sess.run(tf.global_variables_initializer())

print("=== x_data ===")
print(x_data)
print("=== W ===")
print(sess.run(W))
print("=== b ===")
print(sess.run(b))
print("=== expr ===")
# expr 수식에는 X 라는 입력값이 필요합니다.
# 따라서 expr 실행시에는 이 변수에 대한 실제 입력값을 넣어줘야 함.
print(sess.run(expr, feed_dict={X: x_data}))

sess.close()