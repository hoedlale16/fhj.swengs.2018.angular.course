package at.fh.ima.swengs.moviedbv3.facade;

import at.fh.ima.swengs.moviedbv3.dto.ActorDTO;
import at.fh.ima.swengs.moviedbv3.model.Actor;
import at.fh.ima.swengs.moviedbv3.service.ActorService;
import at.fh.ima.swengs.moviedbv3.service.MediaService;
import at.fh.ima.swengs.moviedbv3.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.stream.Collectors;

@Service()
@Transactional
public class ActorFacade {

    @Autowired
    private ActorService actorService;

    @Autowired
    private MovieService movieService;

    @Autowired
    private MediaService mediaService;

    void mapDtoToEntity(ActorDTO dto, Actor entity) {
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setAlive(dto.isAlive());
        entity.setRating(dto.getRating());
        entity.setGender(dto.getGender());
        entity.setDayOfBirth(dto.getDayOfBirth());
        entity.setMovies(movieService.getMovies(dto.getMovies()));
        entity.setPictures(dto.getPictures());
    }

    private void mapEntityToDto(Actor entity, ActorDTO dto) {
        dto.setId(entity.getId());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setAlive(entity.isAlive());
        dto.setRating(entity.getRating());
        dto.setGender(entity.getGender());
        dto.setDayOfBirth(entity.getDayOfBirth());
        dto.setMovies(entity.getMovies().stream().map((m) -> m.getId()).collect(Collectors.toSet()));
        dto.setPictures(entity.getPictures());
    }

    public ActorDTO update(Long id, ActorDTO dto) {
        Actor entity = actorService.findById(id).get();
        mapDtoToEntity(dto, entity);
        mapEntityToDto(actorService.save(entity), dto);
        return dto;
    }

    public ActorDTO create(ActorDTO dto) {
        Actor entity = new Actor();
        mapDtoToEntity(dto, entity);
        mapEntityToDto(actorService.save(entity), dto);
        return dto;
    }

    public ActorDTO getById(Long id) {
        Actor entity = actorService.findById(id).get();
        ActorDTO dto = new ActorDTO();
        mapEntityToDto(entity, dto);
        return dto;
    }
}
