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

##### 6.分割，截取字符串

```java
1>分割  split()
  String str = "abc,12,3yy98,0";
  String[]  strs=str.split(",");
  for(int i=0,len=strs.length;i<len;i++){
      System.out.println(strs[i].toString());
  }

2>截取  subString()  
  	i.只传一个参数 : 
   	String sb = "bbbdsajjds";
    sb.substring(2);
	将字符串从索引号为2开始截取，一直到字符串末尾。（索引值从0开始）； 
     //bdsajjds
     
    ii.传入2个索引值:
	String sb = "bbbdsajjds";
	sb.substring(2, 4);
	从索引号2开始到索引好4结束（并且不包含索引4截取在内，也就是说实际截取的是2和3号字符）； 
	//bd
      
    iii.通过StringUtils提供的方法： 
    StringUtils.substringBefore(“dskeabcee”, “e”); 
    /结果是：dsk/ 
    这里是以第一个”e”，为标准。
	StringUtils.substringBeforeLast(“dskeabcee”, “e”) 
    结果为：dskeabce 
    这里以最后一个“e”为准。
      
    Vi.截取两个标签之间字符串 ：
   public static void main(String[] args) {
			String str = "<img src='www.baidu.com/a'/>333sad2f444 <img src='www.baidu.com/b'/>";
	        String regex = "333(.*)444";
	        Pattern pattern = Pattern.compile(regex);
	        Matcher matcher = pattern.matcher(str);//匹配类
	        while (matcher.find()) {
	            System.out.println(matcher.group(1));//打印中间字符
	        }
	}


	String rightContent[] =  title.split(map.get("identifyingRightBracket"));
	String leftContent[] =  rightContent[0].split(map.get("identifyingLeftBracket"));
	logger.info("打印中间字符----"+leftContent[1]);
									
  
  
```

##### 7.List 排序

```java
i.方式一:直接实现匿名类方法

Collections.sort(questionList,new Comparator<Question>(){

			@Override
			public int compare(Question o1, Question o2) {
				// 默认升序
				return o1.getAuxiliaryNumber().compareTo(o2.getAuxiliaryNumber());
			}
});

Collections.sort(questionList,new Comparator<Question>(){

			@Override
			public int compare(Question o1, Question o2) {
				// 默认升序
				int tempAuxiliaryNumber = o1.getAuxiliaryNumber().compareTo(o2.getAuxiliaryNumber());
				if(tempAuxiliaryNumber==0){
                  // 默认降序
				return o2.getSequence().compareTo(o1.getSequence());
				}
				return tempAuxiliaryNumber;
			}
});

ii.方式二：实现类
  
package com.ahhf.ljxbw;

public class User implements Comparable<User> {

	
	private Integer age ;
	private Integer mark;
	private String userName;
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Integer getMark() {
		return mark;
	}
	public void setMark(Integer mark) {
		this.mark = mark;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Override
	public int compareTo(User o) {
		// 默认为升序  this.getMark().compareTo(o.getMark()); 反之为降序
		//return this.getMark().compareTo(o.getMark());
		// 第一字段 以mark desc
		int  temp = o.getMark().compareTo(this.getMark());
		if(temp==0){
			// 第二字段 以age desc
			return this.getAge().compareTo(o.getAge());
		}
		return temp;
	}
	@Override
	public String toString() {
		return "User [age=" + age + ", mark=" + mark + ", userName=" + userName + "]";
	}
	
	
}

package com.ahhf.ljxbw;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

public class SortMain {

	@Test
	public void test01() {
		User u1 = new User();
		u1.setAge(10);
		u1.setMark(100);
		u1.setUserName("u1");
		User u2 = new User();
		u2.setAge(11);
		u2.setMark(100);
		u2.setUserName("u2");
		User u3 = new User();
		u3.setAge(10);
		u3.setMark(98);
		u3.setUserName("u3");

		List<User> ulist = new ArrayList<User>();
		ulist.add(u1);
		ulist.add(u2);
		ulist.add(u3);
		Collections.sort(ulist);
		for (User user : ulist) {
			System.err.println(user.toString());
		}

	}

	

}


```

##### 8.compareTo() 方法的使用

