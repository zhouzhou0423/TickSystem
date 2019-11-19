package service;

import vo.*;

import java.util.List;

/**
 * @author zhou_zhou
 * @date 2019/4/12 11:14
 */
public interface WebAdmService {
    List<CinemaInfo> findAllCinema();
    List<CinemaInfo> findNewCinema();
    boolean changeCinemaState(int cinemaId);
    List<MovieInfo> findAllMovies() throws Exception;
    List<CommentsInfo> findAllComment();
    List<ActorInfo> findAllActor();
    List<RoleInfo> findAllRole();
}
