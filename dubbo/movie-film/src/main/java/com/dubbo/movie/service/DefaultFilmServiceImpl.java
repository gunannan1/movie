package com.dubbo.movie.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.dubbo.movie.api.film.FilmServiceApi;
import com.dubbo.movie.dao.*;
import com.dubbo.movie.model.*;
import com.dubbo.movie.utils.DateUtil;
import com.dubbo.movie.vo.film.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Service(interfaceClass = FilmServiceApi.class)
public class DefaultFilmServiceImpl implements FilmServiceApi {

    @Autowired
    private BannerMapper bannerMapper;

    @Autowired
    private FilmMapper filmMapper;

    @Autowired
    private CategoryDictMapper categoryDictMapper;

    @Autowired
    private YearDictMapper yearDictMapper;

    @Autowired
    private SourceDictMapper sourceDictMapper;

    @Autowired
    private FilmInfoMapper filmInfoMapper;

    @Autowired
    private ActorMapper actorMapper;

    /**
     * 得到图片
     * @return
     */
    @Override
    public List<BannerVO> getBanners() {
        List<BannerVO> result = new ArrayList<>();
        List<Banner> banners = bannerMapper.selectList(null);

        for(Banner banner : banners){
            BannerVO bannerVO = new BannerVO();
            bannerVO.setBannerId(banner.getId()+"");
            bannerVO.setBannerUrl(banner.getBannerUrl());
            bannerVO.setBannerAddress(banner.getBannerAddress());
            result.add(bannerVO);
        }

        return result;
    }

    /**
     * 把List<Film> 中的内容转换进List<FilmInfoVO>
     * @param films
     * @return
     */
    private List<FilmInformationVO> getFilmInfos(List<Film> films){
        List<FilmInformationVO> filmInformationVOS = new ArrayList<>();
        for(Film film : films){
            FilmInformationVO filmInformationVO = new FilmInformationVO();
            filmInformationVO.setScore(film.getFilmScore());
            filmInformationVO.setImgAddress(film.getImgAddress());
            filmInformationVO.setFilmType(film.getFilmType());
            filmInformationVO.setFilmScore(film.getFilmScore());
            filmInformationVO.setFilmName(film.getFilmName());
            filmInformationVO.setFilmId(film.getId()+"");
            filmInformationVO.setExpectNum(film.getFilmPresalenum());
            filmInformationVO.setBoxNum(film.getFilmBoxOffice());
            filmInformationVO.setShowTime(DateUtil.getDay(film.getFilmTime()));

            // 将转换的对象放入结果集
            filmInformationVOS.add(filmInformationVO);
        }

        return filmInformationVOS;
    }