```java
compareTo() 方法用于将 Number 对象与方法的参数进行比较。可用于比较 Byte, Long, Integer等。
该方法用于两个相同数据类型的比较，两个不同类型的数据不能用此方法来比较。由于比较的变量我用的是int，int型可以直接比较，所有没有用到compareTo比较，如果声明的是Date、String、Integer或者其他的，可以直接使用compareTo比较，compareTo方法内必须做非空判断（规范问题），当然int类型就不用了。
注意事项：

        1模型必须实现Comparable<T>接口

        2Collections.sort(list);会自动调用compareTo，如果没有这句，list是不会排序的，也不会调用compareTo方法

        3如果是数组则用的是Arrays.sort(a)方法
  
语法：
public int compareTo( NumberSubClass referenceName )

参数：
referenceName -- 可以是一个 Byte, Double, Integer, Float, Long 或 Short 类型的参数。
  
返回值： 
如果指定的数与参数相等返回0。
如果指定的数小于参数返回 -1。
如果指定的数大于参数返回 1。
  
 public class Test{ 
   public static void main(String args[]){
      Integer x = 5;
      System.out.println(x.compareTo(3)); // 1
      System.out.println(x.compareTo(5)); // 0
      System.out.println(x.compareTo(8)); // -1           
     }
}

  
```



# JQuery

```DOC
JQuery api  http://www.jquery123.com/
```



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

### url

```javascript
js特效代码网---------------http://www.jstxdm.com/
```



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

##### 2.禁止页面滚动

```javasc
1. pc端实现
$('body').css('overflow','hidden');//浮层出现时窗口不能滚动设置
$('body').css('overflow','auto');// 浮层关闭时滚动设置

2.移动端实现
var preHandler=function(e){e.preventDefault();},// 注意此处代码片段必须这样提出来已保证传入下边两个事件的处理程序一样才生效，分别写到事件处理程序中不生效。
document.addEventListener('touchmove', me.preHandler,false);//阻止默认滑动事件
document.removeEventListener('touchmove', me.preHandler, false);//浮层关闭时解除事件处理程序

3.浮层实现效果
.mask{
    position: fixed;
    top:0;
    left:0;
    width: 100%;
    height: 100%;
    background-color: rgba(0,0,0,0.7);
    z-index: 99;
}
.box-introduce-content{ // 浮层中内容上下左右居中实现
    position: absolute;
    margin: auto;
    top: 0;
    bottom: 0;
    left: 0;
    right: 0;
    padding: 0 0.15rem;
    height: 4.23rem;
}
```

##### 3.鼠标放到控件标签上有提示

```javasc
<li title="提示" />
```



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

##### 2.java.lang.NoSuchFieldException: resourceEntries

```java
JSP表单里面的表单输入
<input type= "text" name="user">这里面的每一个输入都是一个Attribute，相当于setAttribute("name",user);

如果是提交到Action里面，则需要相应的Action有对应的同名变量定义和setter/getter方法，即使你没有用它做任何操作。

Action里面的提供Setter/Getter的方法会将其取出来交给execute方法。
如：
type varname;

public String getVarname() {
return varname;
}

public void setVarname( type varname) {
this.varname = varname;
}
set方法负责设置，get方法负责取出来，交给Action。

出现这种错误的提示，一般都是因为：表单提交的页面的变量在Action里面没有相应的变量定义，也没有相应的setter/getter方法。
具体可以分析源码。
```



# SQL

##### 1.sql的表添加字段

```sql
alter table t_question_property add type int(11);
```

##### 2.sql的表修改字段类型

```sql
ALTER TABLE t_question_property modify COLUMN type varchar(200);
```

##### 3.sql的表更新记录

```sql
update t_question_property  set type='error'  where 1=1;
```

##### 4.sql的表添加记录

```sql
INSERT INTO Persons (LastName, Address) VALUES ('Wilson', 'Champs-Elysees');
```

##### 5.sql的表删除记录

```sql
DELETE FROM Person WHERE LastName = 'Wilson' 
```

##### 6.sql的表查询记录

```sql
select * from t_question order by _id desc limit 10;
```





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

# Maven

##### 1.run -->maven install 常见jdk,jre的错误

