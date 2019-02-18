package elem;

public class Content
{
    private int num;
    private int contentID;
    private int categoryID;
    private String title;
    private String content;
    private String createDT;
    private String modifyDT;
    private String categoryName;

    public Content()
    {
    }

    public Content(int num)
    {
        this.num = num;
    }

    public Content(int num, int contentID, int categoryID, String title, String content, String createDT, String modifyDT)
    {
        this.num = num;
        this.contentID = contentID;
        this.categoryID = categoryID;
        this.title = title;
        this.content = content;
        this.createDT = createDT;
        this.modifyDT = modifyDT;
    }

    public Content(int num, int contentID, int categoryID, String title, String content, String createDT, String modifyDT, String categoryName)
    {
        this.num = num;
        this.contentID = contentID;
        this.categoryID = categoryID;
        this.title = title;
        this.content = content;
        this.createDT = createDT;
        this.modifyDT = modifyDT;
        this.categoryName = categoryName;
    }

    public int getNum()
    {
        return num;
    }

    public void setNum(int num)
    {
        this.num = num;
    }

    public int getContentID()
    {
        return contentID;
    }

    public void setContentID(int contentID)
    {
        this.contentID = contentID;
    }

    public int getCategoryID()
    {
        return categoryID;
    }

    public void setCategoryID(int categoryID)
    {
        this.categoryID = categoryID;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public String getCreateDT()
    {
        return createDT;
    }

    public void setCreateDT(String createDT)
    {
        this.createDT = createDT;
    }

    public String getModifyDT()
    {
        return modifyDT;
    }

    public void setModifyDT(String modifyDT)
    {
        this.modifyDT = modifyDT;
    }

    public String getCategoryName()
    {
        return categoryName;
    }

    public void setCategoryName(String categoryName)
    {
        this.categoryName = categoryName;
    }
}
