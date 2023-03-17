#!/bin/sh
#配置jre 如果java -version不能用，需配置
#JRE_HOME=/home/jdk1.8.0_112/jre
#PATH=$JRE_HOME/bin:$PATH
#export JRE_HOME
#export PATH
jarName="login.jar"
ps -ef|grep $jarName |grep -v grep |awk '{print $2}'|while read pid
	do
		echo "stop process....."
		kill -9 $pid
	done
sleep 1
echo "start process....."
nohup java -jar $jarName >/dev/null 2>&1 &
