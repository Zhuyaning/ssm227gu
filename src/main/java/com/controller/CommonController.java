package com.controller;

import com.alibaba.fastjson.JSON;
import com.annotation.IgnoreAuth;
import com.baidu.aip.face.AipFace;
import com.baidu.aip.face.MatchRequest;
import com.baidu.aip.util.Base64Util;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.entity.ConfigEntity;
import com.service.CommonService;
import com.service.ConfigService;
import com.utils.BaiduUtil;
import com.utils.FileUtil;
import com.utils.Result;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 通用接口
 */
@RestController
@CrossOrigin
public class CommonController {
    @Autowired
    private CommonService commonService;

    @Autowired
    private ConfigService configService;

    private static AipFace client = null;

    private static String BAIDU_DITU_AK = null;

    @RequestMapping("/location")
    public Result location(String lng, String lat) {
        if (BAIDU_DITU_AK == null) {
            BAIDU_DITU_AK = configService.selectOne(new EntityWrapper<ConfigEntity>().eq("name", "baidu_ditu_ak")).getValue();
            if (BAIDU_DITU_AK == null) {
                return Result.error("请在配置管理中正确配置baidu_ditu_ak");
            }
        }
        Map<String, String> map = BaiduUtil.getCityByLonLat(BAIDU_DITU_AK, lng, lat);
        return Result.ok().put("data", map);
    }

