dataSource {
    pooled = true
    username = "webbf"
    password = "webbfpass"
	logSql = true
}
hibernate {
    cache.use_second_level_cache = true
    cache.use_query_cache = false
    cache.region.factory_class = 'net.sf.ehcache.hibernate.EhCacheRegionFactory'
}
// environment specific settings
environments {
    development {
        dataSource {
			driverClassName = "org.h2.Driver"
			dialect = "org.hibernate.dialect.H2Dialect"
            dbCreate = "create-drop" // one of 'create', 'create-drop', 'update', 'validate', ''
            url = "jdbc:h2:mem:WBFdevDbs;MVCC=TRUE;LOCK_TIMEOUT=10000"
            //url = "jdbc:mysql://localhost/WBFdevDb"
        }
    }
    test {
        dataSource {
			driverClassName = "org.h2.Driver"
			dialect = "org.hibernate.dialect.H2Dialect"
            dbCreate = "update"
            url = "jdbc:h2:mem:WBFtestDb;MVCC=TRUE;LOCK_TIMEOUT=10000"
            //url = "jdbc:mysql://localhost/WBFtestDb"
        }
    }
    production {
        dataSource {
            dbCreate = "update"
			driverClassName = "com.mysql.jdbc.Driver"
			dialect = "org.hibernate.dialect.MySQL5InnoDBDialect"
            // url = "jdbc:h2:WBFprodDb;MVCC=TRUE;LOCK_TIMEOUT=10000"
            url = "jdbc:mysql://localhost/WBFprodDb"
            pooled = true
            properties {
               maxActive = -1
               minEvictableIdleTimeMillis=1800000
               timeBetweenEvictionRunsMillis=1800000
               numTestsPerEvictionRun=3
               testOnBorrow=true
               testWhileIdle=true
               testOnReturn=true
               validationQuery="SELECT 1"
            }
        }
    }
}
