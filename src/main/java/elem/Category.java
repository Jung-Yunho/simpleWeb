package elem;

import java.sql.Timestamp;

public class Category
{
    private int num;
    private int categoryID;
    private String name;
    private Timestamp createDT;
    private String name_en;

    public Category()
    {
    }

    public Category(int num, int categoryID, String name, Timestamp createDT, String name_en)
    {
        this.num = num;
        this.categoryID = categoryID;
        this.name = name;
        this.createDT = createDT;
        this.name_en = name_en;
    }

    public int getNum()
    {
        return num;
    }

    public void setNum(int num)
    {
        this.num = num;
    }

    public int getCategoryID()
    {
        return categoryID;
    }

    public void setCategoryID(int categoryID)
    {
        this.categoryID = categoryID;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Timestamp getCreateDT()
    {
        return createDT;
    }

    public void setCreateDT(Timestamp createDT)
    {
        this.createDT = createDT;
    }

    public String getName_en()
    {
        return name_en;
    }

    public void setName_en(String name_en)
    {
        this.name_en = name_en;
    }
}