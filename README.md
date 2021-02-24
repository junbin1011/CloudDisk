# CloudDisk
## 项目概述 

CloudDisk是一个类似于Google Drive的云存储应用。该应用主要拥有3大核心业务模块。

1. 文件模块-用于管理用户云端文件系统。用户能够上传、下载、浏览文件。
2. 动态模块-类似微信朋友圈，用于可以在动态上分享信息及文件
3. 个人中心模块-用于管理用于个人信息

## 问题说明

该项目已经维护超过10年以上，目前有用开发人员100+。目前代码在一个大module中，约30w行左右，编译时间5分钟以上。团队目前主要面临几个问题。

1. 开发效率低，编译时间长，经常出现代码合并冲突
2. 代码质量差，经常修改出新问题
3. 市场响应慢，需要对齐各个模块进行整包发布

## 代码分析

单一个独立的module，采用MVC结构，按功能进行划分package。

包结构如下：

![](https://note.youdao.com/yws/api/personal/file/WEBe0fb28d0717ad58af3ddae9bc36db7f0?method=download&shareKey=2c90e702d3dc6ca710e703c39153c76b)

主要包说明：

包名 | 功能说明
---|---
adapter| Viewpager RecycleView等适配器类
callback| 接口回调
controller|主要的业务逻辑
model|数据模型
ui|Activity、Fragment相关界面
util|公用工具类

主要类说明：

类名 | 功能说明
---|---
MainActivity| 应用主界面，用于加载显示各个模块的Fragment
CallBack | 网络接口操作回调
DynamicController|动态模块主要业务逻辑，包含发布及获取列表
FileController|文件模块主要业务逻辑，主要包含上传、下载、获取文件列表
UserController|用户模块主要业务逻辑，主要包含登录，获取用户信息
HttpUtils|网络请求，用于发送get及post请求
LogUtils|主要用于进行日志记录
