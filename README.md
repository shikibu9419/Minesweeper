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
|-- system
|   |-- Field
|   |-- UnitAction
|   `-- Utils
|-- ui
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
- 何をしてるかわかりやすいようにできればコメントを書いてください.
- 余裕があればどこにどういうクラスを配置したかREADMEに書いてください.
- gitの操作手順は`git.txt`に書いてあるのでわからんくなったらカンニングしてください.
- .bashrcをホームディレクトリに置くとgit操作がクソ楽になります.
  ```
  > cp .bashrc ~/
  ```
