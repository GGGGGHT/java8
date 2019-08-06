# Linux常用命令(基于centos7)



## 系统服务管理

### systemctl

- 启动服务：`systemctl start httpd`
- 关闭服务：`systemctl stop httpd`
- 重启服务（不管是否在运行）：`systemctl restart httpd`
- 重新载入配置（不中断服务）：`systemctl reload httpd`
- 查看运行状态：`systemctl status httpd`
- 设置开机启动：`systemctl enable httpd`
- 禁止开机启动：`systemctl disable httpd`
- 查看系统安装的服务：`systemctl list-units --type=service`

## 文件管理

### ls
列出/home目录下的子目录：`ls -l /home`
列出当前文件夹下所有文件夹及文件大小：`ls -lh`

### pwd
显示当前工作目录

### cd
切换目录： `cd /usr/local`

### passwd
修改root密码：`passwd root`

### su
普通用户切换到超级用户：`su -`

### clear
清除屏幕信息

### man
查看ls命令的帮助信息：`man ls`

### who
- 查看当前运行级别：`who -r`
- 显示当前用户`whoami`

### free
以MB显示内存使用状态：`free -m`

### ps
查看系统所有进程：`ps -ef    | ps  aux`

查看运行的java进程： `ps -ef | grep java`

### top
查看系统当前活跃进程信息

### mkdir
创建目录

### more 

分页查看  
每10行显示一屏查看：`more -c -10 `

### cat
查看config文件：`cat -Ab config`

### rm
- 删除文件：`rm a.txt`
- 删除文件夹：` rm -rf a/`

### touch
创建一个文件：`touch a.txt`

### cp
将目录a的文件拷贝到目录b:` cp -r /home/a /home/b`

### mv
移动或覆盖文件：`mv a.txt b.txt`

## 压缩与解压

### tar
- 打包文件夹到单独的文件：`tar -cvf /opt/etc.tar /etc`
- 压缩文件夹到压缩文件（gzip）：`tar -zcvf /opt/etc.tar.gz /etc`
- 压缩文件夹到压缩文件（bzip2）：`tar -jcvf /opt/etc.tar.bz2 /etc`
- 查阅压缩包中内容（gzip）：`tar -ztvf /opt/etc.tar.gz /etc`
- 解压文件到当前目录（gzip）：`tar -zxvf /opt/etc.tar.gz`

## 磁盘和网络管理

### df
查看磁盘占用情况：`df -hT`

### ifconfig
* 查看当前网络接口状态
* 禁用或启用某块网卡:`ifconfig 网卡名　up/down`
* 为某块网卡设置ip地址: `ifconfig  网卡名   ip`



### netstat

- 查看路由信息：`netstat -rn`
- 查看所有有效TCP连接：`netstat -an`
- 查看系统中启动的监听服务：`netstat -tulnp`
- 查看处于连接状态的系统资源信息：`netstat -atunp`
- 查看系统中正在监听80端口的服务: `netstat -pantu | grep 80`

### kill

*  杀掉某个进程  `kill -9 进程id`

## 软件的安装与管理

### rpm

- 安装软件包：`rpm -ivh nginx-1.12.2-2.el7.x86_64.rpm`
- 模糊搜索软件包：`rpm -qa | grep nginx`
- 精确查找软件包：`rpm -qa nginx`
- 查询软件包的安装路径：`rpm -ql nginx-1.12.2-2.el7.x86_64`
- 查看软件包的概要信息：`rpm -qi nginx-1.12.2-2.el7.x86_64`
- 验证软件包内容和安装文件是否一致：`rpm -V nginx-1.12.2-2.el7.x86_64`
- 更新软件包：`rpm -Uvh nginx-1.12.2-2.el7.x86_64`
- 删除软件包：`rpm -e nginx-1.12.2-2.el7.x86_64`

### yum

- 安装软件包：` yum install nginx`
- 检查可以更新的软件包：`yum check-update`
- 更新指定的软件包：`yum update nginx`
- 在资源库中查找软件包信息：`yum info nginx*`
- 列出已经安装的所有软件包：`yum info installed`
- 列出软件包名称：`yum list redis*`
- 模糊搜索软件包：`yum search redis`

## 网络安全

### iptables

- 开启防火墙：`systemctl start iptables.service`
- 关闭防火墙：`systemctl stop iptables.service`
- 查看防火墙状态：`systemctl status iptables.service`
- 设置开机启动：`systemctl enable iptables.service`
- 禁用开机启动：`systemctl disable iptables.service`
- 查看filter表的链信息：`iptables -L -n`
- 查看NAT表的链信息：`iptables -t nat -L -n`
- 清除防火墙所有规则：`iptables -F` `iptables -X` `iptables -Z`
- 添加过滤规则（开发80端口）：`iptables -I INPUT -p tcp --dport 80 -j ACCEPT`
- 查找规则所做行号：`iptables -L INPUT --line-numbers -n`
- 根据行号删除过滤规则：`iptables -D INPUT 1`



## 其他命令

### alias 

 给某个命令设置别名 例如: `alias ll=ls -l `

### echo

 `echo 1 > a.txt` 将1输出到a.txt文件中(不以追加的方式写入 1会覆盖之前a.txt中所有的内容)

 `echo 1 >> a.txt` 将1以追加的形式输出到a.txt中

### find

* 查找当前目录及子目录下名为a.txt的文件:  `find . -name a.txt`
* 查找根下名为a.txt的文件: `find / -name a.txt`
* 查找当前目录下具体所有权限的一般文件 `find . -perm 777  -type f`
* 查找系统中所有文件长度为0的普通文件，并列出它们的完整路径：`find / -type f -size 0 -exec ls -l {} \;`



