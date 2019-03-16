package com.dubbo.movie.service;

import com.alibaba.dubbo.config.annotation.Service;

import com.dubbo.movie.api.film.FilmAsyncServiceApi;
import com.dubbo.movie.dao.ActorMapper;
import com.dubbo.movie.dao.FilmInfoMapper;
import com.dubbo.movie.model.Actor;
import com.dubbo.movie.model.FilmInfo;
import com.dubbo.movie.vo.film.ActorVO;
import com.dubbo.movie.vo.film.FilmDescVO;
import com.dubbo.movie.vo.film.ImgVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Service(interfaceClass = FilmAsyncServiceApi.class)
public class DefaultFilmAsyncServiceImpl implements FilmAsyncServiceApi {

    @Autowired
    private FilmInfoMapper filmInfoMapper;

    @Autowired
    private ActorMapper actorMapper;

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
