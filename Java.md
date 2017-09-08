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



##### 4.java 返回json逻辑

```java


protected void print(){
        try {
            JSONObject resultObject = new JSONObject();
            resultObject = new JSONObject();
            resultObject.put("code", code.getCode());
            resultObject.put("message", code.getMessage());
            resultObject.put("details", details);

            HttpServletResponse response = ServletActionContext.getResponse();
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/json;charset=utf-8");
            PrintWriter out=response.getWriter();

            out.print(resultObject);
            out.close();

        } catch (Exception e) {
            logger.error(e.getMessage());
        }

    }


public String findQuestionByIds(){
		try {
			params();
			logger.info(data);
			if(TextUtil.isNullOrEmpty(data)){
				code = Code.ERROR_PARAMS_NULL;
			}else{
				JSONObject params = JSONObject.fromObject(data);
				String questionIds=params.getString("questionIds");
				List<Question> list=new ArrayList<Question>();
				if(questionIds!=null&&questionIds.length()>0){
					String[] str=questionIds.split(",");
					Long[] ids=new Long[str.length];
					for(int i=0;i<str.length;i++){
						ids[i]=Long.parseLong(str[i]);
					}
					list=questionService.findQuestionsById(ids);
					code = Code.SUCCESS;
				}else{
					code = Code.ERROR_LACK_QUESTION;
				}
				Map<String,Object> map=new HashMap<String,Object>();
				map.put("questions", list);
				details=map;
			}	
		} catch (Exception e) {
			code = Code.ERROR;
			e.printStackTrace();
		}
		print();
		return null;
	}

public String findPage(){
		try {
			params();
			logger.info(data);
			if(TextUtil.isNullOrEmpty(data)){
				code = Code.ERROR_PARAMS_NULL;
			}else{
				JSONObject params = JSONObject.fromObject(data);
				Integer subjectId=params.getInt("subjectId")==0?null:params.getInt("subjectId");
				Integer studentId=params.getInt("studentId");
				Integer pageIndex=params.getInt("pageIndex");
				Integer pageSize=params.getInt("pageSize");
				Integer begin=pageSize*(pageIndex-1);
				PrepPreview prepPreview=new PrepPreview();
				prepPreview.setSubjectId(subjectId);
				List<StuPreview> stuPreviews=previewService.findPrepPreviewByParam(subjectId,studentId,begin,pageSize);
				details=stuPreviews;
				code=Code.SUCCESS;
			}
		} catch (Exception e) {
			code=Code.ERROR;
			e.printStackTrace();
		}
		print();
		return SUCCESS;
	}


public String submitAnswer(){
		try {
			params();
			logger.info(data);
			if(TextUtil.isNullOrEmpty(data)){
				code = Code.ERROR_PARAMS_NULL;
			}else{
				JSONObject params = JSONObject.fromObject(data);
				Integer previewId=params.getInt("previewId");
				Integer studentId=params.getInt("studentId");
				String answer=params.getString("answer");
				Integer timeCost=JSONUtil.parseInt(params, "timeCost", true);
				PrepPreview pp=previewService.findPrepPreviewById(previewId);
				if(pp==null||pp.getStatus()==PrepPreview.STAT_DELETE){
					code=Code.ERROR_HOMEWORK_NULL;
				}else{
					PrepPreviewStudent pps=previewService.findStudentByParams(previewId, studentId);
					pps.setAnswer(answer);
					pps.setStatus(PrepPreviewStudent.STATUS_ANSWERED);
					pps.setTimeCost(timeCost);
					pps.setSubmitTime(new Date());
					previewService.saveOrUpdatePreviewStudent(pps);
					code=Code.SUCCESS;
				}
			}
		} catch (Exception e) {
			code=Code.ERROR;
			e.printStackTrace();
		}
		print();
		return SUCCESS;
	}
```

##### 5.全角空格trim

```java


public static String replaceBlank(String str) {
		String dest = "";
		if (str != null) {
			Pattern p = Pattern.compile("\\s*|\t|\r|\n");
			Matcher m = p.matcher(str);
			dest = m.replaceAll("");
			dest=dest.replace((char)12288, ' ').trim();
		}
		return dest;
	}

	public static void main(String[] args) {
		String str="　　";
		for (char c : str.toCharArray()) {
			System.out.println((int)c);
		}
		System.out.println();
	}

```

# JQuery

##### 1.click

```javascript
$("button").click(function(){
  $("p").slideToggle();
});
```

##### 2.mouseover,mouseout

```javasc
<script type="text/javascript">
var isClick = true;
var isAClick = true;
$(document).ready(function(){
	
	$(".examine").mouseover(function(){
		$(".analogDevices").hide();
		var temp=$(this).attr("id").substring(7);
		$("#analogDevices"+temp).show();
	}); 
	
	$(".examine").click(function(){
		 isClick = false; 
		var temp=$(this).attr("id").substring(7);
		$("#analogDevices"+temp).show();
	}); 
	
	$(".examine").mouseout(function(){
		 if(isClick) {
			var temp=$(this).attr("id").substring(7);
			$("#analogDevices"+temp).hide();
		 }
		 isClick = true;
	});  
	
	
	$(".examineAns").mouseover(function(){
		$(".analogDevicesAns").hide();
		var temp=$(this).attr("id").substring(10);
		$("#analogDevicesAns"+temp).show();
	}); 
	
	$(".examineAns").click(function(){
		 isAClick = false; 
		var temp=$(this).attr("id").substring(10);
		$("#analogDevicesAns"+temp).show();
	}); 
	
	$(".examineAns").mouseout(function(){
		 if(isAClick) {
			var temp=$(this).attr("id").substring(10);
			$("#analogDevicesAns"+temp).hide();
		 }
		 isAClick = true;
	});  
	
});
</script>	
```





