# 工程说明 - CAC & SAC 项目
## 项目概述
工程分为两个 Spring Boot 项目：CAC (中央空调控制系统) 和 SAC (从控机系统)。这两个系统通过网络通信协作，提供一套完整的空调控制解决方案。
## 开发语言说明
- Java: 两个项目的后端使用 Java 语言开发。
- JavaScript + HTML + CSS: 两个项目的前端使用 JavaScript + HTML + CSS 开发。
## 开发环境说明
- IntelliJ IDEA：使用 IntelliJ IDEA 作为开发 IDE，它提供了对 Spring Boot 项目的优秀支持。
- Apache Maven：用于项目的依赖管理和构建，版本 3.8.1。
- Spring Boot：版本 2.7.6，用于简化新 Spring 应用的初始搭建以及开发过程。
- JDK：使用 JDK 17。
## 数据库软件说明
- H2 Database：
  - CAC 项目使用 H2 作为开发时的内嵌数据库。
  - SAC 项目未使用数据库。
- 数据库建库脚本文件：
  - CAC 项目使用 CAC 项目中的 schema.sql 和 data.sql 文件初始化数据库。
## 软件安装部署说明
> - 构建过程中请科学上网，以便下载 Maven 依赖。
> - 运行过程中请科学上网，以确保前端页面能够正确加载外部的 CSS 样式表。
### 部署 CAC 项目
1. 使用 IntelliJ IDEA 打开 CAC 项目文件夹。
2. 打开该项目的 pom.xml 文件，选择重新构建项目。
3. 修改 application.properties 文件。
   1. 第一次运行时，将 spring.sql.init.mode 设置为 always。
   2. 之后运行时，将 spring.sql.init.mode 设置为 never。
4. 运行运行配置中的 CAC。
5. 访问 http://localhost:8080/ 查看 CAC 项目。
### 部署 SAC 项目
1. 使用 IntelliJ IDEA 打开 SAC 项目文件夹。
2. 打开该项目的 pom.xml 文件，选择重新构建项目。
3. 运行单个 SAC：
   1. 运行运行配置中的 SAC。
   2. 访问 http://localhost:8081/ 查看 SAC 项目。
4. 运行多个 SAC：
   1. 再次运行运行配置中的 SAC。端口号会自动递增，若端口号被占用，会自动选择下一个端口号。
   2. 访问 http://localhost:8082/ 查看第二个 SAC 项目。
   3. 以此类推，可以运行多个 SAC 项目。
## 项目验收时存在的问题
1. 要求温控模式与温度调控方向相互统一，比如说：制冷模式下空调只能降温工作。而在实现时错误地认为空调的工作模式代表的是工作范围，只要目标温度在工作范围内就工作，这样造成了制冷模式下空调也能升温工作。
2. 要求中央空调与从控机在工作时中央空调关机重启后从控机能自动重连。而在实现时忽略了这一点，在中央空调关机时直接停止了所有从控机的服务，中央空调再次重启后，从控机将不会重连。
## 项目的不足
1. 项目模块划分不够明确。存在单个文件中逻辑太多的情况。
2. 接口定义不够明确。在刚开始定义接口的时候并没有统一标准，而是根据一个需求写一个接口，这造成了维护上的困难。
3. 项目没有严格遵循面向对象设计原则和模型层次化。给项目后续维护带来挑战。
4. 只实现了先来先服务调度。后续如果需要实现更复杂的算法时，因为别的算法需要将服务队列中的从控机中途放入等待队列，推荐通过从控机端定时向中央空调端发送请求来更新从控机自己的当前的服务状态来实现。