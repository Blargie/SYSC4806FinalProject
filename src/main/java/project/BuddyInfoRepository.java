package project;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BuddyInfoRepository extends CrudRepository<BuddyInfo, Integer>{
    List<BuddyInfo> findByBuddyName(String buddyName);
}
