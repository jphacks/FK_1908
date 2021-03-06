# スマート・トースター molle

[![ビデオが開けなかった場合に表示されるテキスト](http://img.youtube.com/vi/sWgyxhGbqFs/0.jpg)](http://www.youtube.com/watch?v=sWgyxhGbqFs)

## 製品概要
### 焼きたてパンの香りで起きる朝 x tech

### 背景（製品開発のきっかけ、課題等）
朝起きるのが辛いなと感じたり，予想外の悪天候や渋滞でバタバタしてしまったりすることはありませんか？
そういった課題を解決し，余裕を持って朝を過ごしたいという想いを元に，私たちは『スマート・トースター molle』を作成しました．
これを使えば，素敵な音楽と香ばしいパンの香りがあなたを起こしてくれます．
快適な朝を手に入れましょう！

### 製品説明（具体的な製品の説明）
前日に翌日のスケジュールと生活リズム、さらには交通情報と天気情報を活用して余裕を持って過ごせる起床時間をAIが自動で推測してくれます。
翌朝には，時間になったら焼きたてパンの匂いと共に起こしてくれます。

### 特長
* 素敵な音楽と香ばしいパンの香りが起こしてくれる．
* 翌朝に余裕を持って過ごせる起床時間をAIが自動で決めてくれる．
* 前日にパンをトースターに入れ忘れたら通知をくれる．

### 解決出来ること
* 雨や渋滞による朝のバタつきから開放されます
* 朝，爽やかに目覚めることができます

### 今後の展望
* 起きた時にストレッチをしているので何をすると良さそうか教えて欲しい．
* 前日の運動量や摂取カロリー，当日の予定などを考慮して，適切なジャムなどを自動でパンにつけたい．

## 開発内容・開発技術
### 活用した技術
#### API・データ
* OpenWeatherMap API
* goo ニュースAPI
* Google Map API
* Google Calender

#### フレームワーク・ライブラリ・モジュール
* Android/kotlin
* CircleCi
* Danger/ktlint
* Fuel / Jetpack(ViewModel, Livedata, kotlin-extensions, kotlin coroutines)
* Python/TensorFlow, keras, uwsgi, flask

#### ミドルウェア/サービス
* Nginx
* AWS Lambda
* API Gateway
* AWS EC2
* DynamoDB
* ムームーDNS

#### デバイス
* トースター
* スマートプラグ
* Androidタブレット

### 研究内容・事前開発プロダクト（任意）
* CircleCi Android Build job (build only)
* VGG16

### 独自開発技術（Hack Dayで開発したもの）
#### 2日間に開発した独自の機能・技術
* Android アプリ単体でスマートプラグとIFTTを活用することで自動でトースターの電源を入れ、安全のために自動で電源が切れるような設定をした。
* CircelCI で継続的にビルドすることでコードが常にビルドできる状態を維持した．
* Danger + ktlint で注意点をPRに通知．
* CircleCi で ktlint を継続的にチェックすることでコードの品質を一定に保った．
* Android client app を Clean アーキテクチャ + Usecase, repository など保守性を重視した構成にした．
* CircleCi Android Build job (build only) を用いた．
* Kubernetes を活用したサーバーサイドと機械学習の運用した．
* Android アプリの上で TTS を利用して発音できるようにした．
* Android アプリでPreview 版のカメラAPIを利用してプレビューが見れるようにしたのと、写真を取得してサーバーに送信できるようにしました。
* Androidアプリ内で画面の表示内容とは別にバックグラウンドで BGM を再生できるようにした
* 朝起きた時に今日の天気を知ることができるように天気を見れるようにした
* 今回実装した用件を満たすAPIをほぼ全てサーバーレスで実装(https://github.com/jphacks/FK_1908/tree/master/server/bread-api)
* 複数のAPIコールを有するlambda関数内で並列処理を行い，レスポンスタイムを向上(https://github.com/jphacks/FK_1908/blob/master/server/bread-api/alarm/lambda_function.rb)
* Android 通信処理やタイマー処理をI/O Thread で動作し必要な時だけUI Thread で動作させることにより、アプリのUIが固まらないように工夫した。
* Android アプリを開発する中で画面の回転も考慮する必要があるが ViewModel という概念を導入し、保守性を向上させると共に画面お回転をしたとしても問題がないようにした。
* CircleCi で ktlint を継続的にチェックすることでコードの品質を一定に保った。

##### 起床時間推定について
- google calenderの予定のうち、翌日最も早い時間に行われる予定の開始時間を基準に計算を行います。
- 予定に関して物理的移動を伴う場合、スタート地点とゴール地点の気象情報を取得し、それぞれ悪天候の場合は早めに家を出発するように重み付けをして計算を行います。
- また、2点間の移動に関して、Google Map APIの混み具合を考慮に含めた所要時間の計算結果から、より前もって準備できるよう、早めにアラームを鳴らすための調整を行います。
