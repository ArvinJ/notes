# Java

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
public final class String
    implements java.io.Serializable, Comparable<String>, CharSequence {
    /** The value is used for character storage. */
    private final char value[];
  	.....}

StringBuilder与StringBuffer都继承自AbstractStringBuilder类，在AbstractStringBuilder中也是使用字符数组保存字符串，如下就是，可知这两种对象都是可变的。
　abstract class AbstractStringBuilder implements Appendable, 	CharSequence {
    /**
     * The value is used for character storage.
     */
    char[] value;
   	......}

public final class StringBuffer
    extends AbstractStringBuilder
    implements java.io.Serializable, CharSequence
{.......}
public final class StringBuilder
    extends AbstractStringBuilder
    implements java.io.Serializable, CharSequence
{.......}

2.是否多线程安全

　　String中的对象是不可变的，也就可以理解为常量，显然线程安全。

　　AbstractStringBuilder是StringBuilder与StringBuffer的公共父类，定义了一些字符串的基本操作，如expandCapacity、append、insert、indexOf等公共方法。

　　StringBuffer对方法加了同步锁或者对调用的方法加了同步锁，所以是线程安全的。看如下源码：
  public synchronized StringBuffer reverse() {
    super.reverse();
    return this;
}

public int indexOf(String str) {
    return indexOf(str, 0);        //存在 public synchronized int indexOf(String str, int fromIndex) 方法
}
StringBuilder并没有对方法进行加同步锁，所以是非线程安全的。

 3.StringBuilder与StringBuffer共同点

　　StringBuilder与StringBuffer有公共父类AbstractStringBuilder(抽象类)。

　　抽象类与接口的其中一个区别是：抽象类中可以定义一些子类的公共方法，子类只需要增加新的功能，不需要重复写已经存在的方法；而接口中只是对方法的申明和常量的定义。

　　StringBuilder、StringBuffer的方法都会调用AbstractStringBuilder中的公共方法，如super.append(...)。只是StringBuffer会在方法上加synchronized关键字，进行同步。

　　最后，如果程序不是多线程的，那么使用StringBuilder效率高于StringBuffer。
  
  字符串进行多次拼接用 StringBuffer 效率高
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class TestString {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Test
    public void testPlus() {
        String s = "";
        long ts = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            s = s + String.valueOf(i);
        }
        long te = System.currentTimeMillis();
        logger.info("+ cost {} ms", te - ts);
    }
    @Test
    public void testConcat() {
        String s = "";
        long ts = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            s = s.concat(String.valueOf(i));
        }
        long te = System.currentTimeMillis();
        logger.info("concat cost {} ms", te - ts);
    }
    @Test
    public void testJoin() {
        List<String> list = new ArrayList<String>();
        long ts = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            list.add(String.valueOf(i));
        }
        StringUtils.join(list, "");
        long te = System.currentTimeMillis();
        logger.info("StringUtils.join cost {} ms", te - ts);
    }
    @Test
    public void testStringBuffer() {
        StringBuffer sb = new StringBuffer();
        long ts = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            sb.append(String.valueOf(i));
        }
        sb.toString();
        long te = System.currentTimeMillis();
        logger.info("StringBuffer cost {} ms", te - ts);
    }
    @Test
    public void testStringBuilder() {
        StringBuilder sb = new StringBuilder();
        long ts = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            sb.append(String.valueOf(i));
        }
        sb.toString();
        long te = System.currentTimeMillis();
        logger.info("StringBuilder cost {} ms", te - ts);
    }
}
~~~

##### 3.java中length,length(),size()区别

```java
1 java中的length属性是针对数组说的,比如说你声明了一个数组,想知道这个数组的长度则用到了length这个属性.

2 java中的length()方法是针对字符串String说的,如果想看这个字符串的长度则用到length()这个方法.

3.java中的size()方法是针对泛型集合说的,如果想看这个泛型有多少个元素,就调用此方法来查看!
  
  这个例子来演示这两个方法和一个属性的用法

 public static void main(String[] args) {
        String []list={"ma","cao","yuan"};
        String a="macaoyuan";
        System.out.println(list.length);
        System.out.println(a.length());


        List<Object> array=new ArrayList();
        array.add(a);
        System.out.println(array.size());
    }

 

输出的值为:

3

9

1
```













# JavaScript

#### 1.通过url获取一张网络图片的width,height

~~~javascript
 
      function isInBetweenByHtmlSrc(titleHtml){
	  		var imgArray = new Array();
        	//截取出图片  <img />
        	var re = /<img[^>]+>/g;  
        	var a = titleHtml.match(re);  
        	//a 是 <img src=""/>
        	if(a!=null){
        		var srcStr = new Array();
        		var lsStr = "";
        	   for(var i=0;i<a.length;i++){ 
        		   var tempImgSrc = "";
        		  a[i].replace(/<img [^>]*src=['"]([^'"]+)[^>]*>/gi, 				  function (match, capture) {
        			 lsStr +=capture+",";
				 });
        		  
        	  }   
        	   lsStr =  lsStr.substr(0,lsStr.length-1);
        	   srcStr = lsStr.split(",");
        	   for(var h=0;h<srcStr.length;h++){
        		  
        		// 创建对象
               var img = new Image();
               	// 改变图片的src
               	img.src = srcStr[h];
               	img.onload = function(){
              	    // 打印
              		if(img.width<600 || img.width>640){
               	   layer.msg("图片作答题目中图片宽度范围为600px~640px");
               	    	return false;
               	    }else{
               	    	return true;
               	    }
              	}; 
              	
        	   }
        	  
        	   
        	}
        	
        }
        
       
~~~



# Exception

##### 1.ClassNotFoundException

````jav
 1.Spring集成JSON报错：java.lang.ClassNotFoundException: 
  org.codehaus.jackson.JsonProcessingException   ?
 老版本 class="org.springframework.http.converter.json.MappingJacksonHttpMessageConverter"
 如果将Spring的版本提高到最新的版本(4.3.2.RELEASE)
 改为
 <!--避免IE执行AJAX时，返回JSON出现下载文件 -->
<bean id="mappingJacksonHttpMessageConverter"
    class="org.springframework.http.converter.json.MappingJackson2HttpMessageConverter">
		<property name="supportedMediaTypes">
			<list>
				<value>text/html;charset=UTF-8</value>
			</list>
		</property>
	</bean>
 
  


````



##### 

# hql

##### 1.hql  相同的id 只查询一条

```mys
#使用group by id
String hql = "select new QuestionFeature(qf.featureCode, qf.featureDesc,qf.featureIcon)  from QuestionType qt, QuestionFeature qf"
				+ "  where qt.featureCodeId = qf.id  and qt.parentId=:parentId and qt.status = :status and qf.status = :qfStatus group by qt.featureCodeId";
```







# Validation

# soft

##### 1.Zeplin   用于 产品UI交互图



