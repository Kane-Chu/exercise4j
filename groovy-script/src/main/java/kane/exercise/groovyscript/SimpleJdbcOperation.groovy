package kane.exercise.groovyscript

import groovy.sql.Sql

class GroovySqlExample1 {
    static void main(args) {
        Sql sql = Sql.newInstance("jdbc:oracle:thin:@10.1.3.62:1521:oradb", "gpms_dev",
                "gpms_dev", "oracle.jdbc.driver.OracleDriver")
        sql.eachRow("select * from GP_BM_TEST") {
            row -> println row.data_id + " " + row.name + " " + row.phone
        }
    }
}

