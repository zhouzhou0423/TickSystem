package service.impl;

import dao.MovieInfoDao;
import dao.WebAdmDao;
import dao.impl.MovieInfoDaoImpl;
import dao.impl.WebAdmDaoImpl;
import service.WebAdmService;
import vo.*;

import java.util.List;

/**
 * @author zhou_zhou
 * @date 2019/4/12 11:15
 */
public class WebAdmServiceImpl implements WebAdmService {
    WebAdmDao dao = new WebAdmDaoImpl();
    @Override
    public List<CinemaInfo> findAllCinema() {
        return dao.findAllCinema();
    }

    @Override
    public List<CinemaInfo> findNewCinema() {
        return dao.findNewCinema();
    }

    @Override
    public boolean changeCinemaState(int cinemaId) {
        return dao.changeCinemaState(cinemaId);
    }

    @Override
    public List<MovieInfo> findAllMovies() throws Exception {
        MovieInfoDao movieInfoDao = new MovieInfoDaoImpl();
        return movieInfoDao.findAllMovies();
    }

    @Override
    public List<CommentsInfo> findAllComment() {
        return dao.findAllComment();
    }

    @Override
    public List<ActorInfo> findAllActor() {
        return dao.findAllActor();
    }

    @Override
    public List<RoleInfo> findAllRole() {
        return dao.findAllRole();
    }
}
