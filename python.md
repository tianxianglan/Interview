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
```
-  高阶函数：
```
-  Decorator（装饰器）：  
            @log                          def log(f):  
            def f1(x):                          def fn(x) 
                return x* 2                          print "log detail" 
             print x                                 return f(x)
                                                return fn
```           
-  偏函数
```
    functools.partial可以帮助创建一个偏函数。可以实现把一个参数多的函数变成一个参数较少的函数，减少的参数需要在创建时  
    指定默认值，这样，新函数调用的难度就降低了。  
    import functools
    int2 = functools.partial(int, base = 2)
    int2('101') = 5
```
python每一个包中都必须有一个__init__.py文件

-  面向对象编程：  
```
class Person(object):
    psss
    
  按照Python编程习惯，类名与大写字母开头，进接着是object，表示该类是从哪个类继承下来的
```
```
实际应用中，我们需要自由的给一个实例绑定的各种属性。但现实社会中，一个类型的实例应该拥有相同的相同的名字的属性。  
在定义Person类时，可以为Person类添加一个特殊的__init__()方法，当创建实例时，__init__()方法被自动调用，我们  
就能在此为每个实例都统一加上以下属性：
class Person(object):
    def __init__(self, name, gender, birth):
        self.name = name
        self.gender = gender
        self.birth = birth
```
-  类继承
```
class Person(object):
    def __init__(self, name, gender):
        self.name = name
        self.gender = gender

class Teacher(Person):

    def __init__(self, name, gender, course):
        //函数super(Teacher, self)将返回当前类继承的父类
        super(Teacher, self).__init__(name, gender)
        self.course = course

t = Teacher('Alice', 'Female', 'English')
print t.name
print t.course
```
```
class Person(object):

    def __init__(self, name, gender, **kw):
        self.name = name
        self.gender = gender
        for k, v in kw.iteritems():
            setattr(self, k, v)//设置对象属性

p = Person('Bob', 'Male', age=18, course='Python')
print p.age
print p.course
```
-- 特殊方法：
    类似于Java中的toString（）方法，可以进行重写。在Python中，这类方法包括：__str__()、__repr__()、__cmp__()
    
__slots__  是指一个类允许的属性列表：
```
class Student(object):
    __slots__ = ('name', 'gender', 'score')
    def __init__(self, name, gender, score):
        self.name = name
        self.gender = gender
        self.score = score
```
   
一个类实例可以变成一个可调用对象，只需要实现一个特殊方法__call__()
```
将Person类变成一个可调用对象：
class Person(object):
    def __init__(self, name, age):
        self.name = name
        self.age = age
        
    def __call__(self, friend):
        print 'my name is %s' % self.name
        pritn 'my friend is %s' % friend
        
>>>p = Person('bob', 23)
>>>p('Tim')
my name is bob
my friend is Tim
```

