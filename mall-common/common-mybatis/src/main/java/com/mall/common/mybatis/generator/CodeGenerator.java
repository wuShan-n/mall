package com.mall.common.mybatis.generator;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;

import java.util.Collections;
import java.util.Scanner;

/**
 * MyBatis-Plus code generator
 * Run this class to generate entity, mapper, service, and controller code
 */
public class CodeGenerator {
    
    /**
     * Database configuration
     */
    private static final String DB_URL = "jdbc:mysql://localhost:3306/mall_user?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "root123456";
    
    /**
     * Package configuration
     */
    private static final String PARENT_PACKAGE = "com.mall";
    private static final String MODULE_NAME = "user";
    
    /**
     * Output directory
     */
    private static final String OUTPUT_DIR = System.getProperty("user.dir") + "/mall-service/" + MODULE_NAME + "-service/src/main/java";
    private static final String XML_OUTPUT_DIR = System.getProperty("user.dir") + "/mall-service/" + MODULE_NAME + "-service/src/main/resources/mapper";
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Please enter table names (separated by comma):");
        String tables = scanner.nextLine();
        
        System.out.println("Please enter author name:");
        String author = scanner.nextLine();
        
        FastAutoGenerator.create(DB_URL, DB_USERNAME, DB_PASSWORD)
            // Global configuration
            .globalConfig(builder -> {
                builder.author(author)
                    .outputDir(OUTPUT_DIR)
                    .commentDate("yyyy-MM-dd")
                    .dateType(DateType.TIME_PACK)
                    .enableSwagger()
                    .disableOpenDir();
            })
            // Package configuration
            .packageConfig(builder -> {
                builder.parent(PARENT_PACKAGE)
                    .moduleName(MODULE_NAME)
                    .entity("entity")
                    .mapper("mapper")
                    .service("service")
                    .serviceImpl("service.impl")
                    .controller("controller")
                    .pathInfo(Collections.singletonMap(OutputFile.xml, XML_OUTPUT_DIR));
            })
            // Strategy configuration
            .strategyConfig(builder -> {
                builder.addInclude(tables.split(","))
                    // Entity strategy
                    .entityBuilder()
                    .superClass("com.mall.common.base.BaseEntity")
                    .enableLombok()
                    .enableTableFieldAnnotation()
                    .enableFileOverride()
                    .naming(NamingStrategy.underline_to_camel)
                    .columnNaming(NamingStrategy.underline_to_camel)
                    .addSuperEntityColumns("id", "create_time", "update_time", "create_by", "update_by", "deleted", "version")
                    .logicDeleteColumnName("deleted")
                    .logicDeletePropertyName("deleted")
                    .versionColumnName("version")
                    .versionPropertyName("version")
                    // Mapper strategy
                    .mapperBuilder()
                    .superClass("com.mall.common.mybatis.base.SuperMapper")
                    .enableMapperAnnotation()
                    .enableBaseResultMap()
                    .enableBaseColumnList()
                    .enableFileOverride()
                    // Service strategy
                    .serviceBuilder()
                    .superServiceClass("com.mall.common.mybatis.base.IBaseService")
                    .superServiceImplClass("com.mall.common.mybatis.base.BaseServiceImpl")
                    .formatServiceFileName("%sService")
                    .formatServiceImplFileName("%sServiceImpl")
                    .enableFileOverride()
                    // Controller strategy
                    .controllerBuilder()
                    .enableRestStyle()
                    .enableHyphenStyle()
                    .enableFileOverride();
            })
            // Template engine
            .templateEngine(new VelocityTemplateEngine())
            .execute();
        
        System.out.println("Code generation completed!");
    }
}