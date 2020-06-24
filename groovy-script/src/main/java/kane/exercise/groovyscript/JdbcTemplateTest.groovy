package kane.exercise.groovyscript

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.jdbc.core.namedparam.SqlParameterSource
import org.springframework.jdbc.datasource.DriverManagerDataSource

import java.lang.reflect.Parameter

class JdbcTemplateTest {



    static void main(String[] args) {
        def dataSource = new DriverManagerDataSource("jdbc:oracle:thin:@10.1.3.62:1521:oradb", "gpms_test", "gpms_test")
        def sql = new NamedParameterJdbcTemplate(dataSource)

        SqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("DATA_ID_1","2")
        namedParameters.addValue("DATA_ID_2","2")
        namedParameters.addValue("DATA_DATE","20200412")

        def rules = sql.queryForList("select 1 from GP_QC_TYPE where DATA_ID = :DATA_ID_1*:DATA_ID_2 and DATA_DATE = :DATA_DATE", namedParameters)
        println(!rules.isEmpty())
        println(rules)



    }
}