    /**
     * 人脸比对
     *
     * @param face1 人脸1
     * @param face2 人脸2
     */
    @RequestMapping("/matchFace")
    public Result matchFace(String face1, String face2, HttpServletRequest request) {
        if (client == null) {
            /*String AppID = configService.selectOne(new EntityWrapper<ConfigEntity>().eq("name", "AppID")).getValue();*/
            String apikey = configService.selectOne(new EntityWrapper<ConfigEntity>().eq("name", "APIKey")).getValue();
            String secretKey = configService.selectOne(new EntityWrapper<ConfigEntity>().eq("name", "SecretKey")).getValue();
            String token = BaiduUtil.getAuth(apikey, secretKey);
            if (token == null) {
                return Result.error("请在配置管理中正确配置APIKey和SecretKey");
            }
            client = new AipFace(null, apikey, secretKey);
            client.setConnectionTimeoutInMillis(2000);
            client.setSocketTimeoutInMillis(60000);
        }
        JSONObject res = null;
        try {
            File file1 = new File(request.getSession().getServletContext().getRealPath("/upload") + File.separator + face1);
            File file2 = new File(request.getSession().getServletContext().getRealPath("/upload") + File.separator + face2);
            String img1 = Base64Util.encode(FileUtil.fileToByte(file1));
            String img2 = Base64Util.encode(FileUtil.fileToByte(file2));
            MatchRequest req1 = new MatchRequest(img1, "BASE64");
            MatchRequest req2 = new MatchRequest(img2, "BASE64");
            ArrayList<MatchRequest> requests = new ArrayList<>();
            requests.add(req1);
            requests.add(req2);
            res = client.match(requests);
            System.out.println(res.get("result"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return Result.error("文件不存在");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Result.ok().put("data", JSON.parse(res.get("result").toString()));
    }

    /**
     * 获取table表中的column列表(联动接口)
     */
    @IgnoreAuth
    @RequestMapping("/option/{tableName}/{columnName}")
    public Result getOption(@PathVariable("tableName") String tableName, @PathVariable("columnName") String columnName, String level, String parent) {
        Map<String, Object> params = new HashMap<>();
        params.put("table", tableName);
        params.put("column", columnName);
        if (StringUtils.isNotBlank(level)) {
            params.put("level", level);
        }
        if (StringUtils.isNotBlank(parent)) {
            params.put("parent", parent);
        }
        List<String> data = commonService.getOption(params);
        return Result.ok().put("data", data);
    }

    /**
     * 根据table中的column获取单条记录
     */
    @IgnoreAuth
    @RequestMapping("/follow/{tableName}/{columnName}")
    public Result getFollowByOption(@PathVariable("tableName") String tableName, @PathVariable("columnName") String columnName, @RequestParam String columnValue) {
        Map<String, Object> params = new HashMap<>();
        params.put("table", tableName);
        params.put("column", columnName);
        params.put("columnValue", columnValue);
        Map<String, Object> result = commonService.getFollowByOption(params);
        return Result.ok().put("data", result);
    }

    /**
     * 修改table表的sfsh状态
     */
    @RequestMapping("/sh/{tableName}")
    public Result sh(@PathVariable("tableName") String tableName, @RequestBody Map<String, Object> map) {
        map.put("table", tableName);
        commonService.sh(map);
        return Result.ok();
    }

    /**
     * 获取需要提醒的记录数
     *
     * @param tableName
     * @param columnName
     * @param type       1:数字 2:日期
     * @param map
     * @return
     */
    @IgnoreAuth
    @RequestMapping("/remind/{tableName}/{columnName}/{type}")
    public Result remindCount(@PathVariable("tableName") String tableName, @PathVariable("columnName") String columnName,
                              @PathVariable("type") String type, @RequestParam Map<String, Object> map) {
        map.put("table", tableName);
        map.put("column", columnName);
        map.put("type", type);

        if (type.equals("2")) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Calendar c = Calendar.getInstance();
            Date remindStartDate = null;
            Date remindEndDate = null;
            if (map.get("remindstart") != null) {
                int remindStart = Integer.parseInt(map.get("remindstart").toString());
                c.setTime(new Date());
                c.add(Calendar.DAY_OF_MONTH, remindStart);
                remindStartDate = c.getTime();
                map.put("remindstart", sdf.format(remindStartDate));
            }
            if (map.get("remindend") != null) {
                int remindEnd = Integer.parseInt(map.get("remindend").toString());
                c.setTime(new Date());
                c.add(Calendar.DAY_OF_MONTH, remindEnd);
                remindEndDate = c.getTime();
                map.put("remindend", sdf.format(remindEndDate));
            }
        }

        int count = commonService.remindCount(map);
        return Result.ok().put("count", count);
    }

    /**
     * 单列求和
     */
    @IgnoreAuth
    @RequestMapping("/cal/{tableName}/{columnName}")
    public Result cal(@PathVariable("tableName") String tableName, @PathVariable("columnName") String columnName) {
        Map<String, Object> params = new HashMap<>();
        params.put("table", tableName);
        params.put("column", columnName);
        Map<String, Object> result = commonService.selectCal(params);
        return Result.ok().put("data", result);
    }

    /**
     * 分组统计
     */
    @IgnoreAuth
    @RequestMapping("/group/{tableName}/{columnName}")
    public Result group(@PathVariable("tableName") String tableName, @PathVariable("columnName") String columnName) {
        Map<String, Object> params = new HashMap<>();
        params.put("table", tableName);
        params.put("column", columnName);
        List<Map<String, Object>> result = commonService.selectGroup(params);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for (Map<String, Object> m : result) {
            for (String k : m.keySet()) {
                if (m.get(k) instanceof Date) {
                    m.put(k, sdf.format((Date) m.get(k)));
                }
            }
        }
        return Result.ok().put("data", result);
    }

    /**
     * （按值统计）
     */
    @IgnoreAuth
    @RequestMapping("/value/{tableName}/{xColumnName}/{yColumnName}")
    public Result value(@PathVariable("tableName") String tableName, @PathVariable("yColumnName") String yColumnName, @PathVariable("xColumnName") String xColumnName) {
        Map<String, Object> params = new HashMap<>();
        params.put("table", tableName);
        params.put("xColumn", xColumnName);
        params.put("yColumn", yColumnName);
        List<Map<String, Object>> result = commonService.selectValue(params);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for (Map<String, Object> m : result) {
            for (String k : m.keySet()) {
                if (m.get(k) instanceof Date) {
                    m.put(k, sdf.format((Date) m.get(k)));
                }
            }
        }
        return Result.ok().put("data", result);
    }

}
