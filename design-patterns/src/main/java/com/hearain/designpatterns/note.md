
## 1.单一职责原则(SRP)

* 优点：消除耦合,减小因需求变化引起代码僵化的局面
* 原则：合理的类应该仅有一个引起他变化的原因

## 2.里氏替换原则(LSP)

* 子类必须完全实现父类的方法
* 子类可以有自己的特性
* 覆盖或者实现父类的方法时输入参数可以被放大
* 覆盖或者实现父类的方法时输出结果可以被缩小

## 3.依赖注入原则(DIP)

* 核心思想：依赖于抽象，不要依赖于具体的实现
### 原则说明：
1. 高层模块不应该依赖低层模块，两者都应该依赖于抽象(抽象类或接口)
2. 抽象不应该依赖于实现类
3. 具体实现类应该依赖抽象
