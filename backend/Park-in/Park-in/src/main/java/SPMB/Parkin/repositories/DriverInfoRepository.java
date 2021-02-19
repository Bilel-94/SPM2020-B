package SPMB.Parkin.repositories;

import SPMB.Parkin.models.DriverInfo;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface DriverInfoRepository extends MongoRepository<DriverInfo, String> {

    public Optional<DriverInfo> findByUsername(String username);
}