```doc
解决方式：Windows--->preferences -->java---> Installed JREs---->editor ---->jdk
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

##### 2.回退版本

````doc
1.本地分支版本回退的方法
先用下面命令找到要回退的版本的commit id：
git reflog 
接着回退版本:
git reset --hard Obfafd
0bfafd就是你要回退的版本的commit id的前面几位


2.自己的远程分支版本回退的方法
如果你的错误提交已经推送到自己的远程分支了，那么就需要回滚远程分支了
首先要回退本地分支：
git reflog
git reset --hard Obfafd
紧接着强制推送到远程分支：
git push -f
注意：本地分支回滚后，版本将落后远程分支，必须使用强制推送覆盖远程分支，否则无法推送到远程分支


3.公共远程分支版本回退的问题
git revert HEAD                     //撤销最近一次提交
git revert HEAD~1                   //撤销上上次的提交，注意：数字从0开始
git revert 0ffaacc                  //撤销0ffaacc这次提交
git revert 命令意思是撤销某次提交。它会产生一个新的提交，虽然代码回退了，但是版本依然是向前的，所以，当你用revert回退之后，所有人pull之后，他们的代码也自动的回退了。 


http://blog.csdn.net/fuchaosz/article/details/52170105

````

##### 3.branch

```doc
git branch
git branch 不带参数：列出本地已经存在的分支，并且在当前分支的前面加“*”号标记，
git branch -r 列出远程分支
git branch -a 列出本地分支和远程分支
git branch 创建一个新的本地分支，需要注意，此处只是创建分支，不进行分支切换
git branch newbranch2
git branch -m | -M oldbranch newbranch 重命名分支，如果newbranch名字分支已经存在，则需要使用-M强制重命名，否则，使用-m进行重命名。
git branch -d | -D branchname 删除branchname分支
git branch -d -r branchname 删除远程branchname分支
git checkout newbranch2 切换分支

```

##### 4.merge

````doc
git merge 用来做分支合并，将其他分支中的内容合并到当前分支中。比如分支结构如下：

                        master
                         /
C0 ---- C1 ---- C2 ---- C4
                         \
                         C3 ---- C5
                                  \
                                issueFix
当前分支是master
$ git checkout master

把issueFix中的内容Merge进来：
$ git merge issueFix

如果没有冲突的话，merge完成。有冲突的话，git会提示那个文件中有冲突，比如有如下冲突：

<<<<<<< HEAD:test.c

printf (“test1″);

=======

printf (“test2″);

>>>>>>> issueFix:test.c

可以看到 ======= 隔开的上半部分，是 HEAD（即 master 分支，在运行 merge 命令时检出的分支）中的内容，下半部分是在 issueFix 分支中的内容。解决冲突的办法无非是二者选其一或者由你亲自整合到一起。比如你可以通过把这段内容替换为下面这样来解决：

printf (“test2″);

这个解决方案各采纳了两个分支中的一部分内容，而且删除了 <<<<<<<，=======，和>>>>>>> 这些行。在解决了所有文件里的所有冲突后，运行 git add 将把它们标记为已解决（resolved）。因为一旦暂存，就表示冲突已经解决。如果你想用一个有图形界面的工具来解决这些问题，不妨运行 git mergetool，它会调用一个可视化的合并工具并引导你解决所有冲突


````



# Chrome 帐号

zhuwenjin1221@gmail.com    









# GitHub

```doc
开源项目
https://github.com/spring-projects
https://github.com/hibernate/
https://github.com/apache/maven

```





# API

##### all-api   http://tool.oschina.net/apidocs





# Validation

# soft

##### 1.Zeplin   用于 产品UI交互图



# OOP DOC

##### 1.第一章  系统架构

```doc
1.文档概述
本文档是对XXX系统第一期项目架构设计进行阐述，主要从整体架构、逻辑架构、接口设计等各方面详细说明了本系统各架构维度的内容，期望读者为项目管理人员、架构管理人员、运维人等，在编制过程中参考了各公司需求书、架构设计规范等。
2.整体架构
2.1.技术架构及特点
1)采用目前安全性能高，扩展性好，框架技术最为完善JAVA语言作为系统开发的语言
2)采用SSM框架技术，使系统能够分层开发，各层之间逻辑分明，层与层之间提供接口方式来实现业务和数据的沟通。让系统具有极强的扩展性
3)采用MAVEN自动化的构建工具来管理项目。诸如测试、打包，而且更重要的是它将这些原本各自独立的任务串到了一起，让他们能够自动化的可视化的运行。
4)基于组件技术，力求将变化封装在组件内部。对应组件可以对外提供API。
5)适应能力强，同步、异步都能处理，既能满足快速反映的业务的需求，又能满足大数据量、复杂的、异步的业务的需求
6)具有很好的可扩展性。模块化使得系统很容易在纵向和水平两个方向拓展：一方面可以将系统升级为更大、更有力的平台，同时也可以适当增加规模来增强系统的网络应用。在扩充或修改功能时，基本不会破坏原有结构的稳定性。
7)强而可靠的数据库搜索引擎
8)支持分布式部署的缓存引擎
9)前后端分离

