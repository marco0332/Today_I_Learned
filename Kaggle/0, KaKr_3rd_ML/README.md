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
  
  ## 스터디 2일차 (2019-06-21)
  
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
  <math xmlns="http://www.w3.org/1998/Math/MathML" display="block">
  <msup>
    <mi>p</mi>
    <mo>&#x2217;<!-- ∗ --></mo>
  </msup>
  <mo>=</mo>
  <mrow>
    <mo>{</mo>
    <mtable columnalign="left left" rowspacing=".2em" columnspacing="1em" displaystyle="false">
      <mtr>
        <mtd>
          <mn>1</mn>
        </mtd>
        <mtd>
          <mtext>if&#xA0;</mtext>
          <mi>I</mi>
          <mi>o</mi>
          <mi>U</mi>
          <mo>&#x003E;<!-- > --></mo>
          <mn>0.7</mn>
        </mtd>
      </mtr>
      <mtr>
        <mtd>
          <mo>&#x2212;<!-- − --></mo>
          <mn>1</mn>
        </mtd>
        <mtd>
          <mtext>if&#xA0;</mtext>
          <mi>I</mi>
          <mi>o</mi>
          <mi>U</mi>
          <mo>&#x003C;<!-- < --></mo>
          <mn>0.3</mn>
        </mtd>
      </mtr>
      <mtr>
        <mtd>
          <mn>0</mn>
        </mtd>
        <mtd>
          <mtext>if otherwise</mtext>
        </mtd>
      </mtr>
    </mtable>
    <mo fence="true" stretchy="true" symmetric="true"></mo>
  </mrow>
</math>
  
  </p>
  </details>