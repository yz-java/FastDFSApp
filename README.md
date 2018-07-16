#FastDFSApp

[TOC]

##配置文件：（resources目录下）

### config.json

```
{
  "ip":"47.98.55.122",//文件服务器域名或IP
  "port":8888 //文件服务器端口
}
```
### fdfs_client.conf

```
connect_timeout = 2
network_timeout = 30
charset = UTF-8
tracker_server = 47.98.55.122:22122 //配置FastDFS跟踪服务器
```


## 接口使用说明

### **文件上传**

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

### **文件上传**

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

### **文件删除**

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


