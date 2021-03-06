package sport.totalizator.dao;

import org.apache.log4j.Logger;
import org.junit.Ignore;
import org.junit.Test;
import sport.totalizator.db.jdbc.ConnectionPool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static junit.framework.TestCase.assertEquals;

@Ignore
public class SelectRequestTest
{
    private static final Logger log = Logger.getLogger(SelectRequestTest.class);

    @Test
    public void selectRequest(){
        ResultSet resultSet = null;
        ConnectionPool pool = ConnectionPool.getConnectionPool();
        Connection c = pool.getConnection();
        try {
            Statement statement = c.createStatement();
            statement.execute("SELECT * FROM `event`");
            resultSet = statement.getResultSet();
            while(resultSet.next()){
                log.info(resultSet.getString("event_name"));
            }
        }
        catch (SQLException exc){
            log.error(exc);
        }
        assertEquals(true, resultSet != null);
    }
}
