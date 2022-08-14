## 課題について
realmでの保存ができていませんが、これ以上時間をかけられないためここで提出します
よろしくお願いします

## 書籍の数が増えた際に、パフォーマンスを保つためにはどうすれば良いか、フロント観点/API観点から提案してください。
### フロント視点
apiから取得したjsonを一定時間キャッシュしておく
アプリの性質上時間単位や分単位で情報の更新が必要なものでもないと思われるので、例えばダウンロードした日付を保存しておき、前回のAPIリクエストから日付が変わっていたらAPIリクエストを行う
ユーザーがコンテンツが更新されていないと感じることによるってUXを下げないようにするために、最上部にいるときに下スワイプすることで前回ダウンロードした日付にかかわらず強制的に更新を行う
また、画像の読み込みが一番時間がかかるので、画像はダウンロードしてアプリ領域に保存しておく
無限に容量を食うのを防ぐため、画面描画後等のタイミングで、使わなかった画像は削除する

### API視点
一度に全て送るのではなく、一定冊ごとに分けて送る
例えば`/mock/book/unlimited/new/0`でUnlimitedの新着の上位1〜20冊を送り、`/mock/book/unlimited/new/20`で21〜40冊目を送る
または、全カテゴリの1~20冊目は`/mock/book/all`で送り、サブカテゴリーの21冊目以降は`/mock/book/unlimited/new/20`等の別のAPIに分ける、など
フロント側からはAndroidだとPagingのような仕組みを使い、表示が必要になったら読み込むようにする

## MyBook登録のステータスをサーバー側で管理したいと考えた時に、どのような通信を行うか、提案してください。
どのような、の意味がわからなかったのですが、TCPとかUDPとかそのレベルでの選択肢はないかと思うので、起動から保存までの間に行うべき通信とその順序、という観点で回答します
1. メールアドレスやSNS認証などでログインする
2. Amazon Cognitoを使ってサーバー側で認証を行う
3. フロントエンドでrealmに保存、viewを更新
4. AWS LambdaやAmazon EC2等のAPIサーバーにPOSTでユーザー情報と本の情報を送る
5. Amazon RDSで保存する
6. 成否を返し、フロントのrealmとviewを再度更新、dialogやtoast、snackbarなどで成否を通知する

