# Word2Vec 모델 구현
import tensorflow as tf
import matplotlib
import matplotlib.pyplot as plt
import numpy as np

# 단어 벡터를 분석해볼 임의의 문장들
sentences = ["나 고양이 좋다",
             "나 강아지 좋다",
             "나 동물 좋다",
             "강아지 고양이 동물",
             "여자친구 고양이 강아지 좋다",
             "고양이 생선 우유 좋다",
             "강아지 생선 싫다 우유 좋다",
             "강아지 고양이 눈 좋다",
             "나 여자친구 좋다",
             "여자친구 나 싫다",
             "여자친구 나 영화 책 음악 좋다",
             "나 게임 만화 애니 좋다",
             "고양이 강아지 싫다",
             "강아지 고양이 좋다"]

# 문장을 전부 합친 후 공백으로 단어들을 나누고 고유한 단어들로 리스트를 만든다.
word_sequence = " ".join(sentences).split()
word_list = " ".join(sentences).split() #word_list = word_sequence.copy()
word_list = list(set(word_list))

# 문자열로 분석하는 것 보다, 숫자로 분석하는 것이 훨씬 용이하므로
# 리스트에서 문자들의 인덱스를 뽑아서 사용하기 위해,
# 이를 표현하기 위한 연관 배열과, 단어 리스트에서 단어를 참조 할 수 있는 인덱스 배열 생성
word_dict = {w: i for i, w in enumerate(word_list)}
print(word_dict)

# 윈도우 사이즈를 1로 하는 skip-gram 모델을 만든다.
# 예) 나 게임 만화 애니 좋다
#   -> ([나, 만화], 게임), ([게임, 애니], 만화), ([만화, 좋다], 애니)
#   -> (게임, 나), (게임, 만화), (만화, 게임), (만화, 애니), (애니, 만화), (애니, 좋다)
skip_grams = []
print(word_sequence)

for i in range(1, len(word_sequence) - 1):
    # (context, target) : ([target index -1, target index + 1], target)
    # 스킵그램을 만든 후, 저장은 단어의 고유 번호(index)로 저장합니다
    target = word_dict[word_sequence[i]]
    context = [word_dict[word_sequence[i - 1]], word_dict[word_sequence[i + 1]]]

    # (target, context[0]), (target, context[1])
    for w in context:
        skip_grams.append([target, w])

# skip-gram 데이터에서 무작위로 데이터를 뽑아 입력값과 출력값의 배치 데이터를 생성하는 함수
def random_batch(data, size):
    random_inputs = []
    random_labels = []
    random_index = np.random.choice(range(len(data)), size, replace=False)

    for i in random_index:
        random_inputs.append(data[i][0]) # target
        random_labels.append([data[i][1]]) # context word

    return random_inputs, random_labels


#######
# 옵션 설정
##@####
training_epoch = 300    # 학습을 반복할 횟수
learning_rate = 0.1     # 학습률
batch_size = 20         # 한번에 학습할 데이터 크기
embedding_size = 2      # 단어 벡터를 구성할 임베딩 차원의 크기. x,y 그래프로 표현하기 쉽게 2개의 값만 출력
num_sampled = 15        # word2vec 모델을 학습시키기 위한 nce_loss 함수에서 사용하기 위한 샘플링 크기. batch_size보다 작아야 함
voc_size = len(word_list) # 총 단어 갯수


########
# 신경망 모델 구성
########
inputs = tf.placeholder(tf.int32, shape=[batch_size])

# tf.nn.nce_loss 를 사용하려면 출력값을 [batch_size, 1]로 구성해야 함
labels = tf.placeholder(tf.int32, shape=[batch_size, 1])

# word2vec 모델의 결과 값인 임베딩 벡터를 저장할 변수
# 총 단어 갯수와 임베딩 갯수를 크기로 하는 두 개의 차원
embeddings = tf.Variable(tf.random_uniform([voc_size, embedding_size], -1.0, 1.0))

# 임베딩 벡터의 차원에서 학습할 입력값에 대한 행들을 뽑아옵니다.
# 예) embeddings     inputs    selected
#    [[1, 2, 3]  -> [2, 3] -> [[2, 3, 4]
#     [2, 3, 4]                [3, 4, 5]]
#     [3, 4, 5]
#     [4, 5, 6]]
selected_embed = tf.nn.embedding_lookup(embeddings, inputs)

