package com.eldermoraes.microservice.mono.entities;

import com.eldermoraes.microservice.mono.entities.Player;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-03-19T13:44:30")
@StaticMetamodel(PlayerRankHistory.class)
public class PlayerRankHistory_ { 

    public static volatile SingularAttribute<PlayerRankHistory, Date> date;
    public static volatile SingularAttribute<PlayerRankHistory, Long> rank;
    public static volatile SingularAttribute<PlayerRankHistory, Long> id;
    public static volatile SingularAttribute<PlayerRankHistory, Player> player;
    public static volatile SingularAttribute<PlayerRankHistory, Long> points;

}