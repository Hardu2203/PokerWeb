package User;

import PokerFiles.Hand;

import javax.jws.soap.SOAPBinding;
import javax.persistence.*;

/**
 * Created by harduNel on 2015-01-21.
 */
@Entity
@Table(name="USERGAME")
@IdClass(GameAssociationId.class)
public class UserGame {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer usergameid;
    private Integer gameid;
    private String username;
    private String hand;


    @ManyToOne
    @JoinColumn(name="USERNAME",referencedColumnName="USERNAME", insertable =  false, updatable = false)
    private User u;

    @ManyToOne
    @JoinColumn(name="GAMEID",referencedColumnName="GAMEID", insertable =  false, updatable = false)
    private Game g;

    @Column(name = "WINNER", nullable = false)
    private boolean winner;
   // @Version

   // private User user;
  //  @Version

 //   private Game game;



    public UserGame() {}
    public void setGame(Game g){this.g = g;}
    public void setUser(User u){this.u = u;}

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public Integer getgameid() {
        return gameid;
    }
    public void setgameid(Integer gameidd) {
        this.gameid = gameidd;
    }
    public boolean getwinner() {
        return winner;
    }
    public void setWinner(boolean winner) {
        this.winner = winner;
    }
    public String getHand() {
        return hand;
    }
    public void setHand(String hand) {
        this.hand = hand;
    }







}
