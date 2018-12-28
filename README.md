# nitac_programming_task
マインスイーパ...?

## ゲームのスタート
```shell
> javac Minesweeper.java
> java Minesweeper
# or
> ./run.sh
```

## プログラムの構成
```
nitac_programming_task
|-- Minesweeper.java
|-- control                  ゲームシステム関連
|   |-- Control.java
|   |-- Field.java
|   `-- UnitAction.java
|-- models                   データ関連
|   |-- Cell.java
|   |-- Flatland.java
|   |-- Mine.java
|   `-- Unit.java
|-- ui                       ユーザインターフェース関連
|   |-- UI.java
|   |-- Display.java
|   |-- InputReception.java
|   `-- animations           アニメーション関連
|       |-- Animation.java
|       `-- ExplodeAnimation.java
`-- solver
    `-- ゲームを解いてくれるアルゴリズム(仮)
```

## コードを書く時...
- 何をしてるかわかりやすいようにできればコメントを書いてね!
- できればどこにどんなクラスを配置したかREADMEに書いてね!
- gitでの操作手順を`git.txt`に書いたのでわからなくなったら見てみてね!
- .bashrcをホームディレクトリに置くとgit操作がとっても楽になるよ!
  ```shell
  # .bashrcの配置
  > cp .bashrc ~/
  # git add
  > ga
  # git commit -m
  > gc
  ...
  ```
