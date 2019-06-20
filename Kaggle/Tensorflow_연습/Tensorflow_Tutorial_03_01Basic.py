# 텐서플로우의 기본적인 구성을 익힙니다.
import tensorflow as tf

# tf.constant : 그래프에서 상수 역할을 함
hello = tf.constant("Hello, Tensorflow!")
print(hello)

a = tf.constant(10)
b = tf.constant(32)
c = tf.add(a, b) # a + b 로도 쓸 수 있음
print(c)

# 위에서 변수와 수식들을 정의했지만, 실행이 정의한 시점에서 실행되는 것은 아님.
# Session 객체와 run 메소드를 사용해야 계산이 됨.
# 따라서 모델을 구성하는 것과, 실행하는 것을 분리하여 프로그램을 깔끔하게 작성할 수 있음.
# 그래프를 실행할 세션을 구성.
sess = tf.Session()

# sess.run: 설정한 텐서 그래프(변수나 수식 등등)를 실행.
print(sess.run(hello))
print(sess.run([a, b, c]))

# 세션 종료
sess.close()