3.逻辑架构
3.1.模块组件化
系统采用MAVEN管理各个模块，框架分为九大模块

1)系统支撑模块（support）
支撑整个系统架构的核心模块,主要包括日志的处理、权限的控制、配置文件的管理等。
2)系统框架模块（framework）
     系统框架模块，主要封装一些框架常用的工具。
3)公共模块（common）
存放系统公用资源。包括短信、推送、邮件、缓存等。
4)系统权限模块(permission)
独立、复用。采用shiro来管理系统的权限，颗粒度可控制至某个按钮。
5)持久对象模块（repository）
该模块是存放持久化对象，通常是与数据库映射的POJO;当然也可以是其他对象，视图对象（VO）或者数据对象（DTO）等。
6)数据访问模块（dao）
专门用来封装我们对于实体类的数据库的访问，就是增删改查，不加业务逻辑。
7)业务处理模块（service）
业务处理模块，可以当成”系统总线”,聚合下面各种server(DB、redis)、file、network(SOA服务)，同时包含核心业务处理逻辑，并统一为上层view（可以是web，也可以是gui、cmd等）提供服务。
8)请求控制模块（controller）
负责具体的业务模块流程的控制，在此层里面要调用service层的接口来控制业务流程，控制的配置也同样是在Spring的配置文件里面进行，针对具体的业务流程，会有不同的控制器，我们具体的设计过程中可以将流程进行抽象归纳，设计出可以重复利用的子单元流程模块，这样不仅使程序结构变得清晰，也大大减少了代码量。
9)移动端模块（mobile）
对移动端提供数据接口

4.接口设计
4.1.简述
为了方便沟通交流，以方便多方明确本项目的主要需求功能及工作方向，特编写此文档，为设计人员、开发人员、测试人员提供参考。接口采用restful协议，入参和出参均是标准的JSON格式，POST的方式提交请求。
4.2.Rest介绍
4.2.1.URL规则
http://host/m/{uri}
uri: 具体请求的uri

![aad](C:\Users\wenjin.zhu\Desktop\aad.png)

5.数据架构
对于平台中的所有应用，都存在着各种各样的配置信息、业务数据、系统运行状态等信息。数 据库层对这些数据信息本身进行归档，提供快速查询的底层接口，并保证数据的完整性、可靠性。
在数据库方面，我们的程序是可以无缝对接主流数据库的包括Oracle，Mysql，和MSSql，但建议采用Oracle 或者是 MySQL。因为他们和程序一样具备跨平台的特性，但这两种数据库分别有不同的适用环境。 
5.1.Oracle       
1)适合大型的电子商务应用。      
2) 能使用所有的商城平台功能。如访问量排行榜、降价排行榜、销售排行榜、访问量统计等等，就以往的经验来看，这些功能每天要处理的数据都会超过一千万。这种数据量如果使用传统的统计方法，统计一次的时间就可能会以天来计算了。如果使用Oracle，再配合我们专门对Oracle进行优化的高效率的统计程序，那么只需十数秒便可完成。     
3) 此外，如果只用一台数据库无法应付日益增长的服务器访问量，可以使用Oracle RAC（真正应用集群），通过增加数据库服务器进行集群。  
5.2.MySql       
1)适合中小型的电子商务应用。      
 无法使用商城平台一些高级应用，如排行榜、统计分析、MIS系统对接等。这是由于MySQL设计上强调访问速度，牺牲了一些数据库的高级功能，虽然用程序也可以实现这些功能，但是在性能上无法满足需求。     
