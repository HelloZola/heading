package example.aspect;

import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService{

	@Override
	public void addUser(){
		System.out.println("add user!");
	}
	
}
