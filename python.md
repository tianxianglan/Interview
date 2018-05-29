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