# nce(Noise-Contrastive Estimation)
# 전체 데이터셋에 대해 SoftMax 함수를 적용하는 것이 아니라 샘플링으로 추출한 일부에 대해서만
# 적용하는 방법. k개의 대비되는(contrastive)단어들을 noise distribution에서 구해서
# (몬테카를로) 평균을 구하는 것. Hierarchical SoftMax와 Negative Sampling 등의 여러 방법이 있다.
# nce_loss 함수에서 사용할 변수들을 정의합니다.
nce_weights = tf.Variable(tf.random_uniform([voc_size, embedding_size], -1.0, 1.0))
nce_biases = tf.Variable(tf.zeros([voc_size]))

# nce_loss 함수를 직접 구현하려면 매우 복잡하지만,
# 함수를 텐서플로우가 제공하므로 그냥 tf.nn.nce_loss 함수 사용
loss = tf.reduce_mean(
    tf.nn.nce_loss(nce_weights, nce_biases, labels, selected_embed, num_sampled, voc_size))

train_op = tf.train.AdamOptimizer(learning_rate).minimize(loss)

# CBOW(Continuous Bag-of-Words)와 Skip-Gram 모델은 내부적으로 SoftMax 알고리즘 사용해서 계산
# 모든 단어에 대해 계산을 하고 normalization하는게 오래걸림
# 이 해결 방법으로 Hierachical SoftMax, Negative Sampling 알고리즘 있음.
# Hierachical SoftMax는 계산량이 많은 SoftMax 함수를 빠르게 계산가능한 multinomial distribution 함수로 대체.
# multinomial distribution함수는 루트에서 리프까지 가는 경로를 확률과 연동시켜서 계산 시간 단축.
# Word2Vec 논문에서는 사용 빈도가 높은 다어에 대해 짧은 경로를 부여하는 Binary Huffman Tree를 사용.
# Huffman Tree는 경로의 길이가 일정한 full tree의 ㅅ엊일을 갖고 있기 때문에 성능 향상에 이상적

# Negative Sampling은 몇개의 샘플에 대해서만 적용하는 알고리즘.
# 전체 데이터로부터 일부만 뽑아서 SoftMax 계산을 하고 normalization진행.
# 현재(목표) 단어는 반드시 계산을 수행해야 하기 때문에 Positive Sample이라 부르고, 나머지 단어를 Negative Sample이라고 한다.
# 여기서 핵심은 나머지 단어에 해당하는 Negative Sample을 추출하는 방법이다.

# tSNE (t-distributed Stochastic Neighbor Embedding)
# 차원이 높은 데이터의 이웃한 구조를 유지하면서 낮은 차원으로 변화하는 알고리즘.
# 보통 그래프의 형태로 출력하기 때문에 2차원을 주로 사용.
# 밀도가 높은 데이터에는 PCA, 밀도가 낮은 데이터에는 TruncatedSVD 알고리즘 사용


########
# 신경망 모델 학습
########
with tf.Session() as sess:
    init = tf.global_variables_initializer()
    sess.run(init)

    for step in range(1, training_epoch + 1):
        batch_inputs, batch_labels = random_batch(skip_grams, batch_size)

        _, loss_val = sess.run([train_op, loss],
                               feed_dict={inputs: batch_inputs,
                                          labels: batch_labels})

        if step % 40 == 0:
            print("loss at step ", step, ": ", loss_val)

    # matplot 으로 출력하여 시각적으로 확인해보기 위해
    # 임베딩 벡터의 결과 값을 계산하여 저장
    # with 구문 안에서는 sess.run 대신 간단히 eval() 함수를 사용할 수 있다.
    trained_embeddings = embeddings.eval()


########
# 임베딩된 Word2Vec 결과 확인
# 결과는 해당 단어들이 얼마나 다른 단어와 인접해 있는지를 보여줌
########
for i, label in enumerate(word_list):
    x, y = trained_embeddings[i]
    plt.scatter(x, y)
    plt.annotate(label, xy=(x,y), xytext=(5,2),
                 textcoords='offset points', ha='right', va='bottom')

plt.show()