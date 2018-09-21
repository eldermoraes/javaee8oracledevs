package com.eldermoraes.microservice.rank.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-03-18T20:30:02")
@StaticMetamodel(PlayerRankHistory.class)
public class PlayerRankHistory_ { 

    public static volatile SingularAttribute<PlayerRankHistory, Date> date;
    public static volatile SingularAttribute<PlayerRankHistory, Long> rank;
    public static volatile SingularAttribute<PlayerRankHistory, Long> id;
    public static volatile SingularAttribute<PlayerRankHistory, Long> player;
    public static volatile SingularAttribute<PlayerRankHistory, Long> points;

}