package service;

import dao.Dao;
import elem.Category;
import elem.Content;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class Service
{
    @Autowired
    private Dao dao;

    public List<Category> getCategoryList()
    {
        List<Category> list = dao.getCategoryList();
        return list;
    }

    public Category getCategory(String name)
    {
        Category category = dao.getCategory(name);
        return category;
    }

    public List<Content> getContentList(String name)
    {
        List<Content> list = dao.getContentList(name);
        return list;
    }

    public Content getContent(String name, Integer cid)
    {
        Content content = dao.getContent(name, cid);
        return content;
    }

    public int insertContent(String name, Content content)
    {
        int result = dao.insert(name, content);
        return result;
    }

    public int updateContent(Content content)
    {
        int result = dao.update(content);
        return result;
    }

    public int deleteContent(int contentID)
    {
        int result = dao.delete(contentID);
        return result;
    }

    public Content latestContent()
    {
        Content content = dao.latestContent();
        return content;
    }
}
