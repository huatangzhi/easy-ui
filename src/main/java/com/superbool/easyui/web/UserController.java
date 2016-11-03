package com.superbool.easyui.web;

import com.google.common.base.Strings;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.superbool.easyui.dao.UserInfoDao;
import com.superbool.easyui.model.PageBean;
import com.superbool.easyui.model.UserInfo;
import com.superbool.easyui.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * Created by superbool on 2016/11/3.
 */
@Controller
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    @Autowired
    UserInfoDao userInfoDao;

    @RequestMapping(value = "/userDelete", method = RequestMethod.POST, produces = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public void delete(UserInfo userInfo, Model model) {
        int delId = userInfo.getId();

        try {

            int delNums = userInfoDao.userDelete(delId);
            if (delNums == 1) {
                model.addAttribute("success", "true");
            } else {
                model.addAttribute("errorMsg", "ɾ��ʧ��");
            }

        } catch (Exception e) {

            e.printStackTrace();
        } finally {

        }

    }

    @RequestMapping(value = "/userSave", method = RequestMethod.POST, produces = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseBody
    public JsonObject save(UserInfo userInfo) {

        LOGGER.info("save user={}", userInfo);


        try {
            int saveNums = 0;

            if (userInfo.getId()>0) {
                saveNums = userInfoDao.userModify(userInfo);
            } else {
                saveNums = userInfoDao.userAdd(userInfo);
            }

            JsonObject result = new JsonObject();
            if (saveNums == 1) {
                result.addProperty("success", "true");
            } else {
                result.addProperty("success", "true");
                result.addProperty("errorMsg", "服务器错误");
            }
            JsonElement element = new Gson().toJsonTree(userInfo);
            result.add("data", element);
            return result;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {

        }

        return null;
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST, produces = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public void list(UserInfo userInfo,
                     @RequestParam(value = "page") String page,
                     @RequestParam(value = "rows") String rows, Model model) {

        PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));

        try {

            JsonObject result = new JsonObject();
            JsonArray jsonArray = JsonUtil.formatRsToJsonArray(userInfoDao.userList(pageBean));
            int total = userInfoDao.userCount();
            result.add("rows", jsonArray);
            result.addProperty("total", total);
            model.addAttribute(result);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {

        }
    }


    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @ResponseBody
    public String hello() {
        return "hello world";
    }


}
