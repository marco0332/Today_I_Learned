### 1st. 2019-06-16 일요일
![image](https://user-images.githubusercontent.com/27988544/59562432-acfffe80-9067-11e9-9166-ef8c9efa5565.png)
![image](https://user-images.githubusercontent.com/27988544/59562425-88a42200-9067-11e9-9ec7-ba637aa48247.png)
  
  ### 스터디 참가자
  <a href="https://github.com/sksms17456">박현빈</a>
  <a href="https://github.com/marco0332">이기인</a>
  <a href="#">이상호</a>
  <a href="https://github.com/stupid07">이종연</a>
  
  ## 스터디 1일차 (2019-06-20)
  <details>
  <summary>개요 및 첫 성적</summary>
  
  <p>
  
  Object Detection은 Bounding Box를 예측하면서 동시에 Box 안의 Object의 class를 예측하는 작업.
  ![fig1_cv_task](https://user-images.githubusercontent.com/27988544/59810455-3d786080-9340-11e9-95b8-04227a0a5de9.png)
  
  따라서 이 문제를 해결하는 접근 방법으로 1-stage와 2-stage로 나뉜다.
  1-stage는 속도면에서 장점을 보이고, 2-stage는 정확도면에서 장점을 보인다.
  ![fig5_2stage](https://user-images.githubusercontent.com/27988544/59810468-4d904000-9340-11e9-9c5b-4959603a54b2.png)
  ![fig6_1stage](https://user-images.githubusercontent.com/27988544/59810475-55e87b00-9340-11e9-9db4-3e64003bd75c.png)
  
  우리는 성능을 높이는 것이 최종 목표지만, 처음부터 구현을 해 보면서 이론을 이해하고 최근 발표된 논문까지 구현해보는 것을 중점적으로 진행할 예정이다.
  ![fig4_paper_trend_2019](https://user-images.githubusercontent.com/27988544/59810507-7d3f4800-9340-11e9-9d5a-59e07fd8fa30.png)
  
  일단 새로운 노트북을 지급 받았으니 Tensorflow를 설치해야 했다.<br>
  <a href="https://pythonkim.tistory.com/137?category=574546">윈도우10 Tensorflow 설치</a>
  
  가상환경 활성화 명령어<br>
  python -m venv tf_1<br>
  cd tf_1<br>
  Scripts\activate.bat<br>
  
  
  ### 첫번째 시도!
  ![image](https://user-images.githubusercontent.com/27988544/59836793-24df6900-9387-11e9-8687-f42cc8bfc563.png)
  공부를 좀 더 해야할 듯. 어떻게 하면 성능을 높일 수 있을지, tensorflow로 기본부터 코딩하는 것도 좋을 듯.
  <br>
  
  </p>
  </details>
  
  ## 혼자 공부 1일차 (2019-06-21)
  
  <details>
  <summary> Faster R-CNN 이론 및 구현</summary>
  <p>
  
  #### Faster R-CNN 구현해보기
  Object Detection에는 여러 알고리즘이 있다. R-CNN, YOLO 등등.  
  최초가 된 분석 방법은 R-CNN이고, selective search의 단점을 보오나하면서 속도를 올린게 Faster R-CNN이다.  
  Faster R-CNN은 정확도가 높지만 YOLO보다는 느리다. (속도와 정확도의 trade-off)  
  캐글에서 요구하는 것은 '정확도'이므로, 우리는 R-CNN과 같은 2-stage 모델을 우선적으로 구현해보기로 했다.  
  <br>
  
  #### Architecture
  Faster R-CNN은 두개의 네트워크로 구성이 되어 있다.  
  - Deep Convolution Network로서 Region Proposal Network (RPN)
  - Fast R-CNN Detector로서 앞의 proposed regions을 사용해서 object를 탐색
  <br>
  
  Faster R-CNN에는 2개의 모듈이 존재하지만, 전체적으로는 하나의 object detection network라고 볼 수 있음.  
  이게 중요한 이유는 Faster R-CNN 이후부터 fully differentiable model이기 때문이다.  
  ![image](https://user-images.githubusercontent.com/27988544/59889077-9100b200-9405-11e9-80a0-ac8bc9c4b4db.png)
    
  <br>
  <b>1. Input Images</b>
  
  - H x W x D를 갖고 있는, RGB Image<br><br>
    
  <b>2. Base Network (Shared Network)</b>
  
  - Name Meaning : selective search를 통해 나온 수천개 각각의 region proposals마다 CNN을 사용해서 forward pass를 했었던 이전 모델. 또한 3개의 모델(feature를 뽑아내는 CNN, 어떤 class인지 알아내는 classifier, bounding boxes를 예측하는 regression model)을 각각 학습시켜야 했음.  <br>Fast R-CNN에서는 중복되는 연산을 하나의 CNN으로 해결. 즉 이미지를 가장 먼저 받아서 feature를 뽑아내는 일을 하기 때문에 base network 또는 중복되는 일을 하나의 CNN에서 처리하기 때문에 shared network라고 함.<br><br>  
  
  <b>3. How it works</b><br>
  - Base network가 하는 일은 특징 추출이다. 중요한 것은 pretrained model을 사용해야 한다는 것. (transfer learning과 유사)  <br>모델은 기존의 모델을 주로 사용. ResNet, VGG, Inception 등. 다만 찾고자 하는 object의 feature를 뽑아내야 하기 때문에 이미 해당 object를 학습해놓은 상태여야 함.<br>
  
  ![image](https://user-images.githubusercontent.com/27988544/59889204-3ddb2f00-9406-11e9-8191-0b08195afe94.png)<br><br>
  <b>4. Region Proposal Network</b>
  - RPN은 conv를 통해 구현하며, input은 이전 base network에서 뽑아낸 feature maps를 사용. Region proposals을 생성하기 위해서는 base network에서 생성한 feature maps위에 n x n spatial window (보통 3 x 3)를 슬라이드 시킨다. 각각의 sliding-window가 찍은 지점마다, 한번에 여러개의 region proposals을 예측하게 된다. Region proposals의 최고 갯수는 k로 나타내며, 이것을 <b>Anchor</b>라고 부른다. 보통 각 sliding window의 지점마다 9개의 anchors가 존재하며, 3개의 서로 다른 종횡비 (aspect ratios) 그리고 3개의 서로 다른 크기 (scales)가 조합되며 모두 동일한 중앙지점을 가지게 됨.<br>
  ![image](https://user-images.githubusercontent.com/27988544/59889289-ade9b500-9406-11e9-81a4-7fd5cfb3187c.png)<br>
  <br>
  Sliding window를 통해 나온 feature map의 depth는 더 낮은 차원이 됨( ex) 512 depth -> 256 depth ) 이후의 output 값은 1 x 1 kernel을 갖고 있는 두개의 convolutional layers로 양분되어 들어가게 된다.  
  <br>
  <b>Classification layer</b>에서는 anchor당 2개의 predictions값을 내놓으며, 객체인지에 대한 확률값을 의미.  
  <br>
  Regeression layer (또는 bounding box adjustment layer)는 각 anchor당 델타값들 <sub>x<sub>center</sub></sub>, <sub>y<sub>center</sub></sub>, <sub>width</sub>, <sub>height</sub> 4개의 값을 구함. 이 델타 값들은 anchors에 적용이 되어서 최종 proposals을 얻게 된다.
  
  ![image](https://user-images.githubusercontent.com/27988544/59890063-29009a80-940a-11e9-9684-df3dc27012c0.png)
  <br>
  
  <b>4. Classifier of Background and Foreground</b><br>
  Classifier를 학습시키기 위한 training data는 바로 위의 RPN으로 부터 얻은 anchors와 ground-truth boxes 이다.  
  모든 anchors를 foreground이냐 또는 background이냐로 분류를 해야함. 분류를 하는 기준은 anchor가 ground-truth box와 오버랩되는 부분이 크면 foreground기ㅗ, 적으면 background이다. 각각의 anchor마다 foreground인지 아니면 background인지 구별하는 값을 p<sup>*</sup> 값이라고 했을 때 공식은 다음과 같다.<br>
  ![image](https://user-images.githubusercontent.com/27988544/59890236-f7d49a00-940a-11e9-9511-9c656e54901d.png)
  <br>
  
  여기서 IoU는 Intersection over Union으로서 다음과 같이 정의가 된다.  
  ![image](https://user-images.githubusercontent.com/27988544/59890251-0e7af100-940b-11e9-9919-3d6a66feee7f.png)
  ![image](https://user-images.githubusercontent.com/27988544/59890271-20f52a80-940b-11e9-9964-150c005a6de2.png)
  <br>
  
  일반적으로 IoU값이 가장 높은 값을 1로 잡으면 되지만, 잘 잡히지 않는 경우 0.7 이상으로 해서 찾으면 된다. 또한 하나의 ground-truth box는 여러개의 anchors에 1값을 줄 수가 있다. 또한 0.3 이하로 떨어지는 anchor는 -1값을 준다. 그 외에는 무시.  
  <br>
  
  <b>Region of Interest Pooling</b><br>
  RPN이후, 서로 다른 크기의 proposed regions값을 output으로 받음. CNN의 output인 feature maps을 flatten 시켜서 모델을 통과하고 추후 classification을 할 때는 크기가 모두 제각각이라 어려움이 있다.  
  이 때 사용하는 기법이 Region of Interest Pooling(ROI) 기법이다. ROI를 사용하게 되면 서로 다른 크기의 feature maps를 동일한 크기로 변환시킬 수 있다.
  <br>
  <br>
  
  ROI를 구현하기 위해서는 다음 2개의 inputs이 필요하다.
  1. Depp conv, max pooling layer를 통해 나온 feature map
  2. N x 4 매트릭스 -> N은 ROI의 갯수, 4는 region의 위치를 나타내는 coordinates
  <blockquote>아래 그림은 region proposals(핑크 직사각형)이 포함된 이미지</blockquote>
  
  ![image](https://user-images.githubusercontent.com/27988544/59890538-4d5d7680-940c-11e9-8b22-41eb6542fcc0.png)
  <br>
  
  ROI 로직은 다음과 같다.
  1. 각각의 region proposal을 동일한 크기의 sections으로 나눔 (section의 크기는 ROI pooling의 output크기와 동일)
  2. 각각의 section마다 가장 큰 값을 찾음
  3. 각각의 찾은 maximum값을 output으로 만듦
  <br>
  <br>
  
  <b>Region of Interest Pooling Example</b><br>
  예를 들어서 8 x 8 형태의 feature map을 다음과 같이 있다고 가정.
  ![image](https://user-images.githubusercontent.com/27988544/59890581-8dbcf480-940c-11e9-96ab-9a2591d33a72.png)
  <br>
  
  Region proposal의 값은 (0, 3), (7, 8)일 때 다음과 같다.<br>
  ![image](https://user-images.githubusercontent.com/27988544/59890605-a2998800-940c-11e9-953b-266cc3d7dd93.png)
  <br>
  
  실제로는 수십~수천장의 feature maps를 갖고 있겠지만, 예제에서는 문제를 간단하게 하기 위해 1개의 fature map만 있다고 가정<br>
  Section의 크기 2 x 2로 region proposal을 아래와 같이 나눕니다.
  ![image](https://user-images.githubusercontent.com/27988544/59890635-c361dd80-940c-11e9-8326-8d658824b414.png)
  <br>
  
  위의 그림처럼 section의 크기는 모두 동일할 필요가 없다. 다만 크기가 거의 동일하기만 하면 된다.<br>
  Max values를 보면 다음과 같은 output이 생성이 된다.  
  ![image](https://user-images.githubusercontent.com/27988544/59890662-e12f4280-940c-11e9-8f2d-27bcfdd22b36.png)
  <br>
  
  #### Training
  <b>Loss Function</b><br>
  ![image](https://user-images.githubusercontent.com/27988544/59890736-3cf9cb80-940d-11e9-8c31-8d7bca6c28ff.png)
  <br>
  
  N<sub>reg</sub> 는 Smooth L1 function의 공식을 사용한다. Smmoth L1의 경우 L1과 동일하지만, error의 값이 충분히 작을 경우 거의 맞는 것으로 판단하며, loss 값을 더 빠른 속도로 줄어들게 된다.<br>
  ![image](https://user-images.githubusercontent.com/27988544/59890759-6286d500-940d-11e9-8c3b-adf016164657.png)
  <br>
  <br>
  
  <b>Training RPN</b><br>
  하나의 이미지에서 random 으로 256개(batch 크기)의 anchors를 샘플링한다. 이때 positive anchors(객체)와 nehative anchors(배경)의 비율은 1:1이 되도록 한다. 만약 그냥 랜덤으로 진행하면 negative anchors의 갯수가 더 많기 때문에 편향되게 학습될 수 있기 때문이다.<br>
  <br>
  하지만 현실적으로 1:1 비율을 지속적으로 유지하는 것은 어렵다. 대부분 positive samples의 갯수가 128개를 넘지 못하는 경우인데, 이 경우 zero-padding을 시켜주거나, 아예 없는 경우는 IoU값이 가장 높은 값을 사용하기도 한다.<br>
  <br>
  논문에서는 추가되는 새로운 레이어의 weights값은 0 mean, 0.01 standard deviation을 갖고 있는 gaussian distribution으로부터 초기화를 ㅎ나다(BaseNet에 해당되는 ImageNet을 제외). Learning rate의 경우 처음 60k mini-batches에 대해서는 0.001, 그 다음 20k mini-batches에 대해서는 0.0001을 PASCAL VOC dataset에 적용을 한다.<br>
  <br>
  
  #### Processing Tips
  <b>Non-Maximum Suppression</b><br>
  한 객체당 여러개의 proposals 값을 얻게 됨. acnhors자체가 중복되기 때문이다. 문제를 해결하기 위해서 non-maximum suppression(NMS)알고리즘을 사용해서 proposals의 갯수를 줄이도록 한다. NMS는 IoU값으로 proposals을 모두 정렬시켜놓은 뒤, RoI점수가 가장 높은 proposal과 다른 proposals에 대해서 overlapping을 비교한 뒤 overlapping이 높은 것은 특정 threshold이상이면 지워버리며 이 과정을 iterate돌면서 삭제시킨다.<br>
  <br>
  즉 RoI가 높은 bounding box와 overlapping되는 anchors들 중 특정 threshold이상이면 proposals에서 삭제시키는 형태. 그러면 서로 오버랩은 되지 않으면서 RoI가 높은 anchors만 남게 된다. 일반적으로 threshold값을 0.6~0.9로 한다.<br>
  ![Uploading image.png…]()
  <br>
  
  <b>ROI-Removed Model</b><br>
  만약 구분해야 될 클래스가 1개밖에 없을 때는 RPN만 사용해서 구현이 가능하다. 객체인지 아닌지 구분하는 classifier자체를 사용해서 클래스를 구별하면 된다.<br>
  Computer Vision에서 대표적인 예가 face detection 그리고 text detection 등이 있다. RPN만 사용하게됨으로 당연히 training 그리고 inference의 속도는 매우 빨라지게 된다.<br>
  <br>
  
  <b>Fixed-size Resize instead of ROI pooling</b><br>
  Region of Interest Pooling 대신 object detection을 실제 구현할 때 그냥 더 많이 쓰이고 쉬운 방법이 있습니다. 각각의 convolutional feature map을 각각의 proposal로 crop을 시킨뒤에, cropped된 이미지를 고정된 크기 14 x 14 x depth로 interpolation (보통 bilinear)을 사용해서 resize 시킨다. 이후 2 x 2 kernel을 사용해서 7 x 7 x edepth 형태의 feature map으로 max pooling 시켜준다.<br>
  <br>
  이 때 사용된 크기나 값들은 그 다음에 사용될 block (보통 fully-connected dense layer)에 따라서 결정됨으로 다양하게 바뀔 수 있다.
  
  <br>
  <hr>
  <br>
  ### TODO-1. 데이터 다운 및 불러오는 코드 구현
  ### TODO-2. model 구현 및 기능 별로 파일 분리
  ### TODO-3. 데이터 학습 및 테스트 코드 구현
  ### TODO-4. 결과 그래프 또는 데이터에 대한 시각화 구현 
  
  </p>
  </details>