package at.fh.ima.swengs.moviedbv3.model;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface GenreRepository  extends PagingAndSortingRepository<Genre,Long> {
}
