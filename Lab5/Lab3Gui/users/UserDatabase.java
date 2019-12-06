package users;
import java.util.ArrayList;

public final class UserDatabase {
    private static UserDatabase instance;
    private ArrayList<User> database = new ArrayList<>();
    private User defaultUser = new User("None", "None");
    private UserDatabase(){
    }

    public static UserDatabase getInstance() {
        if (instance == null) {
            instance = new UserDatabase();
        }
        return instance;
    }

    public boolean registration(User user){
        for (int i =0; i<database.size();i++){
            if (database.get(i).getLogin().equals(user.getLogin())){
                return false;
            }
        }
        database.add(user);
        return true;
    }

    public User getUserByLogin(String login){
        for (int i = 0; i<database.size();i++){
            if (database.get(i).getLogin().equals(login)){
                return database.get(i);
            }
        }
        return defaultUser;
    }

    public User logging(String login, String password){
        for (int i = 0; i<database.size();i++){
            if (database.get(i).getLogin().equals(login) & database.get(i).getPassword().equals(password)){
                return database.get(i);
            }
        }
        return defaultUser;
    }
    public void deleteUser(String login){
        for (int i = 0; i<database.size();i++){
            if (database.get(i).getLogin().equals(login)){
                database.remove(i);
            }
        }
    }
    public User getDefaultUser(){
        return defaultUser;
    }
}
