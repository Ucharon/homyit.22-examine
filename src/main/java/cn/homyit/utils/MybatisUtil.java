package cn.homyit.utils;

import org.apache.ibatis.session.SqlSessionManager;

import java.io.InputStream;

public class MybatisUtil {

    /**
     * 这里使用sqlSessionManager，妈妈再也不用当心我忘记关数据库啦
     */
    private static SqlSessionManager sessionManager = null;

    static {
        try {
            InputStream inputStream = MybatisUtil.class.getClassLoader()
                    .getResourceAsStream("mybatis-config.xml");
            sessionManager = SqlSessionManager.newInstance(inputStream);
        } catch (Exception e) {
            throw new RuntimeException("the Configuration of Mybatis is not exist!");
        }
    }

    /**
     * 获取 Mapper
     *
     * @param <T>
     * @param clazz
     * @return
     */
    public static <T> T getMapper(Class<T> clazz) {
        return sessionManager.getMapper(clazz);
    }

    /**
     * 获取 SqlSessionManager
     *
     * @return
     */
    public static SqlSessionManager getSessionManager() {
        return sessionManager;
    }

}