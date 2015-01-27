package User;

import javax.persistence.*;
import java.security.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by harduNel on 2015-01-21.
 */
@Entity
public class Game {
    @Id
   @GeneratedValue(strategy= GenerationType.AUTO)
 //   @Column(name = "gameid", nullable = false)
    private Integer gameid;
    private String gamename;
    @Temporal(TemporalType.TIMESTAMP)
    private Date gamedate;
    private String Status;
    private String HostName;

   // @ManyToMany
  //  @JoinTable(
   //         name="USERGAME"
   //         , joinColumns={
    //        @JoinColumn(name="game_id")
   // }
   //         , inverseJoinColumns={
   //         @JoinColumn(name="USER_NAME")
  //  }
  //  )
  //  private List<User> users;

    @OneToMany(mappedBy="g",targetEntity=UserGame.class,
           fetch=FetchType.EAGER)
    private List<UserGame> Users = new ArrayList<>();

    public void addUser(User u,boolean win) {
        UserGame association = new UserGame();
        association.setgameid(this.gameid);
        association.setUsername(u.getUsername());
        association.setWinner(win);
        association.setGame(this);
        association.setUser(u);

        this.Users.add(association);
        // Also add the association object to the employee.
     //   employee.getProjects().add(association);
    }


    public Game(){}


    public long getgameid() {return gameid;}
    public void setUsers(List<UserGame> users){this.Users = users;}
    public void setgamename(String gamename) {
        this.gamename = gamename;
    }
    public void setgameID(Integer id) {this.gameid = id;}
    public String getGamename() {
        return gamename;
    }
    public void setTimestamp(Date gamedate) {
        this.gamedate = gamedate;
    }
    public Date getgamedate(){return gamedate;}
    public String getStatus() {return Status;}
    public void SetStatus(String Status){this.Status = Status;}
    public String getHostName() {return HostName;}
    public void SetHostName(String HostName){this.HostName = HostName;}

}
