
# Usage
build.xml是ant的配置文件，这里列举了几个最常用的用法，

### 运行ProblemBuilder的main()函数来测试
```
ant -Dproblem two_sum
```
这里参数`-Darg0`给出的Leetcode每个问题的名称，比如Leetcode的第一题就叫`two_sum`。运行之后，`ProblemBuilder`会填写`src/main/resources`目录下的Velocity模板（`.vm`）文件，并部署到Leetcode源码`src/main/java`目录下。


### 运行JUnit单元测试
```
ant junit
```
JUnit单元测试测试了`ProblemBuilder`中的所有函数。


### 清除所有编译和测试产生的文件
```
ant clean
```

### 仅仅是编译项目源代码
```
ant build
```