2)比较难实现数据库集群。


6.部署架构
6.1.系统环境
1)Tomcat
apache-tomcat-8.0+
2)JDK
jdk1.8+
3)Nginx
nginx-1.13.2
4)Redis
redis-2.0+

```

##### 2.第二章  编程规范

```doc

§1.1命名规范
1、命名规范必须符合java规范
2、命名必须有根据实际业务场景切合
3、禁止使用拼音、更不允许直接使用中文。
4、方法名、参数名、成员变量、局部变量必须使用lowerCameCase风格、		必须尊从驼峰形式
5、常量必须全部大写、单词之间用_分隔，力求语义表达完整清楚。
6、接口类中的方法和属性不要加任何修身符号，保持代码间接性，并加上		有效的javaDoc注释。
7、接口和实现类的命名，实现类必须是Impl后缀与接口区别。
数据对象DO结尾、数据传输对象DTO结尾、展示对象VO结尾、进制使	用POJO POJO、领域内可以使用Domain	

§1.2注释规范
需按照javaDoc规范书写注释。参数名、返回参数、方法或者参数意义。

§1.3异常处理
1、异常不要用来做流程控制,条件控制,因为异常的处理效率比条件分支低。
2、大段代码使用try-catch是不负责任的表现，catch请分清稳定代码和		非稳定代码。
3、不能在finally块中使用return。
4、捕获异常与抛异常,必须是完全匹配,或者捕获异常是抛异常的父类。
 
§1.4日志规范
不可直接使用日志系统，应该依赖使用日志框架，使用门面模式的日志框架，有利于维护和日志统一处理。

§1.5应用分层
系统分三层模式。
Web 层:主要是对访问控制进行转发,各类基本参数校验,或者不复用的业务简单处理等。  
Service 层:相对具体的业务逻辑服务层。  
Manager 层:通用业务处理层,它有如下特征: 1) 对第三方平台封装的层,预处理返回结果及转化异常信息; 2) 对Service层通用能力的下沉,如缓存方案、中间件通用处理; 3) 与DAO层交互,对DAO的业务通用能力的封装。  
DAO 层:数据访问层,与底层 MySQL、Oracle、Hbase 进行数据交互。  
外部接口或第三方平台:包括其它部门 RPC 开放接口,基础平台,其它公司的 HTTP 接口

```



##### 3.第三章数据库规范

````doc

§1.6表的分类
数据库中的表可以分为三类：字典表，业务数据主记录表，业务数据关联表。

§1.7通用规则
不必强求遵循数据库设计范式，根据具体情况灵活调整，尽量做到3NF，对于不遵循设计范式的设计建议评审）
字符集采用UTF8
表名、视图和字段名都用小写下划线写法，并且以下划线结尾。不要用引号避免大小写敏感。
系统级表采用sys_开头。
为了避免null的扩散照成的系统稳定，现在要把所有的字段都设置默认值或使用非空。
在数据库中所有字段都有默认值，各种默认值应尽量与产品经理商定，与业务逻辑一致（例如新建合同时默认的应退金额是0）。
不用物理删除。

§1.8字典表
用于存放一些基础信息和配置信息，不会随着业务进行而改变。

§1.9表名
表名采用下划线写法，英文命名（非拼音），无需采用复数形式。表名前是否需要加所归属模块名？还是通过schema

§1.10业务字段
参与业务逻辑和作为查询条件的字段才需要在表中专门建立一个对应的字段，一些只用于用户浏览而不作为查询条件和业务逻辑的其他字段可以放入JSON_简单处理。如果有外键关系尽量将字段名设置为‘表名+字段名’
对于不参与业务和查询条件的数据，只是用来展现的数据无需专字段来存放（这些字段会变动并且不同事业部可能会不同）可由前端在新增/修改时将整个表单转成json发给后端存放起来。
§1.11备份表
备份表的命名采用原表名+备份时间，对于无用的备份表尽快删除。

§1.12索引
索引命名采用"idx_"+表名+字段名
视图、触发器、存储过程、函数
    视图名尽量使用V_开头，其他参考表的规范
    不要自定义触发器
尽量不要使用存储过程、函数
````



