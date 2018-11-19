package com.sda.servlets.users;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UsersService {
    private static UsersService instance;
    private List<User>users;
    private Integer id;

    public static UsersService instaceOf(){
        if(instance==null){
            instance=new UsersService();
        }
    return instance;
    }

    public List<User> findAll(){
        return new ArrayList<>(users);//---->kopia listy
    }

    public UsersService() {
        this.users=new ArrayList<>();
        this.id=1;
        save((new User("Michal","Kaczkowski",29,"Male")));
        save((new User("Jan","Kowalski",39,"Male")));
    }

public void save(User newUser){
        if(newUser.getId()!=null) {
            users.stream().
                    filter(x -> x.getId().equals(newUser.getId()))
                    .findFirst()
                    .ifPresent(x -> {
                        x.setFirstName(newUser.getFirstName());
                        x.setLastName(newUser.getLastName());
                        x.setAge(newUser.getAge());
                        x.setGender(newUser.getGender());
                    });
        }else{
        newUser.setId(id++);
            users.add(newUser);}
}


public User findById(int id) throws UserNotFoundException {

        return users.stream()
                .filter(x->x.getId().equals(id))
                .findFirst()
                .orElseThrow(()->new UserNotFoundException("User with id" + id +" does not exists"));
}

public List<User> findByQuery(String query){
        List<User> usersToReturn=new ArrayList<>();
        for(User user:users){
            String userRepresentation = user.getFirstName() + " " + user.getLastName();
            if(userRepresentation.contains(query)){
                usersToReturn.add(user);
            }
        }
return  usersToReturn;
    }

    public void delete(Integer id) {
        this.users=users.stream()
                .filter(x->!x.getId().equals(id))
                .collect(Collectors.toList());

    }
}
