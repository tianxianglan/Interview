-  基础知识
```
list: Python内置的一种数据类型，是一种有序集合
L = ['Adam', 'Lisa', 'Bart']
L[-1]：表示倒数第一个
append()：在列表`末尾`添加新的元素
insert{index,val}:在指定index下标出添加val元素
pop(index)：删除指定下标index的元素，如index未指定，则默认删除最末尾元素
```
```
tuple: Python中另外一种有序的列表，与list非常相似，但tuple一旦创建完毕就不能修改
t = ('Adam', 'Lisa', 'Bart')
```
```
dict：key-value结构。在一个dict中，key不允许重复，且值不可变
d = {
    'Adam': 95,
    'Lisa': 85,
    'Bart': 59
}
len()：表示dict中key-value个数
```
```
set：set持有一系列元素，这一点和list很像，但set的元素没有重复的，且无序
s = set(['A', 'B', 'C'])

```
```
L = range(1, 101)//1~100的数组

print L[0:10]//输出下标0~9的数
print L[2::3]//从第二个开始
print L[4:50:5]//不大于50的5的倍数
```
```
L = ['Adam', 'Lisa', 'Bart', 'Paul']
for index, name in zip(range(1,5),L):
    print index, '-', name
1、zip把两个list变成一个list。zip([10, 20, 30], ['A', 'B', 'C']) = [(10, 'A'), (20, 'B'), (30, 'C')]
```
```
def toUppers(L):
    return [x.upper() for x in L if isinstance(x, str)]
print toUppers(['Hello', 'world', 101])
1、isinstance(x, str) 可以判断变量 x 是否是字符串
2、字符串的 upper() 方法可以返回大写的字母
```
```
d = { 'Adam': 95, 'Lisa': 85, 'Bart': 59, 'Paul': 74 }
sum = 0.0
for k, v in d.items():
    sum = sum + v
    print k, ':', v
print 'average', ':', sum/len(d.items())
1、items() 方法把dict对象转换成了包含tuple的list。d.items()=[('Lisa', 85), ('Adam', 95), ('Bart', 59)]
2、len()返回长度
```
-  函数式编程
```
map（f,list）：
            接收一个函数 f 和一个 list，并通过把函数 f 依次作用在 list 的每个元素上，得到一个新的 list 并返回。
            
reduce(f,list):
            reduce()函数接收的参数和 map()类似，一个函数 f，一个list，但行为和 map()不同，reduce()传入的函数 f 必须  
            接收两个参数，reduce()对list的每个元素反复调用函数f，并返回最终结果值.   
            同时reduce函数也可以接受第三个值作为计算的初始值。如reduce(f, [1, 3, 5, 7, 9], 100) = 125
           
filtert(f, list):
            filter()函数接收一个函数 f 和一个list，这个函数 f 的作用是对每个元素进行判断，返回 True或 False，  
            filter()根据判断结果自动过滤掉不符合条件的元素，返回由符合条件元素组成的新list
            
sorted(list, f):
            它可以接收一个比较函数来实现自定义排序，比较函数的定义是，传入两个待比较的元素 x, y，如果 x 应该排在 y 的前面，  
            返回 -1，如果 x 应该排在 y 的后面，返回 1。如果 x 和 y 相等，返回 0。函数f可以定义为：
            def reversed_cmp(x, y):
                 if x > y:
                    return -1
                  if x < y:
                    return 1
                  return 0
```
```
-  匿名函数：
            map(lambda x: x* x, [1,2,3]) = [1, 4, 9].关键字表示匿名函数，冒号前面的x表示函数参数
            
-  Decorator（装饰器）：  
            @new_fn                          def f1(x):  
            def f1(x):                           return x* 2  
                return x* 2                 f1 = new_fn(f1)  
            
```           
