package com.dubbo.movie.serivce;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;

import com.dubbo.movie.api.cinema.CinemaServiceAPI;
import com.dubbo.movie.dao.*;
import com.dubbo.movie.model.*;
import com.dubbo.movie.vo.cinema.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Service(interfaceClass = CinemaServiceAPI.class,executes = 10)  //服务端并发执行（或占用线程池线程数）不能超过10个。
public class DefaultCinemaServiceImpl implements CinemaServiceAPI {

    @Autowired
    private CinemaMapper cinemaMapper;
    @Autowired
    private AreaDictMapper areaDictMapper;
    @Autowired
    private BrandDictMapper brandDictMapper;
    @Autowired
    private HallDictMapper hallDictMapper;
    @Autowired
    private HallFilmInfoMapper hallFilmInfoMapper;
    @Autowired
    private ScreeningMapper screeningMapper;


    //1、根据CinemaQueryVO，查询影院列表
    @Override
    public Page<CinemaVO> getCinemas(CinemaQueryVO cinemaQueryVO){
        // 业务实体集合
        List<CinemaVO> cinemaVOS = new ArrayList<>();

        Page<Cinema> page = new Page<>(cinemaQueryVO.getNowPage(),cinemaQueryVO.getPageSize());
        // 判断是否传入查询条件 -> brandId,distId,hallType 是否==99
        EntityWrapper<Cinema> entityWrapper = new EntityWrapper<>();
        if(cinemaQueryVO.getBrandId() != 99){
            entityWrapper.eq("brand_id",cinemaQueryVO.getBrandId());
        }
        if(cinemaQueryVO.getDistrictId() != 99){
            entityWrapper.eq("area_id",cinemaQueryVO.getDistrictId());
        }
        if(cinemaQueryVO.getHallType() != 99){  // %#3#%
            entityWrapper.like("hall_ids","%#+"+cinemaQueryVO.getHallType()+"+#%");
        }

        // 将数据实体转换为业务实体
        List<Cinema> cinemas = cinemaMapper.selectPage(page, entityWrapper);
        for(Cinema cinema : cinemas){
            CinemaVO cinemaVO = new CinemaVO();

            cinemaVO.setUuid(cinema.getId()+"");
            cinemaVO.setMinimumPrice(cinema.getMinimumPrice()+"");
            cinemaVO.setCinemaName(cinema.getCinemaName());
            cinemaVO.setAddress(cinema.getCinemaAddress());

            cinemaVOS.add(cinemaVO);
        }

        // 根据条件，判断影院列表总数
        long counts = cinemaMapper.selectCount(entityWrapper);

        // 组织返回值对象，给page传入总数和单页数量后，它能计算页数
        Page<CinemaVO> result = new Page<>();
        result.setRecords(cinemaVOS);
        result.setSize(cinemaQueryVO.getPageSize());
        result.setTotal(counts);

        return result;
    }


