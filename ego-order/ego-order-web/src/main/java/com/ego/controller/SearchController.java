package com.ego.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * 搜索Controller
 */
@Controller
@RequestMapping("/search")
public class SearchController {

    /**
     * 跳转搜索页
     *
     * @return
     */
    @RequestMapping("/index")
    public String index(String searchStr, Model model, HttpServletRequest request) {
        try {
            // 对输入的内容进行编码，防止中文乱码
            searchStr = URLEncoder.encode(searchStr, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "redirect:" +
                request.getSession().getServletContext().getAttribute("portalUrl") +
                "search/index/?searchStr=" + searchStr;
    }

}
