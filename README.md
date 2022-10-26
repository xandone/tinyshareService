# 工程简介



# 延伸阅读



启动:
<br/>
nohup java -jar tinyshare-1.0.0.jar >server.log 2 >&1 &
不挂起运行服务

关闭:
<br/>
ps -ef|grep tinyshare-1.0.0.jar 查看端口
kill -9 pid端口
