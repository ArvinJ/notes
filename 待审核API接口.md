# 待审核API接口

## 1.获取所有的待审核题目列表

```json
 请求url : http://192.168.86.33:8080/HomeworkManagement/managersExamine/findExamineQuestions.action
 
 请求方式：post

 请求参数：
 ---- default -----(int 和 long 类型  默认值为0，String类型 默认为""。)
{
    "pageInfo": {
        "pageSize": 10,
        "pageIndex": 1
    },
    "schoolId": 0,
    "userId": 0,
    "gradeId": 0,
    "subjectId": 0,
    "versionIndex": 0,
    "questionType": 0,
    "status": 0,
    "startDate": "",
    "endDate": ""
}

例：
{
    "pageInfo": {
        "pageSize": 10,
        "pageIndex": 1
    },
    "schoolId": 68,
    "userId": 71336,
    "gradeId": 53,
    "subjectId": 1,
    "versionIndex": 10001,
    "questionType": 1,
    "status": 1,
    "startDate": "2017-08-07",
    "endDate": "2017-10-10"
}

响应：
{
    "code": "0000",
    "message": "成功",
    "details": [
        {
            "analsis": "&nbsp;&nbsp;",
            "analsisThumb": "",
            "schoolName": "上海实验小学",
            "sequence": 4344644,
            "solutionContentDraft": "",
            "source": "",
            "status": 1,
            "statusStr": "待审核",
            "subjectId": 1,
            "subjectName": "语文",
            "subjecttiveQuestion": false,
            …………………………
            },
            "updateDateStr": "2017-08-29 14:35:38"
        },
        {
            "analsis": "&nbsp;&nbsp;",
          …………………………
        }
      ]
}

```

## 2.筛选中用户、年级、科目、教材版本、题型、属性集合以及题目通过与详情接口

