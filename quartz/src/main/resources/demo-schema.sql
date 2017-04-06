-- 		SchedulerFactory schedulerFactory = new StdSchedulerFactory(quartzProperties());
-- 		Scheduler scheduler = schedulerFactory.getScheduler();
-- 		scheduler.start();  启动Scheduler时会查询表qrtz_locks
--
-- 表的结构 `qrtz_locks`
--
CREATE TABLE IF NOT EXISTS `qrtz_locks` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `LOCK_NAME` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- at org.quartz.impl.jdbcjobstore.StdJDBCDelegate.countMisfiredTriggersInState(StdJDBCDelegate.java:393) ~[quartz-2.2.3.jar:na]
-- 获取misfires信息时，需要查表qrtz_triggers
--
-- 表的结构 `qrtz_triggers`
--
CREATE TABLE IF NOT EXISTS `qrtz_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(120) NOT NULL,
  `TRIGGER_GROUP` varchar(120) NOT NULL,
  `JOB_NAME` varchar(120) NOT NULL,
  `JOB_GROUP` varchar(120) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `NEXT_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PREV_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PRIORITY` int(11) DEFAULT NULL,
  `TRIGGER_STATE` varchar(16) NOT NULL,
  `TRIGGER_TYPE` varchar(8) NOT NULL,
  `START_TIME` bigint(13) NOT NULL,
  `END_TIME` bigint(13) DEFAULT NULL,
  `CALENDAR_NAME` varchar(200) DEFAULT NULL,
  `MISFIRE_INSTR` smallint(2) DEFAULT NULL,
  `JOB_DATA` blob
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- org.quartz.JobPersistenceException: Failure identifying failed instances when checking-in
-- 		SchedulerFactory schedulerFactory = new StdSchedulerFactory(quartzProperties());
-- 		Scheduler scheduler = schedulerFactory.getScheduler();
-- 		scheduler.start();//start时需要表qrtz_scheduler_state
--
-- 表的结构 `qrtz_scheduler_state`
--
CREATE TABLE IF NOT EXISTS `qrtz_scheduler_state` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `INSTANCE_NAME` varchar(120) NOT NULL,
  `LAST_CHECKIN_TIME` bigint(13) NOT NULL,
  `CHECKIN_INTERVAL` bigint(13) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- org.quartz.JobPersistenceException: Failure identifying failed instances when checking-in:
--Scheduler start时会使用
-- 表的结构 `qrtz_fired_triggers`
--
CREATE TABLE IF NOT EXISTS `qrtz_fired_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `ENTRY_ID` varchar(95) NOT NULL,
  `TRIGGER_NAME` varchar(120) NOT NULL,
  `TRIGGER_GROUP` varchar(120) NOT NULL,
  `INSTANCE_NAME` varchar(120) NOT NULL,
  `FIRED_TIME` bigint(13) NOT NULL,
  `SCHED_TIME` bigint(13) NOT NULL,
  `PRIORITY` int(11) NOT NULL,
  `STATE` varchar(16) NOT NULL,
  `JOB_NAME` varchar(120) DEFAULT NULL,
  `JOB_GROUP` varchar(120) DEFAULT NULL,
  `IS_NONCONCURRENT` varchar(1) DEFAULT NULL,
  `REQUESTS_RECOVERY` varchar(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

---------------------有上面的表，Scheduler就可以start了。系统正常启动---------------------

-- 表的结构 `qrtz_blob_triggers`
--
CREATE TABLE IF NOT EXISTS `qrtz_blob_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(120) NOT NULL,
  `TRIGGER_GROUP` varchar(120) NOT NULL,
  `BLOB_DATA` blob
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------



-- org.quartz.JobPersistenceException: Couldn't obtain job groups:
--scheduler.getJobGroupNames()会查表qrtz_job_details
--
-- 表的结构 `qrtz_job_details`
--
CREATE TABLE IF NOT EXISTS `qrtz_job_details` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `JOB_NAME` varchar(120) NOT NULL,
  `JOB_GROUP` varchar(120) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `JOB_CLASS_NAME` varchar(250) NOT NULL,
  `IS_DURABLE` varchar(1) NOT NULL,
  `IS_NONCONCURRENT` varchar(1) NOT NULL,
  `IS_UPDATE_DATA` varchar(1) NOT NULL,
  `REQUESTS_RECOVERY` varchar(1) NOT NULL,
  `JOB_DATA` blob
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


--scheduler.scheduleJob(jobDetail, trigger);
--org.quartz.JobPersistenceException: Couldn't store trigger
--
-- 表的结构 `qrtz_paused_trigger_grps`
--
CREATE TABLE IF NOT EXISTS `qrtz_paused_trigger_grps` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_GROUP` varchar(120) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--org.quartz.JobPersistenceException: Couldn't store trigger
-- scheduler.scheduleJob(jobDetail, trigger);
--
-- 表的结构 `qrtz_cron_triggers`
--
CREATE TABLE IF NOT EXISTS `qrtz_cron_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(120) NOT NULL,
  `TRIGGER_GROUP` varchar(120) NOT NULL,
  `CRON_EXPRESSION` varchar(120) NOT NULL,
  `TIME_ZONE_ID` varchar(80) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

