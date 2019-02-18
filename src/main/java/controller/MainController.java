package controller;

import elem.Category;
import elem.Content;
import exception.CommonExceptionAdvice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import service.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Controller
public class MainController
{
    @Autowired
    private Service service;

    /*--------------------index Part--------------------*/
    @GetMapping("/index")
    public String index(Model model)
    {
        List<Category> categoryList = service.getCategoryList();
        model.addAttribute("categoryList", categoryList);
        return "index";
    }

    /*--------------------contents List Part--------------------*/
    @GetMapping("/{name}/content-list")
    public String contentList(@PathVariable("name") String name, Model model)
    {
        List<Content> contentList = service.getContentList(name);
        model.addAttribute("contentList", contentList);

        Category category = service.getCategory(name);
        model.addAttribute("category", category);
        return "contents/content-list";
    }

    /*--------------------Detail contents Part--------------------*/
    @GetMapping("/{name}/{cid}")
    public String detailContent(@PathVariable("name") String name, @PathVariable("cid") Integer cid, Model model)
    {
        Content content = service.getContent(name, cid);
        model.addAttribute("content", content);
        return "contents/detail-content";
    }

    /*--------------------contents insert Part--------------------*/
    @GetMapping("/{name}/add-content")
    public String addContent(@PathVariable("name") String name, Model model)
    {
        Category category = service.getCategory(name);
        model.addAttribute("category", category);
        return "contents/add-content";
    }

    @PostMapping("/{name}/add-content")
    public String addContent(@PathVariable("name") String name, Content content,
                             HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException
    {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8;");
        request.setCharacterEncoding("UTF-8");

        if (name != null)
        {
            PrintWriter writer = null;
            try
            {
                int result = service.insertContent(name, content);
                writer = response.getWriter();
                if (result == 1)
                {
                    writer.println("<script>location.href='content-list'</script>");
                    writer.flush();
                }
                else
                {
                    writer.println("<script>alert('오류로 인해 게시글 등록에 실패했습니다.'); location.href='content-list'</script>");
                    writer.flush();
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            finally
            {
                if (writer != null)
                    writer.close();
            }
        }
        return null;
    }

    /*--------------------contents update Part--------------------*/
    @GetMapping("/{name}/{cid}/edit-content")
    public String updateContent(@PathVariable("name") String name, @PathVariable("cid") Integer cid, Model model)
    {
        if (name != null && cid != null)
        {
            List<Category> categoryList = service.getCategoryList();
            Content content = service.getContent(name, cid);
            model.addAttribute("content", content);
            model.addAttribute("categoryList", categoryList);
        }
        return "contents/edit-content";
    }

    @PostMapping("/{name}/{cid}/edit-content")
    public String updateContent(@PathVariable("name") String name, @PathVariable("cid") Integer cid, Content content, Category category,
                                HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException
    {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8;");
        request.setCharacterEncoding("UTF-8");

        if (cid != null)
        {
            PrintWriter writer = null;
            try
            {
                int result = service.updateContent(content);
                content = service.latestContent();
                writer = response.getWriter();
                if (result == 1)
                {
                    writer.println("<script>location.href='../" + content.getNum() + "'</script>");
                    writer.flush();
                }
                else
                {
                    writer.println("<script>alert('오류로 인해 게시글 수정에 실패했습니다.'); location.href='../" + content.getNum() + "'</script>");
                    writer.flush();
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            finally
            {
                if (writer != null)
                    writer.close();
            }
        }
        return null;
    }

    /*--------------------contents delete Part--------------------*/
    @ResponseBody
    @RequestMapping("{name}/{cid}/delete")
    public int deleteContent(@PathVariable("name") String name, @PathVariable("cid") Integer cid, int contentID)
    {
        if (contentID != 0)
        {
            int result = service.deleteContent(contentID);
            return result;
        }
        return 0;
    }

    /*Common 메소드는 Exception 타입으로 처리하는 모든 예외를 처리하도록 설정*/
    @ExceptionHandler(Exception.class)
    @RequestMapping("error/error-common")
    public String common(Exception e, Model model)
    {
        Logger logger = LoggerFactory.getLogger(CommonExceptionAdvice.class);
        logger.info(e.toString());
        model.addAttribute("exception", e);
        return "error/error-common";
    }
}