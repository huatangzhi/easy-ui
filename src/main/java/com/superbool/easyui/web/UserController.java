package com.superbool.easyui.web;

import com.google.common.base.Strings;
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

import java.util.ArrayList;
import java.util.List;


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


    @RequestMapping(value = "/userSearch", method = RequestMethod.POST)
    @ResponseBody
    public String search(@RequestParam(value = "key") String key,
                         @RequestParam(value = "value") String value) {
        LOGGER.info("search key={},value={}", key, value);

        List<UserInfo> userInfos = new ArrayList<>();
        JsonObject result = new JsonObject();
        if (Strings.isNullOrEmpty(value)) {
            result.addProperty("errorMsg", "搜索内容不能为空");
        } else {
            try {
                switch (key) {
                    case "cardId":
                        userInfos = userInfoDao.getByCardId(value);
                        break;
                    case "name":
                        userInfos = userInfoDao.getByName(value);
                        break;
                    case "department":
                        userInfos = userInfoDao.getByDepart(value);
                        break;
                    case "id":
                        try {
                            int id = Integer.valueOf(value);
                            userInfos.add(userInfoDao.getById(id));
                        } catch (NumberFormatException e) {
                            result.addProperty("errorMsg", "编号必须是整数");
                        } catch (Exception e) {
                            LOGGER.error("get by id error id={}", value, e);
                            result.addProperty("errorMsg", "没有查询到相关数据");
                        }
                        break;
                    case "sameId":
                        if (value.equals("是") || value.equals("否")) {
                            userInfos = userInfoDao.getBySameId(value.equals("是"));
                        } else {
                            result.addProperty("errorMsg", "只能搜索是或者否");
                        }
                        break;
                    default:
                        break;
                }

                JsonElement jsonElement = new Gson().toJsonTree(userInfos);
                result.add("rows", jsonElement);
                result.addProperty("total", userInfos.size());

            } catch (Exception e) {
                LOGGER.error("userSearch error!", e);
                result.addProperty("errorMsg", "服务器错误");
            }
        }

        LOGGER.info("return result={}", result);

        return result.toString();
    }

}