    /**
     * 得到热映电影
     * @param isLimit
     * @param nums
     * @param nowPage
     * @param sortId
     * @param sourceId
     * @param yearId
     * @param catId
     * @return
     */
    @Override
    public FilmVO getHotFilms(boolean isLimit, int nums, int nowPage, int sortId, int sourceId, int yearId, int catId) {
        FilmVO filmVO = new FilmVO();
        List<FilmInformationVO> filmInformationVOS = new ArrayList<>();

        // 热映影片的限制条件
        EntityWrapper<Film> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("film_status","1");

        // 判断是否是首页需要的内容
        if(isLimit){
            // 如果是，则限制条数、限制内容为热映影片
            Page<Film> page = new Page<>(1,nums);
            List<Film> films = filmMapper.selectPage(page, entityWrapper);

            filmInformationVOS = getFilmInfos(films);
            filmVO.setFilmNum(films.size());
            filmVO.setFilmInformationVO(filmInformationVOS);
        }else{
            // 如果不是，则是列表页，同样需要限制内容为热映影片
            Page<Film> page = null;
            // 根据sortId的不同，来组织不同的Page对象
            // 1-按热门搜索，2-按时间搜索，3-按评价搜索
            switch (sortId){
                case 1 :
                    page = new Page<>(nowPage,nums,"film_box_office");
                    break;
                case 2 :
                    page = new Page<>(nowPage,nums,"film_time");
                    break;
                case 3 :
                    page = new Page<>(nowPage,nums,"film_score");
                    break;
                default:
                    page = new Page<>(nowPage,nums,"film_box_office");
                    break;
            }

            // 如果sourceId,yearId,catId 不为99 ,则表示要按照对应的编号进行查询
            if(sourceId != 99){
                entityWrapper.eq("film_source",sourceId);
            }
            if(yearId != 99){
                entityWrapper.eq("film_date",yearId);
            }
            if(catId != 99){
                // #2#4#22#，字符串匹配
                String catStr = "%#"+catId+"#%";
                entityWrapper.like("film_cats",catStr);
            }

            List<Film> moocFilms = filmMapper.selectPage(page, entityWrapper);
            // 组织filmInfos
            filmInformationVOS = getFilmInfos(moocFilms);
            filmVO.setFilmNum(moocFilms.size());

            // 需要总页数 totalCounts/nums -> 0 + 1 = 1
            // 每页10条，我现在有6条 -> 1
            int totalCounts = filmMapper.selectCount(entityWrapper);
            int totalPages = (totalCounts/nums)+1;

            filmVO.setFilmInformationVO(filmInformationVOS);
            filmVO.setTotalPage(totalPages);
            filmVO.setNowPage(nowPage);
        }

        return filmVO;
    }

    /**
     * 将要上映的电影列表
     * @param isLimit
     * @param nums
     * @param nowPage
     * @param sortId
     * @param sourceId
     * @param yearId
     * @param catId
     * @return
     */
    @Override
    public FilmVO getSoonFilms(boolean isLimit,int nums,int nowPage,int sortId,int sourceId,int yearId,int catId) {
        FilmVO filmVO = new FilmVO();
        List<FilmInformationVO> filmInformationVOS = new ArrayList<>();

        // 即将上映影片的限制条件
        EntityWrapper<Film> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("film_status","2");
        // 判断是否是首页需要的内容
        if(isLimit){
            // 如果是，则限制条数、限制内容为热映影片
            Page<Film> page = new Page<>(1,nums);
            List<Film> moocFilms = filmMapper.selectPage(page, entityWrapper);
            // 组织filmInfos
            filmInformationVOS = getFilmInfos(moocFilms);
            filmVO.setFilmNum(moocFilms.size());
            filmVO.setFilmInformationVO(filmInformationVOS);
        }else{
            // 如果不是，则是列表页，同样需要限制内容为即将上映影片
            Page<Film> page = null;
            // 根据sortId的不同，来组织不同的Page对象
            // 1-按热门搜索，2-按时间搜索，3-按评价搜索
            switch (sortId){
                case 1 :
                    page = new Page<>(nowPage,nums,"film_preSaleNum");
                    break;
                case 2 :
                    page = new Page<>(nowPage,nums,"film_time");
                    break;
                case 3 :
                    page = new Page<>(nowPage,nums,"film_preSaleNum");
                    break;
                default:
                    page = new Page<>(nowPage,nums,"film_preSaleNum");
                    break;
            }

            // 如果sourceId,yearId,catId 不为99 ,则表示要按照对应的编号进行查询
            if(sourceId != 99){
                entityWrapper.eq("film_source",sourceId);
            }
            if(yearId != 99){
                entityWrapper.eq("film_date",yearId);
            }
            if(catId != 99){
                // #2#4#22#
                String catStr = "%#"+catId+"#%";
                entityWrapper.like("film_cats",catStr);
            }

            List<Film> moocFilms = filmMapper.selectPage(page, entityWrapper);
            // 组织filmInfos
            filmInformationVOS = getFilmInfos(moocFilms);
            filmVO.setFilmNum(moocFilms.size());

            // 需要总页数 totalCounts/nums -> 0 + 1 = 1
            // 每页10条，我现在有6条 -> 1
            int totalCounts = filmMapper.selectCount(entityWrapper);
            int totalPages = (totalCounts/nums)+1;

            filmVO.setFilmInformationVO(filmInformationVOS);
            filmVO.setTotalPage(totalPages);
            filmVO.setNowPage(nowPage);
        }

        return filmVO;
    }

