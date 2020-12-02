IDE运行程序：
1、SpringBootHelloworldApplication.java里运行main函数即可

命令行方式运行程序：
1、进入jar所在目录 cd F:\eclipseworkspace\spring-boot-helloworld\target
2、运行命令 jar -jar spring-boot-helloworld-0.0.1-SNAPSHOT.jar

端口占用：
1、根据端口号查找进程  netstat -aon|findstr "8001"
2、杀掉进程 tskill 5796