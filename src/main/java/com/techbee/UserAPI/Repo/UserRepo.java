package com.techbee.UserAPI.Repo;
import com.techbee.UserAPI.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Long> {

}
