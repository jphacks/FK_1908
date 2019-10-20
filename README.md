# スマート・トースター molle

[![Product Name](image.png)](https://www.youtube.com/watch?v=G5rULR53uMk)

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
* CircelCI で継続的にビルドすることでコードが常にビルドできる状態を維持した．
* Danger + ktlint で注意点をPRに通知．
* CircleCi で ktlint を継続的にチェックすることでコードの品質を一定に保った．
* Android client app を Clean アーキテクチャ + Usecase, repository など保守性を重視した構成にした．
* CircleCi Android Build job (build only) を用いた．
* Kubernetes を活用したサーバーサイドと機械学習の運用した．
* Android アプリの上で TTS を利用して発音できるようにした．
* Android アプリでPreview 版のカメラAPIを利用してプレビューが見れるようにしたのと、写真を取得してサーバーに送信できるようにしました。
* BGM を再生できるようにした
* 朝起きた時に今日の天気を知ることができるように天気を見れるようにした
* 今回実装した用件を満たすAPIをほぼ全てサーバーレスで実装(https://github.com/jphacks/FK_1908/tree/master/server/bread-api)
* 複数のAPIコールを有するlambda関数内で並列処理を行い，レスポンスタイムを向上(https://github.com/jphacks/FK_1908/blob/master/server/bread-api/alarm/lambda_function.rb)
* Android 通信処理やタイマー処理をI/O Thread で動作し必要な時だけUI Thread で動作させることにより、アプリのUIが固まらないように工夫した。
* Android アプリを開発する中で画面の回転も考慮する必要があるが ViewModel という概念を導入し、
* CircleCi で ktlint を継続的にチェックすることでコードの品質を一定に保った．アーキテクチャ