    //2、根据条件获取品牌列表[除了就99以外，其他的数字为isActive]
    @Override
    public List<BrandVO> getBrands(int brandId){
        boolean flag = false;
        List<BrandVO> brandVOS = new ArrayList<>();
        // 判断brandId是否存在
        BrandDict brandDict = brandDictMapper.selectById(brandId);
        // 判断brandId 是否等于 99
        if(brandId == 99 || brandDict==null || brandDict.getId() == null){
            flag = true;
        }
        // 查询所有列表
        List<BrandDict> brandDicts = brandDictMapper.selectList(null);
        // 判断flag如果为true，则将99置为isActive
        for(BrandDict brand : brandDicts){
            BrandVO brandVO = new BrandVO();
            brandVO.setBrandName(brand.getShowName());
            brandVO.setBrandId(brand.getId()+"");
            // 如果flag为true，则需要99，如为false，则匹配上的内容为active
            if(flag){
                if(brand.getId() == 99){
                    brandVO.setActive(true);
                }
            }else{
                if(brand.getId() == brandId){
                    brandVO.setActive(true);
                }
            }

            brandVOS.add(brandVO);
        }

        return brandVOS;
    }
    //3、获取行政区域列表
    @Override
    public List<AreaVO> getAreas(int areaId){
        boolean flag = false;
        List<AreaVO> areaVOS = new ArrayList<>();
        // 判断brandId是否存在
        AreaDict moocAreaDictT = areaDictMapper.selectById(areaId);
        // 判断brandId 是否等于 99
        if(areaId == 99 || moocAreaDictT==null || moocAreaDictT.getId() == null){
            flag = true;
        }
        // 查询所有列表
        List<AreaDict> areaDicts = areaDictMapper.selectList(null);
        // 判断flag如果为true，则将99置为isActive
        for(AreaDict area : areaDicts){
            AreaVO areaVO = new AreaVO();
            areaVO.setAreaName(area.getShowName());
            areaVO.setAreaId(area.getId()+"");
            // 如果flag为true，则需要99，如为false，则匹配上的内容为active
            if(flag){
                if(area.getId() == 99){
                    areaVO.setActive(true);
                }
            }else{
                if(area.getId() == areaId){
                    areaVO.setActive(true);
                }
            }

            areaVOS.add(areaVO);
        }

        return areaVOS;
    }
    //4、获取影厅类型列表
    @Override
    public List<HallTypeVO> getHallTypes(int hallType){
        boolean flag = false;
        List<HallTypeVO> hallTypeVOS = new ArrayList<>();
        // 判断brandId是否存在
        HallDict hallDict = hallDictMapper.selectById(hallType);
        // 判断brandId 是否等于 99
        if(hallType == 99 || hallDict==null || hallDict.getId() == null){
            flag = true;
        }
        // 查询所有列表
        List<HallDict> hallDicts = hallDictMapper.selectList(null);
        // 判断flag如果为true，则将99置为isActive
        for(HallDict hall : hallDicts){
            HallTypeVO hallTypeVO = new HallTypeVO();
            hallTypeVO.setHalltypeName(hall.getShowName());
            hallTypeVO.setHalltypeId(hall.getId()+"");
            // 如果flag为true，则需要99，如为false，则匹配上的内容为active
            if(flag){
                if(hall.getId() == 99){
                    hallTypeVO.setActive(true);
                }
            }else{
                if(hall.getId() == hallType){
                    hallTypeVO.setActive(true);
                }
            }

            hallTypeVOS.add(hallTypeVO);
        }

        return hallTypeVOS;
    }
    //5、根据影院编号，获取影院信息
    @Override
    public CinemaInfoVO getCinemaInfoById(int cinemaId){

        // 数据实体
        Cinema cinema = cinemaMapper.selectById(cinemaId);
        // 将数据实体转换成业务实体
        CinemaInfoVO cinemaInfoVO = new CinemaInfoVO();
        cinemaInfoVO.setCinemaAdress(cinema.getCinemaAddress());
        cinemaInfoVO.setImgUrl(cinema.getImgAddress());
        cinemaInfoVO.setCinemaPhone(cinema.getCinemaPhone());
        cinemaInfoVO.setCinemaName(cinema.getCinemaName());
        cinemaInfoVO.setCinemaId(cinema.getId()+"");

        return cinemaInfoVO;
    }

    //6、获取所有电影的信息和对应的放映场次信息，根据影院编号
    @Override
    public List<FilmInfoVO> getFilmInfoByCinemaId(int cinemaId){

        List<FilmInfoVO> filmInfos = screeningMapper.getFilmInfos(cinemaId);

        return filmInfos;
    }


    //7、根据放映场次ID获取放映信息
    @Override
    public HallInfoVO getFilmFieldInfo(int fieldId){

        HallInfoVO hallInfoVO = screeningMapper.getHallInfo(fieldId);

        return hallInfoVO;
    }
    //8、根据放映场次查询播放的电影编号，然后根据电影编号获取对应的电影信息
    @Override
    public FilmInfoVO getFilmInfoByFieldId(int fieldId){

        FilmInfoVO filmInfoVO = screeningMapper.getFilmInfoById(fieldId);

        return filmInfoVO;
    }

    @Override
    public OrderQueryVO getOrderNeeds(int fieldId) {

        OrderQueryVO orderQueryVO = new OrderQueryVO();

        Screening screening = screeningMapper.selectById(fieldId);

        orderQueryVO.setCinemaId(screening.getCinemaId()+"");
        orderQueryVO.setFilmPrice(screening.getPrice()+"");

        return orderQueryVO;
    }

}
