         Integer m = 130;
         Integer n = 130;
         Integer i = 33;
         Integer j = 33;
 
         System.out.println(i == j);
         System.out.println(m == n);
         
输出结果为： true、false   
#### 原因：
Integer源码中对于常量池的定义如下： 
        
        
                private static class IntegerCache {
                                static final int low = -128;
                                static final int high;
                                static final Integer cache[];
                    static {
                        // high value may be configured by property
                        int h = 127;
                        //可以从JVM配置参数中读取自定义的常量池的最大值
                        String integerCacheHighPropValue =
                            sun.misc.VM.getSavedProperty("java.lang.Integer.IntegerCache.high");
                        if (integerCacheHighPropValue != null) {
                            try {
                                int i = parseInt(integerCacheHighPropValue);
                                //获取最大值，默认为127
                                i = Math.max(i, 127);
                                // Maximum array size is Integer.MAX_VALUE
                                h = Math.min(i, Integer.MAX_VALUE - (-low) -1);
                            } catch( NumberFormatException nfe) {
                                // If the property cannot be parsed into an int, ignore it.
                            }
                    }
                    high = h;
        
                    cache = new Integer[(high - low) + 1];
                    int j = low;
                    //对常量池数据cache[]进行赋值
                    for(int k = 0; k < cache.length; k++)
                        cache[k] = new Integer(j++);
        
                    // range [-128, 127] must be interned (JLS7 5.1.7)
                    assert IntegerCache.high >= 127;
                }
        
                private IntegerCache() {}
            }    
            
            
源码中，常量池中最小值写死为-128，最大值可以由用户自定义，默认为127，当开发人员定义 `Integer i = num`的时候，如
果num在[low,high]中，则地址引用i指向的就是常量池缓存数组cache[]中对应的值，所以在测试用例中 `i`与  `j`  的进行判断时返回结果为true， `==`判断的的是比较对象的地址。而 `m`与 `n`的值大于默认值127，所
以虚拟机在分配内存时将在堆内存中新开辟一块空间用于存放`m`与 `n`的值，这也就意味着`m`与 `n`所指向的地址
 不相同，因而返回结果也就为false.
 
#### 延伸：  
前面提到常量池最大值可以用用户指定，通过 `-XX:AutoBoxCacheMax=131`JVM参数来设置其最大值。当我们这样设置虚拟机后再
运行程序发现两者返回结果都为 `true`,这也就意味着`m`与 `n`所指向的地址为同一个。
