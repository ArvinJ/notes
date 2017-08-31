# Java

### 常用语法，方法

##### 1.replace()，replaceAll()区别

~~~java
String x = "[kllkklk\\kk\\kllkk]";
要将里面的“kk”替换为++，可以使用两种方法得到相同的结果  

replace(CharSequence target, CharSequence replacement)   ——   x.replace("kk", "++")
replaceAll(String regex, String replacement)             ——   x.replaceAll("kk", "++")
 
可见两个函数没有什么区别，下面将字符串中的“\\”替换为“++”
  System.out.println(x.replace("\\", "++"));    没有问题
  System.out.println(x.replaceAll("\\", "++"));  报错 Java.util.regex.PatternSyntaxException
  当使用转义字符进行替换的时候，是有区别的。replaceAll的参数就是regex，是正则表达式。
  首先会转义，所以报错。

如果使用System.out.println(x.replaceAll("\\\\", "++"));即可完成。

那么在使用普通的字符串替换时，选用哪一个函数呢？

String x = "[kllkklk\\kk\\kllkk]";
  String tmp;
  //System.out.println(x.replace("[", "#").replace("]", "#"));
  System.out.println(new Date().getTime());
  for(int i =0;i<1000000;i++)
   tmp=x.replace("kk", "--");
  System.out.println(new Date().getTime());
  for(int i =0;i<1000000;i++)
   tmp=x.replaceAll("kk", "++");
  System.out.println(new Date().getTime());

测试结果：
1312424571937
1312424574531
1312424576671

根据测试replaceAll函数要更快一些。看源码发现，replace函数里面仍使用replaceAll函数
总体原则：当字符串无法确定是否具有转义字符时，而且也不需要转义时，建议使用replace函数
否则，使用replaceAll函数
~~~



##### 2.String,StringBuffer,StringBuilder的区别

~~~java
1.可变与不可变
String类中使用字符数组保存字符串，如下就是，因为有“final”修饰符，所以可以知道string对象是不可变的。
　　　　private final char value[];

StringBuilder与StringBuffer都继承自AbstractStringBuilder类，在AbstractStringBuilder中也是使用字符数组保存字符串，如下就是，可知这两种对象都是可变的。
　　　　char[] value;




~~~

