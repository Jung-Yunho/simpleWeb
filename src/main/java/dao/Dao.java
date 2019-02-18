package dao;

import elem.Category;
import elem.Content;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Dao
{
    public List<Category> getCategoryList()
    {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        List<Category> list = new ArrayList<>();

        try
        {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            try
            {
                conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:orcl", "imdemo30", "nets1234");
                st = conn.prepareStatement("SELECT ROWNUM, category_id, name, create_dt, name_en FROM category");
                rs = st.executeQuery();

                while (rs.next())
                {
                    Category category = new Category(rs.getInt(1), rs.getInt(2),
                            rs.getString(3), rs.getTimestamp(4), rs.getString(5));
                    list.add(category);
                }
            }

            catch (SQLException e)
            {
                e.printStackTrace();
            }
            finally
            {
                try
                {
                    rs.close();
                    st.close();
                    conn.close();
                }
                catch (SQLException e)
                {
                    e.printStackTrace();
                }
            }
        }
        catch (ClassNotFoundException cnfe)
        {
            cnfe.printStackTrace();
        }
        return list;
    }

    public Category getCategory(String name)
    {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        Category category = null;

        try
        {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            try
            {
                conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:orcl", "imdemo30", "nets1234");
                st = conn.prepareStatement("SELECT ROWNUM, category_id, name, create_dt, name_en " +
                        "FROM category WHERE name_en=?");
                st.setString(1, name);
                rs = st.executeQuery();

                if (rs.next())
                    category = new Category(rs.getInt(1), rs.getInt(2),
                            rs.getString(3), rs.getTimestamp(4), rs.getString(5));
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
            finally
            {
                try
                {
                    rs.close();
                    st.close();
                    conn.close();
                }
                catch (SQLException e)
                {
                    e.printStackTrace();
                }
            }
        }
        catch (ClassNotFoundException cnfe)
        {
            cnfe.printStackTrace();
        }
        return category;
    }

    public List<Content> getContentList(String name)
    {
        ResultSet rs = null;
        Connection conn = null;
        PreparedStatement st = null;
        List<Content> list = new ArrayList<>();

        try
        {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            try
            {
                conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:orcl", "imdemo30", "nets1234");
                st = conn.prepareStatement("SELECT ROWNUM, content_id, category_id, title, content, create_dt, modify_dt " +
                        "                        FROM (SELECT a.content_id, a.category_id, a.title, a.content, a.create_dt, " +
                        "                        TO_CHAR(a.modify_dt, 'YYYY-MM-DD HH24:MI:SS') AS modify_dt " +
                        "                        FROM content a JOIN category b ON a.category_id = b.category_id " +
                        "                        WHERE b.name_en= ? ORDER BY modify_dt) ORDER BY ROWNUM DESC");
                st.setString(1, name);
                rs = st.executeQuery();

                while (rs.next())
                {
                    Content content = new Content(rs.getInt(1), rs.getInt(2), rs.getInt(3),
                            rs.getString(4), rs.getString(5),
                            rs.getString(6), rs.getString(7));
                    list.add(content);
                }
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
            finally
            {
                try
                {
                    rs.close();
                    st.close();
                    conn.close();
                }
                catch (SQLException e)
                {
                    e.printStackTrace();
                }
            }
        }
        catch (ClassNotFoundException cnfe)
        {
            cnfe.printStackTrace();
        }
        return list;
    }

    public Content getContent(String name, Integer cid)
    {
        try
        {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:orcl", "imdemo30", "nets1234"))
            {
                try (PreparedStatement st = conn.prepareStatement("SELECT num, content_id, category_id, title, content, create_dt, modify_dt, name " +
                        "FROM (SELECT ROWNUM AS num, content_id, category_id, title, content, create_dt, modify_dt, name " +
                        "      FROM (SELECT a.content_id AS content_id, a.category_id AS category_id, a.title AS title, a.content AS content, " +
                        "                                          TO_CHAR(a.create_dt, 'YYYY-MM-DD HH24:MI:SS') AS create_dt, TO_CHAR(a.modify_dt, 'YYYY-MM-DD HH24:MI:SS') AS modify_dt, b.name AS name " +
                        "                                          FROM content a JOIN category b ON a.category_id = b.category_id " +
                        "                                          WHERE b.name_en = ? ORDER BY modify_dt) ORDER BY num DESC) WHERE num = ?"))
                {
                    st.setString(1, name);
                    st.setInt(2, cid);

                    try (ResultSet rs = st.executeQuery())
                    {
                        Content content = null;
                        while (rs.next())
                        {
                            content = new Content(rs.getInt(1), rs.getInt(2), rs.getInt(3),
                                    rs.getString(4), rs.getString(5), rs.getString(6),
                                    rs.getString(7), rs.getString(8));
                        }
                        return content;
                    }
                }
            }
        }
        catch (Exception cnfe)
        {
            cnfe.printStackTrace();
        }
        return null;
    }

    public int insert(String name, Content content)
    {
        Connection conn = null;
        PreparedStatement st = null;
        int result = 0;
        try
        {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            try
            {
                conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:orcl", "imdemo30", "nets1234");
                st = conn.prepareStatement("INSERT INTO content(content_id, category_id, title, content, modify_dt)" +
                        "VALUES((SELECT NVL(MAX(content_id), 0)+1 FROM content),(SELECT category_id FROM category WHERE name_en = ?),?,?, sysdate)");
                st.setString(1, name);
                st.setString(2, content.getTitle());
                st.setString(3, content.getContent());
                result = st.executeUpdate();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
            finally
            {
                try
                {
                    st.close();
                    conn.close();
                }
                catch (SQLException e)
                {
                    e.printStackTrace();
                }
            }
        }
        catch (ClassNotFoundException cnfe)
        {
            cnfe.printStackTrace(); //
        }
        return result;
    }

    public int update(Content content)
    {
        Connection conn = null;
        PreparedStatement st = null;
        int result = 0;
        try
        {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            try
            {
                conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:orcl", "imdemo30", "nets1234");
                st = conn.prepareStatement("UPDATE content SET category_id=?, title=?, content=?, modify_dt=sysdate WHERE content_id=?");
                st.setInt(1, content.getCategoryID());
                st.setString(2, content.getTitle());
                st.setString(3, content.getContent());
                st.setInt(4, content.getContentID());

                result = st.executeUpdate();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
            finally
            {
                try
                {
                    st.close();
                    conn.close();
                }
                catch (SQLException e)
                {
                    e.printStackTrace();
                }
            }
        }
        catch (ClassNotFoundException cnfe)
        {
            cnfe.printStackTrace();
        }

        return result;
    }

    public int delete(int contentID)
    {
        Connection conn = null;
        PreparedStatement st = null;
        int result = 0;
        try
        {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            try
            {
                conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:orcl", "imdemo30", "nets1234");
                st = conn.prepareStatement("DELETE FROM content WHERE content_id=?");
                st.setInt(1, contentID);
                result = st.executeUpdate();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
            finally
            {
                try
                {
                    st.close();
                    conn.close();
                }
                catch (SQLException e)
                {
                    e.printStackTrace();
                }
            }
        }
        catch (ClassNotFoundException cnfe)
        {
            cnfe.printStackTrace();
        }
        return result;
    }

    public Content latestContent()
    {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        Content content = null;

        try
        {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            try
            {
                conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:orcl", "imdemo30", "nets1234");
                st = conn.prepareStatement("SELECT MAX(ROWNUM) FROM content");
                rs = st.executeQuery();

                if (rs.next())
                    content = new Content(rs.getInt(1));
            }
            catch (SQLException e)
            {
            }
            finally
            {
                try
                {
                    rs.close();
                    st.close();
                    conn.close();
                }
                catch (SQLException e)
                {
                    e.printStackTrace();
                }
            }
        }
        catch (ClassNotFoundException cnfe)
        {
            cnfe.printStackTrace();
        }
        return content;
    }
}
