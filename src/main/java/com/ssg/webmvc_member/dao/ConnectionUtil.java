package com.ssg.webmvc_member.dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;

public enum ConnectionUtil {
    INSANCE;

    private HikariDataSource ds;    // HikariDataSource : 데이터베이스 연결을 관리하는 객체

    ConnectionUtil(){
        HikariConfig config = new HikariConfig();
        // 대규모 애플리케이션에서 효율적인 데이터베이스 연결 관리를 제공하는 라이브러리중 하나인 HikariCP,
        // HikariCP의 설정을 HikariConfig 객체에 저장할거야 ! 그 객체의 이름은 config!!

        config.setDriverClassName("org.mariadb.jdbc.Driver");
        config.setDriverClassName("org.mariadb.jdbc.Driver");
        config.setJdbcUrl("jdbc:mariadb://localhost:3307/webdb");
        config.setUsername("webuser");
        config.setPassword("webuser");
        config.addDataSourceProperty("cachePrepStmts","true");  //PreparedStatement의 캐시를 활성화
        config.addDataSourceProperty("prepStmtCacheSize","250"); // 캐시 사이즈 설정
        config.addDataSourceProperty("prepStmtCacheSqlLimit","2048"); // SQL 리미트를 설정

        ds = new HikariDataSource(config); //HikariDataSource 객체에 위의 설정을 적용하여 디비 연결을 관리
    }

    public Connection getConnection() throws Exception{
        return ds.getConnection();  //HikariDataSource에서 데이터베이스 연결을 가져와 반환
    }
}
