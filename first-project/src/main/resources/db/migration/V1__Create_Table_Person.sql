CREATE TABLE IF NOT EXISTS `people` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `first_name` varchar(200) NOT NULL,
  `last_name` varchar(200) NOT NULL,
  `address` varchar(200) NOT NULL,
  `gender` varchar(8) NOT NULL,
  PRIMARY KEY (`id`)
);