# JSTL

##### 1.[JSTL 的 if else : 有 c:if 没有 else 的处理------<c:choose>]

```jsp
<c:choose>
   <c:when test="">如果
   </c:when>
   <c:otherwise>  否则
   </c:otherwise>
</c:choose>


<c:choose>
		<c:when test="${bean.featureCode eq '400003'}">
            <li style="font-size:16px">请点击空格，输入答案</li>
            <li class="question">${bean.questionContent }</li>
		</c:when>
		<c:when test="${bean.featureCode eq '400004' or bean.featureCode eq '500004' }">
			<li style="font-size:16px">答题区：框内为答题区</li>
			<li class="question" style="border:1px solid black">${bean.questionContent }</li>
		</c:when>  
        <c:otherwise>  
            <li class="question">${bean.questionContent }</li>
        </c:otherwise>
</c:choose>


<c:if test="${!empty bean.opts }">
    <c:forEach items="${bean.opts }" var="opt">
    	 <li class="list <c:if test="${bean.correctAnswer eq opt.optionTitle }">active</c:if>"><span id="mlOptionTitle" <c:if test="${bean.correctAnswer eq opt.optionTitle }">class="cur"</c:if>>${opt.optionTitle }</span><span id="mlOptionContent" >${opt.optionContent }</span></li>
    </c:forEach>
</c:if>

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



# React.js

```javasc
React 是一个用于构建用户界面的 JAVASCRIPT 库。

React 是一个用于构建用户界面的 JAVASCRIPT 库。
React主要用于构建UI，很多人认为 React 是 MVC 中的 V（视图）。
React 起源于 Facebook 的内部项目，用来架设 Instagram 的网站，并于 2013 年 5 月开源。
React 拥有较高的性能，代码逻辑非常简单，越来越多的人已开始关注和使用它。


React 特点
1.声明式设计 −React采用声明范式，可以轻松描述应用。
2.高效 −React通过对DOM的模拟，最大限度地减少与DOM的交互。
3.灵活 −React可以与已知的库或框架很好地配合。
4.JSX − JSX 是 JavaScript 语法的扩展。React 开发不一定使用 JSX ，但我们建议使用它。
5.组件 − 通过 React 构建组件，使得代码更加容易得到复用，能够很好的应用在大项目的开发中。
6.单向响应的数据流 − React 实现了单向响应的数据流，从而减少了重复代码，这也是它为什么比传统数据绑定更简单。

第一个实例 HelloWorld!
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Hello React!</title>
    <script src="https://cdn.bootcss.com/react/15.4.2/react.min.js"></script>
    <script src="https://cdn.bootcss.com/react/15.4.2/react-dom.min.js"></script>
    <script src="https://cdn.bootcss.com/babel-standalone/6.22.1/babel.min.js"></script>
  </head>
  <body>
    <div id="example"></div>
    <script type="text/babel">
      ReactDOM.render(
        <h1>Hello, world!</h1>,
        document.getElementById('example')
      );
    </script>
  </body>
</html>





React 安装
1.你可以在官网 http://facebook.github.io/react/ 下载最新版。
2.你也可以直接使用 BootCDN 的 React CDN 库
<script src="https://cdn.bootcss.com/react/15.4.2/react.min.js"></script>
<script src="https://cdn.bootcss.com/react/15.4.2/react-dom.min.js"></script>
<script src="https://cdn.bootcss.com/babel-standalone/6.22.1/babel.min.js"></script>

```

# Linux

##### 1.命令

```doc
cat /etc/issue    // 查看linux 版本
```



# Git

   Git是目前世界上最先进的分布式版本控制系统。 SVN是集中式版本控制系统，版本库是集中放在中央服务器的。

##### 1.git的使用

```doc
pwd 命令是用于显示当前的目录。
/D/TeLuoYiArvin/notes

git init 
把这个目录变成git可以管理的仓库。目录下会多了一个.git的目录，这个目录是Git来跟踪管理版本的

step1
git add .
git add readme.txt添加到暂存区里面去

step2
git commit -m "modify code"
git commit告诉Git，把文件提交到仓库。

git status
通过命令git status来查看是否还有文件未提交

git diff readme.txt
看下readme.txt文件到底改了什么内容

git log
readme.txt文件做了三次修改了，那么我现在想查看下历史记录

git log --pretty=oneline 
查看版本库所有提交的版本记录。

git reset  --hard HEAD^
把当前的版本回退到上一个版本
查看下 readme.txt内容如下：通过命令cat readme.txt查看

git reset  --hard HEAD^^
把当前的版本回退到上上一个版本

git reset  --hard HEAD~100
回退到前100个版本


回退到最新的版本 通过版本号回退
获取到版本号：git reflog    
结果：6fcfc89
git reset  --hard 6fcfc89 来恢复了


    工作区：就是你在电脑上看到的目录，比如目录下testgit里的文件(.git隐藏目录版本库除外)。或者以后需要再新建的目录文件等等都属于工作区范畴。

    版本库(Repository)：工作区有一个隐藏目录.git,这个不属于工作区，这是版本库。其中版本库里面存了很多东西，其中最重要的就是stage(暂存区)，还有Git为我们自动创建了第一个分支master,以及指向master的一个指针HEAD。
    Git提交文件到版本库有两步：
      第一步：是使用 git add 把文件添加进去，实际上就是把文件添加到暂存区。
      第二步：使用git commit提交更改，实际上就是把暂存区的所有内容提交到当前分支上。

撤销修改
git checkout  --  readme.txt
把readme.txt文件在工作区做的修改全部撤销

rm 	b.txt
直接在文件目录中把文件删了
彻底从版本库中删掉
git rm b.txt











```





# Validation

# soft

##### 1.Zeplin   用于 产品UI交互图



