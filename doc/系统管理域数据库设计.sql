-- 7.1 权限管理表设计
-- 权限系统采用RBAC（基于角色的访问控制）模型，灵活管理用户权限。

-- 角色表
CREATE TABLE `roles` (
                         `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
                         `role_code` VARCHAR(50) NOT NULL COMMENT '角色编码',
                         `role_name` VARCHAR(100) NOT NULL COMMENT '角色名称',
                         `description` VARCHAR(255) DEFAULT NULL COMMENT '描述',
                         `status` TINYINT DEFAULT 1 COMMENT '状态：0-禁用，1-启用',
                         `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                         `updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

                         PRIMARY KEY (`id`),
                         UNIQUE KEY `uk_role_code` (`role_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色表';

-- 权限表
CREATE TABLE `permissions` (
                               `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
                               `permission_code` VARCHAR(100) NOT NULL COMMENT '权限编码',
                               `permission_name` VARCHAR(100) NOT NULL COMMENT '权限名称',
                               `permission_type` TINYINT DEFAULT 1 COMMENT '权限类型：1-菜单，2-按钮，3-API',
                               `parent_id` BIGINT UNSIGNED DEFAULT 0 COMMENT '父权限ID',
                               `path` VARCHAR(255) DEFAULT NULL COMMENT '权限路径',
                               `method` VARCHAR(10) DEFAULT NULL COMMENT 'HTTP方法（API权限）',
                               `icon` VARCHAR(50) DEFAULT NULL COMMENT '图标',
                               `sort_order` INT DEFAULT 0 COMMENT '排序',
                               `status` TINYINT DEFAULT 1 COMMENT '状态：0-禁用，1-启用',
                               `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                               `updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

                               PRIMARY KEY (`id`),
                               UNIQUE KEY `uk_permission_code` (`permission_code`),
                               KEY `idx_parent_id` (`parent_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='权限表';

-- 用户角色关联表
CREATE TABLE `user_roles` (
                              `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
                              `user_id` BIGINT UNSIGNED NOT NULL COMMENT '用户ID',
                              `role_id` BIGINT UNSIGNED NOT NULL COMMENT '角色ID',
                              `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,

                              PRIMARY KEY (`id`),
                              UNIQUE KEY `uk_user_role` (`user_id`, `role_id`),
                              KEY `idx_user_id` (`user_id`),
                              KEY `idx_role_id` (`role_id`),
                              CONSTRAINT `fk_user_roles_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
                              CONSTRAINT `fk_user_roles_role` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户角色关联表';

-- 角色权限关联表
CREATE TABLE `role_permissions` (
                                    `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
                                    `role_id` BIGINT UNSIGNED NOT NULL COMMENT '角色ID',
                                    `permission_id` BIGINT UNSIGNED NOT NULL COMMENT '权限ID',
                                    `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,

                                    PRIMARY KEY (`id`),
                                    UNIQUE KEY `uk_role_permission` (`role_id`, `permission_id`),
                                    KEY `idx_role_id` (`role_id`),
                                    KEY `idx_permission_id` (`permission_id`),
                                    CONSTRAINT `fk_role_permissions_role` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`),
                                    CONSTRAINT `fk_role_permissions_perm` FOREIGN KEY (`permission_id`) REFERENCES `permissions` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色权限关联表';

-- 7.2 系统配置与日志表设计
-- 系统配置表用于存储可动态调整的系统参数，日志表记录重要的系统事件。

-- 系统配置表
CREATE TABLE `system_configs` (
                                  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
                                  `config_key` VARCHAR(100) NOT NULL COMMENT '配置键',
                                  `config_value` TEXT NOT NULL COMMENT '配置值',
                                  `config_type` VARCHAR(20) DEFAULT 'STRING' COMMENT '配置类型：STRING/NUMBER/BOOLEAN/JSON',
                                  `description` VARCHAR(255) DEFAULT NULL COMMENT '描述',
                                  `group_name` VARCHAR(50) DEFAULT NULL COMMENT '配置组',
                                  `is_public` BOOLEAN DEFAULT FALSE COMMENT '是否公开（前端可见）',
                                  `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                  `updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

                                  PRIMARY KEY (`id`),
                                  UNIQUE KEY `uk_config_key` (`config_key`),
                                  KEY `idx_group_name` (`group_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统配置表';

-- 操作日志表
CREATE TABLE `operation_logs` (
                                  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
                                  `user_id` BIGINT DEFAULT NULL COMMENT '操作用户ID',
                                  `username` VARCHAR(50) DEFAULT NULL COMMENT '用户名',
                                  `module` VARCHAR(50) NOT NULL COMMENT '模块名称',
                                  `operation` VARCHAR(100) NOT NULL COMMENT '操作类型',
                                  `method` VARCHAR(200) DEFAULT NULL COMMENT '调用方法',
                                  `request_method` VARCHAR(10) DEFAULT NULL COMMENT 'HTTP方法',
                                  `request_url` VARCHAR(500) DEFAULT NULL COMMENT '请求URL',
                                  `request_params` TEXT DEFAULT NULL COMMENT '请求参数',
                                  `response_data` TEXT DEFAULT NULL COMMENT '响应数据',
                                  `ip_address` VARCHAR(45) DEFAULT NULL COMMENT 'IP地址',
                                  `user_agent` VARCHAR(500) DEFAULT NULL COMMENT '用户代理',
                                  `status` TINYINT DEFAULT 1 COMMENT '操作状态：1-成功，0-失败',
                                  `error_msg` TEXT DEFAULT NULL COMMENT '错误信息',
                                  `execution_time` BIGINT DEFAULT NULL COMMENT '执行时间（毫秒）',
                                  `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,

                                  PRIMARY KEY (`id`),
                                  KEY `idx_user_id` (`user_id`),
                                  KEY `idx_module` (`module`),
                                  KEY `idx_operation` (`operation`),
                                  KEY `idx_created_at` (`created_at`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='操作日志表';

-- 系统通知表
CREATE TABLE `system_notifications` (
                                        `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
                                        `title` VARCHAR(200) NOT NULL COMMENT '通知标题',
                                        `content` TEXT NOT NULL COMMENT '通知内容',
                                        `notification_type` VARCHAR(30) NOT NULL COMMENT '通知类型：SYSTEM/ORDER/PROMOTION',
                                        `target_type` TINYINT DEFAULT 1 COMMENT '目标类型：1-全体用户，2-指定用户，3-指定角色',
                                        `target_ids` JSON DEFAULT NULL COMMENT '目标ID列表',
                                        `link_url` VARCHAR(500) DEFAULT NULL COMMENT '跳转链接',
                                        `status` TINYINT DEFAULT 1 COMMENT '状态：0-草稿，1-已发布，2-已撤回',
                                        `publish_time` DATETIME DEFAULT NULL COMMENT '发布时间',
                                        `expire_time` DATETIME DEFAULT NULL COMMENT '过期时间',
                                        `created_by` BIGINT DEFAULT NULL COMMENT '创建人',
                                        `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                        `updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

                                        PRIMARY KEY (`id`),
                                        KEY `idx_notification_type` (`notification_type`),
                                        KEY `idx_status` (`status`),
                                        KEY `idx_publish_time` (`publish_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统通知表';

-- 用户通知记录表
CREATE TABLE `user_notifications` (
                                      `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
                                      `user_id` BIGINT UNSIGNED NOT NULL COMMENT '用户ID',
                                      `notification_id` BIGINT UNSIGNED NOT NULL COMMENT '通知ID',
                                      `is_read` BOOLEAN DEFAULT FALSE COMMENT '是否已读',
                                      `read_time` DATETIME DEFAULT NULL COMMENT '阅读时间',
                                      `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,

                                      PRIMARY KEY (`id`),
                                      UNIQUE KEY `uk_user_notification` (`user_id`, `notification_id`),
                                      KEY `idx_user_id` (`user_id`),
                                      KEY `idx_notification_id` (`notification_id`),
                                      KEY `idx_is_read` (`is_read`),
                                      CONSTRAINT `fk_user_notifications_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
                                      CONSTRAINT `fk_user_notifications_notif` FOREIGN KEY (`notification_id`) REFERENCES `system_notifications` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户通知记录表';
