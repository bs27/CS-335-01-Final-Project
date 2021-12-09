package Common;

public class User {
    String username;
    String password;
    String email;

    int lockCount = 0;

    public User(String username, String password,String email){
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public User(String username, String password,String email, int lockCount){
        this.username = username;
        this.password = password;
        this.email = email;
        this.lockCount = lockCount;
        isLocked();
    }

    public boolean isLocked(){
        return lockCount >= 3;
    }

    public void incLockCount(){
        if(!(lockCount < 3))
            lockCount++;
    }

    public int getLockCount(){
        return lockCount;
    }

    public String getUsername(){
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword(){
       return password;
    }

    public String toString(){
        return username+" "+password+" "+email+" "+lockCount;
    }
}
