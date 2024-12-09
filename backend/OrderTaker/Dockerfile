FROM openjdk:latest

MAINTAINER 1114-group

# 将当前目录下的jar包复制到容器里
COPY *.jar  /java -jar ./OrderTaker-v3.0-final.jar


# 提示当前项目在容器运行的端口
CMD ["========server.port = 8081=========="]

# 暴露运行的端口
EXPOSE 8081

# 执行jar包
ENTRYPOINT ["java","-jar","/java -jar ./OrderTaker-v3.0-final.jar"]