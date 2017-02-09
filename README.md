[![License](https://img.shields.io/badge/license-Apache%202-green.svg)](https://www.apache.org/licenses/LICENSE-2.0)
[![Download](https://api.bintray.com/packages/tangsiyuan/maven/myokhttp/images/download.svg) ](https://dl.bintray.com/androidxsf/maven/com/xsf/zeusLog)

# 0x00 解决问题

1. 新人刚到项目组，往往搞不清每个页面对应的Activity名称，导致完成需要一些时间浪费在找页面上
2. 网络请求查看比较麻烦，需要代理，转为格式化json
3. 系统Log比较挫

项目地址 [https://github.com/xsfelvis/ZeusLog](https://github.com/xsfelvis/ZeusLog)

# 0x01 ZeusLog

主要分为两大块，移动端Log和控制台Log，先上图

> 移动端

![](http://i.imgur.com/DEFJuVq.png)

主要支持

- 显示当前Actvity的名称
- 显示所需打印网络请求的内容，内容部分透传点击事件，不影响app使用
- 右边有一个长条控制区域，可以滑动联动内容区域，便于阅读长的网络请求

具体如下

![](http://i.imgur.com/3z1py7V.png)

【黄色区域】 当前Actvity名称

【红色区域】 log日志开启或者关闭，关闭后右边控制区域也随之消失，只有当前的Activity名称，如下
![](http://i.imgur.com/QOPAgHP.png)

【橙色区域】 网络请求格式化显示区域，该部分透传所有点击事件，从而使整个App使用不受影响

【绿色区域】 自定义sideBar，滑动该区域控制橙色区域长文本滚动阅读

技术点：

没有采用window去实现，原因很简单，兼容性不好，现在各大厂商对自己的windows权限管理都很紧，而且正好尝试一下自定义view+事件分发，有了想法，一时技痒，就撸一个呗，当然你也可以有其他更好的思路，也可以跟我交流

> 控制台

控制台有一些比较全的Log库，如orhanobut/logger、JakeWharton/timber等，该部分主要是借鉴了前人的代码，自己做了一些定制，主要是不想增加无需求的功能，如xml格式化等，最终 支持基本log+多参数+json格式。

- 支持显示行号
- 支持显示Log所在函数名称
- 支持无Tag快捷打印
- 支持在Android Studio开发IDE中，点击函数名称，跳转至Log所在位置
- 支持JSON字符串解析打印
- 支持无限长字符串打印，无Logcat4000字符限制
- 支持变长参数，任意个数打印参数
- 支持设置全局Tag


`基本tag`
![](http://i.imgur.com/nKYFhCz.png)
`无tag显示当前类名`
![](http://i.imgur.com/BaPN1AP.png)
`格式化输出json`
![](http://i.imgur.com/NaKeJTz.png)
`多参数log`
![](http://i.imgur.com/AnK53C9.png)

# 0x02 How to Use

`compile 'com.xsf:zeusLog:1.0.0'`

> 移动端Log使用很简单，需要在你想打印的地方，调用如下API即可

`ZeusMobileView.startZeus(MainActivity.this).setJsonStr(JSON_LONG);`

> 控制台Log 需要先初始化安全等级

`ZeusLog.init(BuildConfig.DEBUG);` 表示仅仅在debug包下打印日志，如果不初始化也可使用，但是需要注意release包保护

然后如同使用系统API一样使用即可

> 不带tag

 - ZeusLog.v(LOG_MSG);
 - ZeusLog.d(LOG_MSG);
 - ZeusLog.i(LOG_MSG);
 - ZeusLog.w(LOG_MSG);
 - ZeusLog.e(LOG_MSG);
 - ZeusLog.a(LOG_MSG);


> 带tag

- ZeusLog.v(TAG, LOG_MSG);
- ZeusLog.d(TAG, LOG_MSG);
- ZeusLog.i(TAG, LOG_MSG);
- ZeusLog.w(TAG, LOG_MSG);
- ZeusLog.e(TAG, LOG_MSG);
- ZeusLog.a(TAG, LOG_MSG);

> json格式化

- ZeusLog.printJsonStr(JSON);

> 多个参数

- ZeusLog.v(TAG, LOG_MSG, "params1", "params2", this);
- ZeusLog.d(TAG, LOG_MSG, "params1", "params2", this);
- ZeusLog.i(TAG, LOG_MSG, "params1", "params2", this);
- ZeusLog.w(TAG, LOG_MSG, "params1", "params2", this);
- ZeusLog.e(TAG, LOG_MSG, "params1", "params2", this);
- ZeusLog.a(TAG, LOG_MSG, "params1", "params2", this);


>最后感谢你宝贵的时间阅读，如果你喜欢的话可以点赞收藏，也可以关注我的账号，大家一起交流技术。
