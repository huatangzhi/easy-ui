package com.superbool.easyui.web;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.superbool.easyui.dao.UserInfoDao;
import com.superbool.easyui.model.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
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

    @RequestMapping(value = "/userDelete", method = RequestMethod.POST)
    @ResponseBody
    public String delete(@RequestParam(value = "delId") Integer delId) {
        LOGGER.info("user delete user={}", delId);

        JsonObject jsonObject = new JsonObject();
        try {
            int delNums = userInfoDao.userDelete(delId);
            if (delNums == 1) {
                jsonObject.addProperty("success", "true");
            } else {
                jsonObject.addProperty("errorMsg", "删除失败");
            }

        } catch (Exception e) {

            LOGGER.error("userDelete error", e);
            jsonObject.addProperty("errorMsg", "服务器错误");
        }
        return jsonObject.toString();
    }

    @RequestMapping(value = "/userSave", method = RequestMethod.POST)
    @ResponseBody
    public String save(UserInfo userInfo) {

        LOGGER.info("save user={}", userInfo);
        JsonObject result = new JsonObject();
        try {
            int saveNums = 0;

            if (userInfo.getId() != null) {
                saveNums = userInfoDao.userModify(userInfo);
            } else {
                saveNums = userInfoDao.userAdd(userInfo);
            }

            LOGGER.info("saveNums={}", saveNums);

            if (saveNums == 1) {
                result.addProperty("success", "true");
            } else {
                result.addProperty("success", "true");
                result.addProperty("errorMsg", "没有需要的更新");
            }
            JsonElement element = new Gson().toJsonTree(userInfo);
            result.add("data", element);

        } catch (Exception e) {
            LOGGER.error("userSave error", e);
            result.addProperty("errorMsg", "身份证号和机构名称已存在，请检查输入");
        }

        return result.toString();
    }

    @RequestMapping(value = "/userList", method = RequestMethod.POST, produces = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseBody
    public String list(@RequestParam(value = "page") Integer page, @RequestParam(value = "rows") Integer rows) {

        LOGGER.info("page={},rows={}", page, rows);
        JsonObject result = new JsonObject();

        try {
            page--;

            JsonElement jsonElement = new Gson().toJsonTree(userInfoDao.getByPage(page * rows, rows));
            int total = userInfoDao.userCount();
            result.add("rows", jsonElement);
            result.addProperty("total", total);

            LOGGER.info("result={}", result);


        } catch (Exception e) {

            LOGGER.error("userList error!", e);
            result.addProperty("errorMsg", "服务器错误");
        }

        return result.toString();
    }

}
