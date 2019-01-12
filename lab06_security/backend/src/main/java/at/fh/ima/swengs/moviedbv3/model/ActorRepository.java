package at.fh.ima.swengs.moviedbv3.model;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource()
public interface ActorRepository extends PagingAndSortingRepository<Actor,Long> {

    //This would be exposed under the URL: http://localhost:8080/actors/search/findByFirstNameAndLastName
    public List<Actor> findByFirstNameAndLastName(@Param("firstName") String firstName, @Param("lastName")String lastName);

}
