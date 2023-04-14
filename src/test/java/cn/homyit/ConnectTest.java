package cn.homyit;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import lombok.SneakyThrows;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.InputStream;
import java.util.Properties;

/**
 * @program: teach
 * @description:
 * @author: Charon
 * @create: 2023-04-14 16:14
 **/
public class ConnectTest {

    @SneakyThrows
    @Test
    public void testConnect() {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        System.out.println("sqlSession = " + sqlSession);
    }
}