```json
 请求url : http://192.168.86.33:8080/HomeworkManagement/managersExamine/findAllChildrenDataById.action
 
 请求方式：post

 请求参数：
 ---- default -----(int 和 long 类型  默认值为0，String类型 默认为""。)

{
    "statusValue": 1,  // 1 获取所有录题用户集合;
  
    "id": 0  // 默认为0
}

响应：{
    "code": "0000",
    "message": "成功",
    "details": [
        {
            "id": 87704,
            "username": "msdevadmin"
        },
        {
            "id": 87705,
            "username": "mstestadmin"
        },
        {
            "id": 1211231347,
            "username": "100999"
        },
        {
            "id": 1211231659,
            "username": "timu001"
        }
    ]
}
-------------------------------------------------------------------------------------------
{
    "statusValue": 2,  // 2 获取schoolId=68该学校的所有年级集合;
  
    "id": 68  // schoolId=68 学校id。
}

响应：{
    "code": "0000",
    "message": "成功",
    "details": [
        {
            "gradeName": "一年级",
            "id": 53
        },
        {
            "gradeName": "二年级",
            "id": 54
        },
        {
            "gradeName": "三年级",
            "id": 55
        },
        {
            "gradeName": "四年级",
            "id": 56
        },
        {
            "gradeName": "五年级",
            "id": 57
        },
        {
            "gradeName": "六年级",
            "id": 58
        },
        {
            "gradeName": "初一",
            "id": 59
        },
        {
            "gradeName": "初二",
            "id": 60
        },
        {
            "gradeName": "初三",
            "id": 61
        },
        {
            "gradeName": "高二",
            "id": 63
        },
        {
            "gradeName": "高三",
            "id": 64
        },
        {
            "gradeName": "毕业",
            "id": 65
        },
        {
            "gradeName": "edddff",
            "id": 636
        }
    ]
}
-------------------------------------------------------------------------------------------
{
    "statusValue": 3,  // 3 获取schoolId=68该学校的所有科目集合;
  
    "id": 68  // schoolId=68 学校id。
}
响应：{
    "code": "0000",
    "message": "成功",
    "details": [
        {
            "subjectId": 1,
            "subjectName": "语文"
        },
        {
            "subjectId": 2,
            "subjectName": "数学"
        },
        {
            "subjectId": 3,
            "subjectName": "英语"
        },
        {
            "subjectId": 4,
            "subjectName": "物理"
        },
        {
            "subjectId": 5,
            "subjectName": "地理"
        },
        {
            "subjectId": 6,
            "subjectName": "历史"
        },
        {
            "subjectId": 7,
            "subjectName": "生物"
        },
        {
            "subjectId": 8,
            "subjectName": "化学"
        },
        {
            "subjectId": 9,
            "subjectName": "政治"
        },
        {
            "subjectId": 10,
            "subjectName": "美术"
        },
        {
            "subjectId": 11,
            "subjectName": "音乐"
        },
        {
            "subjectId": 13,
            "subjectName": "体育"
        }
    ]
}
-------------------------------------------------------------------------------------------
{
    "statusValue": 4,  // 4 获取所有教材版本集合;
  
    "id": 0  // 默认为0
}
响应：{
    "code": "0000",
    "message": "成功",
    "details": [
        {
            "paramName": "人教版",
            "paramValue": "10001"
        },
        {
            "paramName": "北师大版",
            "paramValue": "10003"
        },
        {
            "paramName": "沪教版",
            "paramValue": "10004"
        },
        {
            "paramName": "苏教版",
            "paramValue": "10005"
        },
        {
            "paramName": "湘教版",
            "paramValue": "10006"
        },
        {
            "paramName": "大纲版",
            "paramValue": "10007"
        }
    ]
}
-------------------------------------------------------------------------------------------
{
    "statusValue": 5,  // 5 获取所有题型集合;
  
    "id": 0  // 默认为0
}
响应：{
    "code": "0000",
    "message": "成功",
    "details": [
        {
            "questionType": 1,
            "questionTypeName": "单选题"
        },
        {
            "questionType": 2,
            "questionTypeName": "多选题"
        },
        {
            "questionType": 3,
            "questionTypeName": "判断题"
        },
        {
            "questionType": 4,
            "questionTypeName": "填空题"
        },
        {
            "questionType": 5,
            "questionTypeName": "简答题"
        }
    ]
}
-------------------------------------------------------------------------------------------
{
    "statusValue": 6,  // 6 通过questionId=4343815 设置审核状态通过;
  
    "id": 4343815  // 题目的questionId
}

响应：{
    "code": "0000",
    "message": "成功",
    "details": true
}
-------------------------------------------------------------------------------------------
{
    "statusValue": 7,  // 7 通过questionId=4343815 查看题目详情;
  
    "id": 4343815  // 题目的questionId
}


响应：
{
    "code": "0000",
    "message": "成功",
    "details": {
        "analsis": "&nbsp;&nbsp;",
        "analsisThumb": "",
        "analsisType": 0,
        "answerFills": "",
        "answerNum": 0,
        "answerPerch": "",
        "arrangeTimes": 0,
        "autoFlag": 0,
        "auxiliaryNumber": 0,
        "catalog": null,
        "catalogName": "大家",
        "catalogs": [],
        "childrenQuestionJson": "",
        "childrens": [],
        "choiceQuestion": false,
        "classCourseQuestion": null,
        "contentType": 0,
        "correctAnswer": "&nbsp;&nbsp;",
        "correctRate": 0,
        "createDate": {
            "date": 27,
            "day": 3,
            "hours": 19,
            "minutes": 35,
            "month": 8,
            "nanos": 0,
            "seconds": 29,
            "time": 1506512129000,
            "timezoneOffset": -480,
            "year": 117
        },
        "createDateFormat": "2017-09-27 19:35:29",
        "createUserId": 71336,
        "createUsername": "msadmin",
        "difficulty": 3,
        "difficultyStr": "一般",
        "displaySource": "",
        "doTimes": 0,
        "featureCode": "500004",
        "featureCodeDescStr": "图片解答+题干作答+非自动批改",
        "gradeId": 1,
        "gradeName": "一年级",
        "id": 4343815,
        "isPublic": 1,
        "isPublicStr": "公有",
        "judgeCollect": 0,
        "judgmentQuestion": false,
        "keyPoint": "",
        "optionNum": 0,
        "opts": [],
        "questionContent": "<style>table{border-collapse:collapse;} td{ border:1px solid black;}</style></p><p class=\"p1\" style=\"text-align:justify;hyphenate:auto;font-family:宋体;font-size:10pt;\">（500004图片解答+题干作答+非自动批改）</p><p class=\"p1\" style=\"text-align:justify;hyphenate:auto;font-family:宋体;font-size:10pt;\"><img src=\"http://wtk-001.oss-cn-beijing.aliyuncs.com/datas/image/wheatek20170922150913100a6.png\" style=\"width:5.7625in;height:5.876389in;vertical-align:text-bottom;\"/>&nbsp;&nbsp;&nbsp;",
        "questionContentThumb": "",
        "questionProperty": null,
        "questionRate": 0,
        "questionResultId": 0,
        "questionType": 5,
        "questionTypeStr": "",
        "reportDate": null,
        "reportReason": "",
        "reportor": "",
        "res": [],
        "schoolId": 68,
        "schoolName": "上海实验小学",
        "sequence": 4343815,
        "solutionContentDraft": "",
        "source": "",
        "status": 3,
        "statusStr": "已审核",
        "subjectId": 1,
        "subjectName": "语文",
        "subjecttiveQuestion": true,
        "totalScore": 0,
        "updateDate": {
            "date": 11,
            "day": 3,
            "hours": 15,
            "minutes": 34,
            "month": 9,
            "nanos": 0,
            "seconds": 41,
            "time": 1507707281000,
            "timezoneOffset": -480,
            "year": 117
        },
        "updateDateStr": "2017-10-11 15:34:41"
    }
}
```



## 3.驳回题目

  ```json
请求url : http://192.168.86.33:8080/HomeworkManagement/managersExamine/rejectQuestion.action 
 
 请求方式：post

 请求参数：
{
    "questionId": 4343815,  // 题目id
    "userId": 87704, // 审核人id
    "backDetails": "题型错误;答案错误;答案内容为A" // 驳回的类型拼接上驳回详情
}

响应：
{
    "code": "0000",
    "message": "成功",
    "details": true
}
  ```

