#FastDFSApp
##配置文件

![image.png](http://upload-images.jianshu.io/upload_images/3057341-7228c0733d6a6d78.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![image.png](http://upload-images.jianshu.io/upload_images/3057341-06c08ee7a96d01e5.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

## 接口使用说明

### **文件上传** &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="font-size:16px"></span>

- **请求URL**
> [/file/upload]()

- **请求方式**
> POST(文件表单)

- **请求参数**
>
|参数名称|参数类型|参数说明|
|:----:|:----:|:----:|
|file|File|可上传多个文件|

- **返回示例**
>
```
{"status":0,"data":["http://106.38.75.114:8083/group1/M00/00/34/wKgBeFmQi36AQt0VADEzdLuXaCQ748.pdf"]}
```

### **文件上传** &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="font-size:16px"></span>

- **请求URL**
> [/file/upload_by_url]()

- **请求方式**
> POST(form表单) 或 AJAX

- **请求参数**
>
|参数名称|参数类型|参数说明|
|:----:|:----:|:----:|
|fileUrl|String|文件url|
|fileType|String|文件类型|

- **返回示例**
>
```
{"status":0,"data":"http://106.38.75.114:8083/group1/M00/00/34/wKgBeFmQi36AQt0VADEzdLuXaCQ748.pdf"}
```

### **文件删除** &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="font-size:16px"></span>

- **请求URL**
> [/file/delete]()

- **请求方式**
> POST(form表单) 或 AJAX

- **请求参数**
>
|参数名称|参数类型|参数说明|备注|
|:----:|:----:|:----:|:----:|
|fileId|String|文件ID|示例：http://106.38.75.114:8083/<span style="color:red;">group1/M00/00/00/wKgB3FmN3-2AGRYJAAYt9cJlT_U201.pdf</span>上传红色字体部分 |

- **返回示例**
>
```
{"status":0}
```


