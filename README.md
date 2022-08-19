# Spring-Cloud-Nacos-Seata

## undo_log表

>每个数据库都需要生成该表用户事务回滚

```sql
/*
 Navicat Premium Data Transfer

 Source Server         : 本机MySQL
 Source Server Type    : MySQL
 Source Server Version : 50735
 Source Host           : localhost:3306
 Source Schema         : test

 Target Server Type    : MySQL
 Target Server Version : 50735
 File Encoding         : 65001

 Date: 17/08/2022 16:53:01
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for undo_log
-- ----------------------------
DROP TABLE IF EXISTS `undo_log`;
CREATE TABLE `undo_log`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `branch_id` bigint(20) NOT NULL,
  `xid` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `context` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `rollback_info` longblob NOT NULL,
  `log_status` int(11) NOT NULL,
  `log_created` datetime NOT NULL,
  `log_modified` datetime NOT NULL,
  `ext` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `ux_undo_log`(`xid`, `branch_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
```



## 回滚日志

```java

2022-08-19 15:07:18.582  INFO 22012 --- [nio-3001-exec-1] io.seata.tm.TransactionManagerHolder     : TransactionManager Singleton io.seata.tm.DefaultTransactionManager@22a81476
2022-08-19 15:07:18.596  INFO 22012 --- [nio-3001-exec-1] i.seata.tm.api.DefaultGlobalTransaction  : Begin new global transaction [10.0.0.209:8091:153419096717820208]
Creating a new SqlSession
SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@22c7069] was not registered for synchronization because synchronization is not active
JDBC Connection [io.seata.rm.datasource.ConnectionProxy@46a07c79] will not be managed by Spring
==>  Preparing: UPDATE employee SET employee_name=?, employee_desc=? WHERE id=?
==> Parameters: employee11(String), employee11(String), 1(Long)
2022-08-19 15:07:18.833  INFO 22012 --- [nio-3001-exec-1] c.a.n.client.config.impl.ClientWorker    : [fixed-127.0.0.1_8848] [subscribe] client.rm.lock.retryInterval+DEFAULT_GROUP
2022-08-19 15:07:18.833  INFO 22012 --- [nio-3001-exec-1] c.a.nacos.client.config.impl.CacheData   : [fixed-127.0.0.1_8848] [add-listener] ok, tenant=, dataId=client.rm.lock.retryInterval, group=DEFAULT_GROUP, cnt=1
2022-08-19 15:07:18.833  INFO 22012 --- [nio-3001-exec-1] c.a.nacos.client.config.impl.CacheData   : [fixed-127.0.0.1_8848] [add-listener] ok, tenant=, dataId=client.rm.lock.retryInterval, group=DEFAULT_GROUP, cnt=2
2022-08-19 15:07:18.833  INFO 22012 --- [nio-3001-exec-1] c.a.n.client.config.impl.ClientWorker    : [fixed-127.0.0.1_8848] [subscribe] client.rm.lock.retryTimes+DEFAULT_GROUP
2022-08-19 15:07:18.834  INFO 22012 --- [nio-3001-exec-1] c.a.nacos.client.config.impl.CacheData   : [fixed-127.0.0.1_8848] [add-listener] ok, tenant=, dataId=client.rm.lock.retryTimes, group=DEFAULT_GROUP, cnt=1
2022-08-19 15:07:18.834  INFO 22012 --- [nio-3001-exec-1] c.a.nacos.client.config.impl.CacheData   : [fixed-127.0.0.1_8848] [add-listener] ok, tenant=, dataId=client.rm.lock.retryTimes, group=DEFAULT_GROUP, cnt=2
<==    Updates: 1
Closing non transactional SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@22c7069]
2022-08-19 15:07:19.096  WARN 22012 --- [nio-3001-exec-1] c.l.c.ServiceInstanceListSupplierBuilder : LoadBalancerCacheManager not available, returning delegate without caching.
2022-08-19 15:07:19.146  INFO 22012 --- [nio-3001-exec-1] com.alibaba.nacos.client.naming          : new ips(2) service: DEFAULT_GROUP@@spring-cloud-department-provider -> [{"instanceId":"10.0.0.209#4001#DEFAULT#DEFAULT_GROUP@@spring-cloud-department-provider","ip":"10.0.0.209","port":4001,"weight":1.0,"healthy":true,"enabled":true,"ephemeral":true,"clusterName":"DEFAULT","serviceName":"DEFAULT_GROUP@@spring-cloud-department-provider","metadata":{"preserved.register.source":"SPRING_CLOUD"},"instanceHeartBeatInterval":5000,"instanceHeartBeatTimeOut":15000,"ipDeleteTimeout":30000},{"instanceId":"10.0.0.209#4002#DEFAULT#DEFAULT_GROUP@@spring-cloud-department-provider","ip":"10.0.0.209","port":4002,"weight":1.0,"healthy":true,"enabled":true,"ephemeral":true,"clusterName":"DEFAULT","serviceName":"DEFAULT_GROUP@@spring-cloud-department-provider","metadata":{"preserved.register.source":"SPRING_CLOUD"},"instanceHeartBeatInterval":5000,"instanceHeartBeatTimeOut":15000,"ipDeleteTimeout":30000}]
2022-08-19 15:07:19.147  INFO 22012 --- [nio-3001-exec-1] com.alibaba.nacos.client.naming          : current ips:(2) service: DEFAULT_GROUP@@spring-cloud-department-provider -> [{"instanceId":"10.0.0.209#4001#DEFAULT#DEFAULT_GROUP@@spring-cloud-department-provider","ip":"10.0.0.209","port":4001,"weight":1.0,"healthy":true,"enabled":true,"ephemeral":true,"clusterName":"DEFAULT","serviceName":"DEFAULT_GROUP@@spring-cloud-department-provider","metadata":{"preserved.register.source":"SPRING_CLOUD"},"instanceHeartBeatInterval":5000,"instanceHeartBeatTimeOut":15000,"ipDeleteTimeout":30000},{"instanceId":"10.0.0.209#4002#DEFAULT#DEFAULT_GROUP@@spring-cloud-department-provider","ip":"10.0.0.209","port":4002,"weight":1.0,"healthy":true,"enabled":true,"ephemeral":true,"clusterName":"DEFAULT","serviceName":"DEFAULT_GROUP@@spring-cloud-department-provider","metadata":{"preserved.register.source":"SPRING_CLOUD"},"instanceHeartBeatInterval":5000,"instanceHeartBeatTimeOut":15000,"ipDeleteTimeout":30000}]
2022-08-19 15:07:19.268  WARN 22012 --- [nio-3001-exec-1] c.l.c.ServiceInstanceListSupplierBuilder : LoadBalancerCacheManager not available, returning delegate without caching.
2022-08-19 15:07:19.271  INFO 22012 --- [nio-3001-exec-1] com.alibaba.nacos.client.naming          : new ips(2) service: DEFAULT_GROUP@@spring-cloud-user-provider -> [{"instanceId":"10.0.0.209#5001#DEFAULT#DEFAULT_GROUP@@spring-cloud-user-provider","ip":"10.0.0.209","port":5001,"weight":1.0,"healthy":true,"enabled":true,"ephemeral":true,"clusterName":"DEFAULT","serviceName":"DEFAULT_GROUP@@spring-cloud-user-provider","metadata":{"preserved.register.source":"SPRING_CLOUD"},"instanceHeartBeatInterval":5000,"instanceHeartBeatTimeOut":15000,"ipDeleteTimeout":30000},{"instanceId":"10.0.0.209#5002#DEFAULT#DEFAULT_GROUP@@spring-cloud-user-provider","ip":"10.0.0.209","port":5002,"weight":1.0,"healthy":true,"enabled":true,"ephemeral":true,"clusterName":"DEFAULT","serviceName":"DEFAULT_GROUP@@spring-cloud-user-provider","metadata":{"preserved.register.source":"SPRING_CLOUD"},"instanceHeartBeatInterval":5000,"instanceHeartBeatTimeOut":15000,"ipDeleteTimeout":30000}]
2022-08-19 15:07:19.272  INFO 22012 --- [nio-3001-exec-1] com.alibaba.nacos.client.naming          : current ips:(2) service: DEFAULT_GROUP@@spring-cloud-user-provider -> [{"instanceId":"10.0.0.209#5001#DEFAULT#DEFAULT_GROUP@@spring-cloud-user-provider","ip":"10.0.0.209","port":5001,"weight":1.0,"healthy":true,"enabled":true,"ephemeral":true,"clusterName":"DEFAULT","serviceName":"DEFAULT_GROUP@@spring-cloud-user-provider","metadata":{"preserved.register.source":"SPRING_CLOUD"},"instanceHeartBeatInterval":5000,"instanceHeartBeatTimeOut":15000,"ipDeleteTimeout":30000},{"instanceId":"10.0.0.209#5002#DEFAULT#DEFAULT_GROUP@@spring-cloud-user-provider","ip":"10.0.0.209","port":5002,"weight":1.0,"healthy":true,"enabled":true,"ephemeral":true,"clusterName":"DEFAULT","serviceName":"DEFAULT_GROUP@@spring-cloud-user-provider","metadata":{"preserved.register.source":"SPRING_CLOUD"},"instanceHeartBeatInterval":5000,"instanceHeartBeatTimeOut":15000,"ipDeleteTimeout":30000}]
2022-08-19 15:07:19.647  INFO 22012 --- [g.push.receiver] com.alibaba.nacos.client.naming          : received push data: {"type":"dom","data":"{\"name\":\"DEFAULT_GROUP@@spring-cloud-department-provider\",\"clusters\":\"\",\"cacheMillis\":10000,\"hosts\":[{\"instanceId\":\"10.0.0.209#4001#DEFAULT#DEFAULT_GROUP@@spring-cloud-department-provider\",\"ip\":\"10.0.0.209\",\"port\":4001,\"weight\":1.0,\"healthy\":true,\"enabled\":true,\"ephemeral\":true,\"clusterName\":\"DEFAULT\",\"serviceName\":\"DEFAULT_GROUP@@spring-cloud-department-provider\",\"metadata\":{\"preserved.register.source\":\"SPRING_CLOUD\"},\"ipDeleteTimeout\":30000,\"instanceHeartBeatTimeOut\":15000,\"instanceHeartBeatInterval\":5000},{\"instanceId\":\"10.0.0.209#4002#DEFAULT#DEFAULT_GROUP@@spring-cloud-department-provider\",\"ip\":\"10.0.0.209\",\"port\":4002,\"weight\":1.0,\"healthy\":true,\"enabled\":true,\"ephemeral\":true,\"clusterName\":\"DEFAULT\",\"serviceName\":\"DEFAULT_GROUP@@spring-cloud-department-provider\",\"metadata\":{\"preserved.register.source\":\"SPRING_CLOUD\"},\"ipDeleteTimeout\":30000,\"instanceHeartBeatTimeOut\":15000,\"instanceHeartBeatInterval\":5000}],\"lastRefTime\":1660892839646,\"checksum\":\"\",\"allIPs\":false,\"reachProtectionThreshold\":false,\"valid\":true}","lastRefTime":415700712884300} from /10.0.0.209
2022-08-19 15:07:19.857  INFO 22012 --- [g.push.receiver] com.alibaba.nacos.client.naming          : received push data: {"type":"dom","data":"{\"name\":\"DEFAULT_GROUP@@spring-cloud-user-provider\",\"clusters\":\"\",\"cacheMillis\":10000,\"hosts\":[{\"instanceId\":\"10.0.0.209#5001#DEFAULT#DEFAULT_GROUP@@spring-cloud-user-provider\",\"ip\":\"10.0.0.209\",\"port\":5001,\"weight\":1.0,\"healthy\":true,\"enabled\":true,\"ephemeral\":true,\"clusterName\":\"DEFAULT\",\"serviceName\":\"DEFAULT_GROUP@@spring-cloud-user-provider\",\"metadata\":{\"preserved.register.source\":\"SPRING_CLOUD\"},\"ipDeleteTimeout\":30000,\"instanceHeartBeatTimeOut\":15000,\"instanceHeartBeatInterval\":5000},{\"instanceId\":\"10.0.0.209#5002#DEFAULT#DEFAULT_GROUP@@spring-cloud-user-provider\",\"ip\":\"10.0.0.209\",\"port\":5002,\"weight\":1.0,\"healthy\":true,\"enabled\":true,\"ephemeral\":true,\"clusterName\":\"DEFAULT\",\"serviceName\":\"DEFAULT_GROUP@@spring-cloud-user-provider\",\"metadata\":{\"preserved.register.source\":\"SPRING_CLOUD\"},\"ipDeleteTimeout\":30000,\"instanceHeartBeatTimeOut\":15000,\"instanceHeartBeatInterval\":5000}],\"lastRefTime\":1660892839856,\"checksum\":\"\",\"allIPs\":false,\"reachProtectionThreshold\":false,\"valid\":true}","lastRefTime":415700922274100} from /10.0.0.209
2022-08-19 15:07:20.257  INFO 22012 --- [h_RMROLE_1_1_16] i.s.c.r.p.c.RmBranchRollbackProcessor    : rm handle branch rollback process:xid=10.0.0.209:8091:153419096717820208,branchId=153419096717820210,branchType=AT,resourceId=jdbc:mysql://127.0.0.1:3306/consumer,applicationData=null
2022-08-19 15:07:20.259  INFO 22012 --- [h_RMROLE_1_1_16] io.seata.rm.AbstractRMHandler            : Branch Rollbacking: 10.0.0.209:8091:153419096717820208 153419096717820210 jdbc:mysql://127.0.0.1:3306/consumer
2022-08-19 15:07:20.308  INFO 22012 --- [h_RMROLE_1_1_16] i.s.r.d.undo.AbstractUndoLogManager      : xid 10.0.0.209:8091:153419096717820208 branch 153419096717820210, undo_log deleted with GlobalFinished
2022-08-19 15:07:20.309  INFO 22012 --- [h_RMROLE_1_1_16] io.seata.rm.AbstractRMHandler            : Branch Rollbacked result: PhaseTwo_Rollbacked
2022-08-19 15:07:20.319  INFO 22012 --- [nio-3001-exec-1] i.seata.tm.api.DefaultGlobalTransaction  : Suspending current transaction, xid = 10.0.0.209:8091:153419096717820208
2022-08-19 15:07:20.320  INFO 22012 --- [nio-3001-exec-1] i.seata.tm.api.DefaultGlobalTransaction  : [10.0.0.209:8091:153419096717820208] rollback status: Rollbacked
2022-08-19 15:07:20.342 ERROR 22012 --- [nio-3001-exec-1] o.a.c.c.C.[.[.[.[dispatcherServlet]      : Servlet.service() for servlet [dispatcherServlet] in context with path [/api/consumer] threw exception [Request processing failed; nested exception is java.lang.ArithmeticException: / by zero] with root cause

java.lang.ArithmeticException: / by zero
	at com.gtf.cloud.consumer.service.impl.EmployeeServiceImpl.updateEmployee(EmployeeServiceImpl.java:54) ~[classes/:na]


xid>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>10.0.0.209:8091:153419096717820208
Creating a new SqlSession
SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@418f1bcc] was not registered for synchronization because synchronization is not active
JDBC Connection [io.seata.rm.datasource.ConnectionProxy@4f48fd8d] will not be managed by Spring
==>  Preparing: UPDATE dept SET dept_name=?, dept_desc=? WHERE id=?
==> Parameters: dept11(String), dept11(String), 1(Long)
<==    Updates: 1
Closing non transactional SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@418f1bcc]
2022-08-19 15:07:20.201  INFO 12984 --- [h_RMROLE_1_2_16] i.s.c.r.p.c.RmBranchRollbackProcessor    : rm handle branch rollback process:xid=10.0.0.209:8091:153419096717820208,branchId=153419096717820213,branchType=AT,resourceId=jdbc:mysql://127.0.0.1:3306/provider,applicationData=null
2022-08-19 15:07:20.202  INFO 12984 --- [h_RMROLE_1_2_16] io.seata.rm.AbstractRMHandler            : Branch Rollbacking: 10.0.0.209:8091:153419096717820208 153419096717820213 jdbc:mysql://127.0.0.1:3306/provider
2022-08-19 15:07:20.246  INFO 12984 --- [h_RMROLE_1_2_16] i.s.r.d.undo.AbstractUndoLogManager      : xid 10.0.0.209:8091:153419096717820208 branch 153419096717820213, undo_log deleted with GlobalFinished
2022-08-19 15:07:20.247  INFO 12984 --- [h_RMROLE_1_2_16] io.seata.rm.AbstractRMHandler            : Branch Rollbacked result: PhaseTwo_Rollbacked



xid>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>10.0.0.209:8091:153419096717820208
Creating a new SqlSession
SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@7d86dfec] was not registered for synchronization because synchronization is not active
JDBC Connection [io.seata.rm.datasource.ConnectionProxy@665930a0] will not be managed by Spring
==>  Preparing: UPDATE user SET name=?, password=? WHERE id=?
==> Parameters: user11(String), user11(String), 1(Long)
2022-08-19 15:07:19.876  INFO 14892 --- [nio-5002-exec-1] c.a.n.client.config.impl.ClientWorker    : [fixed-127.0.0.1_8848] [subscribe] client.rm.lock.retryInterval+DEFAULT_GROUP
2022-08-19 15:07:19.876  INFO 14892 --- [nio-5002-exec-1] c.a.nacos.client.config.impl.CacheData   : [fixed-127.0.0.1_8848] [add-listener] ok, tenant=, dataId=client.rm.lock.retryInterval, group=DEFAULT_GROUP, cnt=1
2022-08-19 15:07:19.877  INFO 14892 --- [nio-5002-exec-1] c.a.nacos.client.config.impl.CacheData   : [fixed-127.0.0.1_8848] [add-listener] ok, tenant=, dataId=client.rm.lock.retryInterval, group=DEFAULT_GROUP, cnt=2
2022-08-19 15:07:19.877  INFO 14892 --- [nio-5002-exec-1] c.a.n.client.config.impl.ClientWorker    : [fixed-127.0.0.1_8848] [subscribe] client.rm.lock.retryTimes+DEFAULT_GROUP
2022-08-19 15:07:19.877  INFO 14892 --- [nio-5002-exec-1] c.a.nacos.client.config.impl.CacheData   : [fixed-127.0.0.1_8848] [add-listener] ok, tenant=, dataId=client.rm.lock.retryTimes, group=DEFAULT_GROUP, cnt=1
2022-08-19 15:07:19.877  INFO 14892 --- [nio-5002-exec-1] c.a.nacos.client.config.impl.CacheData   : [fixed-127.0.0.1_8848] [add-listener] ok, tenant=, dataId=client.rm.lock.retryTimes, group=DEFAULT_GROUP, cnt=2
<==    Updates: 1
Closing non transactional SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@7d86dfec]
2022-08-19 15:07:20.124  INFO 14892 --- [h_RMROLE_1_1_16] i.s.c.r.p.c.RmBranchRollbackProcessor    : rm handle branch rollback process:xid=10.0.0.209:8091:153419096717820208,branchId=153419096717820215,branchType=AT,resourceId=jdbc:mysql://127.0.0.1:3306/user,applicationData=null
2022-08-19 15:07:20.126  INFO 14892 --- [h_RMROLE_1_1_16] io.seata.rm.AbstractRMHandler            : Branch Rollbacking: 10.0.0.209:8091:153419096717820208 153419096717820215 jdbc:mysql://127.0.0.1:3306/user
2022-08-19 15:07:20.191  INFO 14892 --- [h_RMROLE_1_1_16] i.s.r.d.undo.AbstractUndoLogManager      : xid 10.0.0.209:8091:153419096717820208 branch 153419096717820215, undo_log deleted with GlobalFinished
2022-08-19 15:07:20.192  INFO 14892 --- [h_RMROLE_1_1_16] io.seata.rm.AbstractRMHandler            : Branch Rollbacked result: PhaseTwo_Rollbacked
```

