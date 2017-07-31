package org.obsidian.scss.controller;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.obsidian.scss.bean.AdvertisementAndFlag;
import org.obsidian.scss.bean.IdList;
import org.obsidian.scss.bean.Show;
import org.obsidian.scss.entity.AdvFlag;
import org.obsidian.scss.entity.Advertisement;
import org.obsidian.scss.entity.ClientAndFlag;
import org.obsidian.scss.entity.Flag;
import org.obsidian.scss.service.AdvFlagService;
import org.obsidian.scss.service.AdvertisementService;
import org.obsidian.scss.service.FlagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by hp on 2017/7/18.
 */
@Controller
public class AdvertisementController {
    @Autowired
    private AdvertisementService advertisementService;
    @Autowired
    private AdvFlagService advFlagService;
    @Autowired
    private FlagService flagService;
    
    @RequestMapping("advertisement")
    @ResponseBody
    public Show advertisement(){
        List<AdvertisementAndFlag> advertisementAndFlags = new ArrayList<AdvertisementAndFlag>(); 
        List<Advertisement> advertisements = advertisementService.getTotalAdvInfo();
        for (int i=0 ; i < advertisements.size();i++){
            AdvertisementAndFlag advertisementAndFlag = new AdvertisementAndFlag();
            advertisementAndFlag.setAdvertisement(advertisements.get(i));
            List<AdvFlag> advFlags = advFlagService.getFlagId(advertisements.get(i).getAdvId());
            List<Flag> flags = new ArrayList<Flag>();
            for (int j = 0 ; j < advFlags.size();j++){
                flags.add(flagService.selectAdv(advFlags.get(j).getFlagId()));
            }
            advertisementAndFlag.setFlagList(flags);
            advertisementAndFlags.add(advertisementAndFlag);
        }
        Show show = new Show();
        show.setData(advertisementAndFlags);
        return show;
    }

    /**
     * 查询关键字的广告信息
     *
     * @return
     */
    @RequestMapping("advertisementSearchName")
    @ResponseBody
    public Show advertisementSearchName( @RequestParam("searchName") String searchName){
        List<AdvertisementAndFlag> advertisementAndFlags = new ArrayList<AdvertisementAndFlag>();
        List<Advertisement> advertisements = advertisementService.getTotalAdvInfo();
        for (int i=0 ; i < advertisements.size();i++){
            boolean flag = false;
            if (advertisements.get(i).getAdvContent().contains(searchName)){
                flag = true;
            }
            AdvertisementAndFlag advertisementAndFlag = new AdvertisementAndFlag();
           
            List<AdvFlag> advFlags = advFlagService.getFlagId(advertisements.get(i).getAdvId());
            List<Flag> flags = new ArrayList<Flag>();
            for (int j = 0 ; j < advFlags.size();j++){
                flags.add(flagService.selectAdv(advFlags.get(j).getFlagId()));
                if (flagService.selectAdv(advFlags.get(j).getFlagId()).getName().contains(searchName)){
                    flag = true;
                }
            }
            if (flag == true){
                advertisementAndFlag.setAdvertisement(advertisements.get(i));
                advertisementAndFlag.setFlagList(flags);
                advertisementAndFlags.add(advertisementAndFlag);
            }
            
        }
        Show show  = new Show();
        if (advertisementAndFlags.size() == 0 ){
            show.setMessage("未查到对应的广告内容或者标签所对应的广告");
        }else{
            show.setData(advertisementAndFlags);
        }
        return show;
    }

    /**
     * 添加广告信息
     */
    @RequestMapping("addAdvertisement")
    @ResponseBody
    public Show addAdvertisement(@RequestParam("flag") String flagString ,@RequestParam("content")String content){
        List<String> flagList = new ArrayList<String>();
        String [] arr = flagString.split("\\s+");
        for(String ss : arr){
            System.out.println("!!!!!"+ss);
            flagList.add(ss);
        }
        Show show = new Show();
        int res = advertisementService.insertAdv(content,flagList);
        if (res == 0){
            show.setStatus(0);
            show.setMessage("已经插入过该条广告");
        }else{
            show.setMessage("插入成功");
        }
        return show;
    }

    /**
     * 删除广告
     */
    @RequestMapping("deleteAdv")
    @ResponseBody
    public Show deleteAdv(@RequestParam("advList") String advIdList){
        Gson gson = new Gson();
        List<IdList> advList = gson.fromJson(advIdList,new TypeToken<List<IdList>>(){}.getType());
        for (int i =  0; i < advList.size();i++){
            advFlagService.deleteAdvFlag(advList.get(i).getId());
            advertisementService.delete(advList.get(i).getId());
        }
        Show show = new Show();
        show.setMessage("删除成功");
        return show;
    }
    /**
     * 删除广告标签
     */
    @RequestMapping("deleteAdvFlag")
    @ResponseBody
    public Show deleteAdvFlag(@RequestParam("flagId") int flagId,@RequestParam("advId") int advId){
        int res =advFlagService.deleteAdvOneFlag(advId,flagId);
        Show show = new Show();
        if (res == 1){
            show.setMessage("删除成功");
        }else{
            show.setMessage("删除失败");
            show.setStatus(0);
        }
        return show;
    }
    /**
     * 更新广告
     */
    @RequestMapping("updateAdv")
    @ResponseBody
    public Show updateAdv(@RequestParam("advContent") String json){
        Show show = new Show();
        Gson gson = new Gson();
        Advertisement advertisement = gson.fromJson(json,new TypeToken<Advertisement>(){}.getType());
        int res = advertisementService.updateAdv(advertisement);
        if (res == 0 ){
            show.setStatus(0);
            show.setMessage("更新失败");
        }
        return show;
    }
}
