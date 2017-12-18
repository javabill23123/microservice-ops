# microservice-ops
微服务运维平台，基于Spring Cloud实现

## 包含功能
主要用于代理微服务项目与业务无关的部分包括  
1.认证  
2.权鉴  
3.限流  
4.动态路由  
5.动态filter  
6.服务监控（基于springbootAdmin，eureka，pinpoint）  
7.消息监控（与[消息中间件](https://github.com/yonyou-auto-dev/microservice-mom)项目配合使用）  
8.任务中心  
9.微信接口



## 整体架构
![](https://github.com/yonyou-auto-dev/microservice-ops/blob/master/model.jpg)

**绿色部分为运维平台会代理的部分从左向右，所有请求会经过网关统一认证，鉴权，限流后再转发到相应的业务模块。**

**蓝色部分是微服务的基础server层，参考这个[server项目](https://github.com/yonyou-auto-dev/microservice-server)**

## 模块大致说明（从左向右）

* **平台UI部分**，参考 [这个项目](https://github.com/yonyou-auto-dev/microservice-ops-ui)，使用VUE+ELementUI实现运维的界面，前后端分离。

* **网关服务 ops-gate：**  
	基于SpringCloud Netflix Zuul开发，实现了基于JWT的权限认证(也支持业务自行认证，只由网关代理JWT)、URL鉴权、动态路由、动态filter、认证忽略、权限判断。
	
* **权限服务 ops-auth：**  
	网关通过调用它来实现对应的认证功能，运营系统的身份认证的逻辑在该服务中实现，支持其它系统的认证代理(后台做相关配置即可)，同时支持服务之间的调用时的鉴权（暂未release）。  
	
* **消息监控 ops-mq：**  
	与[消息中间件](https://github.com/yonyou-auto-dev/microservice-mom)项目配合使用，该项目的中间件client已经默认加入了[埋点](https://github.com/yonyou-auto-dev/track-sdk)功能，该埋点的数据会反应消息发送以及接受的具体情况，后续通过logstash输出到mq中，ops-mq订阅对应的事件来收集消息发送以及接受的情况，并且提供页面来监控每一个消息的生命周期。
	
* **任务中心 ops-task：**  
	将定时任务的调用和业务执行剥离出来，该项目服务调度部分，通过简单的配置可以发布任务给业务服务，支持http方式或者mq发布方式，业务服务只需要提供对应的接口或者订阅相关事件即可。
	
* **服务监控 ops-service：**  
	通过调用eureka的接口来查看所有已注册服务的信息，查看服务提供的api清单(基于swagger)，查看SQL执行效率（基于durid）。
	
* **运维后台逻辑 ops-admin**  
	运维后台自身的一些逻辑，用户、角色、资源和权限的维护，动态路由配置、动态filter配置、认证url配置、认证忽略配置、微信服务号配置和微信菜单配置，对表的增删改查的维护。
* **微信接口**  
    该服务剥离出微信的特定功能，使得其它微服务能够独立出来，供app、web或者微信应用调用，不局限于微信应用。
	
## 一些详细说明

### 关于网关的动态路由

已经实现在后台通过配置设定某些URL路由到哪些服务，也可以路由到异构系统中去，动态修改无需重启服务

### 关于动态Filter
基于groovy实现，直接在页面动态添加自己Filter的逻辑,动态修改网关filter无需重启服务

### 权限服务   
由于不同系统的用户信息的多样化(如微信、app等用户)，为了方便于业务开发和系统性能提升，各个系统负责用户信息的存储和管理，ops-auth代理用户认证，不同  
	系统提供返回用户信息的接口，ops-auth负责生成jwt。该接口接收手机号/验证码、用户名/密码等认证参数，返回包含id,username,name,description,telPhone的json   
	信息给ops-auth，其中id为userId，username为登陆账号，name用户名称，这三个参数必须返回，其它参数可为空。
	
### 微信接口
   实现服务号启动开发者模式要求多点服务器认证回调接口，实现消息接收接口，收到关注或取消关注时发送事件到MQ；  
   统一微信菜单的配置方式，由ops-wechat统一接收微信回调的code，然后根据服务号取得用户的openid，生成jwt返回；  
   多服务号支持。