    /**
     * 经典电影列表
     * @param nums
     * @param nowPage
     * @param sortId
     * @param sourceId
     * @param yearId
     * @param catId
     * @return
     */
    @Override
    public FilmVO getClassicFilms(int nums, int nowPage, int sortId, int sourceId, int yearId, int catId) {
        FilmVO filmVO = new FilmVO();
        List<FilmInformationVO> filmInformationVOS = new ArrayList<>();

        // 即将上映影片的限制条件
        EntityWrapper<Film> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("film_status","3");

        // 如果不是，则是列表页，同样需要限制内容为即将上映影片
        Page<Film> page = null;
        // 根据sortId的不同，来组织不同的Page对象
        // 1-按热门搜索，2-按时间搜索，3-按评价搜索
        switch (sortId){
            case 1 :
                page = new Page<>(nowPage,nums,"film_box_office");
                break;
            case 2 :
                page = new Page<>(nowPage,nums,"film_time");
                break;
            case 3 :
                page = new Page<>(nowPage,nums,"film_score");
                break;
            default:
                page = new Page<>(nowPage,nums,"film_box_office");
                break;
        }

        // 如果sourceId,yearId,catId 不为99 ,则表示要按照对应的编号进行查询
        if(sourceId != 99){
            entityWrapper.eq("film_source",sourceId);
        }
        if(yearId != 99){
            entityWrapper.eq("film_date",yearId);
        }
        if(catId != 99){
            // #2#4#22#
            String catStr = "%#"+catId+"#%";
            entityWrapper.like("film_cats",catStr);
        }

        List<Film> moocFilms = filmMapper.selectPage(page, entityWrapper);
        // 组织filmInfos
        filmInformationVOS = getFilmInfos(moocFilms);
        filmVO.setFilmNum(moocFilms.size());

        // 需要总页数 totalCounts/nums -> 0 + 1 = 1
        // 每页10条，我现在有6条 -> 1
        int totalCounts = filmMapper.selectCount(entityWrapper);
        int totalPages = (totalCounts/nums)+1;

        filmVO.setFilmInformationVO(filmInformationVOS);
        filmVO.setTotalPage(totalPages);
        filmVO.setNowPage(nowPage);

        return filmVO;
    }


    /**
     * 得到票房排名
     * @return
     */
    @Override
    public List<FilmInformationVO> getBoxRanking() {
        // 条件 -> 正在上映的，票房前10名
        EntityWrapper<Film> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("film_status","1");

        //根据票房降序
        Page<Film> page = new Page<>(1,10,"film_box_office");

        List<Film> moocFilms = filmMapper.selectPage(page,entityWrapper);

        List<FilmInformationVO> filmInformationVOS = getFilmInfos(moocFilms);

        return filmInformationVOS;
    }

    @Override
    public List<FilmInformationVO> getExpectRanking() {
        // 条件 -> 即将上映的，预售前10名
        EntityWrapper<Film> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("film_status","2");

        Page<Film> page = new Page<>(1,10,"film_preSaleNum");

        List<Film> moocFilms = filmMapper.selectPage(page,entityWrapper);

        List<FilmInformationVO> filmInformationVOS = getFilmInfos(moocFilms);

        return filmInformationVOS;

    }


    @Override
    public List<FilmInformationVO> getTop() {
        // 条件 -> 正在上映的，评分前10名
        EntityWrapper<Film> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("film_status","1");

        Page<Film> page = new Page<>(1,10,"film_score");

        List<Film> moocFilms = filmMapper.selectPage(page,entityWrapper);

        List<FilmInformationVO> filmInformationVOS = getFilmInfos(moocFilms);

        return filmInformationVOS;
    }

