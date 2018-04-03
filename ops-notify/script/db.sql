CREATE TABLE `notify_app_url` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `app_id` varchar(40) DEFAULT NULL,
  `url` varchar(200) DEFAULT NULL,
  `contentType` varchar(50) DEFAULT NULL,
  `ctr_time` datetime DEFAULT NULL,
  `crt_user` varchar(50) DEFAULT NULL,
  `crt_name` varchar(255) DEFAULT NULL,
  `upd_time` datetime DEFAULT NULL,
  `upd_user` varchar(255) DEFAULT NULL,
  `upd_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

CREATE TABLE `notify_mq_message` (
  `msgKey` varchar(60) NOT NULL,
  `data` varchar(1500) DEFAULT NULL,
  `exchange` varchar(80) DEFAULT NULL,
  `routerKey` varchar(80) DEFAULT NULL,
  `consumerClassName` varchar(200) DEFAULT NULL,
  `bizClassName` varchar(200) DEFAULT NULL,
  `status` varchar(30) DEFAULT NULL,
  `crt_time` datetime DEFAULT NULL,
  `upd_time` datetime DEFAULT NULL,
  PRIMARY KEY (`msgKey`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `notify_third_message` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `app_id` varchar(40) DEFAULT NULL,
  `biz_id` varchar(60) DEFAULT NULL,
  `data` varchar(500) DEFAULT NULL,
  `notify_url` varchar(200) DEFAULT NULL,
  `status` varchar(30) DEFAULT NULL,
  `crt_time` datetime DEFAULT NULL,
  `crt_user` varchar(50) DEFAULT NULL,
  `upd_time` datetime DEFAULT NULL,
  `upd_user` varchar(255) DEFAULT NULL,
  `crt_name` varchar(255) DEFAULT NULL,
  `upd_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4;



