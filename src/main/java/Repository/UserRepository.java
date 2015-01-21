package Repository;

import com.google.inject.Singleton;
import ninja.jpa.UnitOfWork;
import org.eclipse.jetty.server.Authentication;

/**
 * Created by harduNel on 2015-01-16.
 */
//@Singleton
public class UserRepository {//extends  BaseRep<Authentication<User.User>{

    public String Username;
    public String Password;
    public String Salt;

    public String getUsername()
    {
        return  Username;
    }

    public  String getPassword()
    {
        return  Password;
    }

    public  String getSalt()
    {
        return  Salt;
    }
//@UnitOfWork
}
//}