    @Override
    public List<CatVO> getCats() {
        List<CatVO> cats = new ArrayList<>();
        // 查询实体对象 - CategoryDict
        List<CategoryDict> categoryDicts = categoryDictMapper.selectList(null);
        // 将实体对象转换为业务对象 - CatVO
        for(CategoryDict categoryDict : categoryDicts){
            CatVO catVO = new CatVO();
            catVO.setCatId(categoryDict.getId()+"");
            catVO.setCatName(categoryDict.getShowName());

            cats.add(catVO);
        }

        return cats;
    }

    @Override
    public List<SourceVO> getSources() {
        List<SourceVO> sources = new ArrayList<>();
        List<SourceDict> sourceDicts = sourceDictMapper.selectList(null);
        for(SourceDict sourceDict : sourceDicts){
            SourceVO sourceVO = new SourceVO();

            sourceVO.setSourceId(sourceDict.getId()+"");
            sourceVO.setSourceName(sourceDict.getShowName());

            sources.add(sourceVO);
        }
        return sources;
    }

    @Override
    public List<YearVO> getYears() {
        List<YearVO> years = new ArrayList<>();
        // 查询实体对象 - CategoryDict
        List<YearDict> yearDicts = yearDictMapper.selectList(null);
        // 将实体对象转换为业务对象 - CatVO
        for(YearDict yearDict : yearDicts){
            YearVO yearVO = new YearVO();
            yearVO.setYearId(yearDict.getId()+"");
            yearVO.setYearName(yearDict.getShowName());

            years.add(yearVO);
        }
        return years;
    }

    @Override
    public FilmDetailVO getFilmDetail(int searchType, String searchParam) {
        FilmDetailVO filmDetailVO = null;
        // searchType 1-按名称  2-按ID的查找
        if(searchType == 1){
            filmDetailVO = filmMapper.getFilmDetailByName("%"+searchParam+"%");
        }else{
            filmDetailVO = filmMapper.getFilmDetailById(searchParam);
        }

        return filmDetailVO;
    }

    private FilmInfo getFilmInfo(String filmId){

        FilmInfo filmInfo = new FilmInfo();
        filmInfo.setFilmId(filmId);

        filmInfo = filmInfoMapper.selectOne(filmInfo);

        return filmInfo;
    }

    @Override
    public FilmDescVO getFilmDesc(String filmId) {

        FilmInfo filmInfo = getFilmInfo(filmId);

        FilmDescVO filmDescVO = new FilmDescVO();
        filmDescVO.setBiography(filmInfo.getBiography());
        filmDescVO.setFilmId(filmId);

        return filmDescVO;
    }

    @Override
    public ImgVO getImgs(String filmId) {

        FilmInfo filmInfo = getFilmInfo(filmId);
        // 图片地址是五个以逗号为分隔的链接URL
        String filmImgStr = filmInfo.getFilmImgs();
        String[] filmImgs = filmImgStr.split(",");

        ImgVO imgVO = new ImgVO();
        imgVO.setMainImg(filmImgs[0]);
        imgVO.setImg01(filmImgs[1]);
        imgVO.setImg02(filmImgs[2]);
        imgVO.setImg03(filmImgs[3]);
        imgVO.setImg04(filmImgs[4]);

        return imgVO;
    }

    @Override
    public ActorVO getDectInfo(String filmId) {

        FilmInfo filmInfo = getFilmInfo(filmId);

        // 获取导演编号
        Integer directId = filmInfo.getDirectorId();

        Actor actor = actorMapper.selectById(directId);

        ActorVO actorVO = new ActorVO();
        actorVO.setImgAddress(actor.getActorImg());
        actorVO.setDirectorName(actor.getActorName());

        return actorVO;
    }

    @Override
    public List<ActorVO> getActors(String filmId) {

        List<ActorVO> actors = actorMapper.getActors(filmId);

        return actors;
    }


}
