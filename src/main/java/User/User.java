package User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by harduNel on 2015-01-20.
 */

@Entity
public class User {

//@GeneratedValue(strategy= GenerationType.AUTO)
//Long userid;
   // @Column(name = "username", nullable = false)
    @Id
    private String username;

    private String password;

    @OneToMany(mappedBy = "u")
    private List<UserGame> games = new ArrayList<>();

    public void Gameadd(Game _game)
    {
        UserGame usergame = new UserGame();
        usergame.setUsername(this.getUsername());
        games.add(usergame);
    }
   // @Version
   // @OneToMany(mappedBy="User",targetEntity=UserGame.class,
   //         fetch=FetchType.EAGER)
   // private List<UserGame> games;
  // @ManyToMany(mappedBy="users")
  // private List<Game> games;



    public User() {}

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
