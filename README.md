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
|-- control
|   |-- Control
|   |-- Field
|   `-- UnitAction
|-- ui
|   |-- UI
|   |-- Display
|   `-- InputReception
|-- models
|   |-- Cell
|   |-- Flatland
|   |-- Mine
|   `-- Unit
`-- solver
    `-- ゲームを解いてくれるアルゴリズム(仮)
```

## コードを書く時...
- 何をしてるかわかりやすいようにできればコメントを書いてね!
- できればどこにどんなクラスを配置したかREADMEに書いてね!
- gitでの操作手順を`git.txt`に書いたのでわからなくなったら見てみてね!
- .bashrcをホームディレクトリに置くとgit操作がとっても楽になるよ!
  ```
  # .bashrcの配置
  > cp .bashrc ~/
  # git add
  > ga
  # git commit -m
  > gc
  # ...
  ```
