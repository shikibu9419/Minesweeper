# nitac_programming_task
マインスイーパ...?

## ゲームのスタート
```shell
> ./run.sh
```

## プログラムの構成
```
nitac_programming_task
|-- Minesweeper.java
|-- control                       ゲームシステム関連
|   |-- Information.java
|   |-- Field.java
|   `-- UnitAction.java
|-- models                        データ関連
|   |-- Cell.java
|   |-- Flatland.java
|   |-- Mine.java
|   `-- Unit.java
|-- ui                            ユーザインターフェース関連
|   |-- Color.java
|   |-- Display.java
|   |-- InputReceiver.java
|   `-- animations                アニメーション関連
|       |-- Animation.java
|       `-- ExplodeAnimation.java
|-- options                       オプション関連
|   |-- Difficulty.java
|   |-- GameMode.java
|   `-- UnitType.java
`-- algorithm                     アルゴリズム関連
    `-- Computer.java
```
